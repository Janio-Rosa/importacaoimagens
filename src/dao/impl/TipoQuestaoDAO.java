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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import javax.swing.JComboBox;
import model.TbDadosAcessoBanco;
import model.TbDisciplina;
import model.TbTipoQuestao;

/**
 *
 * @author janio
 */
public class TipoQuestaoDAO extends BasicDAO {

    public HashMap<Integer, String> pesquisar(TbDadosAcessoBanco acesso) {
        //public HashMap<Integer, String> pesquisar(TbDadosAcessoBanco acesso, JComboBox jComboBox1) {
        String sql = "SELECT  *  FROM tb_TIPO_QUESTAO WHERE 1 = 1 ";

        //return this.consultaSQL(sql, acesso, jComboBox1);
        return this.consultaSQL(sql, acesso);
    }

    //private HashMap<Integer, String> consultaSQL(String sql, TbDadosAcessoBanco acesso, JComboBox jComboBox1) {
  

    private HashMap<Integer, String> consultaSQL(String sql, TbDadosAcessoBanco acesso) {
        Connection cn = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(), acesso.getNmBanco(), acesso.getNmEsquema(), acesso.getNmUsuario(), acesso.getNmSenha());
        if (cn == null) {
            return null;
        }
        System.out.println(sql);
        try {
            TbTipoQuestao docs = new TbTipoQuestao();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Hashtable<Integer, String> source = new Hashtable<Integer, String>();
            HashMap<Integer, String> map = new HashMap(source);
            while (rs.next()) {
                TbTipoQuestao atual = new TbTipoQuestao();
                map.put(rs.getInt("id_tipo_questao"), rs.getString("nm_tipo_questao"));

                //   jComboBox1.addItem(rs.getString("nm_tipo_questao"));
              //  docs = atual;
            }

            Iterator<Integer> keySetIterator = map.keySet().iterator();

        /*    while (keySetIterator.hasNext()) {
                Integer key = keySetIterator.next();
                jComboBox1.addItem(key + " - " + map.get(key));
                System.out.println("key: " + key + " value: " + map.get(key));
            }*/

            //System.out.println("map = " + map);
            this.desconecta(cn);
            return map;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            this.desconecta(cn);
        }

    }
}
