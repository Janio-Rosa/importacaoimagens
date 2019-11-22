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
 * @author administrator
 */
@Entity
@Table(name = "tb_resposta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbResposta.findAll", query = "SELECT t FROM TbResposta t"),
    @NamedQuery(name = "TbResposta.findByIdResposta", query = "SELECT t FROM TbResposta t WHERE t.idResposta = :idResposta"),
    @NamedQuery(name = "TbResposta.findByCdSerial", query = "SELECT t FROM TbResposta t WHERE t.cdSerial = :cdSerial"),
    @NamedQuery(name = "TbResposta.findByNrQuestao", query = "SELECT t FROM TbResposta t WHERE t.nrQuestao = :nrQuestao"),
    @NamedQuery(name = "TbResposta.findByNrNotaFinal", query = "SELECT t FROM TbResposta t WHERE t.nrNotaFinal = :nrNotaFinal"),
    @NamedQuery(name = "TbResposta.findByFlDiscrepancia", query = "SELECT t FROM TbResposta t WHERE t.flDiscrepancia = :flDiscrepancia"),
    @NamedQuery(name = "TbResposta.findByFlDiscrepanciaCorrigida", query = "SELECT t FROM TbResposta t WHERE t.flDiscrepanciaCorrigida = :flDiscrepanciaCorrigida"),
    @NamedQuery(name = "TbResposta.findByFlCorrigida", query = "SELECT t FROM TbResposta t WHERE t.flCorrigida = :flCorrigida"),
    @NamedQuery(name = "TbResposta.findByFlCorrigindo", query = "SELECT t FROM TbResposta t WHERE t.flCorrigindo = :flCorrigindo")})
public class TbResposta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_resposta")
    private Long idResposta;
    @Basic(optional = false)
    @Column(name = "cd_serial")
    private String cdSerial;
    @Basic(optional = false)
    @Column(name = "nr_questao")
    private int nrQuestao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nr_nota_final")
    private Double nrNotaFinal;
    @Column(name = "fl_discrepancia")
    private Boolean flDiscrepancia;
    @Column(name = "fl_discrepancia_corrigida")
    private Boolean flDiscrepanciaCorrigida;
    @Basic(optional = false)
    @Column(name = "fl_corrigida")
    private boolean flCorrigida;
    @Basic(optional = false)
    @Column(name = "fl_corrigindo")
    private boolean flCorrigindo;
    @JoinColumn(name = "id_processo", referencedColumnName = "id_processo")
    @ManyToOne(optional = false)
    private TbProcesso idProcesso;
    
    @Column(name = "id_disciplina")
    private Integer idDisciplina;

    @Column(name = "id_curso")
    private Integer idCurso;
    
    @Column(name = "id_inscricao")
    private Long idInscricao;

    public TbResposta() {
    }

    public TbResposta(Long idResposta) {
        this.idResposta = idResposta;
    }

    public TbResposta(Long idResposta, String cdSerial, int nrQuestao, boolean flCorrigida, boolean flCorrigindo) {
        this.idResposta = idResposta;
        this.cdSerial = cdSerial;
        this.nrQuestao = nrQuestao;
        this.flCorrigida = flCorrigida;
        this.flCorrigindo = flCorrigindo;
    }

    public Long getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(Long idResposta) {
        this.idResposta = idResposta;
    }

    public String getCdSerial() {
        return cdSerial;
    }

    public void setCdSerial(String cdSerial) {
        this.cdSerial = cdSerial;
    }

    public int getNrQuestao() {
        return nrQuestao;
    }

    public void setNrQuestao(int nrQuestao) {
        this.nrQuestao = nrQuestao;
    }

    public Double getNrNotaFinal() {
        return nrNotaFinal;
    }

    public void setNrNotaFinal(Double nrNotaFinal) {
        this.nrNotaFinal = nrNotaFinal;
    }

    public Boolean getFlDiscrepancia() {
        return flDiscrepancia;
    }

    public void setFlDiscrepancia(Boolean flDiscrepancia) {
        this.flDiscrepancia = flDiscrepancia;
    }

    public Boolean getFlDiscrepanciaCorrigida() {
        return flDiscrepanciaCorrigida;
    }

    public void setFlDiscrepanciaCorrigida(Boolean flDiscrepanciaCorrigida) {
        this.flDiscrepanciaCorrigida = flDiscrepanciaCorrigida;
    }

    public boolean getFlCorrigida() {
        return flCorrigida;
    }

    public void setFlCorrigida(boolean flCorrigida) {
        this.flCorrigida = flCorrigida;
    }

    public boolean getFlCorrigindo() {
        return flCorrigindo;
    }

    public void setFlCorrigindo(boolean flCorrigindo) {
        this.flCorrigindo = flCorrigindo;
    }

    public TbProcesso getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(TbProcesso idProcesso) {
        this.idProcesso = idProcesso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResposta != null ? idResposta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbResposta)) {
            return false;
        }
        TbResposta other = (TbResposta) object;
        if ((this.idResposta == null && other.idResposta != null) || (this.idResposta != null && !this.idResposta.equals(other.idResposta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbResposta[ idResposta=" + idResposta + " ]";
    }

    public Long getIdInscricao() {
        return idInscricao;
    }

    public void setIdInscricao(Long idInscricao) {
        this.idInscricao = idInscricao;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    
}
