/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thiago
 */
@Entity
@Table(name = "tb_processo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbProcesso.findAll", query = "SELECT t FROM TbProcesso t"),
    @NamedQuery(name = "TbProcesso.findByIdProcesso", query = "SELECT t FROM TbProcesso t WHERE t.idProcesso = :idProcesso"),
    @NamedQuery(name = "TbProcesso.findByNmProcesso", query = "SELECT t FROM TbProcesso t WHERE t.nmProcesso = :nmProcesso"),
    @NamedQuery(name = "TbProcesso.findByDtValidadeFinal", query = "SELECT t FROM TbProcesso t WHERE t.dtValidadeFinal = :dtValidadeFinal"),
    @NamedQuery(name = "TbProcesso.findByDtValidadeInicial", query = "SELECT t FROM TbProcesso t WHERE t.dtValidadeInicial = :dtValidadeInicial"),
    @NamedQuery(name = "TbProcesso.findByFlAtivo", query = "SELECT t FROM TbProcesso t WHERE t.flAtivo = :flAtivo"),
    @NamedQuery(name = "TbProcesso.findByNrAno", query = "SELECT t FROM TbProcesso t WHERE t.nrAno = :nrAno")})
public class TbProcesso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_processo")
    private Integer idProcesso;
    @Basic(optional = false)
    @Column(name = "nm_processo")
    private String nmProcesso;
    @Column(name = "dt_validade_final")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtValidadeFinal;
    @Column(name = "dt_validade_inicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtValidadeInicial;
    @Column(name = "fl_ativo")
    private Boolean flAtivo;
    @Column(name = "nr_ano")
    private Integer nrAno;
    @JoinColumn(name = "id_tipo_processo", referencedColumnName = "id_tipo")
    @ManyToOne
    private Integer idTipoProcesso;

    public TbProcesso() {
    }

    public TbProcesso(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }

    public TbProcesso(Integer idProcesso, String nmProcesso) {
        this.idProcesso = idProcesso;
        this.nmProcesso = nmProcesso;
    }

    public TbProcesso(Integer idProcesso, String nmProcesso, Integer nrAno, Integer idTipoProcesso) {
        this.idProcesso = idProcesso;
        this.nmProcesso = nmProcesso;
        this.nrAno = nrAno;
        this.idTipoProcesso = idTipoProcesso;
    }
    

    public Integer getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getNmProcesso() {
        return nmProcesso;
    }

    public void setNmProcesso(String nmProcesso) {
        this.nmProcesso = nmProcesso;
    }

    public Date getDtValidadeFinal() {
        return dtValidadeFinal;
    }

    public void setDtValidadeFinal(Date dtValidadeFinal) {
        this.dtValidadeFinal = dtValidadeFinal;
    }

    public Date getDtValidadeInicial() {
        return dtValidadeInicial;
    }

    public void setDtValidadeInicial(Date dtValidadeInicial) {
        this.dtValidadeInicial = dtValidadeInicial;
    }

    public Boolean getFlAtivo() {
        return flAtivo;
    }

    public void setFlAtivo(Boolean flAtivo) {
        this.flAtivo = flAtivo;
    }

    public Integer getNrAno() {
        return nrAno;
    }

    public void setNrAno(Integer nrAno) {
        this.nrAno = nrAno;
    }

    public Integer getIdTipoProcesso() {
        return idTipoProcesso;
    }

    public void setIdTipoProcesso(Integer idTipoProcesso) {
        this.idTipoProcesso = idTipoProcesso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcesso != null ? idProcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbProcesso)) {
            return false;
        }
        TbProcesso other = (TbProcesso) object;
        if ((this.idProcesso == null && other.idProcesso != null) || (this.idProcesso != null && !this.idProcesso.equals(other.idProcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbProcesso[ idProcesso=" + idProcesso + " ]";
    }
    
}
