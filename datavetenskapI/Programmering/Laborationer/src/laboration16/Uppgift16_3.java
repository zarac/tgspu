package laboration16;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;

import java.awt.geom.Line2D;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Random;

import javax.swing.Icon;

public class Uppgift16_3 implements Icon
{
    public static int WIDTH = 480, HEIGHT = 320;

    private int numLines;
    /**
     * Constructs a new instance.
     */
    public Uppgift16_3(int p_numLines)
    {
        numLines = p_numLines;
    }

    /**
     * {@inheritDoc}
     * @see Icon#paintIcon(Component,Graphics,int,int)
     */
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Random random = new Random();


        Graphics2D g2d = (Graphics2D)g;
        for (int i = 1; i <= numLines; i++)
        {
            g2d.setStroke(new BasicStroke(random.nextInt(17) + 4));
            g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            g2d.draw(new Line2D.Double(random.nextInt(WIDTH), random.nextInt(HEIGHT), random.nextInt(WIDTH), random.nextInt(HEIGHT)));
        }
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
        IconWindow.showIcon(new Uppgift16_3(20));
    }
}
