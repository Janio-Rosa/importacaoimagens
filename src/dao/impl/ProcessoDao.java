/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.TbCandidato;
import model.TbDadosAcessoBanco;
import model.TbProcesso;
import dao.ConnectionFactory;
import dao.BasicDAO;

/**
 *
 * @author katia
 */
public class ProcessoDao extends BasicDAO {

    public TbProcesso pesquisar(TbProcesso filtro, TbDadosAcessoBanco acesso) {
        String sql = "SELECT  *  FROM tb_PROCESSO WHERE  ";
        //Filtros
        if (filtro != null) {
            if (filtro.getIdProcesso() != null) {
                sql += " id_processo = " + filtro.getIdProcesso();
            }

        }
        return this.consultaSQL(sql, acesso);
    }

    public TbProcesso InsereProcesso(TbProcesso tbProcesso, TbDadosAcessoBanco banco) {

        Connection cn = ConnectionFactory.getConnecionSistemas(banco.getNmHost(), banco.getNmBanco(), banco.getNmEsquema(), banco.getNmUsuario(), banco.getNmSenha());

     //   TbProcesso processo = new TbProcesso();
        if (cn == null) {
            return null;
        }
        try {

            String SQL1 = " INSERT INTO tb_processo(id_processo, nm_processo,nr_ano,id_tipo_processo)"
                    + " VALUES(" + tbProcesso.getIdProcesso() + ",'" + tbProcesso.getNmProcesso() + "','" + tbProcesso.getNrAno()+ "','" + tbProcesso.getIdTipoProcesso()+ "');";

            PreparedStatement ps1 = cn.prepareStatement(SQL1);
            ps1.execute();

            return tbProcesso;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconecta(cn);
        }
        return null;

    }

    private TbProcesso consultaSQL(String sql, TbDadosAcessoBanco acesso) {
        //sql+=" ORDER BY Dt_Documento, Nr_Documento DESC ";
        Connection cn = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(), acesso.getNmBanco(), acesso.getNmEsquema(), acesso.getNmUsuario(), acesso.getNmSenha());
        if (cn == null) {
            return null;
        }
        System.out.println(sql);
        try {
            TbProcesso docs = new TbProcesso();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TbProcesso atual = new TbProcesso();
                atual.setNmProcesso(rs.getString("nm_processo"));
                atual.setIdProcesso(rs.getInt("id_processo"));
                atual.setNrAno(rs.getInt("nr_ano"));
                atual.setIdTipoProcesso(rs.getInt("id_tipo_processo"));

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

}
