/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cropimage;

import carrega_imagem.TratarImagem;
import cropimage.Cropping;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author katia
 */
class ClipMover extends MouseInputAdapter{
     Cropping cropping;
    Point offset;
    boolean dragging;
    
    /*
    FormTesteImagem formTestarImagemCB1 =new FormTesteImagem();
    FormTesteImagem formTestarImagemCB2 =new FormTesteImagem();
    FormTesteImagem formTestarClippedImage =new FormTesteImagem();
    
    BufferedImage imagemOriginal = null;
    Rectangle retangulo = null;
    Rectangle retanguloCB1 = null;
    Rectangle retanguloCB2 = null;
    */
 
    public ClipMover(Cropping c)
    {
        cropping = c;
        offset = new Point();
        dragging = false;
    }
 
    public void mousePressed(MouseEvent e)
    {
        
        Point p = e.getPoint();
        //if(cropping.clip.contains(p))
        if(cropping.getRetanguloAtual().contains(p))
        {
            offset.x = p.x - cropping.getRetanguloAtual().x;
            offset.y = p.y - cropping.getRetanguloAtual().y;
            dragging = true;
        }

    }
 
    public void mouseReleased(MouseEvent e)
    {
        dragging = false;
        //formTestarClippedImage.desenharImagem(TratarImagem.cropSubimageWithRectangle(this.imagemOriginal, this.retangulo), "Imagem Correção");
        //formTestarImagemCB1.desenharImagem(TratarImagem.cropSubimageWithRectangle(this.imagemOriginal,this.retanguloCB1),"Imagem CB1");
        //formTestarImagemCB2.desenharImagem(TratarImagem.cropSubimageWithRectangle(this.imagemOriginal, this.retanguloCB2), "Imagem CB2");
        this.cropping.atualizaImagens();
    }
 
    public void mouseDragged(MouseEvent e)
    {
        if(dragging)
        {
            int x = e.getX() - offset.x;
            int y = e.getY() - offset.y;
            
            //cropping.setClip(x, y);
            cropping.setClip(cropping.getRetanguloAtual(),x, y);
        }

    }

/*
    public void setRetangulo(Rectangle retangulo) {
        this.retangulo = retangulo;
    }


    public void setImagemOriginal(BufferedImage imagemOriginal) {
        this.imagemOriginal = imagemOriginal;
    }

    public void setRetanguloCB1(Rectangle retanguloCB1) {
        this.retanguloCB1 = retanguloCB1;
    }

    public void setRetanguloCB2(Rectangle retanguloCB2) {
        this.retanguloCB2 = retanguloCB2;
    }

    public void setFormTestarClippedImage(FormTesteImagem formTestarClippedImage) {
        this.formTestarClippedImage = formTestarClippedImage;
    }

    public void setFormTestarImagemCB1(FormTesteImagem formTestarImagemCB1) {
        this.formTestarImagemCB1 = formTestarImagemCB1;
    }

    public void setFormTestarImagemCB2(FormTesteImagem formTestarImagemCB2) {
        this.formTestarImagemCB2 = formTestarImagemCB2;
    }
*/

}
