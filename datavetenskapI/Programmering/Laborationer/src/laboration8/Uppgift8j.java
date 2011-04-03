/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package laboration8;

import java.awt.Color;
import java.util.Random;
import laboration7.PaintWindow;

/**
 * Create some modern art, it's fun!
 * @author zarac
 */
public class Uppgift8j
{
    private PaintWindow window;
    private Random random;

    private int windowWidth;
    private int windowHeight;

    /**
     * Plain dependency injection...
     * @param window
     * @param random
     */
    public Uppgift8j(PaintWindow window, Random random)
    {
        this.window = window;
        this.random = random;

        windowWidth = window.getWidth();
        windowHeight = window.getHeight();
    }

    /**
     * Will draw a randomly colored, randomly positioned line within the window with a width between 4 and 20.
     */
    public void drawRandomLine()
    {
        window.line(
                random.nextInt(windowWidth),
                random.nextInt(windowHeight),
                random.nextInt(windowWidth),
                random.nextInt(windowHeight),
                new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)),
                random.nextInt(16)+4
                );
    }

    public static void main(String[] argv)
    {
        Uppgift8j uppgift8j = new Uppgift8j(new PaintWindow(), new Random());

        uppgift8j.window.setBackground(Color.BLACK);

        // Draw 10 random lines
        for (int i = 1; i<=20; i++)
        {
            uppgift8j.drawRandomLine();
        }
    }
}
