package importacaoimagens.view;

import carrega_imagem.DesenharImagem;
import carrega_imagem.TratarImagem;
import cropimage.CropImage;
import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import model.ParametrosImportacao;
import model.TbCurso;
import model.TbDadosAcessoBanco;
import service.ImportacaoDocentesService;
import service.ImportacaoService;
import service.RespostaService;
import uteis.TransportaInformacoes;
import uteis.Uteis;

public class RecorteImagem extends javax.swing.JFrame {

    private int corte;
    private TratarImagem i;
    private DesenharImagem desenhar;
    private RecorteImagem frame;
    private static JPanel panel;
    private String caminho;
    private String filename;
    private int idCurso;
    private boolean flCurso;
    private boolean flDisciplina;
    private ParametrosImportacao parametrosImportacao = new ParametrosImportacao();
    RespostaService rs = new RespostaService();

    File file;
    int xx;
    int yy;
    int w, h;
    int altura_0, largura_0;

    int idDisciplina;

    private JTextField txtDiretorio = new javax.swing.JTextField();
    private InputStream imagemTeste = null;

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

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

    public void setFrame(RecorteImagem frame) {
        this.frame = frame;
    }

    public void setI(TratarImagem i) {
        this.i = i;
    }

    public void setDesenhar(DesenharImagem desenhar) {
        this.desenhar = desenhar;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public RecorteImagem() {

        initComponents();
        getHeight();

        this.selecionarBanco();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        lblBancoDestino = new javax.swing.JLabel();
        lblBanco = new javax.swing.JLabel();
        lblHost = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        cmdSenha = new javax.swing.JPasswordField();
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
        jLabelNmDisciplina = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelIdCurso = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelNmCurso = new javax.swing.JLabel();
        jLabelExisteCursoBdDestino = new javax.swing.JLabel();
        jLabelExisteDisciplinaBdDestino = new javax.swing.JLabel();
        cmdImportarTodas = new javax.swing.JButton();
        cmdTestarConexao = new javax.swing.JButton();
        jCheckBoxProducao = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabelProcesso = new javax.swing.JLabel();
        jLabelExisteProcessoBdDestino = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recorte de Imagem");
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(1115, 1000));
        setResizable(false);

        lblBancoDestino.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblBancoDestino.setText("Banco de Destino");

        lblBanco.setText("Banco:");

        lblHost.setText("Host:");

        lblUsuario.setText("Usuário:");

        lblSenha.setText("Senha:");

        cmdSenha.setText("teste");

        pnlImagem.setPreferredSize(new java.awt.Dimension(750, 1070));

        javax.swing.GroupLayout pnlImagemLayout = new javax.swing.GroupLayout(pnlImagem);
        pnlImagem.setLayout(pnlImagemLayout);
        pnlImagemLayout.setHorizontalGroup(
            pnlImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 807, Short.MAX_VALUE)
        );
        pnlImagemLayout.setVerticalGroup(
            pnlImagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 946, Short.MAX_VALUE)
        );

        txtUsuario.setText("teste");
        txtUsuario.setToolTipText("");

        txtHost.setText("192.168.0.135");
        txtHost.setToolTipText("");

        txtBanco.setText("db_correcao_2016_teste");
        txtBanco.setToolTipText("");

        cmdTesteImportacao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmdTesteImportacao.setText("Importar Docentes");
        cmdTesteImportacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTesteImportacaoActionPerformed(evt);
            }
        });

        jLabel1.setText("Nr.Inscrição:");

        jLabelNroInscricao.setText("jLabel2");

        jLabel2.setText("Questão:");

        jLabelNroQuestao.setText("jLabel3");

        jLabel3.setText("Disciplina:");

        jLabelDisciplina.setText("jLabel4");

        jLabelNmDisciplina.setText("jLabel4");
        jLabelNmDisciplina.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel4.setText("Nm Disciplina:");

        jLabel5.setText("IdCurso:");

        jLabelIdCurso.setText("jLabel6");

        jLabel6.setText("NmCurso: ");

        jLabelNmCurso.setText("jLabel7");

        jLabelExisteCursoBdDestino.setText("jLabel7");

        jLabelExisteDisciplinaBdDestino.setText("jLabel7");

        cmdImportarTodas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmdImportarTodas.setText("Importar Vestibular");
        cmdImportarTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdImportarTodasActionPerformed(evt);
            }
        });

        cmdTestarConexao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmdTestarConexao.setLabel("Testar Conexão");
        cmdTestarConexao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTestarConexaoActionPerformed(evt);
            }
        });

        jCheckBoxProducao.setSelected(true);
        jCheckBoxProducao.setText("Produção");
        jCheckBoxProducao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxProducaoActionPerformed(evt);
            }
        });

        jLabel7.setText("Processo: ");

        jLabelProcesso.setText("jLabel8");

        jLabelExisteProcessoBdDestino.setText("jLabel8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdImportarTodas, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdTesteImportacao, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelNroQuestao))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelDisciplina))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelIdCurso))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelNmCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelExisteCursoBdDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelExisteDisciplinaBdDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelNmDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelNroInscricao, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblBancoDestino)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                    .addComponent(jCheckBoxProducao))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblBanco)
                                        .addComponent(lblHost)
                                        .addComponent(lblSenha)
                                        .addComponent(lblUsuario))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtHost, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmdSenha)
                                        .addComponent(txtBanco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(cmdTestarConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelProcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelExisteProcessoBdDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(pnlImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdTestarConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBancoDestino)
                            .addComponent(jCheckBoxProducao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addComponent(cmdImportarTodas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdTesteImportacao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabelProcesso))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabelNroInscricao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabelNroQuestao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabelDisciplina))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabelNmDisciplina))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabelIdCurso))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabelNmCurso))
                        .addGap(18, 18, 18)
                        .addComponent(jLabelExisteProcessoBdDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelExisteCursoBdDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelExisteDisciplinaBdDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabelNroInscricao.getAccessibleContext().setAccessibleName("jLabelNroInscricao");
        cmdImportarTodas.getAccessibleContext().setAccessibleName("cmdImportacao");

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

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed

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

    int resposta;

    resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que é importação para DOCENTE?");
    if (resposta == JOptionPane.YES_OPTION) {
        TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();

        TbDadosAcessoBanco bancoOrigem = this.getParametrosImportacao().getAcessoAoBancoOrigem();
        TbDadosAcessoBanco bancoDestino = new TbDadosAcessoBanco(this.txtHost.getText().trim(), this.txtBanco.getText().trim(), "public", this.txtUsuario.getText().trim(), this.cmdSenha.getText().trim());
        this.getParametrosImportacao().setAcessoAoBancoDestino(bancoDestino);

        ImportacaoDocentesService importando = new ImportacaoDocentesService();
        try {
            importando.setParamCaminho(this.getParametrosImportacao().getCaminhoOrigem());
            importando.setParamDestino(this.criaDiretorioDestino(ti.getCaminhoOrigem()));
            importando.setParamAreaImagemCorrecao(this.getParametrosImportacao().getAreaImagemCorrecao());
            importando.setParamGravaImagemDesidentificada(true);
            importando.setParamAreaImagemCB1(this.getParametrosImportacao().getAreaImagemCB1());
            importando.setParamAreaImagemCB2(this.getParametrosImportacao().getAreaImagemCB2());
            importando.setParamBancoOrigem(bancoOrigem);
            importando.setParamBancoDestino(bancoDestino);

            Thread runningThread = new Thread(importando);
            runningThread.start();

            this.dispose();

            BancoOrigem bo = new BancoOrigem();
            bo.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}//GEN-LAST:event_cmdTesteImportacaoActionPerformed

    public void ConsultaInsereCurso(int idCurso, String nm_curso) {
        //   RespostaService rs = new RespostaService();
        TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();
        TbDadosAcessoBanco bancoOrigem = ti.getAcessoAoBancoOrigem();
        TbDadosAcessoBanco bancoDestino = new TbDadosAcessoBanco(this.txtHost.getText().trim(), this.txtBanco.getText().trim(), "public", this.txtUsuario.getText().trim(), this.cmdSenha.getText().trim());

        rs.insereCurso(idCurso, bancoOrigem, bancoDestino);

    }

    public void ConsultaInsereDisciplina(int idDisciplina, String nm_disciplina) {
        //RespostaService rs = new RespostaService();
        TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();
        TbDadosAcessoBanco bancoOrigem = ti.getAcessoAoBancoOrigem();
        TbDadosAcessoBanco bancoDestino = new TbDadosAcessoBanco(this.txtHost.getText().trim(), this.txtBanco.getText().trim(), "public", this.txtUsuario.getText().trim(), this.cmdSenha.getText().trim());

        rs.insereDisciplina(idDisciplina, nm_disciplina, bancoDestino);
    }

    public void ConsultaInsereDisciplinaVestibular(int idDisciplina, String nm_disciplina) {
        //RespostaService rs = new RespostaService();
        TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();
        TbDadosAcessoBanco bancoOrigem = ti.getAcessoAoBancoOrigem();
        TbDadosAcessoBanco bancoDestino = new TbDadosAcessoBanco(this.txtHost.getText().trim(), this.txtBanco.getText().trim(), "public", this.txtUsuario.getText().trim(), this.cmdSenha.getText().trim());

        rs.insereDisciplinaVestibular(idDisciplina, nm_disciplina, bancoDestino);
    }

    public void ConsultaInsereProcesso(int processo, String nmProcesso, int nrAnoProcesso, int idTipoProcesso) {

        // RespostaService rs = new RespostaService();
        TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();
        TbDadosAcessoBanco bancoOrigem = ti.getAcessoAoBancoOrigem();
        TbDadosAcessoBanco bancoDestino = new TbDadosAcessoBanco(this.txtHost.getText().trim(), this.txtBanco.getText().trim(), "public", this.txtUsuario.getText().trim(), this.cmdSenha.getText().trim());

        rs.insereProcesso(processo, nmProcesso,nrAnoProcesso,idTipoProcesso, bancoDestino);

    }


private void cmdImportarTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdImportarTodasActionPerformed
// TODO add your handling code here:
    int resposta;
    //nome = JOptionPane.showInputDialog("Tem certeza que é importação para VESTIBULAR?");
    resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que é importação para VESTIBULAR?");
    if (resposta == JOptionPane.YES_OPTION) {
        TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();

        TbDadosAcessoBanco bancoOrigem = this.getParametrosImportacao().getAcessoAoBancoOrigem();
        TbDadosAcessoBanco bancoDestino = new TbDadosAcessoBanco(this.txtHost.getText().trim(), this.txtBanco.getText().trim(), "public", this.txtUsuario.getText().trim(), this.cmdSenha.getText().trim());
        this.getParametrosImportacao().setAcessoAoBancoDestino(bancoDestino);

        ImportacaoService importando = new ImportacaoService();
        try {
            importando.setParamCaminho(this.getParametrosImportacao().getCaminhoOrigem());
            importando.setParamDestino(this.criaDiretorioDestino(ti.getCaminhoOrigem()));
            importando.setParamAreaImagemCorrecao(this.getParametrosImportacao().getAreaImagemCorrecao());
            importando.setParamGravaImagemDesidentificada(true);
            importando.setParamAreaImagemCB1(this.getParametrosImportacao().getAreaImagemCB1());
            importando.setParamAreaImagemCB2(this.getParametrosImportacao().getAreaImagemCB2());
            importando.setParamBancoOrigem(bancoOrigem);
            importando.setParamBancoDestino(bancoDestino);

            Thread runningThread = new Thread(importando);
            runningThread.start();

            this.dispose();

            BancoOrigem bo = new BancoOrigem();
            bo.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}//GEN-LAST:event_cmdImportarTodasActionPerformed

private void cmdTestarConexaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTestarConexaoActionPerformed
    TbDadosAcessoBanco bancoDestino = new TbDadosAcessoBanco(this.txtHost.getText().trim(), this.txtBanco.getText().trim(), "public", this.txtUsuario.getText().trim(), this.cmdSenha.getText().trim());
    Uteis.testarConexao(bancoDestino);
}//GEN-LAST:event_cmdTestarConexaoActionPerformed

    private void jCheckBoxProducaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxProducaoActionPerformed
        this.selecionarBanco();
    }//GEN-LAST:event_jCheckBoxProducaoActionPerformed

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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton cmdImportarTodas;
    private javax.swing.JPasswordField cmdSenha;
    private javax.swing.JButton cmdTestarConexao;
    private javax.swing.JButton cmdTesteImportacao;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBoxProducao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelDisciplina;
    private javax.swing.JLabel jLabelExisteCursoBdDestino;
    private javax.swing.JLabel jLabelExisteDisciplinaBdDestino;
    private javax.swing.JLabel jLabelExisteProcessoBdDestino;
    private javax.swing.JLabel jLabelIdCurso;
    private javax.swing.JLabel jLabelNmCurso;
    private javax.swing.JLabel jLabelNmDisciplina;
    private javax.swing.JLabel jLabelNroInscricao;
    private javax.swing.JLabel jLabelNroQuestao;
    private javax.swing.JLabel jLabelProcesso;
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

    public void desenhaAoCarregar(String caminho) {
        DesenharImagem desenharImagem = new DesenharImagem();
        desenharImagem.ExibeImagem(null, this, this.pnlImagem, caminho);
    }

    public void desenhaAoCarregar(BufferedImage clipped, int x, int y, int w, int h, int w0, int h0) {
        DesenharImagem desenharImagem = new DesenharImagem();
        //this.imagemTeste = TratarImagem.converteImagemDada(clipped);

        //desenharImagem.ExibeImagem(null, this, this.pnlImagem, clipped, x, y, w, h, w0, h0);
        //exibeInfAoCarregar();
    }

    public void desenhaAoCarregar(BufferedImage paramImagem) {
        DesenharImagem desenharImagem = new DesenharImagem();
        //this.imagemTeste = TratarImagem.converteImagemDada(clipped);
        //desenharImagem.ExibeImagem(null, this, this.pnlImagem, clipped, x, y, w, h, w0, h0);

        try {
            desenharImagem.exibeImagem(this, this.pnlImagem, this.getParametrosImportacao().getCaminhoOrigem(), this.getParametrosImportacao().getAreaImagemCorrecao());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //exibeInfAoCarregar();
    }

    public void exibeInfAoCarregar() {

        RespostaService rs = new RespostaService();
        File arquivoAtual = TransportaInformacoes.getTransportaInformacoes().getArquivoTrabalho();
        TransportaInformacoes ti = TransportaInformacoes.getTransportaInformacoes();
        TbDadosAcessoBanco bancoOrigem = ti.getAcessoAoBancoOrigem();
        TbDadosAcessoBanco bancoDestino = new TbDadosAcessoBanco(this.txtHost.getText().trim(), this.txtBanco.getText().trim(), "public", this.txtUsuario.getText().trim(), this.cmdSenha.getText().trim());

        //InputStream imagemCortada = this.imagemTeste;
        System.out.println("arquivoAtual 2: " + arquivoAtual);
        System.out.println("bancoOrigem: " + bancoOrigem);
        System.out.println("bancoDestino: " + bancoDestino);
        //System.out.println("imagemCortada: " + imagemCortada);

        //CONSULTA DADOS
        rs.apenasConsultaDados(arquivoAtual, bancoOrigem, bancoDestino);

        setIdCurso(rs.getCurso());
        setIdDisciplina(rs.getDisciplina());
        if (getIdDisciplina() == 81) { //docentes
            this.jLabelDisciplina.setText(String.valueOf(rs.getCurso()));
            this.jLabelNmDisciplina.setText("<HTML>" + String.valueOf(rs.getNomeCurso()) + "</HTML>");

        } else { //vestibular
            this.jLabelDisciplina.setText(String.valueOf(rs.getDisciplina()));
            this.jLabelNmDisciplina.setText("<HTML>" + String.valueOf(rs.getNomeDisciplinaOrigem()) + "</HTML>");
        }

        this.jLabelNroInscricao.setText(rs.getInscricao());
        this.jLabelNroQuestao.setText(String.valueOf(rs.getQuestao()));
        this.jLabelProcesso.setText("<HTML>" + rs.getProcesso() + " - " + String.valueOf(rs.getNomeProcesso()) + "</HTML>");
        this.jLabelIdCurso.setText(String.valueOf(rs.getCurso()));
        this.jLabelNmCurso.setText("<HTML>" + String.valueOf(rs.getNomeCurso()) + "</HTML>");

        if (rs.getFlProcesso() == true) {
            this.jLabelExisteProcessoBdDestino.setForeground(Color.blue);
            this.jLabelExisteProcessoBdDestino.setText("<HTML>" + rs.getMsgProcesso() + "</HTML>");
        } else {
            this.jLabelExisteProcessoBdDestino.setForeground(Color.red);
            this.jLabelExisteProcessoBdDestino.setText("<HTML>" + rs.getMsgProcesso() + "</HTML>");
        }

        flCurso = rs.getFlCurso();
        if (rs.getFlCurso() == true) {
            this.jLabelExisteCursoBdDestino.setForeground(Color.blue);
            this.jLabelExisteCursoBdDestino.setText("<HTML>" + rs.getMsgCurso() + "</HTML>");
        } else {
            this.jLabelExisteCursoBdDestino.setForeground(Color.red);
            this.jLabelExisteCursoBdDestino.setText("<HTML>" + rs.getMsgCurso() + "</HTML>");
        }

        flDisciplina = rs.getFlDisciplina();
        if (rs.getFlDisciplina() == true) {
            this.jLabelExisteDisciplinaBdDestino.setForeground(Color.blue);
            this.jLabelExisteDisciplinaBdDestino.setText("<HTML>" + rs.getMsgDisciplina() + "</HTML>");
        } else if (rs.getFlDisciplinaVestibular() == true && rs.getDisciplina() != 81) {
            this.jLabelExisteDisciplinaBdDestino.setForeground(Color.blue);
            this.jLabelExisteDisciplinaBdDestino.setText("<HTML>" + rs.getMsgDisciplinaVestibular() + "</HTML>");
        } else {
            this.jLabelExisteDisciplinaBdDestino.setForeground(Color.red);
            this.jLabelExisteDisciplinaBdDestino.setText("<HTML>" + rs.getMsgDisciplina() + "</HTML>");
        }
    }

    public ParametrosImportacao getParametrosImportacao() {
        return parametrosImportacao;
    }

    public void setParametrosImportacao(ParametrosImportacao parametrosImportacao) {
        this.parametrosImportacao = parametrosImportacao;
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

    private void selecionarBanco() {
        if (jCheckBoxProducao.isSelected()) {
            //Banco de Produção correktur
            
             txtBanco.setText("db_correcao_treinamento");
             txtHost.setText("dread-novo.dirps.ufu.br");
             txtUsuario.setText("alecio");
             cmdSenha.setText("dl@15mc45lt@");
            
             /*txtBanco.setText("db_correcao_2016");
             txtHost.setText("10.2.48.69");
             txtUsuario.setText("correcao");
             cmdSenha.setText("DatenbankD1rp$");*/
             
/*
            txtBanco.setText("db_correcao_2016");
            txtHost.setText("192.168.0.12");
            txtUsuario.setText("siscouser");
            cmdSenha.setText("dipieonline");
*/
          /*
            txtBanco.setText("db_sisco_vest20152");
            txtHost.setText("correcao.prograd.ufu.br");
            txtUsuario.setText("sisconuser");
            cmdSenha.setText("dipieonline");
            */
            txtBanco.setEditable(false);
            txtHost.setEditable(false);
            txtUsuario.setEditable(false);
            cmdSenha.setEditable(false);
            cmdSenha.setVisible(false);
            lblSenha.setVisible(false);

        } else {
            //Banco de Teste/Desenvolvimento
            txtBanco.setText("db_correcao_treinamento");
            txtHost.setText("dread-novo.dirps.ufu.br");
            txtUsuario.setText("alecio");
            cmdSenha.setText("dl@15mc45lt@");

            txtBanco.setEditable(true);
            txtHost.setEditable(true);
            txtUsuario.setEditable(true);
            cmdSenha.setEditable(true);
            cmdSenha.setVisible(true);
            lblSenha.setVisible(true);
        }
    }

}
