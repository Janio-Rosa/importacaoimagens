/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.Code128Reader;
import java.awt.image.BufferedImage;

/**
 *
 * @author janio
 */
public class CodigoBarrasZXing {
    
    
    public static String lerCodigoBarras(BufferedImage imagemParaLeitura){
        try {
            String retorno="";
            LuminanceSource source = new BufferedImageLuminanceSource(imagemParaLeitura);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            //Reader reader = new MultiFormatReader();
            Reader reader = new Code128Reader();
            Result resultado = reader.decode(bitmap);
            
            retorno = resultado.getText();
            System.out.println("Código de barras lido :"+retorno);
            
            return retorno;
        } catch (NotFoundException  ex) {
            ex.printStackTrace();
            System.out.println("Erro ao tentar ler código de barras: "+ex.getMessage());
            return "";
        } catch (ChecksumException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao tentar ler código de barras: "+ex.getMessage());
            return "";
        } catch (FormatException ex) {
            ex.printStackTrace();
            System.out.println("Erro ao tentar ler código de barras: "+ex.getMessage());
            return "";
        }
    }
    
}
