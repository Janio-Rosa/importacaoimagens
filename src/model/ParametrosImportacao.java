/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Rectangle;
import java.io.File;

/**
 *
 * @author janio
 */
public class ParametrosImportacao {

    private TbDadosAcessoBanco acessoAoBancoOrigem;
    private TbDadosAcessoBanco acessoAoBancoDestino;
    private TbDadosAcessoBanco[] acessosAosBancos;
    private File arquivoTrabalho=null;
    private Rectangle areaImagemCorrecao;
    private Rectangle areaImagemCB1;
    private Rectangle areaImagemCB2;
    private String caminhoOrigem;

    public ParametrosImportacao() {
    }

    public ParametrosImportacao(TbDadosAcessoBanco acessoAoBancoOrigem, TbDadosAcessoBanco acessoAoBancoDestino, TbDadosAcessoBanco[] acessosAosBancos, Rectangle areaImagemCorrecao, Rectangle areaImagemCB1, Rectangle areaImagemCB2, String caminhoOrigem) {
        this.acessoAoBancoOrigem = acessoAoBancoOrigem;
        this.acessoAoBancoDestino = acessoAoBancoDestino;
        this.acessosAosBancos = acessosAosBancos;
        this.areaImagemCorrecao = areaImagemCorrecao;
        this.areaImagemCB1 = areaImagemCB1;
        this.areaImagemCB2 = areaImagemCB2;
        this.caminhoOrigem = caminhoOrigem;
    }

    public TbDadosAcessoBanco getAcessoAoBancoDestino() {
        return acessoAoBancoDestino;
    }

    public void setAcessoAoBancoDestino(TbDadosAcessoBanco acessoAoBancoDestino) {
        this.acessoAoBancoDestino = acessoAoBancoDestino;
    }

    public TbDadosAcessoBanco getAcessoAoBancoOrigem() {
        return acessoAoBancoOrigem;
    }

    public void setAcessoAoBancoOrigem(TbDadosAcessoBanco acessoAoBancoOrigem) {
        this.acessoAoBancoOrigem = acessoAoBancoOrigem;
    }

    public TbDadosAcessoBanco[] getAcessosAosBancos() {
        return acessosAosBancos;
    }

    public void setAcessosAosBancos(TbDadosAcessoBanco[] acessosAosBancos) {
        this.acessosAosBancos = acessosAosBancos;
    }

    public Rectangle getAreaImagemCB1() {
        return areaImagemCB1;
    }

    public void setAreaImagemCB1(Rectangle areaImagemCB1) {
        this.areaImagemCB1 = areaImagemCB1;
    }

    public Rectangle getAreaImagemCB2() {
        return areaImagemCB2;
    }

    public void setAreaImagemCB2(Rectangle areaImagemCB2) {
        this.areaImagemCB2 = areaImagemCB2;
    }

    public Rectangle getAreaImagemCorrecao() {
        return areaImagemCorrecao;
    }

    public void setAreaImagemCorrecao(Rectangle areaImagemCorrecao) {
        this.areaImagemCorrecao = areaImagemCorrecao;
    }

    public File getArquivoTrabalho() {
        return arquivoTrabalho;
    }

    public void setArquivoTrabalho(File arquivoTrabalho) {
        this.arquivoTrabalho = arquivoTrabalho;
    }

    public String getCaminhoOrigem() {
        return caminhoOrigem;
    }

    public void setCaminhoOrigem(String caminhoOrigem) {
        this.caminhoOrigem = caminhoOrigem;
    }

    
}
