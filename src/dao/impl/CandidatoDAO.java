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
 * @author janio
 */
public class CandidatoDAO extends BasicDAO {

    public TbCandidato pesquisar(TbCandidato filtro,TbDadosAcessoBanco acesso) {
        String sql = "SELECT  *  FROM tb_CANDIDATO WHERE 1 = 1 ";
        //Filtros
        if (filtro != null){
            if( filtro.getNrCpf()!=null){
                sql+=" AND nr_cpf = '"+filtro.getNrCpf().replace("'", "") +"'";
            }
            if( filtro.getNrInscricao()!=null &&  !filtro.getNrInscricao().equalsIgnoreCase("")){
                sql+=" AND nr_inscricao = '"+filtro.getNrInscricao()+"'";
                //sql+=" AND nr_inscricao_alfa = '"+filtro.getNrInscricao()+"'";
            }
            if( filtro.getNmArquivo()!=null && filtro.getNmArquivo().compareTo("")!=0){
                sql+=" AND nm_arquivo like '%"+filtro.getNmArquivo()+"%'";
            }
        }
        return this.consultaSQL(sql,acesso);
    }

    private TbCandidato consultaSQL(String sql,TbDadosAcessoBanco acesso){
        //sql+=" ORDER BY Dt_Documento, Nr_Documento DESC ";
        Connection cn = ConnectionFactory.getConnecionSistemas( acesso.getNmHost(), acesso.getNmBanco(),acesso.getNmEsquema(),acesso.getNmUsuario(),acesso.getNmSenha());
        if(cn==null)return null;
        System.out.println(sql);
        try{
            TbCandidato docs = new TbCandidato();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                TbCandidato atual = new TbCandidato();
                
                atual.setIdCandidato(rs.getLong("id_candidato"));

                atual.setIdProcesso(null);

                TbProcesso proc = new TbProcesso();
                proc.setIdProcesso(rs.getInt("id_processo"));
                
                atual.setIdProcesso(proc);

                
                atual.setDtInsercao(rs.getDate("dt_insercao"));
                atual.setNrCpf(rs.getString("nr_cpf"));
                atual.setNrInscricao(rs.getString("nr_inscricao"));
                atual.setNrAnoProcesso(rs.getInt("nr_ano_processo"));
                atual.setNrPeriodoProcesso(rs.getInt("nr_periodo_processo"));
                atual.setTpCondicao(rs.getString("tp_condicao"));
                //atual.setIdCurso(847);
                atual.setIdCurso(rs.getInt("id_curso"));
                atual.setIdLinguaEstrangeira(rs.getInt("id_lingua_estrangeira"));
                atual.setFlHabilidade(rs.getBoolean("fl_habilidade"));
                atual.setNrIpCadastro(rs.getString("nr_ip_cadastro"));
                atual.setDtCadastro(rs.getDate("dt_cadastro"));
                atual.setCdLocalProva(rs.getInt("cd_local_prova"));
                atual.setNmArquivo(rs.getString("nm_arquivo"));
                atual.setFlBoleto(rs.getBoolean("fl_boleto"));
                atual.setFlIsencao(rs.getBoolean("fl_isencao"));
                atual.setVlInscricao(rs.getDouble("vl_inscricao"));
                atual.setFlHabilitado(rs.getBoolean("fl_habilitado"));
                atual.setFlHabilitadoOutroProcesso(rs.getBoolean("fl_habilitado_outro_processo"));
                atual.setNmProcessoHabilitado(rs.getString("nm_processo_habilitado"));
                atual.setCdConclusaoEnsinoMedio(rs.getString("cd_conclusao_ensino_medio"));
                
                //Consulta PESSOA
                atual.setIdPessoa(null);
                
                atual.setIdDeficienciaFisica(rs.getInt("id_deficiencia_fisica"));
                atual.setIdAtendimentoEspecial(rs.getInt("id_atendimento_especial"));
                atual.setIdPerfilSaude(rs.getInt("id_perfil_saude"));
                atual.setDsDeficienciaFisicaOutros(rs.getString("ds_deficiencia_fisica_outros"));
                atual.setDsAtendimentoEspecialOutros(rs.getString("ds_atendimento_especial_outros"));
                atual.setDsPerfilSaudeOutros(rs.getString("ds_perfil_saude_outros"));
                atual.setNmProcessoOutraInstituicao(rs.getString("nm_processo_outra_instituicao"));
                atual.setNmInstrumento(rs.getString("nm_instrumento"));
                
                //atual.setTbSetor();
                
                docs = atual;
            }
            this.desconecta(cn);
            return docs;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }finally{
            this.desconecta(cn);
        }

    }
    
}
