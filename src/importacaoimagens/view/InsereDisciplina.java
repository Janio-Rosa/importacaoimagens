package importacaoimagens.view;

import dao.impl.DisciplinaDAO;
import javax.swing.JOptionPane;
import model.TbDadosAcessoBanco;
import model.TbDisciplina;
import model.TbTipoQuestao;
import service.RespostaService;

/**
 *
 * @author katia
 */
public class InsereDisciplina extends javax.swing.JFrame {

    private int idDisc;
    private String nomeDisciplina;
    private String idTipoQuestão;
    private double nrNotaInicial;
    private TbDadosAcessoBanco bancoDestino;
    private TbDisciplina retorno;

    public TbDadosAcessoBanco getBancoDestino() {
        return bancoDestino;
    }

    public void setBancoDestino(TbDadosAcessoBanco bancoDestino) {
        this.bancoDestino = bancoDestino;
    }

    public InsereDisciplina() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNomeDisciplina = new javax.swing.JLabel();
        jLabelIdTipoQuestão = new javax.swing.JLabel();
        jLabelNrNotaInicial = new javax.swing.JLabel();
        jTextFieldNomeDisciplina = new javax.swing.JTextField();
        jTextFieldNrNotaInicial = new javax.swing.JTextField();
        jButtonVoltar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabelIdDisciplina = new javax.swing.JLabel();
        jLabelIdDisc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inserir Disciplina");

        jLabelNomeDisciplina.setText("Nome Disciplina:");

        jLabelIdTipoQuestão.setText("Id Tipo Questão:");

        jLabelNrNotaInicial.setText("Nr Nota Inicial:");

        jTextFieldNrNotaInicial.setText("0");

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabelIdDisciplina.setText("jLabel1");

        jLabelIdDisc.setText("Código:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelIdTipoQuestão)
                    .addComponent(jLabelNrNotaInicial)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelIdDisc)
                            .addComponent(jLabelNomeDisciplina))
                        .addGap(4, 4, 4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldNrNotaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelIdDisciplina)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonVoltar)
                                .addGap(47, 47, 47)
                                .addComponent(jButtonSalvar))
                            .addComponent(jComboBox1, 0, 389, Short.MAX_VALUE)
                            .addComponent(jTextFieldNomeDisciplina))
                        .addContainerGap(50, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdDisciplina)
                    .addComponent(jLabelIdDisc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeDisciplina)
                    .addComponent(jTextFieldNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdTipoQuestão)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNrNotaInicial)
                    .addComponent(jTextFieldNrNotaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVoltar)
                    .addComponent(jButtonSalvar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        nomeDisciplina = jTextFieldNomeDisciplina.getText();
        idTipoQuestão = (String) jComboBox1.getSelectedItem();

        nrNotaInicial = Double.parseDouble(jTextFieldNrNotaInicial.getText());

        /* JOptionPane.showMessageDialog(null, ("IdDisciplina: '" + idDisc + "' \n " + "Nome Disciplina: '" + nomeDisciplina + "' \n " + 
         "Tipo Questão: '" + idTipoQuestão + "' \n " + "Nr. Nota Inicial: '" + nrNotaInicial + "'\n"), "fdfds");*/
        int response = JOptionPane.showConfirmDialog(null, "IdDisciplina: '" + idDisc + "' \n" + "Nome Disciplina: '" + nomeDisciplina + "' \n"
                + "Tipo Questão: '" + idTipoQuestão + "' \n" + "Nr. Nota Inicial: '" + nrNotaInicial + "'\n", "Confirma os dados?", JOptionPane.YES_NO_OPTION);

    
        
        switch (response) {
            case 0:

           //     TbDisciplina disciplina = new TbDisciplina(idDisc, nomeDisciplina, nrNotaInicial, idTipoQuestão);

        //RespostaService rs = new RespostaService();
                //rs.inserirTpDisciplina(getBancoDestino(), disciplina, idTipoQuestão);
                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
             //   retorno = disciplinaDAO.InsereDisciplina(bancoDestino, disciplina, idTipoQuestão);

                System.out.println("RETORNO = " + retorno);
                if (retorno != null) {
                    JOptionPane.showMessageDialog(null, "Disciplina inserida.");
                    dispose();
                }

            case 1:
               // this.dispose();

        }


    }//GEN-LAST:event_jButtonSalvarActionPerformed

    public int getIdDisc() {
        return idDisc;
    }

    public void setIdDisc(int idDisc) {
        this.idDisc = idDisc;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getIdTipoQuestão() {
        return idTipoQuestão;
    }

    public void setIdTipoQuestão(String idTipoQuestão) {
        this.idTipoQuestão = idTipoQuestão;
    }

    public double getNrNotaInicial() {
        return nrNotaInicial;
    }

    public void setNrNotaInicial(double nrNotaInicial) {
        this.nrNotaInicial = nrNotaInicial;
    }


    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabelIdDisc;
    private javax.swing.JLabel jLabelIdDisciplina;
    private javax.swing.JLabel jLabelIdTipoQuestão;
    private javax.swing.JLabel jLabelNomeDisciplina;
    private javax.swing.JLabel jLabelNrNotaInicial;
    private javax.swing.JTextField jTextFieldNomeDisciplina;
    private javax.swing.JTextField jTextFieldNrNotaInicial;
    // End of variables declaration//GEN-END:variables

    void consultaTipoQuestao(TbDadosAcessoBanco bancoDestino) {
        RespostaService rs = new RespostaService();
        TbTipoQuestao tp = new TbTipoQuestao();

        jComboBox1.addItem(tp.getNmTipoQuestao());
        System.out.println("******** " + tp.getNmTipoQuestao());
    }

    void exibeInfAoCarregar(int idDisciplina, TbDadosAcessoBanco bancoDestino) {
        idDisc = idDisciplina;
        jLabelIdDisciplina.setText(String.valueOf(idDisciplina));
        RespostaService rs = new RespostaService();
        rs.consultaTipoQuestao(bancoDestino, jComboBox1);
        TbTipoQuestao tp = new TbTipoQuestao();
        setBancoDestino(bancoDestino);
    }

}
