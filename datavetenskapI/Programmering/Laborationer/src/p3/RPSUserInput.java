package p3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A window with buttons to play/control the program.
 *
 * @author Hannes Landstedt (hannes.landstedt@gmail.com)
 * @version null
 */
public class RPSUserInput implements ActionListener
{
    private RPSController controller;

    private JFrame frame;

    private JPanel handPanel;
    private JButton rock;
    private JButton paper;
    private JButton scissor;

    private JPanel controlPanel;
    private JButton restart;
    private JButton quit;


    public RPSUserInput(RPSController p_controller)
    {
        controller = p_controller;

        // The window itself
        frame = new JFrame("Rock Paper Scissor - User Input");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 220, 400, 100);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(2, 1));
        //frame.setLayout(new FlowLayout());
        frame.setVisible(true);

        // Hand chooser
        handPanel = new JPanel();
        handPanel.setLayout(new GridLayout(1, 3));
        frame.add(handPanel);

        rock = new JButton("Rock");
        rock.addActionListener(this);
        handPanel.add(rock);

        paper = new JButton("Paper");
        paper.addActionListener(this);
        handPanel.add(paper);

        scissor = new JButton("Scissor");
        scissor.addActionListener(this);
        handPanel.add(scissor);


        // Control
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 3));
        frame.add(controlPanel);

        restart = new JButton("Restart");
        restart.addActionListener(this);
        controlPanel.add(restart);

        quit = new JButton("Quit");
        quit.addActionListener(this);
        controlPanel.add(quit);
    }


    /**
     * {@inheritDoc}
     * @see ActionListener#actionPerformed(ActionEvent)
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == rock)
            controller.playHands(1, controller.computer.newChoice());
        else if (e.getSource() == paper)
            controller.playHands(2, controller.computer.newChoice());
        else if (e.getSource() == scissor)
            controller.playHands(3, controller.computer.newChoice());
        else if (e.getSource() == restart)
            controller.restart();
        else if (e.getSource() == quit)
            controller.quit();
    }
}
