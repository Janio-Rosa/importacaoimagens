package importacaoimagens.view;

import uteis.Conexao;
import cropimage.CropImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.TbDadosAcessoBanco;
import uteis.TransportaInformacoes;

public class BancoOrigem extends javax.swing.JFrame {

    private static String filename;
    private static String filename1;
    private String nm_banco;
    private String host;
    private String usuario;
    private String senha;

    public static String getFilename1() {
        return filename1;
    }

    public static void setFilename1(String filename1) {
        BancoOrigem.filename1 = filename1;
    }

    File file;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public BancoOrigem() {
        initComponents();
        txtDiretorio.setText("C:/Users/Thiago/Desktop/TesteImportar");
        this.selecionarBanco();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        lblDiretorio = new javax.swing.JLabel();
        lblBancoOrigem = new javax.swing.JLabel();
        lblBanco = new javax.swing.JLabel();
        lblHost = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        cmdProximo = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        cmdBuscar = new javax.swing.JButton();
        txtDiretorio = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtBanco = new javax.swing.JTextField();
        txtHost = new javax.swing.JTextField();
        jCheckBoxProducao = new javax.swing.JCheckBox();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Banco de Origem");

        lblDiretorio.setText("Diretório:");

        lblBancoOrigem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblBancoOrigem.setText("Banco de Origem:");

        lblBanco.setText("Banco:");

        lblHost.setText("Host:");

        lblUsuario.setText("Usuário:");

        lblSenha.setText("Senha:");

        cmdProximo.setText("Próximo");
        cmdProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdProximoActionPerformed(evt);
            }
        });

        txtSenha.setText("ifuU74jmccv@1");

        cmdBuscar.setText("Buscar");
        cmdBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBuscarActionPerformed(evt);
            }
        });

        txtDiretorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiretorioActionPerformed(evt);
            }
        });

        txtUsuario.setText("usr_processos");
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        txtBanco.setText("processos");
        txtBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBancoActionPerformed(evt);
            }
        });

        txtHost.setText("dread-novo.dirps.ufu.br");
        txtHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHostActionPerformed(evt);
            }
        });

        jCheckBoxProducao.setSelected(true);
        jCheckBoxProducao.setText("Produção");
        jCheckBoxProducao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxProducaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 537, Short.MAX_VALUE)
                        .addComponent(cmdProximo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDiretorio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiretorio, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblUsuario)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblHost)
                                            .addComponent(lblBanco))
                                        .addGap(25, 25, 25)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtBanco, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                            .addComponent(txtHost, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblSenha)
                                        .addGap(24, 24, 24)
                                        .addComponent(txtSenha)))
                                .addGap(261, 261, 261))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(lblBancoOrigem)
                                .addGap(26, 26, 26)
                                .addComponent(jCheckBoxProducao)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(cmdBuscar)))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiretorio)
                    .addComponent(cmdBuscar)
                    .addComponent(txtDiretorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBancoOrigem)
                    .addComponent(jCheckBoxProducao))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBanco)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHost)
                    .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(cmdProximo)
                .addGap(46, 46, 46))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdProximoActionPerformed

        file = new File(txtDiretorio.getText());
        //file = new File("C:/Users/katia/Documents/Presentes - Cópia");
        if ((!"".equals(txtDiretorio.getText())) && file.isDirectory()) {
            //if ((!"".equals("C:/Users/katia/Documents/Presentes - Cópia")) && file.isDirectory()) {
            //RecorteImagem frame = new RecorteImagem();
            CropImage crop = new CropImage();
            try {
                crop.lerImagem(filename);
                setFilename(filename);

            } catch (IOException ex) {
                Logger.getLogger(BancoOrigem.class.getName()).log(Level.SEVERE, null, ex);
            }
            nm_banco = txtBanco.getText();
            host = txtHost.getText();
            usuario = txtUsuario.getText();
            senha = txtSenha.getText();

            System.out.println("nm_banco = " + nm_banco);
            TbDadosAcessoBanco ti = new TbDadosAcessoBanco();

            ti.setNmBanco(nm_banco);
            ti.setNmEsquema("public");
            ti.setNmUsuario(usuario);
            ti.setNmSenha(senha);
            ti.setNmHost(host);
            TransportaInformacoes.getTransportaInformacoes().setAcessoAoBancoOrigem(ti);
            
            this.dispose();
        } else {

            JOptionPane.showMessageDialog(null, "Selecione um Diretório! ");
        }
    }//GEN-LAST:event_cmdProximoActionPerformed

    private void cmdBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarActionPerformed

        //JFileChooser arquivo = new JFileChooser("/home/janio/Documents/Diversos/temp/importar");// depois mudar diretorio padrão
        JFileChooser arquivo = new JFileChooser("C:/Users/Thiago/Desktop/TesteImportar");// depois mudar diretorio padrão
        arquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int res = arquivo.showOpenDialog(null);
        File file = arquivo.getSelectedFile();

        if (res == JFileChooser.APPROVE_OPTION) {
            filename = file.getAbsolutePath();
            txtDiretorio.setText(filename);
            filename1 = filename;
        }
        TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();
        ti.setCaminhoOrigem(txtDiretorio.getText());
        
    }//GEN-LAST:event_cmdBuscarActionPerformed

    private void txtDiretorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiretorioActionPerformed
    }//GEN-LAST:event_txtDiretorioActionPerformed

    private void jButtonTestarBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTestarBancoActionPerformed
        nm_banco = txtBanco.getText();
        host = txtHost.getText();
        usuario = txtUsuario.getText();
        senha = txtSenha.getText();

        Conexao bd = new Conexao();

        bd.conectaBanco(nm_banco, host, usuario, senha);

    }//GEN-LAST:event_jButtonTestarBancoActionPerformed

    private void txtBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBancoActionPerformed

    private void jCheckBoxProducaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxProducaoActionPerformed
        this.selecionarBanco();;
    }//GEN-LAST:event_jCheckBoxProducaoActionPerformed

    private void txtHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHostActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void selecionarBanco(){
        if(jCheckBoxProducao.isSelected()){
            //Banco de Produção
            txtBanco.setText("processos");
            //txtHost.setText("10.2.49.144");
            //txtUsuario.setText("legados");
            //txtSenha.setText("DipieLegend16");
            
            txtHost.setText("192.168.0.102");
            txtUsuario.setText("siscouser");
            txtSenha.setText("prograd2017");
            
            txtBanco.setEditable(false);
            txtHost.setEditable(false);
            txtUsuario.setEditable(false);
            txtSenha.setEditable(false);
            txtSenha.setVisible(false);
            lblSenha.setVisible(false);
            
        }else{
            //Banco de Teste/Desenvolvimento
            txtBanco.setText("processos");
            txtHost.setText("dread-novo.dirps.ufu.br");
            txtUsuario.setText("usr_processos");
            txtSenha.setText("ifuU74jmccv@1");
            
            txtBanco.setEditable(true);
            txtHost.setEditable(true);
            txtUsuario.setEditable(true);
            txtSenha.setEditable(true);
            txtSenha.setVisible(true);
            lblSenha.setVisible(true);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdBuscar;
    private javax.swing.JButton cmdProximo;
    private javax.swing.JCheckBox jCheckBoxProducao;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBanco;
    private javax.swing.JLabel lblBancoOrigem;
    private javax.swing.JLabel lblDiretorio;
    private javax.swing.JLabel lblHost;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtDiretorio;
    private javax.swing.JTextField txtHost;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
