/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class MD5Hexa {
    
    public static String getMD5(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
     
            byte byteData[] = md.digest();
     
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
             sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
            /*
            StringBuffer hexString = new StringBuffer();
                for (int i=0;i<byteData.length;i++) {
                        String hex=Integer.toHexString(0xff & byteData[i]);
                            if(hex.length()==1) hexString.append('0');
                            hexString.append(hex);
                }*/
            //return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5Hexa.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}