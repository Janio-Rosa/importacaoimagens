/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.BasicDAO;
import dao.ConnectionFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TbDadosAcessoBanco;
import model.TbInscricao;
import model.TbResposta;
import model.TbRespostaImagem;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;
import dao.ConnectionFactory;

/**
 *
 * @author administrator
 */
public class RespostaDAO extends BasicDAO{

    

    public long insereRespostpa(TbDadosAcessoBanco acesso, TbResposta resp){
        Connection cn = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(),acesso.getNmBanco(),acesso.getNmEsquema(),acesso.getNmUsuario(),acesso.getNmSenha());
        String sql = "INSERT INTO "+acesso.getNmEsquema()+".tb_RESPOSTA ( id_disciplina, cd_serial, nr_questao, id_processo, fl_discrepancia, fl_discrepancia_corrigida, fl_corrigida,  fl_corrigindo, id_curso, id_inscricao ) VALUES  "
                + "  ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) RETURNING id_resposta ";
        try {
            if( resp.getIdCurso()!=null && !resp.getCdSerial().equalsIgnoreCase("") ){

                PreparedStatement ps = cn.prepareStatement(sql);
                
                ps.setInt(1, resp.getIdDisciplina());
                ps.setString(2, resp.getCdSerial() );
                ps.setInt(3, resp.getNrQuestao() );
                ps.setInt(4, resp.getIdProcesso().getIdProcesso());
                ps.setBoolean(5,  false);
                ps.setBoolean(6,  false);
                ps.setBoolean(7,  false);
                ps.setBoolean(8,  false);
                ps.setInt(9, resp.getIdCurso());
                ps.setLong(10, resp.getIdInscricao() );
                
                long id=0;
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                    id=rs.getInt("id_resposta");                
                
                ps.close();
                return id;
            }else{
                return 0;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            this.desconecta(cn);
            return 0;
        }finally{
            this.desconecta(cn);
        }
    }

    public long insereCandidatoInscricao(TbDadosAcessoBanco acesso, TbInscricao inscricao){
        Connection cn = ConnectionFactory.getConnecionSistemas( acesso.getNmHost(), acesso.getNmBanco(),acesso.getNmEsquema(),acesso.getNmUsuario(),acesso.getNmSenha());
        String sql = "INSERT INTO "+acesso.getNmEsquema()+".tb_inscricao ( nr_inscricao, nm_arquivo, cd_serial, nr_cpf ) VALUES  "
                + "  ( ?, ?, ? , ?) RETURNING id_inscricao ";
        try {
            if( inscricao.getNrInscricao()!=null && inscricao.getNrInscricao().intValue() !=0 ){

                PreparedStatement ps = cn.prepareStatement(sql);

                ps.setInt(1, inscricao.getNrInscricao() );
                ps.setString(2, inscricao.getNmArquivo() );
                ps.setString(3, inscricao.getCdSerial() );
                ps.setString(4, inscricao.getNrCpf() );

                System.out.println("Inserindo resposta: "+inscricao.getNrInscricao() );
                
                //ps.execute();
                long id=0;
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                    id=rs.getInt("id_inscricao");                
                ps.close();
                this.desconecta(cn);
                return id;
            }else{
                return 0;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            this.desconecta(cn);
            return 0;
        }finally{
            this.desconecta(cn);
        }
    }

    public long insereImagemOLD(TbDadosAcessoBanco acesso, TbRespostaImagem resp,InputStream imagemResposta ,long tamanho){
        Connection cn = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(),acesso.getNmBanco(),acesso.getNmEsquema(),acesso.getNmUsuario(),acesso.getNmSenha());
        String sql = "INSERT INTO "+acesso.getNmEsquema()+".tb_RESPOSTA_IMAGEM ( id_resposta, im_imagem_resposta ) VALUES  "
                + "  ( ?, ? ) returning id_resposta_imagem ";
        try {
            if( resp.getIdResposta()!=0  ){

                PreparedStatement ps = cn.prepareStatement(sql);

                ps.setLong(1, resp.getIdResposta() );
                ps.setBinaryStream(2, imagemResposta, tamanho);

                long id=0;
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                    id=rs.getInt("id_inscricao");                
                
                ps.close();
                return id;
            }else{
                return 0;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            this.desconecta(cn);
            return 0;
        }catch (Exception ex){
            ex.printStackTrace();
            this.desconecta(cn);
            return 0;
        }finally{
            this.desconecta(cn);            
        }
    }

    public long insereImagem(TbDadosAcessoBanco acesso, TbRespostaImagem resp,InputStream imagemResposta ,long tamanho,long nrQuestao){
        Connection cn = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(),acesso.getNmBanco(),acesso.getNmEsquema(),acesso.getNmUsuario(),acesso.getNmSenha());
        
        
        String sql = "INSERT INTO "+acesso.getNmEsquema()+".tb_RESPOSTA_IMAGEM ( id_resposta, im_imagem_resposta, nr_ordem ) VALUES  "
                + "  ( ?, ?, ? ) returning id_resposta_imagem ";
        try {
            cn.setAutoCommit(false);
            LargeObjectManager lom = ((org.postgresql.PGConnection)cn).getLargeObjectAPI();
            
            long oid = lom.createLO(LargeObjectManager.READ | LargeObjectManager.WRITE);
            
            LargeObject lo = lom.open(oid, LargeObjectManager.WRITE);
            
                    
            byte buf[]=new byte[2048];
            int s=0;
            while( (s = imagemResposta.read(buf,0,2048)) > 0 ){
                lo.write(buf,0, s);
                //t1+=s;
            }
            
            if( resp.getIdResposta()!=0  ){

                PreparedStatement ps = cn.prepareStatement(sql);

                ps.setLong(1, resp.getIdResposta() );
                ps.setLong(2, oid);
                ps.setLong(3, nrQuestao);

                long id=0;
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                    id=rs.getInt("id_resposta_imagem");                
                imagemResposta.close();
                ps.close();
                cn.commit();
                return id;
            }else{
                return 0;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            try { cn.rollback(); } catch (SQLException ex1) { }
            this.desconecta(cn);
            return 0;
        }catch (Exception ex){
            ex.printStackTrace();
            this.desconecta(cn);
            return 0;
        }finally{
            this.desconecta(cn);            
        }
    }

    public long insereImagem(TbDadosAcessoBanco acesso, TbRespostaImagem resp,InputStream imagemResposta ,long tamanho){
        return this.insereImagem(acesso, resp, imagemResposta, tamanho,1);
    }
    
    public long consultaInscricao(TbDadosAcessoBanco acesso, TbInscricao inscricao,int cdDisciplina , int numQuestao){
        Connection cn = ConnectionFactory.getConnecionSistemas( acesso.getNmHost(), acesso.getNmBanco(),acesso.getNmEsquema(),acesso.getNmUsuario(),acesso.getNmSenha());
        String sql = "select A.*, B.id_resposta from "+acesso.getNmEsquema()+".tb_inscricao A inner join "+acesso.getNmEsquema()+".tb_resposta B ON A.id_inscricao = B.id_inscricao  "
                + "where A.nr_inscricao = " + inscricao.getNrInscricao()
                + " and B.id_disciplina = " + cdDisciplina
                + " and B.nr_questao = " + numQuestao;

        try {
            if( inscricao.getNrInscricao()!=null && inscricao.getNrInscricao().intValue() !=0 ){

                PreparedStatement ps = cn.prepareStatement(sql);

                System.out.println("Consultando inscricao: "+inscricao.getNrInscricao() );
                
                //ps.execute();
                long id=0;
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    id=rs.getInt("id_resposta");
                    System.out.println("JA EXISTENTE - inscrição: "+inscricao.getNrInscricao() );
                }else{
                    System.out.println("NÃO EXISTENTE - inscrição: "+inscricao.getNrInscricao() );
                }
                
                ps.close();
                this.desconecta(cn);
                return id;
            }else{
                return 0;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            this.desconecta(cn);
            return 0;
        }finally{
            this.desconecta(cn);
        }
    }
    
    public long consultaInscricaoDocente(TbDadosAcessoBanco acesso, TbInscricao inscricao,int cdDisciplina ){
        Connection cn = ConnectionFactory.getConnecionSistemas( acesso.getNmHost(), acesso.getNmBanco(),acesso.getNmEsquema(),acesso.getNmUsuario(),acesso.getNmSenha());
        String sql = "select A.*, B.id_resposta from "+acesso.getNmEsquema()+".tb_inscricao A inner join "+acesso.getNmEsquema()+".tb_resposta B ON A.id_inscricao = B.id_inscricao  "
                + "where B.nr_questao=1 AND A.nr_inscricao = " + inscricao.getNrInscricao()
                + " and B.id_disciplina = " + cdDisciplina;

        try {
            if( inscricao.getNrInscricao()!=null && inscricao.getNrInscricao().intValue() !=0 ){

                PreparedStatement ps = cn.prepareStatement(sql);

                System.out.println("Consultando inscricao: "+inscricao.getNrInscricao() );
                
                //ps.execute();
                long id=0;
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    id=rs.getInt("id_resposta");
                    System.out.println("JA EXISTENTE - inscrição: "+inscricao.getNrInscricao() );
                }else{
                    System.out.println("NÃO EXISTENTE - inscrição: "+inscricao.getNrInscricao() );
                }
                
                ps.close();
                this.desconecta(cn);
                return id;
            }else{
                return 0;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            this.desconecta(cn);
            return 0;
        }finally{
            this.desconecta(cn);
        }
    }

    public long consultaInscricao(TbDadosAcessoBanco acesso, TbInscricao inscricao,int cdDisciplina ){
        Connection cn = ConnectionFactory.getConnecionSistemas( acesso.getNmHost(), acesso.getNmBanco(),acesso.getNmEsquema(),acesso.getNmUsuario(),acesso.getNmSenha());
        String sql = "select A.*, B.id_resposta from "+acesso.getNmEsquema()+".tb_inscricao A inner join "+acesso.getNmEsquema()+".tb_resposta B ON A.id_inscricao = B.id_inscricao  "
                + "where A.nr_inscricao = " + inscricao.getNrInscricao()
                + " and B.id_disciplina = " + cdDisciplina;

        try {
            if( inscricao.getNrInscricao()!=null && inscricao.getNrInscricao().intValue() !=0 ){

                PreparedStatement ps = cn.prepareStatement(sql);

                System.out.println("Consultando inscricao: "+inscricao.getNrInscricao() );
                
                //ps.execute();
                long id=0;
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    id=rs.getInt("id_resposta");
                    System.out.println("JA EXISTENTE - inscrição: "+inscricao.getNrInscricao() );
                }else{
                    System.out.println("NÃO EXISTENTE - inscrição: "+inscricao.getNrInscricao() );
                }
                
                ps.close();
                this.desconecta(cn);
                return id;
            }else{
                return 0;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            this.desconecta(cn);
            return 0;
        }finally{
            this.desconecta(cn);
        }
    }
    

    public byte[] recuperaImagem(TbDadosAcessoBanco acesso, long idRespostaImagem){
        Connection cn = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(),acesso.getNmBanco(),acesso.getNmEsquema(),acesso.getNmUsuario(),acesso.getNmSenha());
        
        String sql = "SELECT im_imagem_resposta FROM "+acesso.getNmEsquema()+".tb_RESPOSTA_IMAGEM WHERE id_resposta_imagem = ? ";
        byte retorno[]=null;
        
        try {
            cn.setAutoCommit(false);
            
            if( idRespostaImagem > 0  ){

                PreparedStatement ps = cn.prepareStatement(sql);

                ps.setLong(1, idRespostaImagem );

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    int oid=rs.getInt(1);
                    
                    LargeObjectManager lom = ((org.postgresql.PGConnection)cn).getLargeObjectAPI();
                    LargeObject imagem = lom.open(oid, LargeObjectManager.READ);

                    retorno = new byte[imagem.size()];
                    imagem.read(retorno,0,imagem.size());

                    imagem.close();
                }

                rs.close();
                ps.close();
                cn.commit();
                return retorno;
            }else{
                return retorno;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            try { cn.rollback(); } catch (SQLException ex1) { }
            this.desconecta(cn);
            return retorno;
        }catch (Exception ex){
            ex.printStackTrace();
            this.desconecta(cn);
            return retorno;
        }finally{
            this.desconecta(cn);            
        }
    }

    public long atualizaImagem(TbDadosAcessoBanco acesso, TbRespostaImagem resp,InputStream imagemResposta ,long tamanho){
        Connection cn = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(),acesso.getNmBanco(),acesso.getNmEsquema(),acesso.getNmUsuario(),acesso.getNmSenha());
        
        
        String sql = "update "+acesso.getNmEsquema()+".tb_RESPOSTA_IMAGEM set im_imagem_resposta = ? WHERE "
                + "  id_resposta = ? ";
        try {
            cn.setAutoCommit(false);
            LargeObjectManager lom = ((org.postgresql.PGConnection)cn).getLargeObjectAPI();
            
            long oid = lom.createLO(LargeObjectManager.READ | LargeObjectManager.WRITE);
            
            LargeObject lo = lom.open(oid, LargeObjectManager.WRITE);
            
                    
            byte buf[]=new byte[2048];
            int s=0;
            while( (s = imagemResposta.read(buf,0,2048)) > 0 ){
                lo.write(buf,0, s);
                //t1+=s;
            }
            
            if( resp.getIdResposta()!=0  ){

                PreparedStatement ps = cn.prepareStatement(sql);

                ps.setLong(2, resp.getIdResposta() );
                ps.setLong(1, oid);

                long id=0;
                ps.execute();
                imagemResposta.close();
                ps.close();
                cn.commit();
                return resp.getIdResposta();
            }else{
                return 0;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            try { cn.rollback(); } catch (SQLException ex1) { }
            this.desconecta(cn);
            return 0;
        }catch (Exception ex){
            ex.printStackTrace();
            this.desconecta(cn);
            return 0;
        }finally{
            this.desconecta(cn);            
        }
    }

    
    public long insereImagemPorQuestao(TbDadosAcessoBanco acesso, TbRespostaImagem resp,InputStream imagemResposta ,int nrQuestao){
        Connection cn = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(),acesso.getNmBanco(),acesso.getNmEsquema(),acesso.getNmUsuario(),acesso.getNmSenha());
        
        
        String sql = "INSERT INTO "+acesso.getNmEsquema()+".tb_RESPOSTA_IMAGEM ( id_resposta, im_imagem_resposta, nr_ordem ) VALUES  "
                + "  ( ?, ?, ? ) returning id_resposta_imagem ";
        try {
            cn.setAutoCommit(false);
            LargeObjectManager lom = ((org.postgresql.PGConnection)cn).getLargeObjectAPI();
            
            long oid = lom.createLO(LargeObjectManager.READ | LargeObjectManager.WRITE);
            
            LargeObject lo = lom.open(oid, LargeObjectManager.WRITE);
            
                    
            byte buf[]=new byte[2048];
            int s=0;
            while( (s = imagemResposta.read(buf,0,2048)) > 0 ){
                lo.write(buf,0, s);
                //t1+=s;
            }
            
            if( resp.getIdResposta()!=0  ){

                PreparedStatement ps = cn.prepareStatement(sql);

                ps.setLong(1, resp.getIdResposta() );
                ps.setLong(2, oid);
                ps.setLong(3, nrQuestao);

                long id=0;
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                    id=rs.getInt("id_resposta_imagem");                
                imagemResposta.close();
                ps.close();
                cn.commit();
                return id;
            }else{
                return 0;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            try { cn.rollback(); } catch (SQLException ex1) { }
            this.desconecta(cn);
            return 0;
        }catch (Exception ex){
            ex.printStackTrace();
            this.desconecta(cn);
            return 0;
        }finally{
            this.desconecta(cn);            
        }
    }
    
    public long insereRespostaDocente(TbDadosAcessoBanco acesso, TbResposta resp){
        Connection cn = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(),acesso.getNmBanco(),acesso.getNmEsquema(),acesso.getNmUsuario(),acesso.getNmSenha());
        String sql = "INSERT INTO "+acesso.getNmEsquema()+".tb_RESPOSTA ( id_disciplina, cd_serial, nr_questao, id_processo, fl_discrepancia, fl_discrepancia_corrigida, fl_corrigida,  fl_corrigindo, id_curso, id_inscricao ) VALUES  "
                + "  ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) RETURNING id_resposta ";
        try {
            if( resp.getIdCurso()!=null && !resp.getCdSerial().equalsIgnoreCase("") ){

                PreparedStatement ps = cn.prepareStatement(sql);
                
                ps.setInt(1, resp.getIdDisciplina());
                ps.setString(2, resp.getCdSerial() );
                ps.setInt(3, resp.getNrQuestao() );
                ps.setInt(4, resp.getIdProcesso().getIdProcesso());
                ps.setBoolean(5,  false);
                ps.setBoolean(6,  false);
                ps.setBoolean(7,  false);
                ps.setBoolean(8,  false);
                ps.setInt(9, resp.getIdCurso());
                ps.setLong(10, resp.getIdInscricao() );
                
                long id=0;
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                    id=rs.getInt("id_resposta");                
                
                ps.close();
                return id;
            }else{
                return 0;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            this.desconecta(cn);
            return 0;
        }finally{
            this.desconecta(cn);
        }
    }
    
}
