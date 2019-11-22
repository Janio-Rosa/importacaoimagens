package cropimage;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.Code128Reader;
import importacaoimagens.view.BancoOrigem;
import importacaoimagens.view.RecorteImagem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import service.DesidentificaService;
import service.ImportacaoService;
import uteis.TransportaInformacoes;
import uteis.Uteis;

/**
 *
 * @author katia
 */
public class Cropping extends javax.swing.JPanel {
//public class Cropping extends javax.swing.JPanel implements MouseMotionListener {

    private JPanel painelAtual = null;
    BufferedImage image;
    Dimension size;
    //Rectangle clip = new Rectangle(40, 250, 950, 1080);
    Rectangle clip = new Rectangle(30, 275, 950, 1080);
    //Rectangle clipCB1 = new Rectangle(490, 190, 420, 190);
    Rectangle clipCB1 = new Rectangle(475, 190, 420, 190);
    // Rectangle clipCB2 = new Rectangle(290, 1250, 420, 140);
    Rectangle clipCB2 = new Rectangle(280, 1250, 420, 140);
    boolean showClip = true;
    boolean checkBoxCB1 = false;
    boolean checkBoxCB2 = false;
    int a = 550;
    int b = 700;
    int largura_original;
    int altura_original;
    File arquivoAtual;
    BufferedImage clippedImage;
    javax.swing.JLabel lblCodigoBarras = new javax.swing.JLabel();
    private int coordenadaRetanguloX = 20;
    private int coordenadaRetanguloY = 160;
    BufferedImage subImageCB1;
    BufferedImage subImageCB2;
    FormTesteImagem formTestarImagemCB1 = new FormTesteImagem();
    FormTesteImagem formTestarImagemCB2 = new FormTesteImagem();
    FormTesteImagem formTestarClippedImage = new FormTesteImagem();
    //TestaImagem frameTestarImagem = new TestaImagem();

    public File getArquivoAtual() {
        return arquivoAtual;
    }

    public void setArquivoAtual(File arquivoAtual) {
        this.arquivoAtual = arquivoAtual;
    }

    public Cropping(BufferedImage image) {

        if (image == null) {
            JOptionPane.showMessageDialog(null, "Arquivo não é do tipo imagem! ");
        } else {
            this.image = image;
            size = new Dimension(image.getWidth(), image.getHeight());
            showClip = true;
        }
    }

    public int getLargura_original() {
        return largura_original;
    }

    public void setLargura_original(int largura_original) {
        this.largura_original = largura_original;
    }

    public int getAltura_original() {
        return altura_original;
    }

    public void setAltura_original(int altura_original) {
        this.altura_original = altura_original;
    }

    protected void paintComponent(Graphics g) {   // carrega a imagem na tela de recorte, a resolução da imagem na tela, será 
        super.paintComponent(g);                  // a resolução da imagem original dividido por 2.5  
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        setLargura_original(image.getWidth());
        setAltura_original(image.getHeight());

        g2.drawImage(image, 0, 0, (int) (largura_original / 2.5), (int) (altura_original / 2.5), null); // divide a altura e largura original por 2.5 para exibir na tela de recorte

        if (showClip) {
            if (clip == null) {
                createClip2(a, b);
            }
            g2.setPaint(Color.RED);
            g2.draw(clip);
        }

        this.desenhaCB1(g2);
        this.desenhaCB2(g2);

        //this.subImageCB1 = image.getSubimage(this.clipCB1.x, this.clipCB1.y, this.clipCB1.x+this.clipCB1.width, this.clipCB1.y+this.clipCB1.height);
        //this.subImageCB2 = image.getSubimage(this.clipCB2.x, this.clipCB2.y, this.clipCB2.width, this.clipCB2.height);
        //TesteImagem testarImagem = new TesteImagem(this.subImageCB1);
    }

    public void setClip(int x, int y) {
        // keep clip within raster
        int x0 = (getWidth() - size.width) / 2;
        int y0 = (getHeight() - size.height) / 2;
        if (x < x0 || x + clip.width > x0 + size.width
                || y < y0 || y + clip.height > y0 + size.height) {
            return;
        }

        if (this.showClip) {
            clip.setLocation(x, y);
        }
        if (this.checkBoxCB1) {
            clipCB1.setLocation(x, y);
        }
        if (this.checkBoxCB2) {
            clipCB2.setLocation(x, y);
        }
        repaint();
    }

    public void setClip(Rectangle retanguloAtual, int x, int y) {
        // keep clip within raster
        int x0 = (getWidth() - size.width) / 2;
        int y0 = (getHeight() - size.height) / 2;
        if (x < x0 || x + retanguloAtual.width > x0 + size.width
                || y < y0 || y + retanguloAtual.height > y0 + size.height) {
            return;
        }

        if (this.showClip) {
            clip.setLocation(x, y);
        }
        if (this.checkBoxCB1) {
            clipCB1.setLocation(x, y);
        }
        if (this.checkBoxCB2) {
            clipCB2.setLocation(x, y);
        }
        repaint();
    }

    public Dimension getPreferredSize() {
        return size;
    }

    public double getRetangleX() {
        return this.getRetanguloAtual().getWidth();
        //return clip.getWidth();
    }

    public double getRetangleY() {
        return this.getRetanguloAtual().getHeight();
        //return clip.getHeight();
    }

    private void createClip2(int a, int b) {

        Rectangle novoClip = new Rectangle(a, b);

        if (this.showClip) {
            //novoClip.x = this.clip.x;
            //novoClip.y = this.clip.y;
            this.clip.setSize(a, b);
            //this.clip = novoClip;
            formTestarClippedImage.desenharImagem(this.cropSubimageWithRectangle(this.image, this.clip), "Imagem para CORREÇÃO");
        }

        if (this.checkBoxCB1) {
            /*
             novoClip.x = this.clipCB1.x;
             novoClip.y = this.clipCB1.y;
             this.clipCB1 = novoClip;*/
            this.clipCB1.setSize(a, b);
            formTestarImagemCB1.desenharImagem(this.cropSubimageWithRectangle(this.image, this.clipCB1), "Imagem CB1");
        }

        if (this.checkBoxCB2) {
            /*
             novoClip.x = this.clipCB2.x;
             novoClip.y = this.clipCB2.y;
             this.clipCB2 = novoClip;*/
            this.clipCB2.setSize(a, b);
            formTestarImagemCB2.desenharImagem(this.cropSubimageWithRectangle(this.image, this.clipCB2), "Imagem CB2");
        }

        //clip.x = 20;
        //clip.y = 160;
    }

    private void createClipCB1(int a, int b) {

        Rectangle novoClip = new Rectangle(a, b);
        novoClip.x = this.clipCB1.x;
        novoClip.y = this.clipCB1.y;
        this.clipCB1 = novoClip;
    }

    private void createClipCB2(int a, int b) {

        Rectangle novoClip = new Rectangle(a, b);
        novoClip.x = this.clipCB2.x;
        novoClip.y = this.clipCB2.y;
        this.clipCB2 = novoClip;

    }

    private void clipImage() { // recorta a imagem
        //PROXIMO FORMULARIO
        /*
         BufferedImage clipped = null;
         int w = clip.width;
         int h = clip.height;
         int x0 = (getWidth() - size.width) / 2;
         int y0 = (getHeight() - size.height) / 2;
         int xx = clip.x - x0;
         int yy = clip.y - y0;
         clipped = image.getSubimage((int) (xx * 2.5), (int) (yy * 2.5), (int) (w * 2.5), (int) (h * 2.5));*/

        TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();
        ti.setAreaImagemCorrecao(this.clip);
        ti.setAreaImagemCB1(this.clipCB1);
        ti.setAreaImagemCB2(this.clipCB2);
        RecorteImagem frame = new RecorteImagem();
        //frame.desenhaAoCarregar(clipped, xx, yy, w, h, largura_original, altura_original);
        frame.getParametrosImportacao().setAreaImagemCB1(this.clipCB1);
        frame.getParametrosImportacao().setAreaImagemCB2(this.clipCB2);
        frame.getParametrosImportacao().setAreaImagemCorrecao(this.clip);
        frame.getParametrosImportacao().setAcessoAoBancoDestino(ti.getAcessoAoBancoDestino());
        frame.getParametrosImportacao().setAcessoAoBancoOrigem(ti.getAcessoAoBancoOrigem());
        frame.getParametrosImportacao().setCaminhoOrigem(ti.getCaminhoOrigem());

        frame.desenhaAoCarregar(this.getImage());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.exibeInfAoCarregar();
        frame.setVisible(true);
        //this.clippedImage = clipped;
        JFrame frameAnterior = (JFrame) SwingUtilities.getWindowAncestor(this.painelAtual);
        frameAnterior.dispose();

    }

    private void conferirImagens() { // Ler o código de barras e gravar no banco os códigos lidos
        //PROXIMO FORMULARIO
        BufferedImage clipped = null;
        int w = clip.width;
        int h = clip.height;
        int x0 = (getWidth() - size.width) / 2;
        int y0 = (getHeight() - size.height) / 2;
        int xx = clip.x - x0;
        int yy = clip.y - y0;
        clipped = image.getSubimage((int) (xx * 2.5), (int) (yy * 2.5), (int) (w * 2.5), (int) (h * 2.5));

        TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();
        ti.setAreaImagemCorrecao(this.clip);
        ti.setAreaImagemCB1(this.clipCB1);
        ti.setAreaImagemCB2(this.clipCB2);

        ////////// PROGRAMAR AQUI
        DesidentificaService importando = new DesidentificaService();
        try {
            //importando.apenasLerArquivosGravarCB(ti.getCaminhoOrigem(),this.criaDiretorioDestino(ti.getCaminhoOrigem() ), this.clip,TransportaInformacoes.getTransportaInformacoes(),true);

            //importando.apenasLerArquivosGravarCB(ti.getCaminhoOrigem(),this.criaDiretorioDestino(ti.getCaminhoOrigem() ), this.clip,TransportaInformacoes.getTransportaInformacoes(),false);
            importando.setParamCaminho(ti.getCaminhoOrigem());
            importando.setParamDestino(this.criaDiretorioDestino(ti.getCaminhoOrigem()));
            importando.setParamAreaImagemCorrecao(this.clip);
            importando.setParamGravaImagemDesidentificada(true);
            importando.setParamAreaImagemCB1(TransportaInformacoes.getTransportaInformacoes().getAreaImagemCB1());
            importando.setParamAreaImagemCB2(TransportaInformacoes.getTransportaInformacoes().getAreaImagemCB2());
            importando.setParamBancoOrigem(TransportaInformacoes.getTransportaInformacoes().getAcessoAoBancoOrigem());

            Thread runningThread = new Thread(importando);
            runningThread.start();

            JFrame frameAnterior = (JFrame) SwingUtilities.getWindowAncestor(this.painelAtual);
            frameAnterior.dispose();

            BancoOrigem bo = new BancoOrigem();
            bo.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void justClipImage() { // recorta a imagem
        BufferedImage clipped = null;
        int w = clip.width;
        int h = clip.height;
        int x0 = (getWidth() - size.width) / 2;
        int y0 = (getHeight() - size.height) / 2;
        int xx = clip.x - x0;
        int yy = clip.y - y0;
        clipped = image.getSubimage((int) (xx * 2.5), (int) (yy * 2.5), (int) (w * 2.5), (int) (h * 2.5));
        this.clippedImage = clipped;

//        this.dispose();
    }

    JPanel getUIPanel() {

        //   final JFrame janela = new JFrame("Teste");
        final JButton voltar = new JButton("Voltar");
        voltar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                BancoOrigem bo = new BancoOrigem();
                bo.setVisible(true);
            }
        });

        final JCheckBox cb1CheckBox = new JCheckBox("CB1", showClip);
        final JCheckBox cb2CheckBox = new JCheckBox("CB2", showClip);
        final JCheckBox clipBox = new JCheckBox("área de recorte", showClip);

        cb1CheckBox.setToolTipText("Quero selecionar a área do CÓDIGO DE BARRAS 1");
        cb1CheckBox.setSelected(this.checkBoxCB1);
        cb1CheckBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                checkBoxCB1 = cb1CheckBox.isSelected();
                showClip = false;
                clipBox.setSelected(false);

                checkBoxCB2 = false;
                cb2CheckBox.setSelected(false);
                repaint();
            }
        });

        cb2CheckBox.setToolTipText("Agora quero exibir o retângulo do segundo CÓDIGO DE BARRAS");
        cb2CheckBox.setSelected(this.checkBoxCB2);
        cb2CheckBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                checkBoxCB2 = cb2CheckBox.isSelected();
                showClip = false;
                clipBox.setSelected(false);

                checkBoxCB1 = false;
                cb1CheckBox.setSelected(false);
                repaint();
            }
        });

        clipBox.setToolTipText("Selecionar a área de CORREÇÃO da imagem");
        clipBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showClip = clipBox.isSelected();
                checkBoxCB2 = false;
                cb2CheckBox.setSelected(false);

                checkBoxCB1 = false;
                cb1CheckBox.setSelected(false);
                repaint();
            }
        });

        final JButton clip = new JButton("Recortar");
        clip.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //    janela.dispose();
                clipImage();

            }
        });

        final JButton conferirImagens = new JButton("Desidentificar");
        conferirImagens.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //    janela.dispose();
                conferirImagens();

            }
        });

        final JButton lerCB = new JButton("Ler CB");
        lerCB.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //    janela.dispose();
                lerCodigoBarras();

            }
        });

        JButton maisHorizontal = new JButton("H++"); // aumenta o tamanho da área de recorte na horizontal
        maisHorizontal.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int d = (int) (getRetangleX() + 200);
                int dMaximo = (int) (largura_original / 2.5);
                System.out.println("dMaximo = " + dMaximo + " h = " + d);
                if (d + 200 > dMaximo) {
                    try {
                        createClip2(dMaximo - 10, (int) getRetangleY());
                        repaint();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    JOptionPane.showMessageDialog(null, "Largura máxima atingida");
                } else {
                    createClip2(d, (int) getRetangleY());
                    repaint();
                }
                System.out.println("h+: " + d);

            }
        });

        JButton sobeHorizontal = new JButton("H+"); // aumenta o tamanho da área de recorte na horizontal
        sobeHorizontal.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int d = (int) (getRetangleX() + 30);
                int dMaximo = (int) (largura_original / 2.5);
                System.out.println("dMaximo = " + dMaximo + " h = " + d);
                if (d + 20 > dMaximo) {
                    JOptionPane.showMessageDialog(null, "Largura máxima atingida");
                } else {
                    createClip2(d, (int) getRetangleY());
                    repaint();
                }
                System.out.println("h+: " + d);

            }
        });

        JButton menosHorizontal = new JButton("H-"); // diminui o tamanho da área de recorte na horizontal
        menosHorizontal.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int d = (int) (getRetangleX() - 30);
                createClip2(d, (int) getRetangleY());
                System.out.println("h-: " + d);
                repaint();
            }
        });

        JButton sobeVertical = new JButton("V+");  // aumenta o tamanho da área de recorte na vertival
        sobeVertical.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int d = (int) (getRetangleY() + 30);
                createClip2((int) getRetangleX(), d);
                System.out.println("v+: " + d);
                repaint();
            }
        });

        JButton menosVertical = new JButton("V-"); // diminui o tamanho da área de recorte na vertical
        menosVertical.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int d = (int) (getRetangleY() - 30);
                createClip2((int) getRetangleX(), d);
                System.out.println("v-: " + d);
                repaint();
            }
        });

        JPanel panel = new JPanel();

        //   panel.add(voltar);
        panel.add(cb1CheckBox);
        panel.add(cb2CheckBox);
        panel.add(clipBox);
        panel.add(maisHorizontal);
        panel.add(sobeHorizontal);
        panel.add(menosHorizontal);
        panel.add(sobeVertical);
        panel.add(menosVertical);
        panel.add(clip);
        panel.add(conferirImagens);

        panel.add(lerCB);
        panel.add(this.lblCodigoBarras);
        MyListener myListener = new MyListener();
        panel.addMouseListener(myListener);
        panel.addMouseMotionListener(myListener);

        // janela.add(panel);
        //   janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //janela.pack();
        //    janela.setVisible(true);
        //  return panel;
        this.painelAtual = panel;
        return panel;

    }

    private String lerCodigoBarras() { // recorta a imagem

        String codigoBarras = "";
        //this.subImageCB1 = image.getSubimage(this.clipCB1.x, this.clipCB1.y, (image.getWidth() - (this.clipCB1.width +this.clipCB1.x )), (image.getHeight() - (this.clipCB1.y+this.clipCB1.height) )  );
        //this.subImageCB2 = image.getSubimage(this.clipCB2.x, this.clipCB2.y, this.clipCB2.width, this.clipCB2.height);

        this.subImageCB1 = this.cropSubimageWithRectangle(image, this.clipCB1);
        this.subImageCB2 = this.cropSubimageWithRectangle(image, this.clipCB2);

        //return corta(0, 630, this.imagem.getWidth(), this.imagem.getHeight() - 630);
        this.justClipImage();
        //TesteImagem testarImagem = new TesteImagem(this.clippedImage,"Imagem CORREÇÃO");
        //TesteImagem testarImagem2 = new TesteImagem(this.subImageCB1,"Imagem CB1");
        //TesteImagem testarImagem3 = new TesteImagem(this.subImageCB2,"Imagem CB2");

        formTestarImagemCB2.desenharImagem(this.subImageCB2, "Imagem CB2");
        formTestarImagemCB1.desenharImagem(this.subImageCB1, "Imagem CB1");
        formTestarClippedImage.desenharImagem(this.clippedImage, "Imagem para CORREÇÃO");

        codigoBarras = this.reconheceCodigoBarras(this.subImageCB1);
        if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
            System.out.println("CB1 falhou. Tentando ler do código de barras 2");
            codigoBarras = this.reconheceCodigoBarras(this.subImageCB2);
            if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
                System.out.println("CB2 falhou. Tentando ler do código da imagem de correção");
                codigoBarras = this.reconheceCodigoBarras(this.clippedImage);
            }
        }

        System.out.println("Código de barras lido :" + codigoBarras);

        this.lblCodigoBarras.setText(codigoBarras);

        JOptionPane.showMessageDialog(null, "CB Lido: " + codigoBarras);

        return codigoBarras;

    }

    private String reconheceCodigoBarras(BufferedImage imagemParaLeitura) { // recorta a imagem

        String codigoBarras = "";

        try {
            //this.justClipImage();

            LuminanceSource source = new BufferedImageLuminanceSource(imagemParaLeitura);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            //Reader reader = new MultiFormatReader();
            Reader reader = new Code128Reader();
            Result resultado = reader.decode(bitmap);

            codigoBarras = resultado.getText();
            System.out.println("Código de barras lido :" + codigoBarras);

            return codigoBarras;
        } catch (NotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao tentar ler código de barras: " + ex.getMessage());
            return null;
        } catch (ChecksumException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao tentar ler código de barras: " + ex.getMessage());
            return null;
        } catch (FormatException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao tentar ler código de barras: " + ex.getMessage());
            return null;
        }

    }

    public int getCoordenadaRetanguloX() {
        return coordenadaRetanguloX;
    }

    public void setCoordenadaRetanguloX(int coordenadaRetanguloX) {
        this.coordenadaRetanguloX = coordenadaRetanguloX;
    }

    public int getCoordenadaRetanguloY() {
        return coordenadaRetanguloY;
    }

    public void setCoordenadaRetanguloY(int coordenadaRetanguloY) {
        this.coordenadaRetanguloY = coordenadaRetanguloY;
    }

    /*
     @Override
     public void mouseDragged(MouseEvent me) {
     throw new UnsupportedOperationException("Not supported yet.");
     }
    
     @Override
     public void mouseMoved(MouseEvent me) {
     if(this.showClip){
     testarClippedImage.desenharImagem(this.cropSubimageWithRectangle(this.image,this.clip),"Imagem para CORREÇÃO");
     }
    
     if(this.checkBoxCB1){
     testarImagemCB1.desenharImagem(this.cropSubimageWithRectangle(this.image,this.clipCB1),"Imagem CB1");
     }
    
     if(this.checkBoxCB2){
     testarImagemCB2.desenharImagem(this.cropSubimageWithRectangle(this.image,this.clipCB2),"Imagem CB2");
     }
    
     }
     */
    private class MyListener extends MouseInputAdapter {

        public void mousePressed(MouseEvent e) {
            System.out.println("Evento mousePressed (inside listener)");
        }

        public void mouseDragged(MouseEvent e) {
            System.out.println("Evento mouseDragged (inside listener)");
        }

        public void mouseReleased(MouseEvent e) {
            System.out.println("Evento mouseReleased (inside listener)");
        }

        void updateSize(MouseEvent e) {
            System.out.println("Evento updateSize (inside listener)");
        }
    }

    private void desenhaCB1(Graphics2D tela) {
        if (this.checkBoxCB1) {
            if (this.clipCB1 == null) {
                createClipCB1(a, b);
            }

            tela.setPaint(Color.BLUE);
            tela.draw(clipCB1);
        }
    }

    private void desenhaCB2(Graphics2D tela) {
        if (this.checkBoxCB2) {
            if (this.clipCB2 == null) {
                createClipCB2(a, b);
            }
            tela.setPaint(Color.GREEN);
            tela.draw(clipCB2);
        }
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

    public FormTesteImagem getTestarClippedImage() {
        return formTestarClippedImage;
    }

    public FormTesteImagem getTestarImagemCB1() {
        return formTestarImagemCB1;
    }

    public FormTesteImagem getTestarImagemCB2() {
        return formTestarImagemCB2;
    }

    public Rectangle getClip() {
        return clip;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Rectangle getClipCB1() {
        return clipCB1;
    }

    public Rectangle getClipCB2() {
        return clipCB2;
    }

    public void atualizaImagens() {

        if (this.showClip) {
            formTestarClippedImage.desenharImagem(this.cropSubimageWithRectangle(image, this.clip), "Imagem para CORREÇÃO");
        }
        if (this.checkBoxCB1) {
            formTestarImagemCB1.desenharImagem(this.cropSubimageWithRectangle(image, this.clipCB1), "Imagem CB1");
        }
        if (this.checkBoxCB2) {
            formTestarImagemCB2.desenharImagem(this.cropSubimageWithRectangle(image, this.clipCB2), "Imagem CB2");
        }

    }

    public Rectangle getRetanguloAtual() {
        if (this.showClip) {
            return this.clip;
        }
        if (this.checkBoxCB1) {
            return this.clipCB1;
        }
        if (this.checkBoxCB2) {
            return this.clipCB2;
        }
        return this.clip;
    }

    private String criaDiretorioDestino(String origem) {
        String retorno = "";
        String diretorio = Uteis.DIRETORIO_DESTINO;

        try {
            File novoDiretorio = new File(origem + "/" + diretorio);
            retorno = novoDiretorio.getAbsolutePath();
            novoDiretorio.mkdirs();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }
}
