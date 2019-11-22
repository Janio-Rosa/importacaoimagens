package cropimage;

import importacaoimagens.view.RecorteImagemErro;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author katia
 */
public class CroppingErro extends javax.swing.JPanel {

    BufferedImage image;
    Dimension size;
    Rectangle clip;
    boolean showClip;
    int a = 550;
    int b = 700;
    int largura_original;
    int altura_original;
    File arquivoAtual;
    String NroInsc;

    public String getNroInsc() {
        return NroInsc;
    }

    public void setNroInsc(String NroInsc) {
        this.NroInsc = NroInsc;
    }

    public File getArquivoAtual() {
        return arquivoAtual;
    }

    public void setArquivoAtual(File arquivoAtual) {
        this.arquivoAtual = arquivoAtual;
    }

    public CroppingErro(BufferedImage image) {

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

        //    System.out.println("w0 = " + largura_original + ", h0 = " + altura_original);
        g2.drawImage(image, 0, 0, (int) (largura_original / 2.5), (int) (altura_original / 2.5), null);

        if (showClip) {
            if (clip == null) {
                createClip2(a, b);
            }
            g2.setPaint(Color.RED);
            g2.draw(clip);
        }
    }

    public void setClip(int x, int y) {
        // keep clip within raster
        int x0 = (getWidth() - size.width) / 2;
        int y0 = (getHeight() - size.height) / 2;
        if (x < x0 || x + clip.width > x0 + size.width
                || y < y0 || y + clip.height > y0 + size.height) {
            return;
        }
        clip.setLocation(x, y);
        repaint();
    }

    public Dimension getPreferredSize() {
        return size;
    }

    public double getRetangleX() {
        return clip.getWidth();
    }

    public double getRetangleY() {
        return clip.getHeight();
    }

    private void createClip2(int a, int b) {

        clip = new Rectangle(a, b);
        clip.x = 20;
        clip.y = 160;
    }

    private void clipImage() { // exibe tela com a imagem cortada
        BufferedImage clipped = null;
        String[] options = {"Voltar", "Recortar"};

        int w = clip.width;
        int h = clip.height;
        int x0 = (getWidth() - size.width) / 2;
        int y0 = (getHeight() - size.height) / 2;
        int xx = clip.x - x0;
        int yy = clip.y - y0;
        clipped = image.getSubimage((int) (xx * 2.5), (int) (yy * 2.5), (int) (w * 2.5), (int) (h * 2.5));

        RecorteImagemErro frame = new RecorteImagemErro();
        frame.setVisible(true);
        frame.desenhaAoCarregar(clipped, xx, yy, w, h, largura_original, altura_original, NroInsc);

        //      }
    }

    JPanel getUIPanel() {

        final JLabel labelInsc = new JLabel("Nro Inscrição: ");

        final JTextField inscricao = new JTextField(10);
        inscricao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        final JButton clip = new JButton("Recortar");
        clip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NroInsc = inscricao.getText();

                if (NroInsc == null || NroInsc.equals("")) {
                    JOptionPane.showMessageDialog(null, "Informar número de inscrição!", "Número de Inscrição não informado", -1);
                } else {
                    int resposta = JOptionPane.showConfirmDialog(null, "Número Informado: \n" + NroInsc, "Conforma o número de inscrição? ", JOptionPane.YES_NO_OPTION, -1);
// JOptionPane.showMessageDialog(null, "Número Informado: \n "+ NroInsc, "Número de Inscrição", -1);
                    if (resposta == JOptionPane.YES_OPTION) {
                        clipImage();

                    } else if (resposta == JOptionPane.NO_OPTION) {

                    }
                }
            }
        });

        JButton sobeHorizontal = new JButton("+H");
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
                System.out.println("h: " + d);

            }
        });

        JButton menosHorizontal = new JButton("-H");
        menosHorizontal.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int d = (int) (getRetangleX() - 30);
                createClip2(d, (int) getRetangleY());
                System.out.println("h: " + d);
                repaint();
            }
        });

        JButton sobeVertical = new JButton("+V");
        sobeVertical.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int d = (int) (getRetangleY() + 30);
                createClip2((int) getRetangleX(), d);
                System.out.println("v: " + d);
                repaint();
            }
        });

        JButton menosVertical = new JButton("-V");
        menosVertical.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int d = (int) (getRetangleY() - 30);
                createClip2((int) getRetangleX(), d);
                System.out.println("v: " + d);
                repaint();
            }
        });

        JPanel panel = new JPanel();
        //    panel.add(clipBox);
        panel.add(labelInsc);
        panel.add(inscricao);
        panel.add(sobeHorizontal);
        panel.add(menosHorizontal);
        panel.add(sobeVertical);
        panel.add(menosVertical);
        panel.add(clip);
        return panel;

    }
}
