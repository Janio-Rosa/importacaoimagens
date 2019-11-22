/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.BasicDAO;
import model.TbDadosAcessoBanco;
import model.TbDisciplina;
import dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author katia
 */
public class DisciplinaDAO extends BasicDAO {

    public TbDisciplina pesquisar(TbDisciplina filtro, TbDadosAcessoBanco acesso) {
        String sql = "SELECT  *  FROM tb_DISCIPLINA WHERE 1 = 1 ";
        //Filtros
        if (filtro != null) {
            if (filtro.getIdDisciplina() != null) {
                sql += " AND id_disciplina = '" + filtro.getIdDisciplina() + "'";
            }
            /* if( !filtro.getNmDisciplina().trim().equalsIgnoreCase("")){
             sql+=" AND nm_disciplina ilike '%"+filtro.getNmDisciplina()+"%'";

             }*/
        }
        return this.consultaSQL(sql, acesso);
    }

    private TbDisciplina consultaSQL(String sql, TbDadosAcessoBanco acesso) {
        //sql+=" ORDER BY Dt_Documento, Nr_Documento DESC ";
        Connection cn = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(), acesso.getNmBanco(), acesso.getNmEsquema(), acesso.getNmUsuario(), acesso.getNmSenha());
        if (cn == null) {
            return null;
        }
        System.out.println(sql);
        try {
            TbDisciplina docs = new TbDisciplina();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TbDisciplina atual = new TbDisciplina();

                atual.setIdDisciplina(rs.getInt("id_disciplina"));
                atual.setNmDisciplina(rs.getString("nm_disciplina"));
                atual.setNrNotaInicial(rs.getDouble("nr_nota_inicial"));

                //atual.setTbSetor();
                docs = atual;
            }
            this.desconecta(cn);
            return docs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            this.desconecta(cn);
        }

    }

    public TbDisciplina InsereDisciplina(TbDadosAcessoBanco banco, TbDisciplina disciplina, String nome) {
        Connection cn = ConnectionFactory.getConnecionSistemas(banco.getNmHost(), banco.getNmBanco(), banco.getNmEsquema(), banco.getNmUsuario(), banco.getNmSenha());
        int idQuestao = 0;
        TbDisciplina dis = new TbDisciplina();
        if (cn == null) {
            return null;
        }
        try {

            String SQL = "select id_tipo_questao from tb_tipo_questao \n"
                    + "where nm_tipo_questao = '" + nome + "'";

            PreparedStatement ps = cn.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idQuestao = rs.getInt(1);

            }
            String SQL1 = "INSERT INTO tb_disciplina(\n"
                    + "            id_disciplina, nm_disciplina, id_tipo_questao, nr_nota_inicial)\n"
                    + "    VALUES (" + disciplina.getIdDisciplina() + " , '" + disciplina.getNmDisciplina() + "',   " + idQuestao + "  , " + disciplina.getNrNotaInicial() + " );";

          /*  String SQL1 = "INSERT INTO tb_disciplina(\n"
                    + "            id_disciplina, nm_disciplina, id_tipo_questao, nr_nota_inicial)\n"
                    + "    VALUES (" + disciplina.getIdDisciplina() + " , 'disdsafdsfsda',   " + idQuestao + "  , " + disciplina.getNrNotaInicial() + " );";

                  */
            PreparedStatement ps1 = cn.prepareStatement(SQL1);
            ps1.execute();
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
