package carrega_imagem;

import cropimage.ScalablePane;
import importacaoimagens.view.RecorteImagem;
import importacaoimagens.view.RecorteImagemErro;
import importacaoimagens.view.VisualizarImagem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uteis.Uteis;

/**
 *
 * @author Thiago
 */
public class DesenharImagem {

    private JLabel lblCabecalho;

    private JLabel lblImagem;
    TratarImagem i = new TratarImagem();
    InputStream imagemCortadaX = null;

    BufferedImage image;
    Dimension size;
    Rectangle clip;
    BufferedImage imagebuffer;

    static int largura;
    static int altura;

    public InputStream getImagemCortadaX() {
        return imagemCortadaX;
    }

    public void setImagemCortadaX(InputStream imagemCortadaX) {
        this.imagemCortadaX = imagemCortadaX;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = (getAltura() - size.width) / 2;
        int y = (getLargura() - size.height) / 2;
        g2.setPaint(Color.RED);
        g2.draw(clip);
    }

    /* public void ExibeImagem_old(TratarImagem ti, RecorteImagem frameAtual, JPanel painel, String caminho) {

     File diretorio = new File(caminho);
     File[] arquivos = diretorio.listFiles();
     System.out.println("entrou em carrega_imagem.DesenharImagem -> ExibeImagem");
     if (arquivos == null || arquivos.length <= 0) {
     System.out.println("Não existem arquivos no diretório selecionado.");
     } else {

     File arquivoAtual = arquivos[0];
     ProgressoController pi = new ProgressoController();
            
     System.out.println("arquivo atual: " + arquivoAtual);
     if (arquivoAtual.isFile()) {

     FileInputStream insereImagem = null;
     try {
     insereImagem = new FileInputStream(arquivoAtual);

     InputStream imagemCortada1 = insereImagem;
     InputStream imagemCortada2 = insereImagem;
     if (ti == null) {
     ti = new TratarImagem(insereImagem);
     }
     imagemCortada1 = ti.getCropRedacao1();
     imagemCortada2 = ti.getCropRedacao2();
                 
                   
     BufferedImage testeBufferedImage1 = ImageIO.read(imagemCortada1);
     BufferedImage testeBufferedImage2 = ImageIO.read(imagemCortada2);

     Image imagemRedimensionada1 = testeBufferedImage1.getScaledInstance(750, 250, Image.SCALE_SMOOTH);
     Image imagemRedimensionada2 = testeBufferedImage2.getScaledInstance(750, 700, Image.SCALE_SMOOTH);

     try {
     painel.remove(lblCabecalho);
     painel.remove(lblImagem);
     } catch (Exception ex) {
     }
     setImagemCortadaX(imagemCortada2);
     painel.setLayout(null);
     lblCabecalho = null;
     lblImagem = null;
     lblCabecalho = new JLabel(new ImageIcon(imagemRedimensionada1));
     lblImagem = new JLabel(new ImageIcon(imagemRedimensionada2));

     lblCabecalho.setBounds(0, 0, 800, 250);
     lblImagem.setBounds(0, 280, 800, 700);

     painel.add(lblCabecalho);
     painel.add(lblImagem);
                    
     setLargura(frameAtual.getWidth());
     setAltura(frameAtual.getHeight());
                    
     System.out.println("####: " + frameAtual.getWidth());
     System.out.println("$$$$:" + frameAtual.getHeight());
                    
     painel.paint(painel.getGraphics());
     painel.repaint();
     } catch (FileNotFoundException fnfe) {
     System.out.println("Ocorreu um erro " + fnfe.getMessage());
     } catch (IOException fnfe) {
     System.out.println("Ocorreu um erro " + fnfe.getMessage());
     }
     }
     }

     frameAtual.setDesenhar(this);
     frameAtual.setFrame(frameAtual);
     frameAtual.setI(ti);
     frameAtual.setPanel(painel);
     frameAtual.setCaminho(caminho);
     }*/
    public void ExibeImagem(TratarImagem ti, RecorteImagem frameAtual, JPanel painel, String caminho) {

        File diretorio = new File(caminho);
        File[] arquivos = diretorio.listFiles();
        System.out.println("entrou em carrega_imagem.DesenharImagem -> ExibeImagem");
        if (arquivos == null || arquivos.length <= 0) {
            System.out.println("Não existem arquivos no diretório selecionado.");
        } else {

            File arquivoAtual = arquivos[0];
            ProgressoController pi = new ProgressoController();

            System.out.println("arquivo atual: " + arquivoAtual);
            if (arquivoAtual.isFile()) {

                FileInputStream insereImagem = null;
                try {
                    insereImagem = new FileInputStream(arquivoAtual);

                    InputStream imagemCortada2 = insereImagem;
                    if (ti == null) {
                        ti = new TratarImagem(insereImagem);
                    }

                    imagemCortada2 = ti.getCropRedacao2();

                    BufferedImage image = ImageIO.read(imagemCortada2);
                    this.image = image;
                    size = new Dimension(image.getWidth(), image.getHeight());

                    Image imagemRedimensionada2 = image.getScaledInstance(750, 950, image.SCALE_SMOOTH);

                    setImagemCortadaX(imagemCortada2);
                    painel.setLayout(null);

                    lblImagem = null;

                    lblImagem = new JLabel(new ImageIcon(imagemRedimensionada2));
                    painel.addMouseListener(null);
                    lblImagem.setBounds(0, 0, 800, 950);

                    painel.add(lblImagem);

                    setLargura(frameAtual.getWidth());
                    setAltura(frameAtual.getHeight());

                    painel.paint(painel.getGraphics());
                    painel.repaint();
                } catch (FileNotFoundException fnfe) {
                    System.out.println("Ocorreu um erro " + fnfe.getMessage());
                } catch (IOException fnfe) {
                    System.out.println("Ocorreu um erro " + fnfe.getMessage());
                }
            }
        }

        frameAtual.setDesenhar(this);
        frameAtual.setFrame(frameAtual);
        frameAtual.setI(ti);
        frameAtual.setPanel(painel);
        frameAtual.setCaminho(caminho);

    }

    public void ExibeImagemErro(RecorteImagemErro frameAtual, JPanel painel, BufferedImage clipped, Rectangle areaCorte) {
        System.out.println("entrou em carrega_imagem.DesenharImagem -> ExibeImagem");

        ProgressoController pi = new ProgressoController();

        FileInputStream insereImagem = null;

        InputStream imagemCortada2 = insereImagem;

        this.image = Uteis.cropSubimageWithRectangle(clipped, areaCorte);
        size = new Dimension(this.image.getWidth(), this.image.getHeight());

        Image imagemRedimensionada2 = this.image.getScaledInstance(750, 950, this.image.SCALE_SMOOTH);

        setImagemCortadaX(imagemCortada2);
        painel.setLayout(null);

        lblImagem = null;

        lblImagem = new JLabel(new ImageIcon(imagemRedimensionada2));

        lblImagem.setBounds(0, 0, 800, 950);

        painel.add(lblImagem);

        setLargura(frameAtual.getWidth());
        setAltura(frameAtual.getHeight());

        painel.paint(painel.getGraphics());
        painel.repaint();

        frameAtual.setDesenhar(this);
        frameAtual.setXx(Double.valueOf(areaCorte.getX()).intValue());
        frameAtual.setYy(Double.valueOf(areaCorte.getY()).intValue());
        frameAtual.setH(Double.valueOf(areaCorte.getHeight()).intValue());
        frameAtual.setW(Double.valueOf(areaCorte.getWidth()).intValue());
        frameAtual.setLargura_0(Double.valueOf(areaCorte.getWidth()).intValue());
        frameAtual.setAltura_0(Double.valueOf(areaCorte.getHeight()).intValue());

        frameAtual.setFrame(frameAtual);

        frameAtual.setPanel(painel);

    }

    private BufferedImage cropImage(BufferedImage src, Rectangle rect) {
        BufferedImage dest = src.getSubimage(0, 0, rect.width, rect.height);
        return dest;
    }

    public void ExibeImagem1(TratarImagem i, VisualizarImagem frame, JPanel panel, String caminho) {
        FileInputStream insereImagem = null;
        try {
            insereImagem = new FileInputStream(caminho);
            InputStream imagemCortada1 = insereImagem;
            InputStream imagemCortada2 = insereImagem;
            if (i == null) {
                i = new TratarImagem(insereImagem);
            }
            imagemCortada1 = i.getCropRedacao1();
            imagemCortada2 = i.getCropRedacao2();

            BufferedImage testeBufferedImage1 = ImageIO.read(imagemCortada1);
            BufferedImage testeBufferedImage2 = ImageIO.read(imagemCortada2);

            Image imagemRedimensionada1 = testeBufferedImage1.getScaledInstance(750, 250, Image.SCALE_SMOOTH);
            Image imagemRedimensionada2 = testeBufferedImage2.getScaledInstance(750, 700, Image.SCALE_SMOOTH);

            try {
                panel.remove(lblCabecalho);
                panel.remove(lblImagem);
            } catch (Exception ex) {
            }

            panel.setLayout(null);
            lblCabecalho = null;
            lblImagem = null;
            lblCabecalho = new JLabel(new ImageIcon(imagemRedimensionada1));
            lblImagem = new JLabel(new ImageIcon(imagemRedimensionada2));

            lblCabecalho.setBounds(0, 0, 800, 250);
            lblImagem.setBounds(0, 280, 800, 700);

            panel.add(lblCabecalho);
            panel.add(lblImagem);

            panel.paint(panel.getGraphics());
            panel.repaint();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Ocorreu um erro " + fnfe.getMessage());
        } catch (IOException fnfe) {
            System.out.println("Ocorreu um erro " + fnfe.getMessage());
        }
        // }

        frame.setDesenhar(this);
        frame.setFrame(frame);
        frame.setI(i);
        frame.setPanel(panel);
        frame.setCaminho(caminho);
    }

    public void ExibeImagem(Object object, RecorteImagem frameAtual, JPanel painel, BufferedImage imagemBuferizada, int x, int y, int w, int h, int w0, int h0) {

        System.out.println("entrou em carrega_imagem.DesenharImagem -> ExibeImagem");

        ProgressoController pi = new ProgressoController();

        FileInputStream insereImagem = null;

        InputStream imagemCortada2 = insereImagem;


        this.image = imagemBuferizada;
        size = new Dimension(image.getWidth(), image.getHeight());

        Image imagemRedimensionada2 = image.getScaledInstance(750, 950, image.SCALE_SMOOTH);

        setImagemCortadaX(imagemCortada2);
        painel.setLayout(null);

        //lblImagem = null;

        //lblImagem = new JLabel(new ImageIcon(imagemRedimensionada2));

        //lblImagem.setBounds(0, 0, 800, 950);

        //painel.add(lblImagem);
        painel.add(new ScalablePane(imagemBuferizada));

        setLargura(frameAtual.getWidth());
        setAltura(frameAtual.getHeight());

        painel.paint(painel.getGraphics());
        painel.repaint();

        frameAtual.setDesenhar(this);
        frameAtual.setXx(x);
        frameAtual.setYy(y);
        frameAtual.setH(h);
        frameAtual.setW(w);
        frameAtual.setLargura_0(w0);
        frameAtual.setAltura_0(h0);

        frameAtual.setFrame(frameAtual);

        frameAtual.setPanel(painel);

    }

    
    public void exibeImagem(RecorteImagem frameAtual, JPanel painel,String caminho , Rectangle areaCorte) throws IOException {
        System.out.println("entrou em carrega_imagem.DesenharImagem -> ExibeImagem");

        ProgressoController pi = new ProgressoController();

        FileInputStream insereImagem = null;
        File diretorio = new File(caminho);
        File[] arquivos = diretorio.listFiles();
        File primeiroArquivo = arquivos[0];

        if(primeiroArquivo.isFile()){
        BufferedImage clipped=ImageIO.read(primeiroArquivo);
        InputStream imagemCortada2 = insereImagem;

        this.image = Uteis.cropSubimageWithRectangle(clipped, areaCorte);
        size = new Dimension(this.image.getWidth(), this.image.getHeight());

        Image imagemRedimensionada2 = this.image.getScaledInstance(750, 950, this.image.SCALE_SMOOTH);

        setImagemCortadaX(imagemCortada2);
        painel.setLayout(null);

        lblImagem = null;

        lblImagem = new JLabel(new ImageIcon(imagemRedimensionada2));

        lblImagem.setBounds(0, 0, 800, 950);

        painel.add(lblImagem);

        setLargura(frameAtual.getWidth());
        setAltura(frameAtual.getHeight());

        painel.paint(painel.getGraphics());
        painel.repaint();

        frameAtual.setDesenhar(this);
        frameAtual.setXx(Double.valueOf(areaCorte.getX()).intValue());
        frameAtual.setYy(Double.valueOf(areaCorte.getY()).intValue());
        frameAtual.setH(Double.valueOf(areaCorte.getHeight()).intValue());
        frameAtual.setW(Double.valueOf(areaCorte.getWidth()).intValue());
        frameAtual.setLargura_0(Double.valueOf(areaCorte.getWidth()).intValue());
        frameAtual.setAltura_0(Double.valueOf(areaCorte.getHeight()).intValue());

        frameAtual.setFrame(frameAtual);

        frameAtual.setPanel(painel);
        }
    }
    
}
