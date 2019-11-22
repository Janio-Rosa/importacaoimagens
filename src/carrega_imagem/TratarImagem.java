package carrega_imagem;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class TratarImagem {

    private BufferedImage imagem;
    private BufferedImage imagemBKP;
    private int corte = 322;

    public int getCorte() {
        return corte;
    }

    public void setImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public void setCorte(int corte) {
        this.corte = corte;
    }

    public TratarImagem(BufferedImage imagem) {
        this.imagem = imagem;
    }

    public TratarImagem() {

    }

    public TratarImagem(InputStream imagem) {
        try {
            this.imagem = ImageIO.read(imagem);
        } catch (IOException ex) {
            Logger.getLogger(TratarImagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public InputStream getCropRedacao1() {
        return corta(0, 0, this.imagem.getWidth(), getCorte());
    }

    public InputStream getCropRedacao2() {
        return corta(0, (getCorte()+1), this.imagem.getWidth(), this.imagem.getHeight() - (getCorte()+1));
    }

    private InputStream corta(int x, int y, int z, int w) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage resultado = this.imagem.getSubimage(x, y, z, w);
            this.imagemBKP = this.imagem.getSubimage(x, y, z, w);
            ImageIO.write(resultado, "JPEG", baos);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            return is;
        } catch (IOException ex) {

            Logger.getLogger(TratarImagem.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    private BufferedImage rotate90DX(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();

        BufferedImage biFlip = new BufferedImage(height, width, bi.getType());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //biFlip.setRGB(height - 1 - j, width - 1 - i, bi.getRGB(i, j));
                biFlip.setRGB(height-1-j, i, bi.getRGB(i, j));
            }
        }

        return biFlip;
    }
    
    private BufferedImage rotate90SX(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();

        BufferedImage biFlip = new BufferedImage(height, width, bi.getType());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //biFlip.setRGB(j, i, bi.getRGB(i, j));
                biFlip.setRGB(j, width-1-i, bi.getRGB(i, j));
            }
        }

        return biFlip;
    }
    
    public InputStream roda90Direita() {
        return this.converteBufferedImageParaInputStream(this.rotate90DX(this.imagem));
    }

    public InputStream roda90Direita2Vezes() {
        return this.converteBufferedImageParaInputStream(this.rotate90DX(this.rotate90DX(this.imagem)));
    }
    
    public InputStream roda90Esquerda() { 
        return this.converteBufferedImageParaInputStream(this.rotate90SX(this.imagem));
    }
    
    public InputStream roda90Esquerda2Vezes() { 
        return this.converteBufferedImageParaInputStream(this.rotate90SX(this.rotate90SX(this.imagem)));
    }
    
    
    private InputStream converteBufferedImageParaInputStream(BufferedImage converte){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(converte, "JPEG", baos);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            return is;
        } catch (IOException ex) {
            Logger.getLogger(TratarImagem.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    

    public InputStream converteImagemAtual() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage resultado = this.imagem;
            ImageIO.write(resultado, "JPEG", baos);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            return is;
        } catch (IOException ex) {

            Logger.getLogger(TratarImagem.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public static InputStream converteImagemDada(BufferedImage imagemAConverter) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage resultado = imagemAConverter;
            ImageIO.write(resultado, "JPEG", baos);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            return is;
        } catch (IOException ex) {

            Logger.getLogger(TratarImagem.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    
    public static BufferedImage cropSubimageWithRectangle(BufferedImage original,Rectangle areaDeCorte){
        BufferedImage retorno = null;
        int w = areaDeCorte.width;
        int h = areaDeCorte.height;
        int xx = areaDeCorte.x ;
        int yy = areaDeCorte.y ;
        try{
            retorno = original.getSubimage((int) (xx * 2.5), (int) (yy * 2.5), (int) (w * 2.5), (int) (h * 2.5));
        }catch(Exception ex){
            System.out.println("Erro: "+ex.getMessage());
        }
        return retorno;
    }
    
    
}
