/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package p2;

import java.awt.Color;
// Only needed for main, (testing)
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author zarac
 */
public class Race
{
    private PaintWindow window;
    private Car car1;
    private Car car2;

    private Track track;

    private JLabel result;

    Random random = new Random();

    public Race(PaintWindow window, Car car1, Car car2)
    {
        this.window = window;
//        window.addKeyListener(this);
        window.addKeyListener(new GameController());
        this.car1 = car1;
        this.car2 = car2;

        // Should be injected... (?)
        this.track = new Track();
    }

    public void action()
    {
        this.track.draw();
        this.resetCars();
        this.drawCars();
        this.doRace();
//        this.checkAndSetWinner();
        this.drawWinnerText();
    }

    private void doRace()
    {
        // - Cars should randomly go forward
        do
        {
//            car1.moveTo(car1.getX() - random.nextInt(5), car1.getY());
//            car2.moveTo(car2.getX() - random.nextInt(5), car2.getY());
            drawCars();
            PaintWindow.pause(10);
        }
        // - Race is over when ONE of the cars reach the finish line.
        while (car1.getX() > track.finishLineMargin && car2.getX() > track.finishLineMargin);
    }

    private void drawWinnerText()
    {
//        window.getContentPane().setLayout(new FlowLayout());
//        window.setLayout(new FlowLayout());
        window.setLayout(null);
        result = new JLabel("GAME ON!");
//        result.setText("AFEFE");
		result.setForeground(Color.BLACK);
//        window.setSize(500, 400);
        result.setFont(new Font("Serif", Font.BOLD, 14));
//        result.setLocation(10, 10);
        Container pane = window.getContentPane();
        pane.add(result);
//        window.getContentPane().add(result);
        pane.setVisible(true);
//        window.getContentPane().setVisible(true);
//        window.repaint();
//        window.show();

//        window.getContentPane().add(new JTextField("HEHAHFEHEHHE"));
//        JTextArea jtextnorth = new JTextArea("<html><h2>hej</h2></html>");
//        jtextnorth.setLocation(150, 150);
//        jtextnorth.setSize(100,100);
//        window.add(jtextnorth);
//        window.setVisible(true);
    }

    private void checkAndSetWinner()
    {
        if (car1.getX() == car2.getX())
        {
//            result = "The race is a tie!";
            result.setText("The race is a tie!");
        }
        else if (car1.getX() > car2.getX())
        {
//            result = "Car 1 won!";
            result.setText("Car 1 won!");
        }
        else
        {
//            result = "Car 2 won!";
            result.setText("Car 2 won!");
        }
    }

    private void resetCars()
    {
        // ToDo: Don't use startLineMargin here, would be nicer with a y pos
        car1.moveTo(
                track.startLineMargin
                    + track.startLineWidth,
                track.roadStartYPos
                    - car1.getImage().getIconHeight()/2
                    + (track.roadWidth- track.startLineWidth)/4);

        car2.moveTo(
                track.startLineMargin
                    + track.startLineWidth,
                (int)(track.roadStartYPos
                    - car2.getImage().getIconHeight()/2
                    + (track.roadWidth - track.startLineWidth) * 0.75));

        drawCars();
    }

    private void drawCars()
    {
        window.showImage(car1.getImage(), car1.getX(), car1.getY());
        window.showImage(car2.getImage(), car2.getX(), car2.getY());
    }


    public static void main(String[] args)
    {
        PaintWindow window = new PaintWindow();
        Car c1 = new Car(new ImageIcon("D:/me/Spelutveckling/DA211T/DA211TP2HT10/CarBlue.GIF"));
        Car c2 = new Car(new ImageIcon("D:/me/Spelutveckling/DA211T/DA211TP2HT10/CarRed.GIF"));
        Race race = new Race(window,c1,c2);
        race.action();
        if(args.length>0) {
            PaintWindow.pause(2000);
            window.dispose();
        }
    }


    public class GameController implements KeyListener
    {
        public void keyTyped(KeyEvent e)
        {
            int carSpeed = 5;

            System.out.println("e.getKeyCode() -> " + e.getKeyChar());
            //        throw new UnsupportedOperationException("Not supported yet.");
            //        int keyCode = (int)e.getKeyChar();

            int keyChar = e.getKeyChar();

            if (keyChar == 'a' || keyChar == 's')
            {
                car1.moveTo(car1.getX() - carSpeed, car1.getY());
            }
            else if (keyChar == 'k' || keyChar == 'l')
            {
                car2.moveTo(car2.getX() - carSpeed, car2.getY());
            }
            System.out.println(e.getKeyChar());
            //            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void keyPressed(KeyEvent e) {
//            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void keyReleased(KeyEvent e) {
//            throw new UnsupportedOperationException("Not supported yet.");
        }
    }


    /**
     * The track / course made for two cars to race on.
     */
    protected class Track
    {
        // ToDo: Privatize...
        Color backgroundColor = Color.YELLOW;

        int stripeLength = 30;
        int stripeWidth = 3;
        int stripeSpace = 15;
        Color stripeColor = Color.WHITE;

        int roadWidth = 100;
        Color roadColor = Color.GRAY;

        int roadStartYPos;
        int stripeStartYPos;

        int finishLineMargin = 25;
        int finishLineWidth = 5;
        Color finishLineColor = Color.MAGENTA;
//        Color finishLineColor = Color.RED;

        int startLineMargin; // from the left
        int startLineWidth = 2;
        Color startLineColor = Color.CYAN;


        public Track()
        {
            roadStartYPos = window.getHeight()/2;
            stripeStartYPos = roadStartYPos + roadWidth/2 - stripeWidth/2;
            startLineMargin = window.getWidth() - 50;
        }

        /**
         * Draw the track, including background etc. Everything static.
         */
        public void draw()
        {
            // Background, it's sand!
            window.fillRect(0, 0, window.getWidth(), window.getHeight(), backgroundColor);

            // Road
            window.fillRect(0, roadStartYPos, window.getWidth(), roadWidth, roadColor);

            // Road stripes
            // i = stripe y start pos
            for (int i = 5; i < window.getWidth(); i += (stripeSpace + stripeLength))
            {
                window.fillRect(i, stripeStartYPos, stripeLength, stripeWidth, stripeColor);
            }

            // Finish line
            window.fillRect(finishLineMargin, roadStartYPos, finishLineWidth, roadWidth, finishLineColor);

            // Start line
            window.fillRect(startLineMargin, roadStartYPos, startLineWidth, roadWidth, startLineColor);
        }
    }
}
