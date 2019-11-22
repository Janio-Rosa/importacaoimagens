/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author janio
 */
public class ConnectionFactory {

    public ConnectionFactory() {
    }

    public static Connection getConnecion() {
        Connection con = null;

        try {
            // carregando a classe que representa o driver de conexão com o
            // banco de dados
            try {
                Class.forName("org.postgresql.Driver");
                //obtendo a conexão com o banco de dados
                String url = "jdbc:postgresql://192.168.0.10/usuarios";

                //conexão com o banco de dados
                con = DriverManager.getConnection(url, "", "");
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Couldn't find the driver!");
                cnfe.printStackTrace();
            }

            // retornando conexão
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnecionSistemas() {
        Connection con = null;

        try {
            // carregando a classe que representa o driver de conexão com o
            // banco de dados
            try {
                Class.forName("org.postgresql.Driver");
                //obtendo a conexão com o banco de dados
                String url = "jdbc:postgresql://192.168.0.10/usuarios";
                //String url = "jdbc:postgresql://127.0.0.1/usuarios";

                //conexão com o banco de dados
                con = DriverManager.getConnection(url, "", "");
                //con = DriverManager.getConnection(url, "teste", "teste");
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Couldn't find the driver!");
                cnfe.printStackTrace();
            }

            // retornando conexão
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnecionSistemas(String banco, String esquema, String usuario, String senha) {
        Connection con = null;

        try {
            try {
                Class.forName("org.postgresql.Driver");
                String host = "192.168.0.10";
                String url = "jdbc:postgresql://" + host + "/" + banco + "?searchpath=" + esquema;

                con = DriverManager.getConnection(url, usuario, senha);
                /*
                 if( (con!=null) && (con instanceof Connection) && (!con.isClosed() ) ){
                 PreparedStatement ps = con.prepareStatement("set search_path to"+esquema);
                 ps.execute();
                 }*/
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Couldn't find the driver!");
                cnfe.printStackTrace();
            }

            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnecionSistemas(String host, String banco, String esquema, String usuario, String senha) {
        Connection con = null;

        try {
            try {
                if (esquema.trim().equalsIgnoreCase("")) {
                    esquema = "public";
                }
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://" + host + "/" + banco + "?searchpath=" + esquema;
                //String url = "jdbc:postgresql://"+host+"/"+banco;

                con = DriverManager.getConnection(url, usuario, senha);
                /*
                 if( (con!=null) && (con instanceof Connection) && (!con.isClosed() ) ){
                 PreparedStatement ps = con.prepareStatement("set search_path to"+esquema);
                 ps.execute();
                 }*/
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Couldn't find the driver!");
                cnfe.printStackTrace();
            }

            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
