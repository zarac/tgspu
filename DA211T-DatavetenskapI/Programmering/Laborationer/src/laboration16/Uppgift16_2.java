package laboration16;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Uppgift16_2 implements Icon
{
    private final int WIDTH = 420, HEIGHT = 420;

    ImageIcon image1;
    ImageIcon image2;
    ImageIcon image3;
    ImageIcon image4;

    public Uppgift16_2()
    {
        image1 = new ImageIcon("Z:/me/studies/mah-spelutveckling/da211t/static/laboration16/Winter.jpg");
        image2 = new ImageIcon("Z:/me/studies/mah-spelutveckling/da211t/static/laboration16/Spring.jpg");
        image3 = new ImageIcon("Z:/me/studies/mah-spelutveckling/da211t/static/laboration16/Summer.jpg");
        image4 = new ImageIcon("Z:/me/studies/mah-spelutveckling/da211t/static/laboration16/Autumn.jpg");
    }


    /**
     * {@inheritDoc}
     * @see Icon#paintIcon(Component,Graphics,int,int)
     */
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        // Background
        //c.setBackground(Color.BLACK);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 420, 420);

        g.drawImage(image1.getImage(), x+20, y+20, 185, 185, null);
        g.drawImage(image2.getImage(), x+215, y+20, 185, 185, null);
        g.drawImage(image3.getImage(), x+20, y+215, 185, 185, null);
        g.drawImage(image4.getImage(), x+215, y+215, 185, 185, null);
    }


    /**
     * {@inheritDoc}
     * @see Icon#getIconWidth()
     */
    public int getIconWidth()
    {
        return WIDTH;
    }

    /**
     * {@inheritDoc}
     * @see Icon#getIconHeight()
     */
    public int getIconHeight()
    {
        return HEIGHT;
    }


    public static void main(String[] argv)
    {
        IconWindow.showIcon(new Uppgift16_2());
    }
}
