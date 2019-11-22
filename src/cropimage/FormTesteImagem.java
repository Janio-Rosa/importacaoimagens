/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cropimage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author janio
 */
public final class FormTesteImagem {

    public JFrame frameTeste = null;
    //public JFrame frameTeste=new JFrame("Teste de Desenho da Imagem");
    private BufferedImage imagem;
    private ScalablePane imagemParaDesenhar;

    /*
    public TesteImagem(BufferedImage imagemAtual) {
    TesteImagem.imagem = new BufferedImage(imagemAtual.getWidth(),imagemAtual.getHeight(),imagemAtual.getType());
    Graphics newGraphics = TesteImagem.imagem.getGraphics();
    newGraphics.drawImage(imagemAtual, 0, 0, null);
    newGraphics.dispose();
    if(TesteImagem.frameTeste==null){
    TesteImagem.frameTeste=new JFrame("Teste de Desenho da Imagem");
    }
    TesteImagem.frameTeste.removeAll();
    
    TesteImagem.frameTeste.getContentPane().setLayout(new FlowLayout());
    //TesteImagem.frameTeste.setSize(this.getImagem().getWidth(), this.getImagem().getHeight());
    TesteImagem.frameTeste.add(new JLabel(new ImageIcon( TesteImagem.imagem )));
    TesteImagem.frameTeste.pack();
    TesteImagem.frameTeste.setVisible(true);
    
    TesteImagem.frameTeste.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    }*/
    public FormTesteImagem() {
    }

    public FormTesteImagem(BufferedImage imagemAtual) {
        this(imagemAtual, "Teste de Desenho da Imagem");
    }

    public FormTesteImagem(BufferedImage imagemAtual, String textoBarraTitulo) {
        if (imagemAtual != null) {
            this.imagem = new BufferedImage(imagemAtual.getWidth(), imagemAtual.getHeight(), imagemAtual.getType());
            Graphics newGraphics = this.imagem.getGraphics();
            newGraphics.drawImage(imagemAtual, 0, 0, imagemAtual.getWidth(), imagemAtual.getHeight(), null);
            newGraphics.dispose();
            if (this.frameTeste == null) {
                this.frameTeste = new JFrame(textoBarraTitulo);
            }
            //this.frameTeste.setSize( 100,250);
            this.frameTeste.getContentPane().setPreferredSize(new Dimension(500, 500));
            //this.frameTeste.getContentPane().setLayout(new FlowLayout());
            this.frameTeste.getContentPane().setLayout(new BorderLayout());
            //TesteImagem.frameTeste.setSize(this.getImagem().getWidth(), this.getImagem().getHeight());
            //this.frameTeste.getContentPane().add(new JLabel(new ImageIcon( this.imagem )));
            this.frameTeste.getContentPane().add(new ScalablePane(this.imagem));
            this.frameTeste.pack();
            this.frameTeste.setVisible(true);

            this.frameTeste.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    /*
    public static JFrame getFrameTeste() {
    return frameTeste;
    }
    
    public static void setFrameTeste(JFrame frameTeste) {
    TesteImagem.frameTeste = frameTeste;
    }
     */
    public BufferedImage getImagem() {
        return imagem;
    }

    public void setImagem(BufferedImage imagem) {
        //TesteImagem.imagem = imagem;
        this.imagem = imagem;
    }

    public void desenharImagem(BufferedImage imagemAtual, String textoBarraTitulo) {
        if (imagemAtual != null) {
            this.imagem = new BufferedImage(imagemAtual.getWidth(), imagemAtual.getHeight(), imagemAtual.getType());
            Graphics newGraphics = this.imagem.getGraphics();
            newGraphics.drawImage(imagemAtual, 0, 0, imagemAtual.getWidth(), imagemAtual.getHeight(), null);
            newGraphics.dispose();
            if (this.frameTeste == null) {
                this.frameTeste = new JFrame(textoBarraTitulo);
            }
            this.frameTeste.getContentPane().setPreferredSize(new Dimension(500, 500));
            this.frameTeste.getContentPane().setLayout(new BorderLayout());
            if (this.imagemParaDesenhar == null) {
                this.imagemParaDesenhar = new ScalablePane(this.imagem);
                this.frameTeste.getContentPane().add(this.imagemParaDesenhar);
            } else {
                this.frameTeste.getContentPane().removeAll();
                this.imagemParaDesenhar = new ScalablePane(this.imagem);
                this.frameTeste.getContentPane().add(this.imagemParaDesenhar);

                //this.imagemParaDesenhar.redimensionaImagem(this.imagem);

            }

            this.frameTeste.pack();
            this.frameTeste.setVisible(true);

            this.frameTeste.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    public ScalablePane getImagemParaDesenhar() {
        return imagemParaDesenhar;
    }

    public void setImagemParaDesenhar(ScalablePane imagemParaDesenhar) {
        this.imagemParaDesenhar = imagemParaDesenhar;
    }
}
