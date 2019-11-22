/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import carrega_imagem.TratarImagem;
import dao.impl.CandidatoDAO;
import dao.impl.CursoDAO;
import dao.impl.ImportacaoDAO;
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
import uteis.Uteis;

/**
 *
 * @author janio
 */
public class DesidentificaService implements Runnable {

    private String destinoCurso = "";
    private static final int QUANTIDADE_PROVAS_PARA_GABARITOS = 300;
    private List<Integer> listaCursos = new ArrayList<Integer>();
    
    private String paramCaminho;
    private String paramDestino="";
    private boolean paramGravaImagemDesidentificada;
    private TbDadosAcessoBanco paramBancoOrigem;
    private TbDadosAcessoBanco paramBancoDestino;
    private Rectangle paramAreaImagemCorrecao;
    private Rectangle paramAreaImagemCB1;
    private Rectangle paramAreaImagemCB2;
    

    public TbImportacao insereDisciplina(TbDadosAcessoBanco banco, TbImportacao importacao) {
        ImportacaoDAO id = new ImportacaoDAO();
        return id.InsereDisciplina(banco, importacao);
    }

    public void apenasLerArquivosGravarCB(String caminho, String destino, Rectangle clip,boolean gravaImagemDesidentificada) throws IOException {
        System.out.println("entrou em apenasLerArquivosGravarCB -> ImportacaoService ..... lendo arquivos...... " + caminho);
        //Lista de cursos para separar provas para gerar gabaritos
        //Medicina, Mecanica, Aeronautica, Odonto, Direito
        JFrame frame = new JFrame("Trabalhando com o diretório: "+this.getParamCaminho());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 200));
        JPanel panel = new JPanel();
        JLabel lblProgresso = new JLabel();
        panel.add(lblProgresso);
        frame.getContentPane().add(panel,"South");
        frame.pack();
        frame.setVisible(true);
        
        listaCursos.add(new Integer(357));
        listaCursos.add(new Integer(359));
        listaCursos.add(new Integer(471));
        listaCursos.add(new Integer(448));
        listaCursos.add(new Integer(342));


        File diretorio = new File(caminho);
        File[] arquivos = diretorio.listFiles();

        int totalArquivos = arquivos.length;

        System.out.println("quantidade de arquivos: " + totalArquivos);

        List aux = new ArrayList();
        int contadorGabarito = 0;
        int contadorGabaritoQ1 = 0;
        int contadorGabaritoQ2 = 0;
        int contadorGabaritoQ3 = 0;
        int contadorGabaritoQ4 = 0;
        TbImportacao ultimaImportacao =null;
        
        for (int i = 0; i < totalArquivos; i++) {
            /*****************************************************************/
            //Variaveis locais importantes
            File arquivoAtual = arquivos[i];
            TbCandidato candidatoAtual = null;
            //Mensagem
            lblProgresso.setText(arquivoAtual.getAbsolutePath());
            if (arquivoAtual.isFile()) {
                aux.add(arquivos[i]);
                BufferedImage imagem_buffered = ImageIO.read(arquivos[i]);
                BufferedImage imagemOriginal = imagem_buffered;
                
                BufferedImage bi = cropSubimageWithRectangle(imagem_buffered, this.paramAreaImagemCorrecao);

                System.out.println("arquivo  " + i + " de " + totalArquivos + ". Gravando arquivo desidentificado em: " + (destino + "/" + arquivos[i].getName()));

                RespostaService rs = new RespostaService();

                TbDadosAcessoBanco bancoOrigem = paramBancoOrigem;

                InputStream imagemCortada = TratarImagem.converteImagemDada(bi);

                
                TbImportacao importacaoAtual = decodificaCodigoBarras(arquivoAtual, imagemOriginal, false);
                ultimaImportacao=importacaoAtual;
                candidatoAtual = this.consultaCandidato(importacaoAtual, bancoOrigem);

                DesidentificaService is = new DesidentificaService();
                TbImportacao inserida = is.insereDisciplina(bancoOrigem, importacaoAtual);

                try {
                    if(gravaImagemDesidentificada){
                        Uteis.copiarStream(Uteis.comprimeImagem(bi), new File(destino + Uteis.getOperatingSystemSep() + "D" + importacaoAtual.getIdDisciplina() + "Q" + importacaoAtual.getNrQuestao() + "I" + importacaoAtual.getNrInscricao() + ".jpg"));
                    }
                    //ImageIO.write(bi, "jpg", new File(destino + Uteis.getOperatingSystemSep() + "D" + importacaoAtual.getIdDisciplina() + "Q" + importacaoAtual.getNrQuestao() + "I" + importacaoAtual.getNrInscricao()));
                } catch (Exception ex) {
                    System.out.println("Erro ao gravar imagem desidentificada");
                }


                if (candidatoAtual != null) {
                    if (listaCursos != null && listaCursos.contains(candidatoAtual.getIdCurso())) {
                        String nomeCurso = this.consultaNomeCurso(candidatoAtual.getIdCurso(), bancoOrigem);
                        destinoCurso = Uteis.retiraCaracteres(nomeCurso);
                        if (importacaoAtual.getNrQuestao() == 1) {
                            contadorGabaritoQ1++;
                            contadorGabarito = contadorGabaritoQ1;
                        } else if (importacaoAtual.getNrQuestao() == 2) {
                            contadorGabaritoQ2++;
                            contadorGabarito = contadorGabaritoQ2;
                        } else if (importacaoAtual.getNrQuestao() == 3) {
                            contadorGabaritoQ3++;
                            contadorGabarito = contadorGabaritoQ3;
                        } else if (importacaoAtual.getNrQuestao() == 4) {
                            contadorGabaritoQ4++;
                            contadorGabarito = contadorGabaritoQ4;

                        }
                        if (contadorGabarito <= QUANTIDADE_PROVAS_PARA_GABARITOS) {
                            try {
                                destinoCurso = Uteis.criaDiretorioDestino(arquivoAtual.getParent() + Uteis.getOperatingSystemSep() + destinoCurso+Uteis.getOperatingSystemSep()+"Q"+importacaoAtual.getNrQuestao());
                                //ImageIO.write(bi, "jpg", new File(destinoCurso + Uteis.getOperatingSystemSep() + "D" + importacaoAtual.getIdDisciplina() + "Q" + importacaoAtual.getNrQuestao() + "I" + importacaoAtual.getNrInscricao()));
                                Uteis.copiarStream(Uteis.comprimeImagem(bi), new File(destinoCurso + Uteis.getOperatingSystemSep() + "D" + importacaoAtual.getIdDisciplina() + "Q" + importacaoAtual.getNrQuestao() + "I" + importacaoAtual.getNrInscricao() + ".jpg"));
                            } catch (Exception ex) {
                                System.out.println("Erro ao gravar imagem para o curso " + nomeCurso);
                            }
                        }else{
                            System.out.println("Separação de imagens CONCLUÍDA para a questão: "+importacaoAtual.getNrQuestao()+" discplina: " + ultimaImportacao.getIdDisciplina());
                        }

                    }
                }
                //Verifica se já chegou no limite. Se não irá gravar uma cópia de TODAS as imagens desidentificadas, então basta gerar o limite de imagens para cada questão (para confecção dos gabaritos de correção)
                if(!gravaImagemDesidentificada &&  contadorGabaritoQ4 > QUANTIDADE_PROVAS_PARA_GABARITOS && contadorGabaritoQ1 > QUANTIDADE_PROVAS_PARA_GABARITOS && contadorGabaritoQ2 > QUANTIDADE_PROVAS_PARA_GABARITOS && contadorGabaritoQ3 > QUANTIDADE_PROVAS_PARA_GABARITOS){
                    System.out.println("Desidentificação CONCLUÍDA para a discplina: " + ultimaImportacao.getIdDisciplina());
                    break;
                }
                /*
                if (inserida.getIdImportacao() != 0) {
                    System.out.println(" Deu certo para o arquivo " + arquivoAtual.getName() + " com código:" + inserida.getIdImportacao() + " .");
                } else {
                    System.out.println("Deu ERRO para o arquivo " + arquivoAtual.getName());
                }*/
            }
        }
        //Mensagem
        lblProgresso.setText("Desidentificaçao concluida");
        System.out.println("Desidentificação CONCLUÍDA para a discplina: " + ultimaImportacao.getIdDisciplina());
        JOptionPane.showMessageDialog(null, "Desidentificação CONCLUÍDA para a discplina: " + ultimaImportacao.getIdDisciplina());

    }

    private TbImportacao decodificaCodigoBarras(File arquivoAtual, BufferedImage imagemOriginal, boolean flTentarNomeArquivo) {
        //TENTA LER CÓDIGO DE BARRAS 1 (priemiro - parte superior do papel)
        TbImportacao retorno = new TbImportacao();
        BufferedImage primeiraTentativa = TratarImagem.cropSubimageWithRectangle(imagemOriginal, this.paramAreaImagemCB1);
        String codigoBarras = CodigoBarrasZXing.lerCodigoBarras(primeiraTentativa);
        if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
            //TENTA LER CÓDIGO DE BARRAS 2 (o segundo, na parte inferior do papel)
            BufferedImage segundaTentativa = TratarImagem.cropSubimageWithRectangle(imagemOriginal, this.paramAreaImagemCB2);
            codigoBarras = CodigoBarrasZXing.lerCodigoBarras(segundaTentativa);
        }

        if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
            //Se ainda assim houver problema para ler o código de barras, agora tenta-se ler da imagem INTEIRA.
            codigoBarras = CodigoBarrasZXing.lerCodigoBarras(imagemOriginal);
        }

        if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
            //Se o código continuar inválido, tenta-se ler pelo nome do arquivo
            if (arquivoAtual != null && flTentarNomeArquivo) {
                retorno = this.decodificaPorNomeArquivo(arquivoAtual);
            }
        } else if (codigoBarras != null && !codigoBarras.trim().equalsIgnoreCase("")) {
            String codigosDetectados[] = {""};
            DecodificaInscricao di = new DecodificaInscricao();
            codigosDetectados = di.carregaInscricaoPeloCodigoBarras(codigoBarras);

            try {
                retorno.setNrInscricao(Integer.valueOf(codigosDetectados[0]));
                retorno.setNrQuestao(Integer.valueOf(codigosDetectados[1]));
                retorno.setIdDisciplina(Integer.valueOf(codigosDetectados[2]));
            } catch (Exception ex) {
                System.out.println("Erro ao ler código de barras");
                retorno.setNrInscricao(0);
                retorno.setNrQuestao(0);
                retorno.setIdDisciplina(0);
            }
        }
        return retorno;
    }

    private TbImportacao decodificaPorNomeArquivo(File arquivoAtual) {

        String[] decodificaByFileName;
        TbImportacao retorno = new TbImportacao();
        String codigosByFileName[] = {arquivoAtual.getName().substring(0, arquivoAtual.getName().indexOf("."))};

        DecodificaInscricao di = new DecodificaInscricao();
        decodificaByFileName = di.carregaInscricaoPeloCodigoBarras(arquivoAtual);

        try {
            retorno.setNrInscricao(Integer.valueOf(decodificaByFileName[0]));
            retorno.setNrQuestao(Integer.valueOf(decodificaByFileName[1]));
            retorno.setIdDisciplina(Integer.valueOf(decodificaByFileName[2]));
        } catch (Exception ex) {
            System.out.println("Erro ao ler código de barras");
            retorno.setNrInscricao(0);
            retorno.setNrQuestao(0);
            retorno.setIdDisciplina(0);

        }

        return retorno;
    }

    private BufferedImage cropSubimageWithRectangle(BufferedImage original, Rectangle areaDeCorte) {
        /*
        BufferedImage retorno = new BufferedImage((int)areaDeCorte.getWidth(), (int)areaDeCorte.getHeight(), original.getType());
        Graphics g = retorno.getGraphics();
        g.drawImage(original, 0, 0, (int)areaDeCorte.getWidth(), (int)areaDeCorte.getHeight(), (int)areaDeCorte.getX(), (int)areaDeCorte.getY(), (int)areaDeCorte.getX() + (int)areaDeCorte.getWidth(), (int)areaDeCorte.getY() + (int)areaDeCorte.getHeight(), null);
        g.dispose();
         */
        BufferedImage retorno = null;
        int w = areaDeCorte.width;
        int h = areaDeCorte.height;
        int xx = areaDeCorte.x;
        int yy = areaDeCorte.y;
        try {
            retorno = original.getSubimage((int) (xx * 2.5), (int) (yy * 2.5), (int) (w * 2.5), (int) (h * 2.5));
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return retorno;
    }

    private String consultaNomeCurso(int idCurso, TbDadosAcessoBanco banco) {
        String retorno = "";
        CursoDAO cursoDao = new CursoDAO();
        TbCurso filtroCurso = new TbCurso();
        filtroCurso.setIdCurso(idCurso);
        TbCurso consultaCurso = cursoDao.pesquisar(filtroCurso, banco);
        if (consultaCurso != null) {
            retorno = consultaCurso.getNmCurso();
        }
        return retorno;
    }

    private TbCandidato consultaCandidato(TbImportacao consultar, TbDadosAcessoBanco banco) {
        TbCandidato retorno = null;
        TbCandidato filtro = new TbCandidato();
        filtro.setNrInscricao(String.valueOf(consultar.getNrInscricao()));
        CandidatoDAO cd = new CandidatoDAO();
        retorno = cd.pesquisar(filtro, banco);
        return retorno;
    }

    @Override
    public void run() {
        try {
            this.apenasLerArquivosGravarCB(this.getParamCaminho(),this.getParamDestino(),this.getParamAreaImagemCorrecao(),true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Execução concluída.");
    }

    public Rectangle getParamAreaImagemCB1() {
        return paramAreaImagemCB1;
    }

    public void setParamAreaImagemCB1(Rectangle paramAreaImagemCB1) {
        this.paramAreaImagemCB1 = paramAreaImagemCB1;
    }

    public Rectangle getParamAreaImagemCB2() {
        return paramAreaImagemCB2;
    }

    public void setParamAreaImagemCB2(Rectangle paramAreaImagemCB2) {
        this.paramAreaImagemCB2 = paramAreaImagemCB2;
    }

    public Rectangle getParamAreaImagemCorrecao() {
        return paramAreaImagemCorrecao;
    }

    public void setParamAreaImagemCorrecao(Rectangle paramAreaImagemCorrecao) {
        this.paramAreaImagemCorrecao = paramAreaImagemCorrecao;
    }

    public TbDadosAcessoBanco getParamBancoOrigem() {
        return paramBancoOrigem;
    }

    public void setParamBancoOrigem(TbDadosAcessoBanco paramBancoOrigem) {
        this.paramBancoOrigem = paramBancoOrigem;
    }

    public String getParamCaminho() {
        return paramCaminho;
    }

    public void setParamCaminho(String paramCaminho) {
        this.paramCaminho = paramCaminho;
    }

    public String getParamDestino() {
        return paramDestino;
    }

    public void setParamDestino(String paramDestino) {
        this.paramDestino = paramDestino;
    }

    public boolean isParamGravaImagemDesidentificada() {
        return paramGravaImagemDesidentificada;
    }

    public void setParamGravaImagemDesidentificada(boolean paramGravaImagemDesidentificada) {
        this.paramGravaImagemDesidentificada = paramGravaImagemDesidentificada;
    }
    

    
    
    
    public void percorreImportaTodasImagens(String caminho, String destino, Rectangle clip,boolean gravaImagemDesidentificada) throws IOException {
        System.out.println("entrou em percorreImportaTodasImagens -> ImportacaoService ..... lendo e importando todos os arquivos...... " + caminho);
        //Lista de cursos para separar provas para gerar gabaritos
        //Medicina, Mecanica, Aeronautica, Odonto, Direito
        JFrame frame = new JFrame("Importando o diretório: "+this.getParamCaminho());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(200, 400));
        JPanel panel = new JPanel();
        JLabel lblArquivoAtual = new JLabel();
        JLabel lblProgresso = new JLabel();
        panel.add(lblProgresso);
        panel.add(lblArquivoAtual);
        frame.getContentPane().add(panel,"South");
        frame.pack();
        frame.setVisible(true);
        
        listaCursos.add(new Integer(357));
        listaCursos.add(new Integer(359));
        listaCursos.add(new Integer(471));
        listaCursos.add(new Integer(448));
        listaCursos.add(new Integer(342));


        File diretorio = new File(caminho);
        File[] arquivos = diretorio.listFiles();

        int totalArquivos = arquivos.length;

        System.out.println("quantidade de arquivos: " + totalArquivos);
        lblProgresso.setText("Quantidade de arquivos: " + totalArquivos);

        List aux = new ArrayList();
        int contadorGabarito = 0;
        int contadorGabaritoQ1 = 0;
        int contadorGabaritoQ2 = 0;
        int contadorGabaritoQ3 = 0;
        int contadorGabaritoQ4 = 0;
        TbImportacao ultimaImportacao =null;
        
        for (int i = 0; i < totalArquivos; i++) {
            /*****************************************************************/
            //Variaveis locais importantes
            File arquivoAtual = arquivos[i];
            TbCandidato candidatoAtual = null;
            //Mensagem
            lblArquivoAtual.setText(arquivoAtual.getAbsolutePath());
            if (arquivoAtual.isFile()) {
                aux.add(arquivos[i]);
                BufferedImage imagem_buffered = ImageIO.read(arquivos[i]);
                BufferedImage imagemOriginal = imagem_buffered;
                
                BufferedImage bi = cropSubimageWithRectangle(imagem_buffered, this.paramAreaImagemCorrecao);

                System.out.println("arquivo  " + i + " de " + totalArquivos + ". Gravando arquivo desidentificado em: " + (destino + "/" + arquivos[i].getName()));
                

                RespostaService rs = new RespostaService();

                TbDadosAcessoBanco bancoOrigem = paramBancoOrigem;

                InputStream imagemCortada = TratarImagem.converteImagemDada(bi);

                
                TbImportacao importacaoAtual = decodificaCodigoBarras(arquivoAtual, imagemOriginal, false);
                ultimaImportacao=importacaoAtual;
                candidatoAtual = this.consultaCandidato(importacaoAtual, bancoOrigem);

                //ImportacaoService is = new ImportacaoService();
                //TbImportacao inserida = is.insereDisciplina(bancoOrigem, importacaoAtual);

                lblProgresso.setText("Arquivo  " + i + " de " + totalArquivos + ". Importando arquivo " + (arquivos[i].getName()) + ", codigo de barras: D" + importacaoAtual.getIdDisciplina() + "Q" + importacaoAtual.getNrQuestao() + "I" + importacaoAtual.getNrInscricao() + ".jpg");
                
                try {
                    if(gravaImagemDesidentificada){
                        Uteis.copiarStream(Uteis.comprimeImagem(bi), new File(destino + Uteis.getOperatingSystemSep() + "D" + importacaoAtual.getIdDisciplina() + "Q" + importacaoAtual.getNrQuestao() + "I" + importacaoAtual.getNrInscricao() + ".jpg"));
                    }
                    //ImageIO.write(bi, "jpg", new File(destino + Uteis.getOperatingSystemSep() + "D" + importacaoAtual.getIdDisciplina() + "Q" + importacaoAtual.getNrQuestao() + "I" + importacaoAtual.getNrInscricao()));
                } catch (Exception ex) {
                    System.out.println("Erro ao gravar imagem desidentificada");
                }


                /*
                 REALIZA IMPORTAÇÃO
                 */
                
                /*
                if (inserida.getIdImportacao() != 0) {
                    System.out.println(" Deu certo para o arquivo " + arquivoAtual.getName() + " com código:" + inserida.getIdImportacao() + " .");
                } else {
                    System.out.println("Deu ERRO para o arquivo " + arquivoAtual.getName());
                }*/
            }
        }
        //Mensagem
        lblArquivoAtual.setText("Importação concluida");
        System.out.println("Importação CONCLUÍDA para a discplina: " + ultimaImportacao.getIdDisciplina());
        JOptionPane.showMessageDialog(null, "Importação CONCLUÍDA para a discplina: " + ultimaImportacao.getIdDisciplina());

    }

    public TbDadosAcessoBanco getParamBancoDestino() {
        return paramBancoDestino;
    }

    public void setParamBancoDestino(TbDadosAcessoBanco paramBancoDestino) {
        this.paramBancoDestino = paramBancoDestino;
    }

    
}
