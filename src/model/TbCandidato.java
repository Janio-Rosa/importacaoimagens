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
 * @author administrator
 */
@Entity
@Table(name = "tb_candidato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbCandidato.findAll", query = "SELECT t FROM TbCandidato t"),
    @NamedQuery(name = "TbCandidato.findByIdCandidato", query = "SELECT t FROM TbCandidato t WHERE t.idCandidato = :idCandidato"),
    @NamedQuery(name = "TbCandidato.findByDtInsercao", query = "SELECT t FROM TbCandidato t WHERE t.dtInsercao = :dtInsercao"),
    @NamedQuery(name = "TbCandidato.findByNrCpf", query = "SELECT t FROM TbCandidato t WHERE t.nrCpf = :nrCpf"),
    @NamedQuery(name = "TbCandidato.findByNrInscricao", query = "SELECT t FROM TbCandidato t WHERE t.nrInscricao = :nrInscricao"),
    @NamedQuery(name = "TbCandidato.findByNrAnoProcesso", query = "SELECT t FROM TbCandidato t WHERE t.nrAnoProcesso = :nrAnoProcesso"),
    @NamedQuery(name = "TbCandidato.findByNrPeriodoProcesso", query = "SELECT t FROM TbCandidato t WHERE t.nrPeriodoProcesso = :nrPeriodoProcesso"),
    @NamedQuery(name = "TbCandidato.findByTpCondicao", query = "SELECT t FROM TbCandidato t WHERE t.tpCondicao = :tpCondicao"),
    @NamedQuery(name = "TbCandidato.findByIdCurso", query = "SELECT t FROM TbCandidato t WHERE t.idCurso = :idCurso"),
    @NamedQuery(name = "TbCandidato.findByIdLinguaEstrangeira", query = "SELECT t FROM TbCandidato t WHERE t.idLinguaEstrangeira = :idLinguaEstrangeira"),
    @NamedQuery(name = "TbCandidato.findByFlHabilidade", query = "SELECT t FROM TbCandidato t WHERE t.flHabilidade = :flHabilidade"),
    @NamedQuery(name = "TbCandidato.findByNrIpCadastro", query = "SELECT t FROM TbCandidato t WHERE t.nrIpCadastro = :nrIpCadastro"),
    @NamedQuery(name = "TbCandidato.findByDtCadastro", query = "SELECT t FROM TbCandidato t WHERE t.dtCadastro = :dtCadastro"),
    @NamedQuery(name = "TbCandidato.findByCdLocalProva", query = "SELECT t FROM TbCandidato t WHERE t.cdLocalProva = :cdLocalProva"),
    @NamedQuery(name = "TbCandidato.findByNmArquivo", query = "SELECT t FROM TbCandidato t WHERE t.nmArquivo = :nmArquivo"),
    @NamedQuery(name = "TbCandidato.findByFlBoleto", query = "SELECT t FROM TbCandidato t WHERE t.flBoleto = :flBoleto"),
    @NamedQuery(name = "TbCandidato.findByFlIsencao", query = "SELECT t FROM TbCandidato t WHERE t.flIsencao = :flIsencao"),
    @NamedQuery(name = "TbCandidato.findByVlInscricao", query = "SELECT t FROM TbCandidato t WHERE t.vlInscricao = :vlInscricao"),
    @NamedQuery(name = "TbCandidato.findByFlHabilitado", query = "SELECT t FROM TbCandidato t WHERE t.flHabilitado = :flHabilitado"),
    @NamedQuery(name = "TbCandidato.findByFlHabilitadoOutroProcesso", query = "SELECT t FROM TbCandidato t WHERE t.flHabilitadoOutroProcesso = :flHabilitadoOutroProcesso"),
    @NamedQuery(name = "TbCandidato.findByNmProcessoHabilitado", query = "SELECT t FROM TbCandidato t WHERE t.nmProcessoHabilitado = :nmProcessoHabilitado"),
    @NamedQuery(name = "TbCandidato.findByCdConclusaoEnsinoMedio", query = "SELECT t FROM TbCandidato t WHERE t.cdConclusaoEnsinoMedio = :cdConclusaoEnsinoMedio"),
    @NamedQuery(name = "TbCandidato.findByIdDeficienciaFisica", query = "SELECT t FROM TbCandidato t WHERE t.idDeficienciaFisica = :idDeficienciaFisica"),
    @NamedQuery(name = "TbCandidato.findByIdAtendimentoEspecial", query = "SELECT t FROM TbCandidato t WHERE t.idAtendimentoEspecial = :idAtendimentoEspecial"),
    @NamedQuery(name = "TbCandidato.findByIdPerfilSaude", query = "SELECT t FROM TbCandidato t WHERE t.idPerfilSaude = :idPerfilSaude"),
    @NamedQuery(name = "TbCandidato.findByDsDeficienciaFisicaOutros", query = "SELECT t FROM TbCandidato t WHERE t.dsDeficienciaFisicaOutros = :dsDeficienciaFisicaOutros"),
    @NamedQuery(name = "TbCandidato.findByDsAtendimentoEspecialOutros", query = "SELECT t FROM TbCandidato t WHERE t.dsAtendimentoEspecialOutros = :dsAtendimentoEspecialOutros"),
    @NamedQuery(name = "TbCandidato.findByDsPerfilSaudeOutros", query = "SELECT t FROM TbCandidato t WHERE t.dsPerfilSaudeOutros = :dsPerfilSaudeOutros"),
    @NamedQuery(name = "TbCandidato.findByNmProcessoOutraInstituicao", query = "SELECT t FROM TbCandidato t WHERE t.nmProcessoOutraInstituicao = :nmProcessoOutraInstituicao")})
public class TbCandidato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_candidato")
    private Long idCandidato;
    @Basic(optional = false)
    @Column(name = "dt_insercao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInsercao;
    @Basic(optional = false)
    @Column(name = "nr_cpf")
    private String nrCpf;
    @Column(name = "nr_inscricao")
    private String nrInscricao;
    @Basic(optional = false)
    @Column(name = "nr_ano_processo")
    private int nrAnoProcesso;
    @Basic(optional = false)
    @Column(name = "nr_periodo_processo")
    private int nrPeriodoProcesso;
    @Basic(optional = false)
    @Column(name = "tp_condicao")
    private String tpCondicao;
    @Column(name = "id_curso")
    private Integer idCurso;
    @Column(name = "id_lingua_estrangeira")
    private Integer idLinguaEstrangeira;
    @Column(name = "fl_habilidade")
    private Boolean flHabilidade;
    @Column(name = "nr_ip_cadastro")
    private String nrIpCadastro;
    @Basic(optional = false)
    @Column(name = "dt_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadastro;
    @Column(name = "cd_local_prova")
    private Integer cdLocalProva;
    @Column(name = "nm_arquivo")
    private String nmArquivo;
    @Column(name = "fl_boleto")
    private Boolean flBoleto;
    @Column(name = "fl_isencao")
    private Boolean flIsencao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vl_inscricao")
    private Double vlInscricao;
    @Column(name = "fl_habilitado")
    private Boolean flHabilitado;
    @Column(name = "fl_habilitado_outro_processo")
    private Boolean flHabilitadoOutroProcesso;
    @Column(name = "nm_processo_habilitado")
    private String nmProcessoHabilitado;
    @Column(name = "cd_conclusao_ensino_medio")
    private String cdConclusaoEnsinoMedio;
    @Column(name = "id_deficiencia_fisica")
    private Integer idDeficienciaFisica;
    @Column(name = "id_atendimento_especial")
    private Integer idAtendimentoEspecial;
    @Column(name = "id_perfil_saude")
    private Integer idPerfilSaude;
    @Column(name = "ds_deficiencia_fisica_outros")
    private String dsDeficienciaFisicaOutros;
    @Column(name = "ds_atendimento_especial_outros")
    private String dsAtendimentoEspecialOutros;
    @Column(name = "ds_perfil_saude_outros")
    private String dsPerfilSaudeOutros;
    @Column(name = "nm_processo_outra_instituicao")
    private String nmProcessoOutraInstituicao;
    @Column(name = "nm_instrumento")
    private String nmInstrumento;
    @JoinColumn(name = "id_processo", referencedColumnName = "id_processo")
    @ManyToOne(optional = false)
    private TbProcesso idProcesso;
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    @ManyToOne(optional = false)
    private TbPessoa idPessoa;

    public TbCandidato() {
    }
    public TbCandidato(long id) {
        this.idCandidato=id;
    }

    public TbCandidato(Long idCandidato) {
        this.idCandidato = idCandidato;
    }

    public TbCandidato(Long idCandidato, Date dtInsercao, String nrCpf, int nrAnoProcesso, int nrPeriodoProcesso, String tpCondicao, Date dtCadastro) {
        this.idCandidato = idCandidato;
        this.dtInsercao = dtInsercao;
        this.nrCpf = nrCpf;
        this.nrAnoProcesso = nrAnoProcesso;
        this.nrPeriodoProcesso = nrPeriodoProcesso;
        this.tpCondicao = tpCondicao;
        this.dtCadastro = dtCadastro;
    }

    public Long getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Long idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Date getDtInsercao() {
        return dtInsercao;
    }

    public void setDtInsercao(Date dtInsercao) {
        this.dtInsercao = dtInsercao;
    }

    public String getNrCpf() {
        return nrCpf;
    }

    public void setNrCpf(String nrCpf) {
        this.nrCpf = nrCpf;
    }

    public int getNrAnoProcesso() {
        return nrAnoProcesso;
    }

    public void setNrAnoProcesso(int nrAnoProcesso) {
        this.nrAnoProcesso = nrAnoProcesso;
    }

    public int getNrPeriodoProcesso() {
        return nrPeriodoProcesso;
    }

    public void setNrPeriodoProcesso(int nrPeriodoProcesso) {
        this.nrPeriodoProcesso = nrPeriodoProcesso;
    }

    public String getTpCondicao() {
        return tpCondicao;
    }

    public void setTpCondicao(String tpCondicao) {
        this.tpCondicao = tpCondicao;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getIdLinguaEstrangeira() {
        return idLinguaEstrangeira;
    }

    public void setIdLinguaEstrangeira(Integer idLinguaEstrangeira) {
        this.idLinguaEstrangeira = idLinguaEstrangeira;
    }

    public Boolean getFlHabilidade() {
        return flHabilidade;
    }

    public void setFlHabilidade(Boolean flHabilidade) {
        this.flHabilidade = flHabilidade;
    }

    public String getNrIpCadastro() {
        return nrIpCadastro;
    }

    public void setNrIpCadastro(String nrIpCadastro) {
        this.nrIpCadastro = nrIpCadastro;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Integer getCdLocalProva() {
        return cdLocalProva;
    }

    public void setCdLocalProva(Integer cdLocalProva) {
        this.cdLocalProva = cdLocalProva;
    }

    public String getNmArquivo() {
        return nmArquivo;
    }

    public void setNmArquivo(String nmArquivo) {
        this.nmArquivo = nmArquivo;
    }

    public Boolean getFlBoleto() {
        return flBoleto;
    }

    public void setFlBoleto(Boolean flBoleto) {
        this.flBoleto = flBoleto;
    }

    public Boolean getFlIsencao() {
        return flIsencao;
    }

    public void setFlIsencao(Boolean flIsencao) {
        this.flIsencao = flIsencao;
    }

    public Double getVlInscricao() {
        return vlInscricao;
    }

    public void setVlInscricao(Double vlInscricao) {
        this.vlInscricao = vlInscricao;
    }

    public Boolean getFlHabilitado() {
        return flHabilitado;
    }

    public void setFlHabilitado(Boolean flHabilitado) {
        this.flHabilitado = flHabilitado;
    }

    public Boolean getFlHabilitadoOutroProcesso() {
        return flHabilitadoOutroProcesso;
    }

    public void setFlHabilitadoOutroProcesso(Boolean flHabilitadoOutroProcesso) {
        this.flHabilitadoOutroProcesso = flHabilitadoOutroProcesso;
    }

    public String getNmProcessoHabilitado() {
        return nmProcessoHabilitado;
    }

    public void setNmProcessoHabilitado(String nmProcessoHabilitado) {
        this.nmProcessoHabilitado = nmProcessoHabilitado;
    }

    public String getCdConclusaoEnsinoMedio() {
        return cdConclusaoEnsinoMedio;
    }

    public void setCdConclusaoEnsinoMedio(String cdConclusaoEnsinoMedio) {
        this.cdConclusaoEnsinoMedio = cdConclusaoEnsinoMedio;
    }

    public Integer getIdDeficienciaFisica() {
        return idDeficienciaFisica;
    }

    public void setIdDeficienciaFisica(Integer idDeficienciaFisica) {
        this.idDeficienciaFisica = idDeficienciaFisica;
    }

    public Integer getIdAtendimentoEspecial() {
        return idAtendimentoEspecial;
    }

    public void setIdAtendimentoEspecial(Integer idAtendimentoEspecial) {
        this.idAtendimentoEspecial = idAtendimentoEspecial;
    }

    public Integer getIdPerfilSaude() {
        return idPerfilSaude;
    }

    public void setIdPerfilSaude(Integer idPerfilSaude) {
        this.idPerfilSaude = idPerfilSaude;
    }

    public String getDsDeficienciaFisicaOutros() {
        return dsDeficienciaFisicaOutros;
    }

    public void setDsDeficienciaFisicaOutros(String dsDeficienciaFisicaOutros) {
        this.dsDeficienciaFisicaOutros = dsDeficienciaFisicaOutros;
    }

    public String getDsAtendimentoEspecialOutros() {
        return dsAtendimentoEspecialOutros;
    }

    public void setDsAtendimentoEspecialOutros(String dsAtendimentoEspecialOutros) {
        this.dsAtendimentoEspecialOutros = dsAtendimentoEspecialOutros;
    }

    public String getDsPerfilSaudeOutros() {
        return dsPerfilSaudeOutros;
    }

    public void setDsPerfilSaudeOutros(String dsPerfilSaudeOutros) {
        this.dsPerfilSaudeOutros = dsPerfilSaudeOutros;
    }

    public String getNmProcessoOutraInstituicao() {
        return nmProcessoOutraInstituicao;
    }

    public void setNmProcessoOutraInstituicao(String nmProcessoOutraInstituicao) {
        this.nmProcessoOutraInstituicao = nmProcessoOutraInstituicao;
    }

    public TbProcesso getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(TbProcesso idProcesso) {
        this.idProcesso = idProcesso;
    }

    public TbPessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(TbPessoa idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCandidato != null ? idCandidato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbCandidato)) {
            return false;
        }
        TbCandidato other = (TbCandidato) object;
        if ((this.idCandidato == null && other.idCandidato != null) || (this.idCandidato != null && !this.idCandidato.equals(other.idCandidato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbCandidato[ idCandidato=" + idCandidato + " ]";
    }

    public String getNmInstrumento() {
        return nmInstrumento;
    }

    public void setNmInstrumento(String nmInstrumento) {
        this.nmInstrumento = nmInstrumento;
    }

    public String getNrInscricao() {
        return nrInscricao;
    }

    public void setNrInscricao(String nrInscricao) {
        this.nrInscricao = nrInscricao;
    }

    
}
