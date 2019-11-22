package importacaoimagens.view;

import carrega_imagem.CopiarImagens;
import carrega_imagem.GravarTodasImagens;
import carrega_imagem.ProgressoController;
import java.io.IOException;

public class ProgressoErro extends javax.swing.JFrame {

    private int corte;
    private int quantArquivos;

    public ProgressoErro() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prgProgresso = new javax.swing.JProgressBar();
        lblEnviados = new javax.swing.JLabel();
        cmdRelatorioFinal = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Progresso");

        lblEnviados.setText("Enviados: ");

        cmdRelatorioFinal.setText("Ok");
        cmdRelatorioFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRelatorioFinalActionPerformed(evt);
            }
        });

        lblTotal.setText("Total: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prgProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEnviados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotal)
                        .addGap(125, 125, 125))))
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(cmdRelatorioFinal)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEnviados, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prgProgresso, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(cmdRelatorioFinal)
                .addGap(47, 47, 47))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdRelatorioFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRelatorioFinalActionPerformed

        this.dispose();
        
    }//GEN-LAST:event_cmdRelatorioFinalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdRelatorioFinal;
    private javax.swing.JLabel lblEnviados;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JProgressBar prgProgresso;
    // End of variables declaration//GEN-END:variables

    public void lerImagens(String caminho) throws IOException {
        ProgressoController progresso = new ProgressoController();
        progresso.percorrerImagens(caminho, this.prgProgresso);
    }
    
    void copiarImagens(String caminho, String filename, int x, int y, int w, int h, int w0, int h0) throws IOException {
        GravarTodasImagens cp = new GravarTodasImagens();
        cp.copiarImagens(caminho, filename,this.prgProgresso, x, y, w, h, w0, h0);
    } 
    
    void copiarImagem(String caminho, String filename, int x, int y, int w, int h, int w0, int h0) throws IOException {
        GravarTodasImagens cp = new GravarTodasImagens();
        cp.copiarImagem(caminho, filename,this.prgProgresso, x, y, w, h, w0, h0);
    }     

  /*  void copiarImagens(String caminho, String filename) throws IOException {
        CopiarImagens cp = new CopiarImagens();
        cp.copiarImagens(caminho, filename,this.prgProgresso);
    }    */

    public int getCorte() {
        return corte;
    }

    public void setCorte(int corte) {
        this.corte = corte;
    }

    public int getQuantArquivos() {
        return quantArquivos;
    }

    public void setQuantArquivos(int quantArquivos) {
        this.quantArquivos = quantArquivos;
    }

}
