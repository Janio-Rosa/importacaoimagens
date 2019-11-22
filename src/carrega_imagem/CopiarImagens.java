/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrega_imagem;

import static carrega_imagem.ProgressoController.lista;
import importacaoimagens.view.RecorteImagem;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;

/**
 *
 * @author katia
 */
public class CopiarImagens {

    static List lista = new ArrayList();

    public List getLista() {
        return lista;
    }

    public void setLista(List lista) {
        this.lista = lista;
    }

    public void copiarImagens(String caminho, String destino, JProgressBar barra, int x, int y, int w, int h, int w0, int h0) throws IOException {
        System.out.println("entrou em CopiarImagens -> copiarImagens " + caminho);
        System.out.println("X = " + x + " Y= " + y);

        File diretorio = new File(caminho);
        File[] arquivos = diretorio.listFiles();

        int totalArquivos = arquivos.length;

        barra.setMinimum(0);
        barra.setMaximum(totalArquivos);
        barra.setStringPainted(true);

        DesenharImagem di = new DesenharImagem();
        RecorteImagem ri = new RecorteImagem();
        TratarImagem ti = new TratarImagem();

        List aux = new ArrayList();

        for (int i = 0; i < totalArquivos; i++) {

            aux.add(arquivos[i]);
            BufferedImage imagem_buffered = ImageIO.read(arquivos[i]);

            BufferedImage bi = new BufferedImage(h0, w0, imagem_buffered.TYPE_INT_RGB);
            Graphics2D grph = (Graphics2D) bi.getGraphics();

            grph.drawImage(imagem_buffered, 0, 0, null);
            System.out.println("bi.getWidth = " + bi.getWidth());
            System.out.println("bi.getHeight = " + bi.getHeight());
            bi = bi.getSubimage((int) (x * 2.5), (int) (y * 2.5), (int) (w * 2.5), (int) (h * 2.5));

            grph.dispose();

            ImageIO.write(bi, "jpg", new File(destino + "/" + arquivos[i].getName()));

            System.out.println("arquivo  " + i + " de " + totalArquivos);

            barra.setValue(i);
            for (long espera = 0; espera < 10000000; espera++);
        }
        setLista(aux);

    }

    //public void copiarImagens(String caminho, String destino) throws IOException {
 /*   public void copiarImagens_old(String caminho, String destino, JProgressBar barra) throws IOException {
     System.out.println("entrou em CopiarImagens -> copiarImagens " + caminho);

     File diretorio = new File(caminho);
     File[] arquivos = diretorio.listFiles();

     int totalArquivos = arquivos.length;

     barra.setMinimum(0);        
     barra.setMaximum(totalArquivos);
     barra.setStringPainted(true);
        
     DesenharImagem di = new DesenharImagem();
     RecorteImagem ri = new RecorteImagem();
     TratarImagem ti = new TratarImagem();
     System.out.println(">>>>>>>>>>>" + ri.getPanel());
     System.out.println(">>>>>>>>>>>" + di.getLargura() + "..." + di.getAltura());
     System.out.println("quantidade de arquivos: " + totalArquivos);
     List aux = new ArrayList();
        
     for (int i = 0; i < totalArquivos; i++) {
     //  System.out.println("entrou em percorrer imagens ->for");
           
     aux.add(arquivos[i]);
     BufferedImage imagem_buffered = ImageIO.read(arquivos[i]);
     
     int w = 1644;
     //int w = di.getLargura();
     int h0 = 2344;
     int h = 1920;
     // int h = di.getAltura();
     BufferedImage bi = new BufferedImage( w, h0, imagem_buffered.TYPE_INT_RGB);            
     Graphics2D grph = (Graphics2D) bi.getGraphics();

     //   grph.drawImage(imagem_buffered, 0, 0, w, h0, 0, h0, w, 0, null); 
     grph.drawImage(imagem_buffered, 0, 0, w, h0, w, h0,0 , 0, null); 
            
     bi = rotate90DX(rotate90DX(bi.getSubimage(0,0, w, h)));            
            
     grph.dispose();
     //  rotate90DX(bi);
     ImageIO.write(bi, "jpg", new File(destino + "/" + arquivos[i].getName()));                    

     System.out.println("arquivo  " + i + " de " + totalArquivos);
     //System.out.println(" Percorrendo imagens: " + arquivos[i]);
     barra.setValue(i);
     for (long espera = 0; espera < 10000000; espera++);
     }
     setLista(aux);
     //System.out.println("imprimindo array : " + lista);
     //System.out.println("tamanho array : " + lista.size());
     }    */
    private BufferedImage rotate90DX(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();

        BufferedImage biFlip = new BufferedImage(height, width, bi.getType());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //biFlip.setRGB(height - 1 - j, width - 1 - i, bi.getRGB(i, j));
                biFlip.setRGB(height - 1 - j, i, bi.getRGB(i, j));
            }
        }

        return biFlip;
    }

    public void listarArquivos(JList jListListaDeArquivos, JLabel jLabel1) {

        System.out.println("entrou em ProgressoController -> listarArquivos: ");

        DefaultListModel model;
        model = new DefaultListModel();
        jListListaDeArquivos.setModel(model);

        System.out.println("imprimindo arrayList : " + lista);
        System.out.println("tamanho arrayList : " + lista.size());

        for (int i = 0; i < lista.size(); i++) {
            model.addElement(lista.get(i));
            jLabel1.setText("" + lista.size() + " arquivos");
        }
    }

}
