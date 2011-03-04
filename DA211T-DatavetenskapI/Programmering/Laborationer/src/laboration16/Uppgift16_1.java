package laboration16;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class Uppgift16_1 implements Icon
{
    private final int WIDTH = 480, HEIGHT = 320;
    //private Component component;
    //private Graphics graphics;

    /**
     * {@inheritDoc}
     * @see Icon#paintIcon(Component,Graphics,int,int)
     */
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        //component = c;
        //graphics = g;

        //g.drawPolygon(new int[]{20, 30, 40, 30, 20}, new int[]{20, 30, 40, 50, 60}, 5);
        //g.drawPolygon(new int[]{20, 30, 40, 50, 60}, new int[]{20,230, 40, 50, 60}, 5);
        

        // Head
        g.setColor(Color.YELLOW);
        g.fillOval(40, 70, 190, 150);

        // Left Eye
        g.setColor(Color.BLACK);
        g.drawOval(50, 80, 30, 60);
        g.setColor(Color.RED);
        g.fillOval(60, 90, 15, 30);

        // Right Eye
        g.setColor(Color.BLACK);
        g.drawOval(150, 80, 30, 60);
        g.setColor(Color.RED);
        g.fillOval(155, 90, 15, 30);

        // Mouth
        g.setColor(Color.BLACK);
        g.drawArc(60, 130, 70, 55, 270, 90);

        //g.setColor(Color.CYAN);
        //g.fillOval(160, 190, 115, 130);

        //g.setColor(Color.GREEN);
        //g.draw3DRect(100, 100, 100, 100, true);

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
        IconWindow.showIcon(new Uppgift16_1());
    }
}
