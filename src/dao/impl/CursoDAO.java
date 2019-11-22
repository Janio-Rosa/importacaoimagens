/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.BasicDAO;
import model.TbCurso;
import model.TbDadosAcessoBanco;
import dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author janio
 */
public class CursoDAO extends BasicDAO {

    public TbCurso pesquisar(TbCurso filtro, TbDadosAcessoBanco acesso) {
        // String sql = "SELECT  *  FROM tb_CANDIDATO WHERE 1 = 1 ";
        String sql = "SELECT  *  FROM tb_curso WHERE 1 = 1 ";
        //Filtros
        if (filtro != null) {
            if (filtro.getIdCurso() != null) {
                sql += " AND id_curso = '" + filtro.getIdCurso() + "'";
            }
            /*        if( !filtro.getNmCurso().trim().equalsIgnoreCase("")){
             sql+=" AND nm_curso ilike '%"+filtro.getNmCurso()+"%'";

             }*/
        }
        return this.consultaSQL(sql, acesso);
    }

    public TbCurso inserir(TbCurso filtro, TbDadosAcessoBanco acessoBancoOrigem, TbDadosAcessoBanco acesso) {

        try {
            System.out.println("Entrou em CursoDAO - inserir");
            Connection cnOrigem = ConnectionFactory.getConnecionSistemas(acessoBancoOrigem.getNmHost(), acessoBancoOrigem.getNmBanco(), acessoBancoOrigem.getNmEsquema(), acessoBancoOrigem.getNmUsuario(), acessoBancoOrigem.getNmSenha());
            Connection cnDestino = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(), acesso.getNmBanco(), acesso.getNmEsquema(), acesso.getNmUsuario(), acesso.getNmSenha());

            String sql_consulta = "SELECT id_curso, nm_curso_sie, cd_curso_sie, nm_curso_ps, cd_curso_ps, id_curso_ps, nm_turno, id_campus, cd_curso_sisu, "
                    + "cd_curso_matricula, fl_habilidade, fl_reuni, "
                    + "nm_grau, id_nivel, fl_teste, "
                    + "nm_abreviado, id_area_cnpq, fl_instrumento, id_area, nm_modalidade, fl_ingresso_anual FROM tb_curso WHERE id_curso = " + filtro.getIdCurso() + " ";
                System.out.println("SQL consulta = " + sql_consulta);            
            
            TbCurso curso = new TbCurso();
            PreparedStatement ps = cnOrigem.prepareStatement(sql_consulta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            //    TbCurso atual = new TbCurso();

                curso.setIdCurso(rs.getInt("id_curso"));
                curso.setNmCursoSie(rs.getString("nm_curso_sie"));                
                curso.setCdCursoSie(rs.getString("cd_curso_sie"));
                curso.setNmCursoPs(rs.getString("nm_curso_ps"));
                curso.setCdCursoPs(rs.getInt("cd_curso_ps")); 
                curso.setIdCursoPs(rs.getInt("id_curso_ps"));
                curso.setNmTurno(rs.getString("nm_turno"));
                curso.setIdCampus(rs.getInt("id_campus"));
                curso.setCdCursoSisu(rs.getString("cd_curso_sisu"));
                curso.setCdCursoMatricula(rs.getString("cd_curso_matricula"));
                curso.setFlHabilidade(rs.getBoolean("fl_habilidade"));
                curso.setFlReuni(rs.getBoolean("fl_reuni"));
                curso.setNmGrau(rs.getString("nm_grau"));
                curso.setIdNivel(rs.getInt("id_nivel"));
                curso.setFlTeste(rs.getBoolean("fl_teste"));
                curso.setNmAbreviado(rs.getString("nm_abreviado"));
                curso.setIdAreaCnpq(rs.getShort("id_area_cnpq"));
                curso.setFlInstrumento(rs.getBoolean("fl_instrumento"));
                curso.setIdArea(rs.getInt("id_area"));
                curso.setNmModalidade(rs.getString("nm_modalidade"));
                curso.setFlIngressoAnual(rs.getBoolean("fl_ingresso_anual"));
                
                String cd_curso_mat = curso.getCdCursoMatricula();
                if (cd_curso_mat == null){
                cd_curso_mat = "";
            }
                        
              //  docs = atual;
                System.out.println("!!!!!!!!! = "+ curso.getCdCursoPs());
                String sql_insert = "INSERT INTO tb_curso(id_curso, nm_curso, nm_curso_sie, cd_curso_sie, nm_curso_ps, cd_curso_ps, id_curso_ps, nm_turno, id_campus, "
                +" cd_curso_sisu, cd_curso_matricula, fl_habilidade, fl_reuni, nm_grau, id_nivel, fl_teste, nm_abreviado, id_area_cnpq, "
                +" fl_instrumento, id_area, nm_modalidade, fl_ingresso_anual) VALUES " + " ("+ curso.getIdCurso()+",'"+curso.getNmCursoPs()+"','"+curso.getNmCursoSie()+"','"+
                        curso.getCdCursoSie()+"','"+curso.getNmCursoPs()+"',"+curso.getCdCursoPs()+","+curso.getIdCursoPs()+",'"+curso.getNmTurno()+"',"+
                        curso.getIdCampus()+",'"+curso.getCdCursoSisu()+"','"+cd_curso_mat+"','"+curso.getFlHabilidade()+"','"+curso.getFlReuni()+"','"+
                        curso.getNmGrau()+"',"+curso.getIdNivel()+",'"+curso.getFlTeste()+"','"+curso.getNmAbreviado()+"',"+curso.getIdAreaCnpq()+",'"+
                        curso.getFlInstrumento()+"',"+curso.getIdArea()+",'"+curso.getNmModalidade()+"','"+curso.getFlIngressoAnual()+"') RETURNING id_curso ";
                  System.out.println("SQL insert = " + sql_insert);
                
            PreparedStatement ps1 = cnDestino.prepareStatement(sql_insert);
            ps1.execute();
                
                this.desconecta(cnOrigem);
                this.desconecta(cnDestino);
              //JOptionPane.showMessageDialog(null, "Curso Cadastrado!");
                return null;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

      
    }

    

    private TbCurso consultaSQL(String sql, TbDadosAcessoBanco acesso) {
        //sql+=" ORDER BY Dt_Documento, Nr_Documento DESC ";
        Connection cn = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(), acesso.getNmBanco(), acesso.getNmEsquema(), acesso.getNmUsuario(), acesso.getNmSenha());
        if (cn == null) {
            return null;
        }
        System.out.println("SQL = " + sql);
        try {
            TbCurso docs = new TbCurso();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TbCurso atual = new TbCurso();

                atual.setIdCurso(rs.getInt("id_curso"));

                //atual.setNmCurso(rs.getString("nm_curso"));
                atual.setNmCurso(rs.getString("nm_curso_sie"));
                //atual.setCdCurso(rs.getInt("cd_curso"));
                atual.setCdCurso(rs.getInt("cd_curso_ps"));
                atual.setNmCursoPs(rs.getString("nm_curso_ps"));
                atual.setNmCursoSie(rs.getString("nm_curso_sie"));
                atual.setIdArea(rs.getInt("id_area"));

                try {
                    atual.setIdCampus(rs.getInt("id_campus"));
                } catch (Exception ex) {
                }
                try {
                    atual.setIdAreaCnpq(rs.getShort("id_area_cnpq"));
                } catch (Exception ex) {
                }
                try {
                    atual.setIdCidade(rs.getInt("id_cidade"));
                } catch (Exception ex) {
                }

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
