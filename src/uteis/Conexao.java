/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author katia
 */

public class Conexao {

    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://192.168.0.135:5432/db_teste";
    private String usuario = "teste";
    private String senha = "teste";
    public Connection conexao;
    public Statement statement;
    public ResultSet resultset;

    public boolean conecta() {
        boolean result = true;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conectou");
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver nao localizado: " + Driver);
            result = false;
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Deu erro na conexao" + " com a fonte de dados: " + Fonte);
            result = false;
        }

        return result;
    }
    
        public boolean conectaBanco(String banco, String host, String usuaribanco, String senhabanco) {
        boolean result = true;
            String urlBanco = "jdbc:postgresql://" + host + ":5432/" + banco;        
        try {
            Class.forName(driver);

            conexao = DriverManager.getConnection(urlBanco, usuaribanco, senhabanco);
            JOptionPane.showMessageDialog(null, "Conectou");
            desconecta();
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver nao localizado: " + Driver);
            result = false;
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Deu erro na conexao" + " com a fonte de dados: " + Fonte);
            result = false;
        }
        System.out.println("URL: >>> " + urlBanco);
        return result;
    }

    public void desconecta() {

        boolean result = true;
        try {
            conexao.close();
            JOptionPane.showMessageDialog(null, "Banco Desconectado");
        } catch (SQLException fecha) {
            JOptionPane.showMessageDialog(null, "Nao foi possivel" + " fechar o banco de dados: " + fecha.getMessage());
            result = false;
        }
    }

    public void executeSQL(String sql) {
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, sqlex.getMessage());
        }
    }
}