/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author janio
 */
public class BasicDAO {

    protected void desconecta(Connection cn){
        if(cn!=null){
            try {
                cn.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

}
