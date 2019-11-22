/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author janio
 */
@Entity
@Table(name = "tb_importacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbImportacao.findAll", query = "SELECT t FROM TbImportacao t"),
    @NamedQuery(name = "TbImportacao.findByIdImportacao", query = "SELECT t FROM TbImportacao t WHERE t.idImportacao = :idImportacao"),
    @NamedQuery(name = "TbImportacao.findByNrInscricao", query = "SELECT t FROM TbImportacao t WHERE t.nrInscricao = :nrInscricao"),
    @NamedQuery(name = "TbImportacao.findByFlImportado", query = "SELECT t FROM TbImportacao t WHERE t.flImportado = :flImportado")})
public class TbImportacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_importacao",insertable=false)
    private Integer idImportacao;
    @Basic(optional = false)
    @Column(name = "nr_inscricao")
    private int nrInscricao;
    @Basic(optional = false)
    @Column(name = "fl_importado")
    private boolean flImportado;
    @Basic(optional = false)
    @Column(name = "dt_importacao",insertable=false,updatable=false)
    private boolean dtImportacao;
    @Basic(optional = false)
    @Column(name = "nr_questao")
    private Integer nrQuestao;
    @Basic(optional = false)
    @Column(name = "id_disciplina")
    private Integer idDisciplina;
    
    public TbImportacao() {
    }

    public TbImportacao(Integer idImportacao) {
        this.idImportacao = idImportacao;
    }

    public TbImportacao(Integer idImportacao, int nrInscricao, boolean flImportado) {
        this.idImportacao = idImportacao;
        this.nrInscricao = nrInscricao;
        this.flImportado = flImportado;
    }

    public Integer getIdImportacao() {
        return idImportacao;
    }

    public void setIdImportacao(Integer idImportacao) {
        this.idImportacao = idImportacao;
    }

    public int getNrInscricao() {
        return nrInscricao;
    }

    public void setNrInscricao(int nrInscricao) {
        this.nrInscricao = nrInscricao;
    }

    public boolean getFlImportado() {
        return flImportado;
    }

    public void setFlImportado(boolean flImportado) {
        this.flImportado = flImportado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImportacao != null ? idImportacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbImportacao)) {
            return false;
        }
        TbImportacao other = (TbImportacao) object;
        if ((this.idImportacao == null && other.idImportacao != null) || (this.idImportacao != null && !this.idImportacao.equals(other.idImportacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbImportacao[ idImportacao=" + idImportacao + " ]";
    }

    public boolean isDtImportacao() {
        return dtImportacao;
    }

    public void setDtImportacao(boolean dtImportacao) {
        this.dtImportacao = dtImportacao;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public Integer getNrQuestao() {
        return nrQuestao;
    }

    public void setNrQuestao(Integer nrQuestao) {
        this.nrQuestao = nrQuestao;
    }
    
    
}
