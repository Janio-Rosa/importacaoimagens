/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import carrega_imagem.TratarImagem;
import dao.impl.CandidatoDAO;
import dao.impl.CursoDAO;
import dao.impl.ImportacaoDAO;
import importacaoimagens.view.RecorteImagem;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import model.TbCandidato;
import model.TbCurso;
import model.TbDadosAcessoBanco;
import model.TbImportacao;
import uteis.CodigoBarrasZXing;
import uteis.TransportaInformacoes;
import uteis.Uteis;

/**
 *
 * @author janio
 */
public class ImportacaoDocentesService extends ImportacaoService implements Runnable {

    @Override
    public void run() {
        try {
            this.percorreImportaTodasImagens(this.getParamCaminho(), this.getParamDestino(), this.getParamAreaImagemCorrecao(), true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Execução concluída.");
    }

    @Override
    public void percorreImportaTodasImagens(String caminho, String destino, Rectangle clip, boolean gravaImagemDesidentificada) throws IOException {
        System.out.println("entrou em percorreImportaTodasImagens -> ImportacaoService ..... lendo e importando todos os arquivos...... " + caminho);
        //Lista de cursos para separar provas para gerar gabaritos
        //Medicina, Mecanica, Aeronautica, Odonto, Direito
        JFrame frame = new JFrame("Importando o diretório: " + this.getParamCaminho());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       // frame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 80));
        frame.setResizable(false);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel lblArquivoAtual = new JLabel();
        JLabel lblProgresso = new JLabel();
        panel.add(lblProgresso, BorderLayout.NORTH);
        panel.add(lblArquivoAtual, BorderLayout.SOUTH);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);

        File diretorio = new File(caminho);
        File[] arquivos = diretorio.listFiles();

        int totalArquivos = arquivos.length;
    

        System.out.println("quantidade de arquivos: " + totalArquivos);
        lblProgresso.setText("Quantidade de arquivos: " + (totalArquivos-1));

        List aux = new ArrayList();
        TbImportacao ultimaImportacao = null;
        int cont = 1;

        for (int i = 0; i < totalArquivos; i++) {
            /**
             * **************************************************************
             */
            //Variaveis locais importantes
            File arquivoAtual = arquivos[i];
            TbCandidato candidatoAtual = null;
            //Mensagem
            lblArquivoAtual.setText(arquivoAtual.getAbsolutePath());
            if (arquivoAtual.isFile()) {
                aux.add(arquivos[i]);
                BufferedImage imagem_buffered = ImageIO.read(arquivos[i]);
                BufferedImage imagemOriginal = imagem_buffered;

                BufferedImage bi = super.cropSubimageWithRectangle(imagem_buffered, this.getParamAreaImagemCorrecao());

                System.out.println("arquivo  " + i + " de " + totalArquivos + ". Gravando arquivo desidentificado em: " + (destino + "/" + arquivos[i].getName()));

                RespostaService rs = new RespostaService();

                TbDadosAcessoBanco bancoOrigem = this.getParamBancoOrigem();

                rs.apenasConsultaDados(arquivoAtual, bancoOrigem, this.getParamBancoDestino());

                if (rs.getFlProcesso() == false) {

                    RecorteImagem recorteImagem = new RecorteImagem();
                    recorteImagem.ConsultaInsereProcesso(rs.getProcesso(), rs.getNomeProcesso(), rs.getNrAnoProcessso(),rs.getId_tipo_processo());
                    
                }

                if (rs.getFlCurso() == false) {
                    RecorteImagem recorteImagem = new RecorteImagem();
                    recorteImagem.ConsultaInsereCurso(rs.getCurso(), rs.getNomeCurso());

                }
                if (rs.getFlDisciplina() == false) {
                    RecorteImagem recorteImagem = new RecorteImagem();
                    recorteImagem.ConsultaInsereDisciplina(rs.getCurso(), rs.getNomeCurso());
                }

                InputStream imagemCortada = TratarImagem.converteImagemDada(bi);

                TbImportacao importacaoAtual = super.decodificaCodigoBarras(arquivoAtual, imagemOriginal, false);
                ultimaImportacao = importacaoAtual;
                //candidatoAtual = this.consultaCandidato(importacaoAtual, bancoOrigem);
                /*
                 REALIZA IMPORTAÇÃO
                
                 */

                long codigoRetorno = 0;
                codigoRetorno = rs.importaArquivoDocentes(arquivoAtual, bancoOrigem, this.getParamBancoDestino(), imagemCortada);

                //ImportacaoService is = new ImportacaoService();
                //TbImportacao inserida = is.insereDisciplina(bancoOrigem, importacaoAtual);
                lblProgresso.setText("Arquivo  " + cont + " de " + (totalArquivos - 1) + ". Importando arquivo " + (arquivos[i].getName()) + ", codigo de barras: D" + importacaoAtual.getIdDisciplina() + "Q" + importacaoAtual.getNrQuestao() + "I" + importacaoAtual.getNrInscricao() + ".jpg");

                try {
                    if (gravaImagemDesidentificada) {
                        Uteis.copiarStream(Uteis.comprimeImagem(bi), new File(destino + Uteis.getOperatingSystemSep() + "D" + importacaoAtual.getIdDisciplina() + "Q" + importacaoAtual.getNrQuestao() + "I" + importacaoAtual.getNrInscricao() + ".jpg"));
                    }
                    //ImageIO.write(bi, "jpg", new File(destino + Uteis.getOperatingSystemSep() + "D" + importacaoAtual.getIdDisciplina() + "Q" + importacaoAtual.getNrQuestao() + "I" + importacaoAtual.getNrInscricao()));
                } catch (Exception ex) {
                    System.out.println("Erro ao gravar imagem desidentificada");
                }

                if (codigoRetorno != 0) {
                    System.out.println(" Deu certo para o arquivo " + arquivoAtual.getName() + " com código:" + codigoRetorno + " .");
                    lblProgresso.setText("" + cont + " de " + (totalArquivos - 1) + ". IMPORTADO com sucesso " + (arquivos[i].getName()) + ", codigo de barras: D" + importacaoAtual.getIdDisciplina() + "Q" + importacaoAtual.getNrQuestao() + "I" + importacaoAtual.getNrInscricao() + ".jpg. Código: " + codigoRetorno);
                } else {
                    System.out.println("Deu ERRO para o arquivo " + arquivoAtual.getName());
                    lblProgresso.setText("" + cont + " de " + (totalArquivos - 1) + ". ERRO ao importar " + (arquivos[i].getName()) + ", codigo de barras: D" + importacaoAtual.getIdDisciplina() + "Q" + importacaoAtual.getNrQuestao() + "I" + importacaoAtual.getNrInscricao() + ".jpg.");
                }

            }
        }
        //Mensagem
        lblArquivoAtual.setText("Importação concluida");
        // System.out.println("Importação CONCLUÍDA para a discplina: " + ultimaImportacao.getIdDisciplina());
        JOptionPane.showMessageDialog(null, "Importação CONCLUÍDA com Sucesso");

    }

}
