/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Thiago
 */
@Entity
@Table(name = "tb_tipo_processo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbTipoProcesso.findAll", query = "SELECT t FROM TbTipoProcesso t"),
    @NamedQuery(name = "TbTipoProcesso.findByIdTipo", query = "SELECT t FROM TbTipoProcesso t WHERE t.idTipo = :idTipo"),
    @NamedQuery(name = "TbTipoProcesso.findByNmTipo", query = "SELECT t FROM TbTipoProcesso t WHERE t.nmTipo = :nmTipo")})
public class TbTipoProcesso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo")
    private Integer idTipo;
    @Basic(optional = false)
    @Column(name = "nm_tipo")
    private String nmTipo;
    @OneToMany(mappedBy = "idTipoProcesso")
    private Collection<TbProcesso> tbProcessoCollection;

    public TbTipoProcesso() {
    }

    public TbTipoProcesso(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public TbTipoProcesso(Integer idTipo, String nmTipo) {
        this.idTipo = idTipo;
        this.nmTipo = nmTipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNmTipo() {
        return nmTipo;
    }

    public void setNmTipo(String nmTipo) {
        this.nmTipo = nmTipo;
    }

    @XmlTransient
    public Collection<TbProcesso> getTbProcessoCollection() {
        return tbProcessoCollection;
    }

    public void setTbProcessoCollection(Collection<TbProcesso> tbProcessoCollection) {
        this.tbProcessoCollection = tbProcessoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbTipoProcesso)) {
            return false;
        }
        TbTipoProcesso other = (TbTipoProcesso) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbTipoProcesso[ idTipo=" + idTipo + " ]";
    }
    
}
