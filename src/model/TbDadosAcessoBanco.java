/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author administrator
 */
public class TbDadosAcessoBanco {

    private String nmHost="";
    private String nmBanco="";
    private String nmEsquema="public";
    private String nmUsuario="";
    private String nmSenha="";

    public TbDadosAcessoBanco() {
        this("","","","","");
    }
    
    public TbDadosAcessoBanco(String host,String bancoDeDados, String esquema, String usuario, String senha){
        this.nmHost=host;
        this.nmBanco=bancoDeDados;
        this.nmEsquema=esquema;
        this.nmUsuario=usuario;
        this.nmSenha=senha;
    }

    public String getNmBanco() {
        return nmBanco;
    }

    public void setNmBanco(String nmBanco) {
        this.nmBanco = nmBanco;
    }

    public String getNmEsquema() {
        return nmEsquema;
    }

    public void setNmEsquema(String nmEsquema) {
        this.nmEsquema = nmEsquema;
    }

    public String getNmSenha() {
        return nmSenha;
    }

    public void setNmSenha(String nmSenha) {
        this.nmSenha = nmSenha;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getNmHost() {
        return nmHost;
    }

    public void setNmHost(String nmHost) {
        this.nmHost = nmHost;
    }

    @Override
    public String toString() {
        return "TbDadosAcessoBanco{" + "nmHost=" + nmHost + ", nmBanco=" + nmBanco + ", nmEsquema=" + nmEsquema + ", nmUsuario=" + nmUsuario + ", nmSenha=******}";
    }

    
}
