/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cropimage;

import cropimage.Cropping;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author katia
 */
class ClipMoverErro extends MouseInputAdapter{
     CroppingErro cropping;
    Point offset;
    boolean dragging;
 
    public ClipMoverErro(CroppingErro c)
    {
        cropping = c;
        offset = new Point();
        dragging = false;
    }
 
    public void mousePressed(MouseEvent e)
    {
        Point p = e.getPoint();
        if(cropping.clip.contains(p))
        {
            offset.x = p.x - cropping.clip.x;
            offset.y = p.y - cropping.clip.y;
            dragging = true;
        }
    }
 
    public void mouseReleased(MouseEvent e)
    {
        dragging = false;
    }
 
    public void mouseDragged(MouseEvent e)
    {
        if(dragging)
        {
            int x = e.getX() - offset.x;
            int y = e.getY() - offset.y;
            cropping.setClip(x, y);
        }
    }
}
