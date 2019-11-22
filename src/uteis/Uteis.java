/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uteis;

import dao.ConnectionFactory;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.Normalizer;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.util.logging.Logger;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import model.TbDadosAcessoBanco;

/**
 *
 * @author janio
 */
public class Uteis {

    public static final String DIRETORIO_DESTINO="DESIDENTIFICADAS";
    
    public static boolean ehDiretorio(File verifica){
       
        if(!verifica.exists())return true;
        if(verifica.isDirectory() && !verifica.isFile())return true;
        return false;
    }
    
    public static String retiraCaracteres(String nomeOriginal){
        String retorno="";
        retorno=nomeOriginal.replace(" ", "_").replace(".","").replace("-","").replace("/","").replace(".","").replace(".","").replace(".","");
        retorno = Normalizer.normalize(retorno, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return retorno;
    }

    public static String getOperatingSystemSep(){
        String retorno=java.nio.file.FileSystems.getDefault().getSeparator();;
        return retorno;
    }
    
    
    public static String criaDiretorioDestinoDesidentificadas(String origem){
        String retorno="";
        String diretorio=Uteis.DIRETORIO_DESTINO;
        
        try{
            File novoDiretorio = new File(origem + Uteis.getOperatingSystemSep() + diretorio);
            retorno=novoDiretorio.getAbsolutePath();
            novoDiretorio.mkdirs();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    public static String criaDiretorioDestino(String diretorio){
        String retorno="";
        
        try{
            File novoDiretorio = new File( diretorio);
            retorno=novoDiretorio.getAbsolutePath();
            novoDiretorio.mkdirs();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return retorno;
    }
    
    public static InputStream comprimeImagem(InputStream imagemComprimir){
        try {
            InputStream retorno = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream( baos );

            BufferedImage originalImage=ImageIO.read(imagemComprimir);
            Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");
            ImageWriter writer = (ImageWriter)iter.next();
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            //float quality= 0.5f; //Quantidade razoável
            //float quality= 0.5f; //reduzir em 40%
            float quality= 0.2f; //reduzir em 40%
            iwp.setCompressionQuality(quality);
            
            
            writer.setOutput(ios);
            IIOImage iooimage = new IIOImage(originalImage,null,null);
            writer.write(null,iooimage,iwp);
            writer.dispose();
            
            /*
            File file = new File("/home/janio/Documents/Diversos/temp/comprimir/"+nomeOriginal+"_compressed_.jpg");
            FileOutputStream gravarArquivo=new FileOutputStream(file);
            byte[] b = new byte[]{};
            gravarArquivo.write(baos.toByteArray());
            gravarArquivo.close();
             */

            
            retorno=new ByteArrayInputStream(baos.toByteArray());
            
            return retorno;
        } catch (IOException ex) {
            Logger.getLogger(Uteis.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void copiarStream(InputStream in, File destino) throws IOException {
        if (in != null) {
            in.reset();
            BufferedImage bi = ImageIO.read(in);
            ImageIO.write(bi, "JPEG", destino);
            /*
            OutputStream os = new FileOutputStream(destino);
            byte[] buf = new byte[2048];
            int length;
            
            while ((length = in.read(buf)) != -1) {
                os.write(buf, 0, length);
            }
            in.close();
            os.close();*/
        }
    }

    public static InputStream converteBufferedImageParaInputStream(BufferedImage converte){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(converte, "JPEG", baos);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            return is;
        } catch (IOException ex) {
            Logger.getLogger(Uteis.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    
    public static InputStream comprimeImagem(BufferedImage originalImage){
        try {
            InputStream retorno = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream( baos );

            Iterator iter = ImageIO.getImageWritersByFormatName("jpeg");
            ImageWriter writer = (ImageWriter)iter.next();
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            //float quality= 0.5f; //Quantidade razoável
            //float quality= 0.5f; //reduzir em 40%
            //float quality= 0.01f; //reduzir em 10%
            float quality= 0.01f;  //reduzir em 90%
            iwp.setCompressionQuality(quality);
            
            
            writer.setOutput(ios);
            IIOImage iooimage = new IIOImage(originalImage,null,null);
            writer.write(null,iooimage,iwp);
            writer.dispose();
            
            retorno=new ByteArrayInputStream(baos.toByteArray());
            
            return retorno;
        } catch (IOException ex) {
            Logger.getLogger(Uteis.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    

    public static Graphics2D redimensionaImagem(BufferedImage originalImage){
        BufferedImage original = originalImage;
        Image scaled = original.getScaledInstance(original.getWidth(), original.getHeight(), Image.SCALE_SMOOTH); // scale the image to a smaller one

        BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
        Graphics2D g = result.createGraphics();     

        g.drawImage(scaled, 0, 0, null); //draw the smaller image
        g.dispose();
        return g;
    }

    
    public static BufferedImage cropSubimageWithRectangle(BufferedImage original, Rectangle areaDeCorte) {
        BufferedImage retorno = null;
        int w = areaDeCorte.width;
        int h = areaDeCorte.height;
        int xx = areaDeCorte.x;
        int yy = areaDeCorte.y;
        try {
            retorno = original.getSubimage((int) (xx * 2.5), (int) (yy * 2.5), (int) (w * 2.5), (int) (h * 2.5));
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return retorno;
    }

    
    public static void testarConexao(TbDadosAcessoBanco acesso){
    
        Connection cnDestino = ConnectionFactory.getConnecionSistemas(acesso.getNmHost(), acesso.getNmBanco(), acesso.getNmEsquema(), acesso.getNmUsuario(), acesso.getNmSenha());
        
        
        if(cnDestino!=null){
            JOptionPane.showMessageDialog(null, "Conexão realizada com SUCESSO ao banco " + acesso + "!");
            try {
                cnDestino.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "ERRO ao conectar no banco : " + acesso);
        }
    
    }
}
