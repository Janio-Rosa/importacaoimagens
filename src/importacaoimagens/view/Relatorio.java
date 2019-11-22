package importacaoimagens.view;

import carrega_imagem.CopiarImagens;
import carrega_imagem.GravarTodasImagens;
import carrega_imagem.ProgressoController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Relatorio extends javax.swing.JFrame {

    GravarTodasImagens progresso = new GravarTodasImagens();
    CopiarImagens cp = new CopiarImagens();

    public Relatorio() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblRelatorio = new javax.swing.JLabel();
        cmdVoltar = new javax.swing.JButton();
        cmdSair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListListaDeArquivos = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jRadioButtonLogsErros = new javax.swing.JRadioButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Relatório");

        lblRelatorio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblRelatorio.setText("Arquivos Importados:");

        cmdVoltar.setText("Voltar");
        cmdVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdVoltarActionPerformed(evt);
            }
        });

        cmdSair.setText("Sair");
        cmdSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSairActionPerformed(evt);
            }
        });

        jListListaDeArquivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListListaDeArquivosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListListaDeArquivos);

        jLabel1.setText("jLabel1");

        jRadioButtonLogsErros.setText("Logs com Erro");
        jRadioButtonLogsErros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonLogsErrosMouseClicked(evt);
            }
        });
        jRadioButtonLogsErros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLogsErrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(cmdVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 836, Short.MAX_VALUE)
                .addComponent(cmdSair)
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButtonLogsErros)
                .addGap(256, 256, 256))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRelatorio)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jRadioButtonLogsErros)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdVoltar)
                    .addComponent(cmdSair))
                .addGap(32, 32, 32))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdVoltarActionPerformed
//        Progresso frame = new Progresso();
//        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cmdVoltarActionPerformed

    private void cmdSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSairActionPerformed
        Relatorio r = new Relatorio();
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.exit(0);
    }//GEN-LAST:event_cmdSairActionPerformed

    private void jListListaDeArquivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListListaDeArquivosMouseClicked
        int indice = this.jListListaDeArquivos.getSelectedIndex();
        System.out.println("indice: " + indice);
        try {
            progresso.abrirImagem(indice);
        } catch (IOException ex) {
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jListListaDeArquivosMouseClicked

    private void jRadioButtonLogsErrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonLogsErrosMouseClicked
          JOptionPane.showMessageDialog(null, "Botão Logs com Erro Selecionado");  
    }//GEN-LAST:event_jRadioButtonLogsErrosMouseClicked

    private void jRadioButtonLogsErrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonLogsErrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonLogsErrosActionPerformed

    public void listarArquivos() {
        System.out.println("Entrou em listarArquivos ");
        // ProgressoController progresso = new ProgressoController();

          progresso.listarArquivos(this.jListListaDeArquivos, this.jLabel1);          

//        cp.listarArquivos(this.jListListaDeArquivos, this.jLabel1);                   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSair;
    private javax.swing.JButton cmdVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jListListaDeArquivos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonLogsErros;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRelatorio;
    // End of variables declaration//GEN-END:variables
}
