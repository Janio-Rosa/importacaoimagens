/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import carrega_imagem.TratarImagem;
import dao.impl.CandidatoDAO;
import dao.impl.CursoDAO;
import dao.impl.DisciplinaDAO;
import dao.impl.ProcessoDao;
import dao.impl.RespostaDAO;
import dao.impl.TipoQuestaoDAO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import model.TbCandidato;
import model.TbCurso;
import model.TbDadosAcessoBanco;
import model.TbDisciplina;
import model.TbInscricao;
import model.TbProcesso;
import model.TbResposta;
import model.TbRespostaImagem;
import model.TbTipoQuestao;
import uteis.CodigoBarrasZXing;
import uteis.TransportaInformacoes;

/**
 *
 * @author janio
 */
public class RespostaService {

    private String inscricao = "";
    private int questao = 1;
    private int disciplina = 0;
    private String nomeArquivo = "";
    private File arquivoTrabalho;
    private String nomeDisciplina = "";
    private String nomeDisciplinaOrigem = "";
    private int curso = 0;
    private int processo = 0;
    private String nomeCurso = "";
    private String nomeProcesso = "";
    
    
    private int nrAnoProcessso = 0;
    private int id_tipo_processo = 0;
    
    

    private boolean flCurso = false;
    private boolean flDisciplina = false;
    private boolean flDisciplinaVestibular = false;
    private boolean flDisciplinaOrigem = false;
    private boolean flProcesso = false;

    private TbProcesso tbProcesso;
    private String msgDisciplina = "";
    private String msgDisciplinaVestibular = "";
    private String msgCurso = "";
    private String msgProcesso = "";
    
    private String cadastrado = "Curso está cadastrado no banco de destino";
    private String não_cadastrado = "Curso não está cadastrado no banco de destino";
    
    private String disc_cadastrado = "Disciplina está cadastrada no banco de destino";
    private String disc_não_cadastrado = "Disciplina não está cadastrada no banco de destino";
    
    private String disc_cadastrado_vestibular = "Disciplina está cadastrada no banco de destino";
    private String disc_não_cadastrado_vestibular = "Disciplina não está cadastrada no banco de destino";
    
    private String processo_cadastrado = "Processo está cadastrada no banco de destino";
    private String processo_não_cadastrado = "Processo não está cadastrada no banco de destino";

    public String getMsgDisciplinaVestibular() {
        return msgDisciplinaVestibular;
    }

    public void setMsgDisciplinaVestibular(String msgDisciplinaVestibular) {
        this.msgDisciplinaVestibular = msgDisciplinaVestibular;
    }

    public int getNrAnoProcessso() {
        return nrAnoProcessso;
    }

    public void setNrAnoProcessso(int nrAnoProcessso) {
        this.nrAnoProcessso = nrAnoProcessso;
    }

    public int getId_tipo_processo() {
        return id_tipo_processo;
    }

    public void setId_tipo_processo(int id_tipo_processo) {
        this.id_tipo_processo = id_tipo_processo;
    }
    

    
    
    public String getMsgProcesso() {
        return msgProcesso;
    }

    public void setMsgProcesso(String msgProcesso) {
        this.msgProcesso = msgProcesso;
    }

    public String getProcesso_cadastrado() {
        return processo_cadastrado;
    }

    public void setProcesso_cadastrado(String processo_cadastrado) {
        this.processo_cadastrado = processo_cadastrado;
    }

    public String getProcesso_não_cadastrado() {
        return processo_não_cadastrado;
    }

    public void setProcesso_não_cadastrado(String processo_não_cadastrado) {
        this.processo_não_cadastrado = processo_não_cadastrado;
    }

    public boolean getFlDisciplinaOrigem() {
        return flDisciplinaOrigem;
    }

    public void setFlDisciplinaOrigem(boolean flDisciplinaOrigem) {
        this.flDisciplinaOrigem = flDisciplinaOrigem;
    }

    public boolean getFlDisciplinaVestibular() {
        return flDisciplinaVestibular;
    }

    public void setFlDisciplinaVestibular(boolean flDisciplinaVestibular) {
        this.flDisciplinaVestibular = flDisciplinaVestibular;
    }

    public String getNomeDisciplinaOrigem() {
        return nomeDisciplinaOrigem;
    }

    public void setNomeDisciplinaOrigem(String nomeDisciplinaOrigem) {
        this.nomeDisciplinaOrigem = nomeDisciplinaOrigem;
    }

    public TbProcesso getTbProcesso() {
        return tbProcesso;
    }

    public void setTbProcesso(TbProcesso tbProcesso) {
        this.tbProcesso = tbProcesso;
    }

    public String getNomeProcesso() {
        return nomeProcesso;
    }

    public void setNomeProcesso(String nomeProcesso) {
        this.nomeProcesso = nomeProcesso;
    }

    public boolean getFlProcesso() {
        return flProcesso;
    }

    public void setFlProcesso(boolean flProcesso) {
        this.flProcesso = flProcesso;
    }

    public int getProcesso() {
        return processo;
    }

    public void setProcesso(int processo) {
        this.processo = processo;
    }

    public long importaArquivo(File arquivoImportar, TbDadosAcessoBanco bancoOrigem, TbDadosAcessoBanco bancoDestinoCorrecao, InputStream imagemAInserir) {
        this.decodificaCodigoBarras(arquivoImportar);

        TbCandidato cand = this.lerCandidato(this.getInscricao(), this.getNomeArquivo(), arquivoImportar, bancoOrigem);
        this.setArquivoTrabalho(arquivoImportar);
        //Pegar código da disciplina (D), código da questão (Q) e inscrição (I)

        //Importar
        if (cand != null && cand.getIdProcesso() != null) {

            TbInscricao gravaCand = new TbInscricao();
            //PENDENTE - Corrigir a inserção do número de inscrição no banco de dados
            gravaCand.setNrInscricao(Integer.valueOf(cand.getNrInscricao()));
            gravaCand.setNmArquivo(cand.getNmArquivo());
            String serial = uteis.MD5Hexa.getMD5("" + gravaCand.getIdInscricao() + gravaCand.getNmArquivo() + cand.getNrCpf());
            gravaCand.setCdSerial(serial);
            gravaCand.setNrCpf(cand.getNrCpf());

            RespostaDAO rd = new RespostaDAO();

            long consultaJaExisteInscricao = rd.consultaInscricao(bancoDestinoCorrecao, gravaCand, this.getDisciplina(), this.getQuestao());

            if (consultaJaExisteInscricao <= 0) {
                long insercaoInscricao = rd.insereCandidatoInscricao(bancoDestinoCorrecao, gravaCand);

                TbResposta resp = new TbResposta();
                resp.setCdSerial(serial);
                resp.setIdCurso(cand.getIdCurso());
                resp.setIdInscricao(insercaoInscricao);
                resp.setIdProcesso(cand.getIdProcesso());
                resp.setNrQuestao(this.getQuestao());
                resp.setIdDisciplina(this.getDisciplina());
                if (resp != null && resp.getIdDisciplina() != null && resp.getIdDisciplina().intValue() == 0) {
                    resp.setIdDisciplina(13);
                } else if (resp != null && resp.getIdDisciplina() == null) {
                    resp.setIdDisciplina(new Integer(0));
                }
                ///////////////////////////// ***** INSERE RESPOSTA ******* \\\\\\\\\\\\\\\\\\\\\\\\\\\
                long idResp = rd.insereRespostpa(bancoDestinoCorrecao, resp);

                TbRespostaImagem rImagem = new TbRespostaImagem();
                rImagem.setIdResposta(idResp);

                if (imagemAInserir != null) {
                    long idInserirImagem = rd.insereImagem(bancoDestinoCorrecao, rImagem, imagemAInserir, arquivoImportar.length(), this.getQuestao());
                    //inserir na tabela de controle (Nome do arquivo, status (sucesso ou com erro), mensagem de erro, número de inscrição)
                    if (idInserirImagem == 0) {
                        System.out.println("Erro ao inserir a imagem - inscrição: " + this.getInscricao() + " - arquivo: "
                                + this.getNomeArquivo() + " - " + arquivoImportar.getAbsolutePath());
                        return 0; //deu erro;
                    } else {
                        System.out.println("Imagem inserida com sucesso - inscrição: " + this.getInscricao());
                        return idInserirImagem;
                    }
                }
            } else {
                TbRespostaImagem rImagem = new TbRespostaImagem();
                rImagem.setIdResposta(consultaJaExisteInscricao);
                //inserir na tabela de controle (Nome do arquivo, status (sucesso ou com erro), mensagem de erro, número de inscrição)

                //long idInserirImagem = rd.atualizaImagem(bancoCorrecao, rImagem, imagemCortada, arquivoAtual.length());
                //long idInserirImagem = rd.insereImagem(bancoDestinoCorrecao, rImagem, imagemAInserir, arquivoImportar.length(), this.getQuestao());
                long idInserirImagem = rd.atualizaImagem(bancoDestinoCorrecao, rImagem, imagemAInserir, arquivoImportar.length());

                return idInserirImagem;
            }

        }
        return 0;

    }
    
     public long importaArquivoDocentes(File arquivoImportar, TbDadosAcessoBanco bancoOrigem, TbDadosAcessoBanco bancoDestinoCorrecao, InputStream imagemAInserir) {
        this.decodificaCodigoBarras(arquivoImportar);

        TbCandidato cand = this.lerCandidato(this.getInscricao(), this.getNomeArquivo(), arquivoImportar, bancoOrigem);
        this.setArquivoTrabalho(arquivoImportar);
        //Pegar código da disciplina (D), código da questão (Q) e inscrição (I)

        //Importar
        if (cand != null && cand.getIdProcesso() != null) {

            TbInscricao gravaCand = new TbInscricao();
            //PENDENTE - Corrigir a inserção do número de inscrição no banco de dados
            gravaCand.setNrInscricao(Integer.valueOf(cand.getNrInscricao()));
            gravaCand.setNmArquivo(cand.getNmArquivo());
            String serial = uteis.MD5Hexa.getMD5("" + gravaCand.getIdInscricao() + gravaCand.getNmArquivo() + cand.getNrCpf());
            gravaCand.setCdSerial(serial);
            gravaCand.setNrCpf(cand.getNrCpf());

            RespostaDAO rd = new RespostaDAO();

            long consultaJaExisteInscricao = rd.consultaInscricaoDocente(bancoDestinoCorrecao, gravaCand, cand.getIdCurso());

            if (consultaJaExisteInscricao <= 0) {
                long insercaoInscricao = rd.insereCandidatoInscricao(bancoDestinoCorrecao, gravaCand);

                TbResposta resp = new TbResposta();
                resp.setCdSerial(serial);
                resp.setIdCurso(cand.getIdCurso());
                resp.setIdInscricao(insercaoInscricao);
                resp.setIdProcesso(cand.getIdProcesso());
                resp.setNrQuestao(this.getQuestao());
                resp.setIdDisciplina(cand.getIdCurso()); //idDisciplina vai ser igual ao do idcurso

                ///////////////////////////// ***** INSERE RESPOSTA ******* \\\\\\\\\\\\\\\\\\\\\\\\\\\
                resp.setNrQuestao(1); //no caso de concurso para docentes, sempre insere como QUESTÃO 1 //Q1 - Concurso de docentes sempre terá a primeira página inserida como questão 1
                
            long idResp = rd.insereRespostaDocente(bancoDestinoCorrecao, resp);

                TbRespostaImagem rImagem = new TbRespostaImagem();
                rImagem.setIdResposta(idResp);

                if (imagemAInserir != null) {
                    long idInserirImagem = rd.insereImagem(bancoDestinoCorrecao, rImagem, imagemAInserir, arquivoImportar.length(), this.getQuestao()); 
                    //inserir na tabela de controle (Nome do arquivo, status (sucesso ou com erro), mensagem de erro, número de inscrição)
                    if (idInserirImagem == 0) {
                        System.out.println("Erro ao inserir a imagem - inscrição: " + this.getInscricao() + " - arquivo: "
                                + this.getNomeArquivo() + " - " + arquivoImportar.getAbsolutePath());
                        return 0; //deu erro;
                    } else {
                        System.out.println("Imagem inserida com sucesso - inscrição: " + this.getInscricao());
                        return idInserirImagem;
                    }
                }
            } else {
                long idInserirImagem = 0;
                /*
                long consultaJaExisteInscricaoQuestao = rd.consultaInscricao(bancoDestinoCorrecao, gravaCand, this.getDisciplina(), this.getQuestao());
                if (consultaJaExisteInscricaoQuestao <= 0) {
                  */  
                    TbRespostaImagem rImagem = new TbRespostaImagem();
                    rImagem.setIdResposta(consultaJaExisteInscricao);
                    idInserirImagem = rd.insereImagem(bancoDestinoCorrecao, rImagem, imagemAInserir, arquivoImportar.length(), this.getQuestao());

/*
                } else {
                    TbRespostaImagem rImagem = new TbRespostaImagem();
                    rImagem.setIdResposta(consultaJaExisteInscricaoQuestao);
                    idInserirImagem = rd.atualizaImagem(bancoDestinoCorrecao, rImagem, imagemAInserir, arquivoImportar.length());
                }*/
                return idInserirImagem;
            }

        }
        return 0;

    }

   

    public int getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(int disciplina) {
        this.disciplina = disciplina;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public int getQuestao() {
        return questao;
    }

    public void setQuestao(int questao) {
        this.questao = questao;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public boolean getFlCurso() {
        return flCurso;
    }

    public void setFlCurso(boolean flCurso) {
        this.flCurso = flCurso;
    }

    public String getMsgDisciplina() {
        return msgDisciplina;
    }

    public void setMsgDisciplina(String msgDisciplina) {
        this.msgDisciplina = msgDisciplina;
    }

    public String getMsgCurso() {
        return msgCurso;
    }

    public void setMsgCurso(String msgCurso) {
        this.msgCurso = msgCurso;
    }

    public boolean getFlDisciplina() {
        return flDisciplina;
    }

    public void setFlDisciplina(boolean flDisciplina) {
        this.flDisciplina = flDisciplina;
    }

    private void decodificaCodigoBarras(File arquivoAtual, BufferedImage imagemOriginal) {
        //TENTA LER CÓDIGO DE BARRAS 1 (priemiro - parte superior do papel)
        BufferedImage primeiraTentativa = TratarImagem.cropSubimageWithRectangle(imagemOriginal, TransportaInformacoes.getTransportaInformacoes().getAreaImagemCB1());
        String codigoBarras = CodigoBarrasZXing.lerCodigoBarras(primeiraTentativa);
        if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
            //TENTA LER CÓDIGO DE BARRAS 2 (o segundo, na parte inferior do papel)
            BufferedImage segundaTentativa = TratarImagem.cropSubimageWithRectangle(imagemOriginal, TransportaInformacoes.getTransportaInformacoes().getAreaImagemCB2());
            codigoBarras = CodigoBarrasZXing.lerCodigoBarras(segundaTentativa);
        }

        if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
            //Se ainda assim houver problema para ler o código de barras, agora tenta-se ler da imagem INTEIRA.
            codigoBarras = CodigoBarrasZXing.lerCodigoBarras(imagemOriginal);
        }

        if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
            //Se o código continuar inválido, tenta-se ler pelo nome do arquivo
            if (arquivoAtual != null) {
                this.decodificaPorNomeArquivo(arquivoAtual);
            }
        } else if (codigoBarras != null && !codigoBarras.trim().equalsIgnoreCase("")) {
            String codigosDetectados[] = {""};
            DecodificaInscricao di = new DecodificaInscricao();
            codigosDetectados = di.carregaInscricaoPeloCodigoBarras(codigoBarras);

            try {
                this.setInscricao(codigosDetectados[0]);
                this.setQuestao(Integer.valueOf(codigosDetectados[1]));
                this.setDisciplina(Integer.valueOf(codigosDetectados[2]));
            } catch (Exception ex) {
                System.out.println("Erro ao ler código de barras");
                this.setInscricao("");
                this.setQuestao(0);
                this.setDisciplina(0);

            }

        }

        return;
    }

    private void decodificaCodigoBarrasDocentes(File arquivoAtual, BufferedImage imagemOriginal) {
        //TENTA LER CÓDIGO DE BARRAS 1 (priemiro - parte superior do papel)
        BufferedImage primeiraTentativa = TratarImagem.cropSubimageWithRectangle(imagemOriginal, TransportaInformacoes.getTransportaInformacoes().getAreaImagemCB1());
        String codigoBarras = CodigoBarrasZXing.lerCodigoBarras(primeiraTentativa);
        if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
            //TENTA LER CÓDIGO DE BARRAS 2 (o segundo, na parte inferior do papel)
            BufferedImage segundaTentativa = TratarImagem.cropSubimageWithRectangle(imagemOriginal, TransportaInformacoes.getTransportaInformacoes().getAreaImagemCB2());
            codigoBarras = CodigoBarrasZXing.lerCodigoBarras(segundaTentativa);
        }

        if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
            //Se ainda assim houver problema para ler o código de barras, agora tenta-se ler da imagem INTEIRA.
            codigoBarras = CodigoBarrasZXing.lerCodigoBarras(imagemOriginal);
        }

        if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
            //Se o código continuar inválido, tenta-se ler pelo nome do arquivo
            if (arquivoAtual != null) {
                this.decodificaPorNomeArquivo(arquivoAtual);
            }
        } else if (codigoBarras != null && !codigoBarras.trim().equalsIgnoreCase("")) {
            String codigosDetectados[] = {""};
            DecodificaInscricao di = new DecodificaInscricao();
            codigosDetectados = di.carregaInscricaoPeloCodigoBarras(codigoBarras);
            System.out.println("aqui1: " + codigosDetectados[0]);
            //se for folha reserva, decodifica pelo nome do arquivo
            if ((codigosDetectados[0].equals("9999999999"))){
                System.out.println("aqui2: " + codigosDetectados[0]);
                if (arquivoAtual != null) {
                    this.decodificaPorNomeArquivo(arquivoAtual);
                    System.out.println("aqui: " + this.getInscricao());
                    return;
                }
            }

            try {
                this.setInscricao(codigosDetectados[0]);
                this.setQuestao(Integer.valueOf(codigosDetectados[1]));
                this.setDisciplina(Integer.valueOf(codigosDetectados[2]));
                //   this.setDisciplina(getCurso());
            } catch (Exception ex) {
                System.out.println("Erro ao ler código de barras");
                this.setInscricao("");
                this.setQuestao(0);
                this.setDisciplina(0);

            }

        }

        return;
    }

    private void decodificaCodigoBarras(File arquivoAtual) {
        try {
            BufferedImage imagemOriginal = ImageIO.read(arquivoAtual);
            this.decodificaCodigoBarrasDocentes(arquivoAtual, imagemOriginal);
            return;
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    private void decodificaPorNomeArquivo(File arquivoAtual) {

        String[] decodificaByFileName;

        String codigosByFileName[] = {arquivoAtual.getName().substring(0, arquivoAtual.getName().indexOf("."))};

        //************************************************************ LEITURA DO CÓDIGO DE BARRAS ************************************************************
        //LEITURA DO CÓDIGO DE BARRAS
        DecodificaInscricao di = new DecodificaInscricao();
        //decodificaByFileName = di.carregaNovaInscricaoPeloNomeArquivo(codigosByFileName, arquivoAtual.getName().substring(0, arquivoAtual.getName().indexOf(".")));
        decodificaByFileName = di.carregaInscricaoPeloCodigoBarras(arquivoAtual);

        try {
            this.setInscricao(decodificaByFileName[0]);
            this.setQuestao(Integer.valueOf(decodificaByFileName[1]));
            this.setDisciplina(Integer.valueOf(decodificaByFileName[2]));
        } catch (Exception ex) {
            System.out.println("Erro ao ler código de barras");
            this.setInscricao("");
            this.setQuestao(0);
            this.setDisciplina(0);

        }
        // this.setDisciplina(Integer.valueOf(5555));        
        //    this.setNomeArquivo(decodificaByFileName[0]);
        return;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public File getArquivoTrabalho() {
        return arquivoTrabalho;
    }

    public void setArquivoTrabalho(File arquivoTrabalho) {
        this.arquivoTrabalho = arquivoTrabalho;
    }

    private TbCandidato lerCandidato(String inscricao, String arquivo, File arquivoImagem, TbDadosAcessoBanco acessoBanco) {

        String arquivoFisico = "";

        if (arquivoImagem != null) {
            arquivoFisico = arquivoImagem.getName();
        }

        CandidatoDAO cdao = new CandidatoDAO();
        TbCandidato filtro = new TbCandidato();
        TbCandidato ret = null;
        filtro.setNrInscricao(inscricao);

        ret = cdao.pesquisar(filtro, acessoBanco); //Procura por número da inscrição
        //if (ret != null) {
        //ret.setCdConclusaoEnsinoMedio("1");
        //}

        if (ret.getIdCandidato() == null && (!arquivo.equalsIgnoreCase(""))) { //Se não achou, procura pelo nome do arquivo
            filtro = null;
            filtro = new TbCandidato();
            filtro.setNrInscricao(arquivo);
            ret = cdao.pesquisar(filtro, acessoBanco);
            if (ret != null) {
                ret.setCdConclusaoEnsinoMedio("2");
            }
        }

        if (ret.getIdCandidato() == null && (!arquivoFisico.equalsIgnoreCase(""))) { // Se não achou, procura pelo nome do arquivo físico
            filtro = null;
            filtro = new TbCandidato();
            filtro.setNmArquivo(arquivoFisico);
            ret = cdao.pesquisar(filtro, acessoBanco);
        }

        return ret;
    }
    

    public void consultaDisciplina(int cdDisciplina, TbDadosAcessoBanco acessoBanco) { // consulta disciplina no banco de destino
        System.out.println("Entrou em consulta Disciplina");
        DisciplinaDAO disDao = new DisciplinaDAO();
        TbDisciplina ret = null;
        TbDisciplina filtro = new TbDisciplina();
        filtro.setIdDisciplina(cdDisciplina);
        ret = disDao.pesquisar(filtro, acessoBanco);
        setNomeDisciplina(ret.getNmDisciplina());
        if (ret.getIdDisciplina() != null) {
            setMsgDisciplina(disc_cadastrado);
            setFlDisciplina(true);
        } else {
            setMsgDisciplina(disc_não_cadastrado);
        }
        // return ret;

    }

    public void consultaDisciplinaVestibular(int cdDisciplina, TbDadosAcessoBanco acessoBanco) { // consulta disciplina no banco de destino
        System.out.println("Entrou em consulta Disciplina");
        DisciplinaDAO disDao = new DisciplinaDAO();
        TbDisciplina ret = null;
        TbDisciplina filtro = new TbDisciplina();
        filtro.setIdDisciplina(cdDisciplina);
        ret = disDao.pesquisar(filtro, acessoBanco);
        setNomeDisciplina(ret.getNmDisciplina());
        if (ret.getIdDisciplina() != null) {
            setMsgDisciplinaVestibular(disc_cadastrado);
            setFlDisciplinaVestibular(true);
        } else {
            setMsgDisciplinaVestibular(disc_não_cadastrado);
        }
        // return ret;

    }

    public void consultaDisciplinaOrigem(int cdDisciplina, TbDadosAcessoBanco acessoBanco) { // consulta disciplina no banco de destino
        System.out.println("Entrou em consulta Disciplina");
        DisciplinaDAO disDao = new DisciplinaDAO();
        TbDisciplina ret = null;
        TbDisciplina filtro = new TbDisciplina();
        filtro.setIdDisciplina(cdDisciplina);
        ret = disDao.pesquisar(filtro, acessoBanco);
        setNomeDisciplinaOrigem(ret.getNmDisciplina());
        if (ret.getIdDisciplina() != null) {
            setMsgDisciplina(disc_cadastrado);
            setFlDisciplinaOrigem(true);
        } else {
            setMsgDisciplina(disc_não_cadastrado);
        }
        // return ret;

    }

    public TbCurso consultaCurso(int cdCurso, TbDadosAcessoBanco acessoBanco) { // consulta curso no banco de origem
        System.out.println("Entrou em consulta Curso");
        CursoDAO disDao = new CursoDAO();
        TbCurso ret = null;
        TbCurso filtro = new TbCurso();
        String nmcurso;
        filtro.setIdCurso(cdCurso);
        ret = disDao.pesquisar(filtro, acessoBanco);
        if (ret.getNmCursoSie() != null) {
            nmcurso = ret.getNmCursoSie();
        } else {
            nmcurso = ret.getNmCursoPs();
        }

        setNomeCurso(nmcurso);
        return ret;

    }

    public TbProcesso consultaProcessoBdDestino(int idprocesso, TbDadosAcessoBanco acessoBanco) { // consulta curso no banco de origem
        System.out.println("Entrou em consulta Processo");
        ProcessoDao processoDao = new ProcessoDao();
        TbProcesso ret = null;
        TbProcesso filtro = new TbProcesso();
        String nmprocesso = null;
        filtro.setIdProcesso(idprocesso);
        ret = processoDao.pesquisar(filtro, acessoBanco);
        if (ret.getNmProcesso() != null) {
            setMsgProcesso(processo_cadastrado);
            setFlProcesso(true);
            return ret;
        } else {
            setMsgProcesso(processo_não_cadastrado);
            return null;
        }

    }

    public TbProcesso cosultaProcessoBdOrigem(int idprocesso, TbDadosAcessoBanco acessoBanco) { // consulta curso no banco de origem
        System.out.println("Entrou em consulta Processo");
        ProcessoDao processoDao = new ProcessoDao();
        TbProcesso ret = null;
        TbProcesso filtro = new TbProcesso();

        filtro.setIdProcesso(idprocesso);
        ret = processoDao.pesquisar(filtro, acessoBanco);
        if (ret.getNmProcesso() != null) {

            return ret;
        } else {
            return null;
        }

    }

    public TbCurso insereCurso(int cdCurso, TbDadosAcessoBanco acessoBancoOrigem, TbDadosAcessoBanco acessoBanco) {
        System.out.println("Entrou em Insere Curso");
        CursoDAO cursoDao = new CursoDAO();
        TbCurso ret = null;
        TbCurso filtro = new TbCurso();
        filtro.setIdCurso(cdCurso);
        ret = cursoDao.inserir(filtro, acessoBancoOrigem, acessoBanco);
        System.out.println("REt = " + ret);
        return ret;
    }

    public void insereDisciplina(int idDisciplina, String nomeDisciplina, TbDadosAcessoBanco acessoBanco) {

        Double nrNotaInicial = 0.0;
        int idTipoQuestão = 24;

        String nm_tipo_questao = "Questão Discursiva - Concurso Docentes";

        TbDisciplina disciplina = new TbDisciplina(idDisciplina, nomeDisciplina, nrNotaInicial, idTipoQuestão);

        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        disciplinaDAO.InsereDisciplina(acessoBanco, disciplina, nm_tipo_questao);

    }

    public void insereDisciplinaVestibular(int idDisciplina, String nomeDisciplina, TbDadosAcessoBanco acessoBanco) {

        if (idDisciplina == 13) {
            Double nrNotaInicial = 0.0;
            int idTipoQuestão = 22;
            String nm_tipo_questao = "Redação";
            TbDisciplina disciplina = new TbDisciplina(idDisciplina, nomeDisciplina, nrNotaInicial, idTipoQuestão);
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
            disciplinaDAO.InsereDisciplina(acessoBanco, disciplina, nm_tipo_questao);
        } else {
            Double nrNotaInicial = 0.0;
            int idTipoQuestão = 21;
            String nm_tipo_questao = "Discursiva";
            TbDisciplina disciplina = new TbDisciplina(idDisciplina, nomeDisciplina, nrNotaInicial, idTipoQuestão);
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
            disciplinaDAO.InsereDisciplina(acessoBanco, disciplina, nm_tipo_questao);
        }
    }

    public void insereProcesso(int idProcesso, String nomeProcesso, int nrAnoProcesso, int idTipoProcesso, TbDadosAcessoBanco acessoBanco) {

        tbProcesso = new TbProcesso(idProcesso, nomeProcesso, nrAnoProcesso, idTipoProcesso);

        ProcessoDao processoDao = new ProcessoDao();
        processoDao.InsereProcesso(tbProcesso, acessoBanco);

    }

    public void consultaCursoBdDestino(int cdCurso, TbDadosAcessoBanco acessoBanco) { // consulta curso no banco de destino

        CursoDAO disDao = new CursoDAO();
        TbCurso ret = null;
        TbCurso filtro = new TbCurso();
        filtro.setIdCurso(cdCurso);
        String msg = "";
        ret = disDao.pesquisar(filtro, acessoBanco);
        System.out.println("consulta destino:" + ret.getIdCurso());
        if (ret.getIdCurso() != null) {
            setMsgCurso(cadastrado);
            setFlCurso(true);
        } else {
            setMsgCurso(não_cadastrado);
        }
        //     return msg;

    }

    public void consultaTipoQuestao(TbDadosAcessoBanco acessoBanco, JComboBox jComboBox1) {
        System.out.println("Entrou em consulta TipoQuestao");
        TipoQuestaoDAO tpQDao = new TipoQuestaoDAO();
        //  HashMap<Integer, String> ret = null;
        Hashtable<Integer, String> source = new Hashtable<Integer, String>();
        HashMap<Integer, String> map = new HashMap(source);

        List lista = new ArrayList();
        //ret = tpQDao.pesquisar(acessoBanco, jComboBox1);
        map = tpQDao.pesquisar(acessoBanco);

        //     System.out.println("ret = " + map);
        Iterator<Integer> keySetIterator = map.keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            //     jComboBox1.addItem(key + " - " + map.get(key));
            jComboBox1.addItem(map.get(key));
            //     System.out.println("key: " + key + " value: " + map.get(key));
        }

    }

    public void inserirTpDisciplina(TbDadosAcessoBanco acessoBanco, TbDisciplina disciplina, String tpDisciplina) {

        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        disciplinaDAO.InsereDisciplina(acessoBanco, disciplina, nomeDisciplina);

    }

    public TbCandidato apenasConsultaDados(File arquivoImportar, TbDadosAcessoBanco bancoOrigem, TbDadosAcessoBanco bancoDestinoCorrecao) {
        //OBTÉM CÓDIGO DE BARRAS
        this.decodificaCodigoBarras(arquivoImportar);

        TbCandidato cand = this.lerCandidato(this.getInscricao(), this.getNomeArquivo(), arquivoImportar, bancoOrigem);

        //this.setArquivoTrabalho(arquivoImportar);
        //Pegar código da disciplina (D), código da questão (Q) e inscrição (I)
      
        consultaCurso(cand.getIdCurso(), bancoOrigem);
        consultaCursoBdDestino(cand.getIdCurso(), bancoDestinoCorrecao); // consulta se curso está cadastrada no banco destino

        consultaDisciplinaOrigem(getDisciplina(), bancoOrigem);

        consultaDisciplina(cand.getIdCurso(), bancoDestinoCorrecao); //docentes

        consultaDisciplinaVestibular(getDisciplina(), bancoDestinoCorrecao); //vestibular

        tbProcesso = consultaProcessoBdDestino(cand.getIdProcesso().getIdProcesso(), bancoDestinoCorrecao); //consulta se o processo está no banco de 
      
        if (tbProcesso == null) {
            tbProcesso = cosultaProcessoBdOrigem(cand.getIdProcesso().getIdProcesso(), bancoOrigem);
        }

        setCurso(cand.getIdCurso());
        setProcesso(cand.getIdProcesso().getIdProcesso());
        setNomeProcesso(tbProcesso.getNmProcesso());
        setNrAnoProcessso(tbProcesso.getNrAno());
        setId_tipo_processo(tbProcesso.getIdTipoProcesso());
        
        //  setTbProcesso(tbProcesso);
        // setCurso(810);
        if (cand != null && cand.getIdProcesso() != null) {
            System.out.println("cand = " + cand);
            return cand;
        } else {
            return null;
        }
    }

    public void selectCurso(TbDadosAcessoBanco bancoOrigem, TbDadosAcessoBanco bancoDestino, int idCurso) throws SQLException {
        /*    System.out.println("Entrou em selectCurso");
         CursoDAO cursoDAO = new CursoDAO();
         TbCurso filtro = new TbCurso();
         TbCurso ret = null;
         filtro.setIdCurso(idCurso);        
         ret =  cursoDAO.pesquisar(filtro, bancoOrigem);
         cursoDAO.inserir(filtro, bancoDestino);
        
         System.out.println("ret: " + ret);*/
    }

    public long importaArquivo(TbDadosAcessoBanco bancoOrigem, TbDadosAcessoBanco bancoDestinoCorrecao, InputStream imagemAInserir, BufferedImage imagemCorrecao, BufferedImage areaCB1, BufferedImage areaCB2) {
        this.decodificaCodigoBarras(imagemCorrecao, areaCB1, areaCB2);

        TbCandidato cand = this.lerCandidato(this.getInscricao(), this.getNomeArquivo(), bancoOrigem);
        //Pegar código da disciplina (D), código da questão (Q) e inscrição (I)

        //Importar
        if (cand != null && cand.getIdProcesso() != null) {

            TbInscricao gravaCand = new TbInscricao();
            //PENDENTE - Corrigir a inserção do número de inscrição no banco de dados
            gravaCand.setNrInscricao(Integer.valueOf(cand.getNrInscricao()));
            gravaCand.setNmArquivo(cand.getNmArquivo());
            String serial = uteis.MD5Hexa.getMD5("" + gravaCand.getIdInscricao() + gravaCand.getNmArquivo() + cand.getNrCpf());
            gravaCand.setCdSerial(serial);

            RespostaDAO rd = new RespostaDAO();

            long consultaJaExisteInscricao = rd.consultaInscricao(bancoDestinoCorrecao, gravaCand, this.getDisciplina());

            if (consultaJaExisteInscricao <= 0) {
                long insercaoInscricao = rd.insereCandidatoInscricao(bancoDestinoCorrecao, gravaCand);

                TbResposta resp = new TbResposta();
                resp.setCdSerial(serial);
                resp.setIdCurso(cand.getIdCurso());
                resp.setIdInscricao(insercaoInscricao);
                resp.setIdProcesso(cand.getIdProcesso());
                resp.setNrQuestao(this.getQuestao());
                resp.setIdDisciplina(this.getDisciplina());

                ///////////////////////////// ***** INSERE RESPOSTA ******* \\\\\\\\\\\\\\\\\\\\\\\\\\\
                long idResp = rd.insereRespostpa(bancoDestinoCorrecao, resp);

                TbRespostaImagem rImagem = new TbRespostaImagem();
                rImagem.setIdResposta(idResp);

                if (imagemAInserir != null) {
                    //long idInserirImagem = rd.insereImagem(bancoDestinoCorrecao, rImagem, imagemAInserir, arquivoImportar.length(), this.getQuestao());
                    long idInserirImagem = rd.insereImagemPorQuestao(bancoDestinoCorrecao, rImagem, imagemAInserir, this.getQuestao());
                    //inserir na tabela de controle (Nome do arquivo, status (sucesso ou com erro), mensagem de erro, número de inscrição)
                    if (idInserirImagem == 0) {
                        System.out.println("Erro ao inserir a imagem - inscrição: " + this.getInscricao() + " - arquivo: "
                                + this.getNomeArquivo() + " - " + this.getInscricao() + " - " + this.getNomeArquivo() + " - " + this.getNomeDisciplina());
                        return 0; //deu erro;
                    } else {
                        System.out.println("Imagem inserida com sucesso - inscrição: " + this.getInscricao());
                        return idInserirImagem;
                    }
                }
            } else {
                TbRespostaImagem rImagem = new TbRespostaImagem();
                rImagem.setIdResposta(consultaJaExisteInscricao);
                //inserir na tabela de controle (Nome do arquivo, status (sucesso ou com erro), mensagem de erro, número de inscrição)

                long idInserirImagem = rd.atualizaImagem(bancoDestinoCorrecao, rImagem, imagemAInserir, this.getQuestao());
                //long idInserirImagem = rd.insereImagem(bancoDestinoCorrecao, rImagem, imagemAInserir, arquivoImportar.length(), this.getQuestao());

                return idInserirImagem;
            }

        }
        return 0;

    }

    private void decodificaCodigoBarras(BufferedImage imagemCorrecao, BufferedImage areaCB1, BufferedImage areaCB2) {
        //TENTA LER CÓDIGO DE BARRAS 1 (priemiro - parte superior do papel)
        BufferedImage primeiraTentativa = areaCB1;
        String codigoBarras = CodigoBarrasZXing.lerCodigoBarras(primeiraTentativa);
        if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
            //TENTA LER CÓDIGO DE BARRAS 2 (o segundo, na parte inferior do papel)
            BufferedImage segundaTentativa = areaCB2;
            codigoBarras = CodigoBarrasZXing.lerCodigoBarras(segundaTentativa);
        }

        if (codigoBarras == null || codigoBarras.trim().equalsIgnoreCase("")) {
            //Se ainda assim houver problema para ler o código de barras, agora tenta-se ler da imagem INTEIRA.
            codigoBarras = CodigoBarrasZXing.lerCodigoBarras(imagemCorrecao);
        }

        if (codigoBarras != null && !codigoBarras.trim().equalsIgnoreCase("")) {
            String codigosDetectados[] = {""};
            DecodificaInscricao di = new DecodificaInscricao();
            codigosDetectados = di.carregaInscricaoPeloCodigoBarras(codigoBarras);

            try {
                this.setInscricao(codigosDetectados[0]);
                this.setQuestao(Integer.valueOf(codigosDetectados[1]));
                this.setDisciplina(Integer.valueOf(codigosDetectados[2]));
            } catch (Exception ex) {
                System.out.println("Erro ao ler código de barras");
                this.setInscricao("");
                this.setQuestao(0);
                this.setDisciplina(0);

            }

        }

        return;
    }

    private TbCandidato lerCandidato(String inscricao, String arquivo, TbDadosAcessoBanco acessoBanco) {

        CandidatoDAO cdao = new CandidatoDAO();
        TbCandidato filtro = new TbCandidato();
        TbCandidato ret = null;
        filtro.setNrInscricao(inscricao);

        ret = cdao.pesquisar(filtro, acessoBanco); //Procura por número da inscrição
        //if (ret != null) {
        //ret.setCdConclusaoEnsinoMedio("1");
        //}

        if (ret.getIdCandidato() == null && (!arquivo.equalsIgnoreCase(""))) { //Se não achou, procura pelo nome do arquivo
            filtro = null;
            filtro = new TbCandidato();
            filtro.setNrInscricao(arquivo);
            ret = cdao.pesquisar(filtro, acessoBanco);
            if (ret != null) {
                ret.setCdConclusaoEnsinoMedio("2");
            }
        }

        return ret;
    }
}
