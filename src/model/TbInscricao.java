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
 * @author administrator
 */
@Entity
@Table(name = "tb_inscricao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbInscricao.findAll", query = "SELECT t FROM TbInscricao t"),
    @NamedQuery(name = "TbInscricao.findByIdInscricao", query = "SELECT t FROM TbInscricao t WHERE t.idInscricao = :idInscricao"),
    @NamedQuery(name = "TbInscricao.findByNrInscricao", query = "SELECT t FROM TbInscricao t WHERE t.nrInscricao = :nrInscricao"),
    @NamedQuery(name = "TbInscricao.findByNmArquivo", query = "SELECT t FROM TbInscricao t WHERE t.nmArquivo = :nmArquivo"),
    @NamedQuery(name = "TbInscricao.findByCdSerial", query = "SELECT t FROM TbInscricao t WHERE t.cdSerial = :cdSerial")})
public class TbInscricao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_inscricao")
    private Long idInscricao;
    @Column(name = "nr_inscricao")
    private Integer nrInscricao;
    @Column(name = "nm_arquivo")
    private String nmArquivo;
    @Column(name = "cd_serial")
    private String cdSerial;

    @Column(name = "nr_cpf")
    private String nrCpf;
    
    public TbInscricao() {
    }

    public TbInscricao(Long idInscricao) {
        this.idInscricao = idInscricao;
    }

    public Long getIdInscricao() {
        return idInscricao;
    }

    public void setIdInscricao(Long idInscricao) {
        this.idInscricao = idInscricao;
    }

    public String getNmArquivo() {
        return nmArquivo;
    }

    public void setNmArquivo(String nmArquivo) {
        this.nmArquivo = nmArquivo;
    }

    public String getCdSerial() {
        return cdSerial;
    }

    public void setCdSerial(String cdSerial) {
        this.cdSerial = cdSerial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInscricao != null ? idInscricao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbInscricao)) {
            return false;
        }
        TbInscricao other = (TbInscricao) object;
        if ((this.idInscricao == null && other.idInscricao != null) || (this.idInscricao != null && !this.idInscricao.equals(other.idInscricao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbInscricao[ idInscricao=" + idInscricao + " ]";
    }

    public Integer getNrInscricao() {
        return nrInscricao;
    }

    public void setNrInscricao(Integer nrInscricao) {
        this.nrInscricao = nrInscricao;
    }

    public String getNrCpf() {
        return nrCpf;
    }

    public void setNrCpf(String nrCpf) {
        this.nrCpf = nrCpf;
    }

    
}
