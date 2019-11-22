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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author janio
 */
@Entity
@Table(name = "tb_disciplina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbDisciplina.findAll", query = "SELECT t FROM TbDisciplina t"),
    @NamedQuery(name = "TbDisciplina.findByIdDisciplina", query = "SELECT t FROM TbDisciplina t WHERE t.idDisciplina = :idDisciplina"),
    @NamedQuery(name = "TbDisciplina.findByNmDisciplina", query = "SELECT t FROM TbDisciplina t WHERE t.nmDisciplina = :nmDisciplina"),
    @NamedQuery(name = "TbDisciplina.findByNrNotaInicial", query = "SELECT t FROM TbDisciplina t WHERE t.nrNotaInicial = :nrNotaInicial")})
public class TbDisciplina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_disciplina")
    private Integer idDisciplina;
    @Basic(optional = false)
    @Column(name = "nm_disciplina")
    private String nmDisciplina;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nr_nota_inicial")
    private Double nrNotaInicial;

    @JoinColumn(name = "id_tipo_questao", referencedColumnName = "id_tipo_questao")
    @ManyToOne
    private Integer idTipoQuestao;

    public TbDisciplina() {
    }

    public TbDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public TbDisciplina(Integer idDisciplina, String nmDisciplina) {
        this.idDisciplina = idDisciplina;
        this.nmDisciplina = nmDisciplina;
    }

    public TbDisciplina(Integer idDisciplina, String nmDisciplina, Double nrNotaInicial, int idTipoQuestao) {
        this.idDisciplina = idDisciplina;
        this.nmDisciplina = nmDisciplina;
        this.nrNotaInicial = nrNotaInicial;
        this.idTipoQuestao = idTipoQuestao;
    }

    public Integer getIdTipoQuestao() {
        return idTipoQuestao;
    }

    public void setIdTipoQuestao(Integer idTipoQuestao) {
        this.idTipoQuestao = idTipoQuestao;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNmDisciplina() {
        return nmDisciplina;
    }

    public void setNmDisciplina(String nmDisciplina) {
        this.nmDisciplina = nmDisciplina;
    }

    public Double getNrNotaInicial() {
        return nrNotaInicial;
    }

    public void setNrNotaInicial(Double nrNotaInicial) {
        this.nrNotaInicial = nrNotaInicial;
    }

    /*
     public TbTipoQuestao getIdTipoQuestao() {
     return idTipoQuestao;
     }

     public void setIdTipoQuestao(TbTipoQuestao idTipoQuestao) {
     this.idTipoQuestao = idTipoQuestao;
     }
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDisciplina != null ? idDisciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbDisciplina)) {
            return false;
        }
        TbDisciplina other = (TbDisciplina) object;
        if ((this.idDisciplina == null && other.idDisciplina != null) || (this.idDisciplina != null && !this.idDisciplina.equals(other.idDisciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbDisciplina[ idDisciplina=" + idDisciplina + " ]";
    }

}
