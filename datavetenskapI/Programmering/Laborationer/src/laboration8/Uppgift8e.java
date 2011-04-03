package laboration8;
import javax.swing.ImageIcon;
import laboration7.*;
/**
 * Flytta bild i ett PaintWindow-f�nster
 * @author tsroax
 */
public class Uppgift8e {
    private PaintWindow window = new PaintWindow();

    public void leftRight( ImageIcon icon )
    {
        System.out.println("leftRight()");
        for(int x=0; x<=500; x+=5)
        {
            window.showImage(icon, x, 150);
            PaintWindow.pause(50);
        }
    }
    
    public void rightLeft( ImageIcon icon )
    {
        System.out.println("rightLeft()");
        for(int x=500; x>=0; x-=5)
        {
            window.showImage(icon, x, 150);
            PaintWindow.pause(50);
        }
    }

    public void upDown( ImageIcon icon )
    {
        System.out.println("upDown()");
        for(int y=0; y<=375; y+=5)
        {
            window.showImage(icon, 250, y);
            PaintWindow.pause(50);
        }
    }

    public void downUp( ImageIcon icon )
    {
        System.out.println("downUp()");
        for(int y=375; y>=0; y-=5)
        {
            window.showImage(icon, 250, y);
            PaintWindow.pause(50);
        }
    }

    public static void main(String[] args) {
        Uppgift8e u8e = new Uppgift8e();
        ImageIcon gubbe = new ImageIcon("D:/me/Spelutveckling/DA211T/DA211TL6HT10/Gubbe.jpg");
        System.out.println(gubbe.getIconWidth());
        u8e.leftRight(gubbe);
        u8e.rightLeft(gubbe);
        u8e.upDown(gubbe);
        u8e.downUp(gubbe);
    }
}
