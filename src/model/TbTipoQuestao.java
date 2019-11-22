/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author janio
 */
@Entity
@Table(name = "tb_tipo_questao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbTipoQuestao.findAll", query = "SELECT t FROM TbTipoQuestao t"),
    @NamedQuery(name = "TbTipoQuestao.findByIdTipoQuestao", query = "SELECT t FROM TbTipoQuestao t WHERE t.idTipoQuestao = :idTipoQuestao"),
    @NamedQuery(name = "TbTipoQuestao.findByNmTipoQuestao", query = "SELECT t FROM TbTipoQuestao t WHERE t.nmTipoQuestao = :nmTipoQuestao")})
public class TbTipoQuestao implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo_questao")
    private Integer idTipoQuestao;
    @Basic(optional = false)
    @Column(name = "nm_tipo_questao")
    private String nmTipoQuestao;
    @OneToMany(mappedBy = "idTipoQuestao")
    private List<TbDisciplina> tbDisciplinaList;

    public TbTipoQuestao() {
    }

    public TbTipoQuestao(Integer idTipoQuestao) {
        this.idTipoQuestao = idTipoQuestao;
    }

    public TbTipoQuestao(Integer idTipoQuestao, String nmTipoQuestao) {
        this.idTipoQuestao = idTipoQuestao;
        this.nmTipoQuestao = nmTipoQuestao;
    }

    public Integer getIdTipoQuestao() {
        return idTipoQuestao;
    }

    public void setIdTipoQuestao(Integer idTipoQuestao) {
        Integer oldIdTipoQuestao = this.idTipoQuestao;
        this.idTipoQuestao = idTipoQuestao;
        changeSupport.firePropertyChange("idTipoQuestao", oldIdTipoQuestao, idTipoQuestao);
    }

    public String getNmTipoQuestao() {
        return nmTipoQuestao;
    }

    public void setNmTipoQuestao(String nmTipoQuestao) {
        String oldNmTipoQuestao = this.nmTipoQuestao;
        this.nmTipoQuestao = nmTipoQuestao;
        changeSupport.firePropertyChange("nmTipoQuestao", oldNmTipoQuestao, nmTipoQuestao);
    }

    @XmlTransient
    public List<TbDisciplina> getTbDisciplinaList() {
        return tbDisciplinaList;
    }

    public void setTbDisciplinaList(List<TbDisciplina> tbDisciplinaList) {
        this.tbDisciplinaList = tbDisciplinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoQuestao != null ? idTipoQuestao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbTipoQuestao)) {
            return false;
        }
        TbTipoQuestao other = (TbTipoQuestao) object;
        if ((this.idTipoQuestao == null && other.idTipoQuestao != null) || (this.idTipoQuestao != null && !this.idTipoQuestao.equals(other.idTipoQuestao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbTipoQuestao[ idTipoQuestao=" + idTipoQuestao + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
