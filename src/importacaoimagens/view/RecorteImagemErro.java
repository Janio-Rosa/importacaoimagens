package importacaoimagens.view;

import carrega_imagem.DesenharImagem;
import carrega_imagem.TratarImagem;
import cropimage.CropImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.TbDadosAcessoBanco;
import service.RespostaService;
import uteis.TransportaInformacoes;

public class RecorteImagemErro extends javax.swing.JFrame {

    private int corte;
    private TratarImagem i;
    private DesenharImagem desenhar;
    private RecorteImagemErro frame;
    private static JPanel panel;
    private String caminho;
    private String filename;
    private static String filename1;

    File file;
    int xx;
    int yy;
    int w, h;
    int altura_0, largura_0;
    private JTextField txtDiretorio = new javax.swing.JTextField();
    private InputStream imagemTeste = null;

    public int getW() {
        return w;
    }

    public int getXx() {
        return xx;
    }

    public void setXx(int xx) {
        this.xx = xx;
    }

    public int getYy() {
        return yy;
    }

    public void setYy(int yy) {
        this.yy = yy;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getAltura_0() {
        return altura_0;
    }

    public void setAltura_0(int altura_0) {
        this.altura_0 = altura_0;
    }

    public int getLargura_0() {
        return largura_0;
    }

    public void setLargura_0(int largura_0) {
        this.largura_0 = largura_0;
    }

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

    public void setFrame(RecorteImagemErro frame) {
        this.frame = frame;
    }

    public void setI(TratarImagem i) {
        this.i = i;
    }

    public void setDesenhar(DesenharImagem desenhar) {
        this.desenhar = desenhar;
    }

    public RecorteImagemErro() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblBancoDestino = new javax.swing.JLabel();
        lblBanco = new javax.swing.JLabel();
        lblHost = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        cmdSenha = new javax.swing.JPasswordField();
        cmdAnterior = new javax.swing.JButton();
        pnlImagem = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        txtHost = new javax.swing.JTextField();
        txtBanco = new javax.swing.JTextField();
        cmdTesteImportacao = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabelNroInscricao = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelNroQuestao = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelDisciplina = new javax.swing.JLabel();
        cmdGravarImagem = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recorte de Imagem");
        setLocationByPlatform(true);

        lblBancoDestino.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblBancoDestino.setText("Banco de Destino");

        lblBanco.setText("Banco:");

        lblHost.setText("Host:");

        lblUsuario.setText("Usuário:");

        lblSenha.setText("Senha:");

        cmdSenha.setText("dirps");

        cmdAnterior.setText("Anterior");
        cmdAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAnteriorActionPerformed(evt);
            }
        });

        pnlImagem.setPreferredSize(new java.awt.Dimension(750, 1070));

        javax.swing.GroupLayout pnlImagemLayout = new javax.swing.GroupLayout(pnlImagem);
        pnlImagem.setLayout(pnlImagemLayout);
        pnlImagemLayout.setHorizontalGroup(
            pnlImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );
        pnlImagemLayout.setVerticalGroup(
            pnlImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1070, Short.MAX_VALUE)
        );

        txtUsuario.setText("dirps");
        txtUsuario.setToolTipText("");

        txtHost.setText("192.168.0.135");
        txtHost.setToolTipText("");

        txtBanco.setText("db_sisco_correcao2015");
        txtBanco.setToolTipText("");

        cmdTesteImportacao.setText("Teste Importação");
        cmdTesteImportacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTesteImportacaoActionPerformed(evt);
            }
        });

        jLabel1.setText("Nr.Inscrição Informado:");

        jLabelNroInscricao.setText("jLabel2");

        jLabel2.setText("Questão:");

        jLabelNroQuestao.setText("jLabel3");

        jLabel3.setText("Disciplina:");

        jLabelDisciplina.setText("jLabel4");

        cmdGravarImagem.setText("Chamar Gravar Imagem");
        cmdGravarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGravarImagemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBanco)
                            .addComponent(lblHost))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBanco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblBancoDestino))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelNroInscricao, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblUsuario)
                                    .addGap(14, 14, 14)
                                    .addComponent(txtUsuario))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblSenha)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelNroQuestao))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelDisciplina))
                            .addComponent(cmdGravarImagem))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmdTesteImportacao)
                                .addComponent(cmdAnterior))
                            .addGap(43, 43, 43))))
                .addGap(31, 31, 31)
                .addComponent(pnlImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lblBancoDestino)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBanco)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHost)
                    .addComponent(txtHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(cmdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cmdAnterior)
                .addGap(18, 18, 18)
                .addComponent(cmdTesteImportacao)
                .addGap(26, 26, 26)
                .addComponent(cmdGravarImagem)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelNroInscricao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelNroQuestao))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelDisciplina))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabelNroInscricao.getAccessibleContext().setAccessibleName("jLabelNroInscricao");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdImportarActionPerformed

        Progresso frame = new Progresso();
        frame.setVisible(true);
        try {
            frame.lerImagens(caminho);
        } catch (IOException ex) {
            Logger.getLogger(RecorteImagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.setCorte(this.corte);
        this.dispose();

    }//GEN-LAST:event_cmdImportarActionPerformed

    private void cmdAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAnteriorActionPerformed

        CropImage crop = new CropImage();
        frame.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_cmdAnteriorActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed

        JFileChooser arquivo = new JFileChooser("C:\\Users\\Thiago\\Desktop\\imagens");// depois mudar diretorio padrão
        arquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int res = arquivo.showOpenDialog(null);
        File file = arquivo.getSelectedFile();

        if (res == JFileChooser.APPROVE_OPTION) {
            filename = file.getAbsolutePath();
            txtDiretorio.setText(filename);
            filename1 = filename;
        }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButtonCopiarImagensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCopiarImagensActionPerformed
        /*   file = new File(txtDiretorio.getText());
         if ((!"".equals(txtDiretorio.getText())) && file.isDirectory()) {
         //    CopiarImagens frame = new CopiarImagens();
         Progresso frame = new Progresso();
         BancoOrigem bo = new BancoOrigem();
         caminho = bo.getFilename();
         frame.setVisible(true);
         try {
         frame.copiarImagens(caminho, filename, xx, yy, w, h, altura_0, largura_0);
         } catch (IOException ex) {
         Logger.getLogger(RecorteImagem.class.getName()).log(Level.SEVERE, null, ex);
         }
         this.dispose();
         } else {
         JOptionPane.showMessageDialog(null, "Selecione um Diretório Destino! ");
         }*/
    }//GEN-LAST:event_jButtonCopiarImagensActionPerformed

    private void txtDiretorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiretorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiretorioActionPerformed

private void cmdTesteImportacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTesteImportacaoActionPerformed

    RespostaService rs = new RespostaService();

    File arquivoAtual = TransportaInformacoes.getTransportaInformacoes().getArquivoTrabalho();
    TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();
    TbDadosAcessoBanco bancoOrigem = ti.getAcessoAoBancoOrigem();
    TbDadosAcessoBanco bancoDestino = new TbDadosAcessoBanco(this.txtHost.getText().trim(), this.txtBanco.getText().trim(), "public", this.txtUsuario.getText().trim(), this.cmdSenha.getText().trim());

    InputStream imagemCortada = this.imagemTeste;

    System.out.println("arquivoAtual: " + arquivoAtual);
    System.out.println("bancoOrigem: " + bancoOrigem);
    System.out.println("bancoDestino: " + bancoDestino);
    System.out.println("imagemCortada: " + imagemCortada);

    rs.importaArquivo(arquivoAtual, bancoOrigem, bancoDestino, imagemCortada);

    String insc = rs.getInscricao();
    this.jLabelNroInscricao.setText(insc);
    setVisible(false);
}//GEN-LAST:event_cmdTesteImportacaoActionPerformed

    private void cmdGravarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGravarImagemActionPerformed
      TbDadosAcessoBanco bancoDestino = new TbDadosAcessoBanco(this.txtHost.getText().trim(), this.txtBanco.getText().trim(), "public", this.txtUsuario.getText().trim(), this.cmdSenha.getText().trim());
        TransportaInformacoes.getTransportaInformacoes().setAcessoAoBancoDestino(bancoDestino);
        ProgressoErro frame = new ProgressoErro();
        BancoOrigem bo = new BancoOrigem();
        frame.setVisible(true);
        caminho = bo.getFilename();
        try {

            frame.copiarImagem(caminho, filename, xx, yy, w, h, altura_0, largura_0);
        } catch (IOException ex) {
            Logger.getLogger(RecorteImagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_cmdGravarImagemActionPerformed

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
    private javax.swing.JButton cmdGravarImagem;
    private javax.swing.JPasswordField cmdSenha;
    private javax.swing.JButton cmdTesteImportacao;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelDisciplina;
    private javax.swing.JLabel jLabelNroInscricao;
    private javax.swing.JLabel jLabelNroQuestao;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBanco;
    private javax.swing.JLabel lblBancoDestino;
    private javax.swing.JLabel lblHost;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlImagem;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtHost;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    public void desenhaAoCarregar(BufferedImage clipped, int x, int y, int w, int h, int w0, int h0,String NroInsc) {
        DesenharImagem desenharImagem = new DesenharImagem();
        this.imagemTeste = TratarImagem.converteImagemDada(clipped);
        
        //desenharImagem.ExibeImagemErro(null, this, this.pnlImagem, clipped, x, y, w, h, w0, h0);
        exibeInfAoCarregarErro(NroInsc);

    }

    public void exibeInfAoCarregar() {

        RespostaService rs = new RespostaService();
        File arquivoAtual = TransportaInformacoes.getTransportaInformacoes().getArquivoTrabalho();
        TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();
        TbDadosAcessoBanco bancoOrigem = ti.getAcessoAoBancoOrigem();
        TbDadosAcessoBanco bancoDestino = new TbDadosAcessoBanco(this.txtHost.getText().trim(), this.txtBanco.getText().trim(), "public", this.txtUsuario.getText().trim(), this.cmdSenha.getText().trim());

        InputStream imagemCortada = this.imagemTeste;

        rs.apenasConsultaDados(arquivoAtual, bancoOrigem, bancoDestino);

        this.jLabelNroInscricao.setText(rs.getInscricao());
        this.jLabelNroQuestao.setText(String.valueOf(rs.getQuestao()));
        this.jLabelDisciplina.setText(String.valueOf(rs.getDisciplina()));
        
    }

        public void exibeInfAoCarregarErro(String NroInscr) {

        RespostaService rs = new RespostaService();
        File arquivoAtual = TransportaInformacoes.getTransportaInformacoes().getArquivoTrabalho();
        TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();
        TbDadosAcessoBanco bancoOrigem = ti.getAcessoAoBancoOrigem();
        TbDadosAcessoBanco bancoDestino = new TbDadosAcessoBanco(this.txtHost.getText().trim(), this.txtBanco.getText().trim(), "public", this.txtUsuario.getText().trim(), this.cmdSenha.getText().trim());

        InputStream imagemCortada = this.imagemTeste;

        rs.apenasConsultaDados(arquivoAtual, bancoOrigem, bancoDestino);

        this.jLabelNroInscricao.setText(NroInscr);
        this.jLabelNroQuestao.setText(String.valueOf(rs.getQuestao()));
        this.jLabelDisciplina.setText(String.valueOf(rs.getDisciplina()));
        
    }
    private void cmdProximoActionPerformed(java.awt.event.ActionEvent evt) {

        Progresso frame = new Progresso();
        frame.setVisible(true);
        try {
            frame.lerImagens(caminho);
        } catch (IOException ex) {
            Logger.getLogger(RecorteImagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.setCorte(this.corte);
        this.dispose();

    }
}
