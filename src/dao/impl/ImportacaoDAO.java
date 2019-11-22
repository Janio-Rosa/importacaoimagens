/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.BasicDAO;
import dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.TbDadosAcessoBanco;
import model.TbImportacao;

/**
 *
 * @author janio
 */
public class ImportacaoDAO  extends BasicDAO {

    public TbImportacao InsereDisciplina(TbDadosAcessoBanco banco, TbImportacao importacao) {
        Connection cn = ConnectionFactory.getConnecionSistemas(banco.getNmHost(), banco.getNmBanco(), banco.getNmEsquema(), banco.getNmUsuario(), banco.getNmSenha());
        int idQuestao = 0;
        TbImportacao dis = new TbImportacao();
        if (cn == null) {
            return null;
        }
        try {


            String SQL1 = "INSERT INTO tb_importacao_imagem(\n"
                    + "            nr_inscricao, nr_questao, id_disciplina, fl_importado)\n"
                    + "    VALUES ( '" + importacao.getNrInscricao() + "' , '" + importacao.getNrQuestao() + "',   " + importacao.getIdDisciplina() + "  , " + importacao.getFlImportado() + " ) returning id_importacao_imagem;";

            PreparedStatement ps1 = cn.prepareStatement(SQL1);
            ResultSet resultado = ps1.executeQuery();
            if(resultado.next()){
                dis.setIdImportacao(resultado.getInt("id_importacao_imagem"));
            }
            return dis;
            //JOptionPane.showMessageDialog(null, "Disciplina inserida.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconecta(cn);
        }
        return null;

    }

    
}
