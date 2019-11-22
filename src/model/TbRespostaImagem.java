/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author administrator
 */
@Entity
@Table(name = "tb_resposta_imagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbRespostaImagem.findAll", query = "SELECT t FROM TbRespostaImagem t"),
    @NamedQuery(name = "TbRespostaImagem.findByIdRespostaImagem", query = "SELECT t FROM TbRespostaImagem t WHERE t.idRespostaImagem = :idRespostaImagem"),
    @NamedQuery(name = "TbRespostaImagem.findByDtImportacao", query = "SELECT t FROM TbRespostaImagem t WHERE t.dtImportacao = :dtImportacao"),
    @NamedQuery(name = "TbRespostaImagem.findByImImagemResposta", query = "SELECT t FROM TbRespostaImagem t WHERE t.imImagemResposta = :imImagemResposta")})
public class TbRespostaImagem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_resposta_imagem")
    private Long idRespostaImagem;
    @Basic(optional = false)
    @Column(name = "dt_importacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtImportacao;
    @Column(name = "im_imagem_resposta")
    private long imImagemResposta;
    @Column(name = "id_resposta")
    private long idResposta;

    public TbRespostaImagem() {
    }

    public TbRespostaImagem(Long idRespostaImagem) {
        this.idRespostaImagem = idRespostaImagem;
    }

    public TbRespostaImagem(Long idRespostaImagem, Date dtImportacao) {
        this.idRespostaImagem = idRespostaImagem;
        this.dtImportacao = dtImportacao;
    }

    public Long getIdRespostaImagem() {
        return idRespostaImagem;
    }

    public void setIdRespostaImagem(Long idRespostaImagem) {
        this.idRespostaImagem = idRespostaImagem;
    }

    public Date getDtImportacao() {
        return dtImportacao;
    }

    public void setDtImportacao(Date dtImportacao) {
        this.dtImportacao = dtImportacao;
    }

    public long getImImagemResposta() {
        return imImagemResposta;
    }

    public void setImImagemResposta(long imImagemResposta) {
        this.imImagemResposta = imImagemResposta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespostaImagem != null ? idRespostaImagem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbRespostaImagem)) {
            return false;
        }
        TbRespostaImagem other = (TbRespostaImagem) object;
        if ((this.idRespostaImagem == null && other.idRespostaImagem != null) || (this.idRespostaImagem != null && !this.idRespostaImagem.equals(other.idRespostaImagem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbRespostaImagem[ idRespostaImagem=" + idRespostaImagem + " ]";
    }

    public long getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(long idResposta) {
        this.idResposta = idResposta;
    }
    
    
    
}
