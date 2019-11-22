/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrega_imagem;

import cropimage.CropImageErro;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;
import model.TbDadosAcessoBanco;
import service.RespostaService;
import uteis.TransportaInformacoes;

/**
 *
 * @author katia
 */
public class GravarTodasImagens {
    
    static List lista = new ArrayList();

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }   
    
    public void copiarImagens(String caminho, String destino, JProgressBar barra, int x, int y, int w, int h, int w0, int h0) throws IOException {
        System.out.println("entrou em GravarTotasImagens.java -> copiarImagens " + caminho);
        //HERE ************************** CONTINUAÇÃO
        //PENDENTE: MEXER AQUI
        //LEITURA E IMPORTAÇÃO DE TODAS AS IMAGENS

        File diretorio = new File(caminho);
        File[] arquivos = diretorio.listFiles();

        int totalArquivos = arquivos.length;

        barra.setMinimum(0);
        barra.setMaximum(totalArquivos-1);
        barra.setStringPainted(true);

        System.out.println("quantidade de arquivos: " + totalArquivos);
        
        List aux = new ArrayList();

        for (int i = 0; i < totalArquivos; i++) {
            File arquivoAtual = arquivos[i];
            aux.add(arquivos[i]);
            BufferedImage imagem_buffered = ImageIO.read(arquivos[i]);

            BufferedImage bi = new BufferedImage(h0, w0, imagem_buffered.TYPE_INT_RGB);
            Graphics2D grph = (Graphics2D) bi.getGraphics();

            grph.drawImage(imagem_buffered, 0, 0, null);

            bi = bi.getSubimage((int) (x * 2.5), (int) (y * 2.5), (int) (w * 2.5), (int) (h * 2.5));

           // ImageIO.write(bi, "jpg", new File(destino + "/" + arquivos[i].getName()));

            System.out.println("arquivo  " + i + " de " + totalArquivos);

            RespostaService rs = new RespostaService();

            TransportaInformacoes transporta = TransportaInformacoes.getTransportaInformacoes();

            TbDadosAcessoBanco bancoOrigem = transporta.getAcessoAoBancoOrigem();

            TbDadosAcessoBanco bancoDestino = transporta.getAcessoAoBancoDestino();

            InputStream imagemCortada = TratarImagem.converteImagemDada(bi);

            long codigoRetorno=0;
            //GRAVAR A IMAGEM
            codigoRetorno=rs.importaArquivo(arquivoAtual, bancoOrigem, bancoDestino, imagemCortada);
            
            if(codigoRetorno!=0){
                System.out.println(" Deu certo para o arquivo "+arquivoAtual.getName()+" com código:"+codigoRetorno+" .");
            //    aux.add(i, arquivos[i] + " Sem erros. Código arquivo:"+codigoRetorno+" .");
            }else{
                System.out.println("Deu ERRO para o arquivo "+arquivoAtual.getName());
              //  aux.add(i, " ERRO para o arquivo ");
            }
            barra.setValue(i);
            barra.repaint();

        }
        setLista(aux);
    }

    public void copiarImagem(String caminho, String destino, JProgressBar barra, int x, int y, int w, int h, int w0, int h0) throws IOException {
        System.out.println("entrou em GravarTotasImagens.java -> copiarImagens " + caminho);

        File diretorio = new File(caminho);
        File[] arquivos = diretorio.listFiles();

        int totalArquivos = 1;

        barra.setMinimum(0);
        barra.setMaximum(totalArquivos);
        barra.setStringPainted(true);
       File arquivoAtual = arquivos[1];
        System.out.println("diretorio: " + arquivoAtual);
        
        List aux = new ArrayList();
     
            aux.remove(arquivoAtual);
            BufferedImage imagem_buffered = ImageIO.read(arquivoAtual);

            BufferedImage bi = new BufferedImage(h0, w0, imagem_buffered.TYPE_INT_RGB);
            Graphics2D grph = (Graphics2D) bi.getGraphics();

            grph.drawImage(imagem_buffered, 0, 0, null);

            bi = bi.getSubimage((int) (x * 2.5), (int) (y * 2.5), (int) (w * 2.5), (int) (h * 2.5));

           // ImageIO.write(bi, "jpg", new File(destino + "/" + arquivos[i].getName()));

          //  System.out.println("arquivo  " + diretorio + " de " + totalArquivos);

            RespostaService rs = new RespostaService();

            TransportaInformacoes transporta = TransportaInformacoes.getTransportaInformacoes();

            TbDadosAcessoBanco bancoOrigem = transporta.getAcessoAoBancoOrigem();

            TbDadosAcessoBanco bancoDestino = transporta.getAcessoAoBancoDestino();

            InputStream imagemCortada = TratarImagem.converteImagemDada(bi);

            long codigoRetorno=0;
            //GRAVAR A IMAGEM
            codigoRetorno=rs.importaArquivo(arquivoAtual, bancoOrigem, bancoDestino, imagemCortada);
            
            if(codigoRetorno!=0){
                System.out.println(" Deu certo para o arquivo "+arquivoAtual.getName()+" com código:"+codigoRetorno+" .");
            //    aux.add(i, arquivos[i] + " Sem erros. Código diretorio:"+codigoRetorno+" .");
            }else{
                System.out.println("Deu ERRO para o arquivo "+arquivoAtual.getName());

            }

            barra.setValue(1);
            barra.repaint();

        setLista(aux);
    }   
    
    private BufferedImage rotate90DX(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();

        BufferedImage biFlip = new BufferedImage(height, width, bi.getType());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                biFlip.setRGB(height - 1 - j, i, bi.getRGB(i, j));
            }
        }

        return biFlip;
    }

    public void listarArquivos(JList jListListaDeArquivos, JLabel jLabel1) {

        System.out.println("entrou em GravarTodasImagens.java -> listarArquivos: ");

        DefaultListModel model;
        model = new DefaultListModel();
        jListListaDeArquivos.setModel(model);

        System.out.println("imprimindo arrayList : " + lista);
        System.out.println("tamanho arrayList : " + lista.size());

        for (int i = 0; i < lista.size(); i++) {
            // System.out.println("entrou em listar arquivos ->for");
            // System.out.println("arquivo:  " + i + " de " + totalArquivos);              
            model.addElement(lista.get(i));
            jLabel1.setText("" + lista.size() + " arquivos");
        }
    }
    
    public void abrirImagem(int indice) throws IOException {

        String indiceValor = lista.get(indice).toString();
        System.out.println("conteudo do indice: " + indiceValor);
        CropImageErro visualizar = new CropImageErro();
        visualizar.setVisible(true);
    
        visualizar.lerImagem(indiceValor);

        //   frame.setCorte(this.corte);
        //   this.dispose();
    }    

}
