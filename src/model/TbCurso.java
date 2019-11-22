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
@Table(name = "tb_curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbCurso.findAll", query = "SELECT t FROM TbCurso t"),
    @NamedQuery(name = "TbCurso.findByIdCurso", query = "SELECT t FROM TbCurso t WHERE t.idCurso = :idCurso"),
    @NamedQuery(name = "TbCurso.findByNmCurso", query = "SELECT t FROM TbCurso t WHERE t.nmCurso = :nmCurso"),
    @NamedQuery(name = "TbCurso.findByNmTurno", query = "SELECT t FROM TbCurso t WHERE t.nmTurno = :nmTurno"),
    @NamedQuery(name = "TbCurso.findByIdCidade", query = "SELECT t FROM TbCurso t WHERE t.idCidade = :idCidade"),
    @NamedQuery(name = "TbCurso.findByIdArea", query = "SELECT t FROM TbCurso t WHERE t.idArea = :idArea"),
    @NamedQuery(name = "TbCurso.findByCdCurso", query = "SELECT t FROM TbCurso t WHERE t.cdCurso = :cdCurso"),
    @NamedQuery(name = "TbCurso.findByNmCursoSie", query = "SELECT t FROM TbCurso t WHERE t.nmCursoSie = :nmCursoSie"),
    @NamedQuery(name = "TbCurso.findByCdCursoSie", query = "SELECT t FROM TbCurso t WHERE t.cdCursoSie = :cdCursoSie"),
    @NamedQuery(name = "TbCurso.findByNmCursoPs", query = "SELECT t FROM TbCurso t WHERE t.nmCursoPs = :nmCursoPs"),
    @NamedQuery(name = "TbCurso.findByCdCursoPs", query = "SELECT t FROM TbCurso t WHERE t.cdCursoPs = :cdCursoPs"),
    @NamedQuery(name = "TbCurso.findByIdCursoPs", query = "SELECT t FROM TbCurso t WHERE t.idCursoPs = :idCursoPs"),
    @NamedQuery(name = "TbCurso.findByIdCampus", query = "SELECT t FROM TbCurso t WHERE t.idCampus = :idCampus"),
    @NamedQuery(name = "TbCurso.findByCdCursoSisu", query = "SELECT t FROM TbCurso t WHERE t.cdCursoSisu = :cdCursoSisu"),
    @NamedQuery(name = "TbCurso.findByCdCursoMatricula", query = "SELECT t FROM TbCurso t WHERE t.cdCursoMatricula = :cdCursoMatricula"),
    @NamedQuery(name = "TbCurso.findByFlHabilidade", query = "SELECT t FROM TbCurso t WHERE t.flHabilidade = :flHabilidade"),
    @NamedQuery(name = "TbCurso.findByFlReuni", query = "SELECT t FROM TbCurso t WHERE t.flReuni = :flReuni"),
    @NamedQuery(name = "TbCurso.findByNmGrau", query = "SELECT t FROM TbCurso t WHERE t.nmGrau = :nmGrau"),
    @NamedQuery(name = "TbCurso.findByIdNivel", query = "SELECT t FROM TbCurso t WHERE t.idNivel = :idNivel"),
    @NamedQuery(name = "TbCurso.findByFlTeste", query = "SELECT t FROM TbCurso t WHERE t.flTeste = :flTeste"),
    @NamedQuery(name = "TbCurso.findByNmAbreviado", query = "SELECT t FROM TbCurso t WHERE t.nmAbreviado = :nmAbreviado"),
    @NamedQuery(name = "TbCurso.findByIdAreaCnpq", query = "SELECT t FROM TbCurso t WHERE t.idAreaCnpq = :idAreaCnpq"),
    @NamedQuery(name = "TbCurso.findByFlInstrumento", query = "SELECT t FROM TbCurso t WHERE t.flInstrumento = :flInstrumento"),
    @NamedQuery(name = "TbCurso.findByNmModalidade", query = "SELECT t FROM TbCurso t WHERE t.nmModalidade = :nmModalidade"),
    @NamedQuery(name = "TbCurso.findByFlIngressoAnual", query = "SELECT t FROM TbCurso t WHERE t.flIngressoAnual = :flIngressoAnual")})
public class TbCurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_curso")
    private Integer idCurso;
    @Column(name = "nm_curso")
    private String nmCurso;
    @Column(name = "nm_turno")
    private String nmTurno;
    @Column(name = "id_cidade")
    private Integer idCidade;
    @Column(name = "id_area")
    private Integer idArea;
    @Column(name = "cd_curso")
    private Integer cdCurso;
    @Column(name = "nm_curso_sie")
    private String nmCursoSie;
    @Column(name = "cd_curso_sie")
    private String cdCursoSie;
    @Column(name = "nm_curso_ps")
    private String nmCursoPs;
    @Column(name = "cd_curso_ps")
    private Integer cdCursoPs;
    @Column(name = "id_curso_ps")
    private Integer idCursoPs;
    @Column(name = "id_campus")
    private Integer idCampus;
    @Column(name = "cd_curso_sisu")
    private String cdCursoSisu;
    @Column(name = "cd_curso_matricula")
    private String cdCursoMatricula;
    @Column(name = "fl_habilidade")
    private Boolean flHabilidade;
    @Column(name = "fl_reuni")
    private Boolean flReuni;
    @Column(name = "nm_grau")
    private String nmGrau;
    @Column(name = "id_nivel")
    private Integer idNivel;
    @Column(name = "fl_teste")
    private Boolean flTeste;
    @Column(name = "nm_abreviado")
    private String nmAbreviado;
    @Column(name = "id_area_cnpq")
    private Short idAreaCnpq;
    @Column(name = "fl_instrumento")
    private Boolean flInstrumento;
    @Column(name = "nm_modalidade")
    private String nmModalidade;
    @Column(name = "fl_ingresso_anual")
    private Boolean flIngressoAnual;

    public TbCurso() {
    }

    public TbCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNmCurso() {
        return nmCurso;
    }

    public void setNmCurso(String nmCurso) {
        this.nmCurso = nmCurso;
    }

    public String getNmTurno() {
        return nmTurno;
    }

    public void setNmTurno(String nmTurno) {
        this.nmTurno = nmTurno;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public Integer getCdCurso() {
        return cdCurso;
    }

    public void setCdCurso(Integer cdCurso) {
        this.cdCurso = cdCurso;
    }

    public String getNmCursoSie() {
        return nmCursoSie;
    }

    public void setNmCursoSie(String nmCursoSie) {
        this.nmCursoSie = nmCursoSie;
    }

    public String getCdCursoSie() {
        return cdCursoSie;
    }

    public void setCdCursoSie(String cdCursoSie) {
        this.cdCursoSie = cdCursoSie;
    }

    public String getNmCursoPs() {
        return nmCursoPs;
    }

    public void setNmCursoPs(String nmCursoPs) {
        this.nmCursoPs = nmCursoPs;
    }

    public Integer getCdCursoPs() {
        return cdCursoPs;
    }

    public void setCdCursoPs(Integer cdCursoPs) {
        this.cdCursoPs = cdCursoPs;
    }

    public Integer getIdCursoPs() {
        return idCursoPs;
    }

    public void setIdCursoPs(Integer idCursoPs) {
        this.idCursoPs = idCursoPs;
    }

    public Integer getIdCampus() {
        return idCampus;
    }

    public void setIdCampus(Integer idCampus) {
        this.idCampus = idCampus;
    }

    public String getCdCursoSisu() {
        return cdCursoSisu;
    }

    public void setCdCursoSisu(String cdCursoSisu) {
        this.cdCursoSisu = cdCursoSisu;
    }

    public String getCdCursoMatricula() {
        return cdCursoMatricula;
    }

    public void setCdCursoMatricula(String cdCursoMatricula) {
        this.cdCursoMatricula = cdCursoMatricula;
    }

    public Boolean getFlHabilidade() {
        return flHabilidade;
    }

    public void setFlHabilidade(Boolean flHabilidade) {
        this.flHabilidade = flHabilidade;
    }

    public Boolean getFlReuni() {
        return flReuni;
    }

    public void setFlReuni(Boolean flReuni) {
        this.flReuni = flReuni;
    }

    public String getNmGrau() {
        return nmGrau;
    }

    public void setNmGrau(String nmGrau) {
        this.nmGrau = nmGrau;
    }

    public Integer getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(Integer idNivel) {
        this.idNivel = idNivel;
    }

    public Boolean getFlTeste() {
        return flTeste;
    }

    public void setFlTeste(Boolean flTeste) {
        this.flTeste = flTeste;
    }

    public String getNmAbreviado() {
        return nmAbreviado;
    }

    public void setNmAbreviado(String nmAbreviado) {
        this.nmAbreviado = nmAbreviado;
    }

    public Short getIdAreaCnpq() {
        return idAreaCnpq;
    }

    public void setIdAreaCnpq(Short idAreaCnpq) {
        this.idAreaCnpq = idAreaCnpq;
    }

    public Boolean getFlInstrumento() {
        return flInstrumento;
    }

    public void setFlInstrumento(Boolean flInstrumento) {
        this.flInstrumento = flInstrumento;
    }

    public String getNmModalidade() {
        return nmModalidade;
    }

    public void setNmModalidade(String nmModalidade) {
        this.nmModalidade = nmModalidade;
    }

    public Boolean getFlIngressoAnual() {
        return flIngressoAnual;
    }

    public void setFlIngressoAnual(Boolean flIngressoAnual) {
        this.flIngressoAnual = flIngressoAnual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbCurso)) {
            return false;
        }
        TbCurso other = (TbCurso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbCurso[ idCurso=" + idCurso + " ]";
    }
    
}
