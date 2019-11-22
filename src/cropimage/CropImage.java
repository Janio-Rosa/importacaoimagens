/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cropimage;

import cropimage.ClipMover;
import importacaoimagens.view.BancoOrigem;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import static javax.imageio.ImageIO.createImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import uteis.TransportaInformacoes;

/**
 *
 * @author katia
 */
public class CropImage extends JApplet {

    /**
     * @param args the command line arguments
     */
    public void lerImagem(String caminho) throws IOException {
        {
            System.out.println("Entrou em CropImage > lerImagem " + caminho);
            if (caminho == null) {
                JOptionPane.showMessageDialog(null, "Esso ao selecionar o diretório! Não foi possivel ler do diretorio: " + caminho + ".");
            }

            File file = new File(caminho);
            File[] arquivos = file.listFiles();

            if (arquivos.length == 0) {
                JOptionPane.showMessageDialog(null, "Diretório Vazio");

            } else {
                System.out.println("Quantidade de Arquivos: "+arquivos.length);
                for (File arquivoAtual : arquivos) {

                    //if (!arquivoAtual.isDirectory() && arquivoAtual.isFile() ) {
                    if (arquivoAtual.isFile()) {

                        if (arquivoAtual == null) {
                            JOptionPane.showMessageDialog(null, "input == null!");
                        }
                        if (!arquivoAtual.canRead()) {
                            JOptionPane.showMessageDialog(null, "Can't read input file!");
                        }
                        TransportaInformacoes ti = new TransportaInformacoes();
                        ImageInputStream stream = createImageInputStream(arquivoAtual);
                        TransportaInformacoes.getTransportaInformacoes().setArquivoTrabalho(arquivoAtual);
                        BufferedImage image = ImageIO.read(ti.getTransportaInformacoes().getArquivoTrabalho());
                        if (stream == null) {
                            JOptionPane.showMessageDialog(null, "Can't create an ImageInputStream!");
                        } else {
                            System.out.println("Cropping ");
                            Cropping test = new Cropping(ImageIO.read(ti.getTransportaInformacoes().getArquivoTrabalho()));
                            Toolkit toolkit = Toolkit.getDefaultToolkit();
                            Dimension scrnsize = toolkit.getScreenSize();
                            System.out.println("Tamanho: " + scrnsize);

                            ClipMover mover = new ClipMover(test);

                            /*
                             mover.setImagemOriginal(test.getImage());
                             mover.setFormTestarClippedImage(test.getTestarClippedImage());
                             mover.setFormTestarImagemCB1(test.getTestarImagemCB1());
                             mover.setFormTestarImagemCB2(test.getTestarImagemCB2());
                             mover.setRetangulo(test.getClip());
                             mover.setRetanguloCB1(test.getClipCB1());
                             mover.setRetanguloCB2(test.getClipCB2());*/
                            test.addMouseListener(mover);
                            test.addMouseMotionListener(mover);

                            JFrame f = new JFrame();

                            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            f.getContentPane().add(new JScrollPane(test));
                            f.getContentPane().add(test.getUIPanel(), "South");

                            //f.setTitle("Selecione a área de corte (ou desidentificação) da imagem.");
                            f.setTitle("Selecione a área de corte (diretório de trabalho: " + caminho + ").");
                            System.out.println("%%%%%%%%%%%%% = " + image.getWidth());
                            //f.setSize((int) (image.getWidth()/2.35) ,(int) (image.getHeight()/2.35));  //tamanho da tela de recorte da imagem
                            f.setSize((int) (image.getWidth() / 2.5), (int) (scrnsize.getHeight()) - 50);  //tamanho da tela de recorte da imagem
                            f.setLocation(0, 0);
                            f.setVisible(true);
                            f.setLocationRelativeTo(null);

                            System.out.print("arquivoAtual = " + arquivoAtual);
                            break;
                        }
                    } else {
                        //Diretório
                    }
                }
            }
        }
    }

    public static boolean isDiretorioVazio(String diretorio) {
        File dir = new File(diretorio);
        String[] arquivos = dir.list();
        if (arquivos == null) {
            JOptionPane.showMessageDialog(null, "Diretório Vazio");
            return true;
        } else {
            if (arquivos.length > 0) {
                return false;
            }
        }
        return false;
    }
}
