package importacaoimagens.view;

import carrega_imagem.DesenharImagem;
import carrega_imagem.TratarImagem;
import cropimage.CropImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JPanel;

public class VisualizarImagem extends javax.swing.JFrame {

    private int corte;
    private TratarImagem i;
    private DesenharImagem desenhar;
    private VisualizarImagem frame;
    private JPanel panel;
    private String caminho;

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setFrame(VisualizarImagem frame) {
        this.frame = frame;
    }

    public void setI(TratarImagem i) {
        this.i = i;
    }

    public void setDesenhar(DesenharImagem desenhar) {
        this.desenhar = desenhar;
    }

    public VisualizarImagem() {
        
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdAnterior = new javax.swing.JButton();
        cmdProximo = new javax.swing.JButton();
        cmdDescer = new javax.swing.JButton();
        cmdSubir = new javax.swing.JButton();
        pnlImagem = new javax.swing.JPanel();
        jButton2_Salvar = new javax.swing.JButton();
        cmdVoltar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCodigodeBarras = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Visualizar Imagem");
        setFocusCycleRoot(false);
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(1161, 1015));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        cmdAnterior.setText("Anterior");
        cmdAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAnteriorActionPerformed(evt);
            }
        });

        cmdProximo.setText("Próximo");
        cmdProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdProximoActionPerformed(evt);
            }
        });

        cmdDescer.setText("Descer");
        cmdDescer.setToolTipText("");
        cmdDescer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDescerActionPerformed(evt);
            }
        });

        cmdSubir.setText("Subir");
        cmdSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSubirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlImagemLayout = new javax.swing.GroupLayout(pnlImagem);
        pnlImagem.setLayout(pnlImagemLayout);
        pnlImagemLayout.setHorizontalGroup(
            pnlImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 834, Short.MAX_VALUE)
        );
        pnlImagemLayout.setVerticalGroup(
            pnlImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1078, Short.MAX_VALUE)
        );

        jButton2_Salvar.setText("Salvar*");
        jButton2_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2_SalvarActionPerformed(evt);
            }
        });

        cmdVoltar.setText("Voltar");
        cmdVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdVoltarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Código de Barras:");

        txtCodigodeBarras.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCodigodeBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigodeBarrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cmdSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmdDescer))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cmdAnterior)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                    .addComponent(cmdProximo)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(cmdVoltar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtCodigodeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jButton2_Salvar)))
                .addGap(47, 47, 47)
                .addComponent(pnlImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(89, 89, 89))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdDescer)
                    .addComponent(cmdSubir))
                .addGap(28, 28, 28)
                .addComponent(jButton2_Salvar)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdAnterior)
                    .addComponent(cmdProximo))
                .addGap(27, 27, 27)
                .addComponent(cmdVoltar)
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigodeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdProximoActionPerformed

//        Progresso frame = new Progresso();
//        frame.setVisible(true);
//        frame.lerImagens(caminho);
//        frame.setCorte(this.corte);
//        this.dispose();

    }//GEN-LAST:event_cmdProximoActionPerformed

    private void cmdAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAnteriorActionPerformed

//        BancoOrigem frame = new BancoOrigem();
//        frame.setVisible(true);
//        this.dispose();

    }//GEN-LAST:event_cmdAnteriorActionPerformed

    private void cmdDescerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDescerActionPerformed
        this.corte = i.getCorte() + 10;
        i.setCorte(i.getCorte() + 10);
        desenhar.ExibeImagem1(i, frame, panel, caminho);

    }//GEN-LAST:event_cmdDescerActionPerformed

    private void cmdSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSubirActionPerformed

        i.setCorte(i.getCorte() - 10);
        desenhar.ExibeImagem1(i, frame, panel, caminho);


    }//GEN-LAST:event_cmdSubirActionPerformed

    private void jButton2_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2_SalvarActionPerformed
        // String desc = (String) txtHost1.getText();
        // JPanel imagem = this.pnlImagem;        
        // ImagensDAO.inserirImagem(imagem,desc);    
        // i.comprimeImagem(imagem);
    }//GEN-LAST:event_jButton2_SalvarActionPerformed

    private void cmdVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdVoltarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdVoltarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.dispose();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void txtCodigodeBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigodeBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigodeBarrasActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txtCodigodeBarras.setText("TESTE");
        txtCodigodeBarras.setEditable(false);
       
    }//GEN-LAST:event_formWindowOpened

    @Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        Dimension m = getMaximumSize();
        boolean resize = d.width > m.width || d.height > m.height;
        d.width = Math.min(m.width, d.width);
        d.height = Math.min(m.height, d.height);

        if (resize) {
            Point p = getLocation();
            setVisible(false);
            setSize(d);
            setLocation(p);
            setVisible(true);
        }
        super.paint(g);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAnterior;
    private javax.swing.JButton cmdDescer;
    private javax.swing.JButton cmdProximo;
    private javax.swing.JButton cmdSubir;
    private javax.swing.JButton cmdVoltar;
    private javax.swing.JButton jButton2_Salvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlImagem;
    private javax.swing.JTextField txtCodigodeBarras;
    // End of variables declaration//GEN-END:variables

    public void desenhaAoCarregar(String caminho) throws IOException {
       CropImage crop = new CropImage();
        crop.lerImagem(caminho);
     
    }
}
