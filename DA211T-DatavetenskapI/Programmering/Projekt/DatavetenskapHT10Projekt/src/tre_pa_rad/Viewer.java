/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tre_pa_rad;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author wiik
 */
public class Viewer implements ActionListener {
    /**
     * Instansvariabler som används inom klassen.
     */
    private JPanel cardsMainLayout;
    private CardLayout cardlayout;
    private Controller controller;
    private HighScore hs = new HighScore();
    private JPanel frame = new JPanel();
    private JPanel cards;
    private JPanel panel = new JPanel();
    private JPanel buttonPan = new JPanel();
    private JLabel label = new JLabel();
    private JButton newGame = new JButton();
    private JButton highScore = new JButton();
    private JButton extGame = new JButton();
    private JButton button1 = new JButton();
    private JButton button2 = new JButton();
    private JButton button3 = new JButton();
    private JButton button4 = new JButton();
    private JButton button5 = new JButton();
    private JButton button6 = new JButton();
    private JButton button7 = new JButton();
    private JButton button8 = new JButton();
    private JButton button9 = new JButton();
    private String headline = "3paRad: ";
    private Font font = new Font("Monospaced", Font.BOLD, 60);
    private Font head = new Font("Monospaced", Font.BOLD, 30);
    private Player pl1;
    private PlayerCPU pl2;
    /**
     * Konstruktorn tar emot ett controller objekt.
     * Sedan utformas JFramens utseende.
     *
     * @param controller    controller objektet som sätts till instans variabel
     */
    public Viewer(Controller controller, Player pl1, PlayerCPU pl2){

        this.controller = controller;
        this.pl1 = pl1;
        this.pl2 = pl2;
//        frame.addWindowFocusListener(new WindowAdapter() {
//            public void windowGainedFocus(WindowEvent e) {
//        newGame.requestFocusInWindow();
//            }
//        });
//        frame.setTitle("3paRad");
        label.setFont(head);
        label.setOpaque(true);
        label.setVisible(true);
        label.setText(headline);
        label.setBackground(Color.ORANGE);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setLayout(new BorderLayout());
        buttonPan.setBackground(Color.yellow);
        buttonPan.setLayout(new GridLayout(1,3));
        buttonPan.add(newGame); newGame.setText("Reset");
        buttonPan.add(highScore); highScore.setText("High Score");
        buttonPan.add(extGame); extGame.setText("Quit");
        newGame.addActionListener(this);
        highScore.addActionListener(this);
        extGame.addActionListener(this);
        panel.setLayout(new GridLayout(3,3));
        panel.add(button1); initButton(button1);
        panel.add(button2); initButton(button2);
        panel.add(button3); initButton(button3);
        panel.add(button4); initButton(button4);
        panel.add(button5); initButton(button5);
        panel.add(button6); initButton(button6);
        panel.add(button7); initButton(button7);
        panel.add(button8); initButton(button8);
        panel.add(button9); initButton(button9);
        frame.add(label, BorderLayout.NORTH);

        cards = new JPanel(new CardLayout());
        cards.add(panel, "Game");
        cards.add(hs, "High Score");
        frame.add(cards, BorderLayout.CENTER);

        frame.add(buttonPan, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    /**
     * getTrePaRad metoden returnerar ett jPanel objekt
     * @return      frame
     */
    public JPanel getTrePaRad(){
        return frame;
    }

    public void setCards(JPanel cards, CardLayout cardlayout){
        this.cardsMainLayout = cards;
        this.cardlayout= cardlayout;
    }

    /**
     * Sätter rubrik text.
     *
     * @param in    text som läggs till efter rubriken
     * @param c     om rubriken är ensam eller inte.
     */
    public void setText(String in, boolean c){
        if(c){
            label.setText(headline+in);
        }
        else{
            label.setText(headline);
        }
    }
    /**
     * Gör metod andropen till knappen som skickas in.
     *
     * @param b     knappen som ska ställas in.
     */
    public void initButton(JButton b){
        b.setBackground(Color.ORANGE);
        b.setOpaque(true);
        b.setFont(font);
        b.addActionListener(this);
    }
    /**
     * Sätter alla knappar till true eller false
     *
     * @param b     värde som sätts till enable.
     */
    public void setEnabling(boolean b){
        button1.setEnabled(b);
        button2.setEnabled(b);
        button3.setEnabled(b);
        button4.setEnabled(b);
        button5.setEnabled(b);
        button6.setEnabled(b);
        button7.setEnabled(b);
        button8.setEnabled(b);
        button9.setEnabled(b);
    }
    /**
     * Tar emot en position och sätter X eller O till knappen.
     *
     * @param i     vilken position.
     * @param in    vilken sträng som sätts.
     */
    public void setTextInButton(int i, String in){
        switch(i){
            case 1:
                button1.setText(in);
                button1.setEnabled(false);
                break;
            case 2:
                button2.setText(in);
                button2.setEnabled(false);
                break;
            case 3:
                button3.setText(in);
                button3.setEnabled(false);
                break;
            case 4:
                button4.setText(in);
                button4.setEnabled(false);
                break;
            case 5:
                button5.setText(in);
                button5.setEnabled(false);
                break;
            case 6:
                button6.setText(in);
                button6.setEnabled(false);
                break;
            case 7:
                button7.setText(in);
                button7.setEnabled(false);
                break;
            case 8:
                button8.setText(in);
                button8.setEnabled(false);
                break;
            case 9:
                button9.setText(in);
                button9.setEnabled(false);
                break;
        }
    }
    /**
     * Sätter X i knappen och skickar positionen till humanPlayer,
     * eller nollställer spelet för ett nytt spel, eller exit.
     *
     * @param e     Vilken knapp som blev tryckt.
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1){
            setTextInButton(1, "X");
            controller.humanPlayer(1);
        }
        if(e.getSource() == button2){
            setTextInButton(2, "X");
            controller.humanPlayer(2);
        }
        if(e.getSource() == button3){
            setTextInButton(3, "X");
            controller.humanPlayer(3);
        }
        if(e.getSource() == button4){
            setTextInButton(4, "X");
            controller.humanPlayer(4);
        }
        if(e.getSource() == button5){
            setTextInButton(5, "X");
            controller.humanPlayer(5);
        }
        if(e.getSource() == button6){
            setTextInButton(6, "X");
            controller.humanPlayer(6);
        }
        if(e.getSource() == button7){
            setTextInButton(7, "X");
            controller.humanPlayer(7);
        }
        if(e.getSource() == button8){
            setTextInButton(8, "X");
            controller.humanPlayer(8);
        }
        if(e.getSource() == button9){
            setTextInButton(9, "X");
            controller.humanPlayer(9);
        }
        if(e.getSource() == newGame){
            setTextInButton(1, ""); button1.setEnabled(true);
            setTextInButton(2, ""); button2.setEnabled(true);
            setTextInButton(3, ""); button3.setEnabled(true);
            setTextInButton(4, ""); button4.setEnabled(true);
            setTextInButton(5, ""); button5.setEnabled(true);
            setTextInButton(6, ""); button6.setEnabled(true);
            setTextInButton(7, ""); button7.setEnabled(true);
            setTextInButton(8, ""); button8.setEnabled(true);
            setTextInButton(9, ""); button9.setEnabled(true);
            controller.reSet();
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, (String)"Game");
        }
        if(e.getSource() == highScore){
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, (String)"High Score");
            String namn = JOptionPane.showInputDialog("Namn, tack.");
            hs.sendScore(namn, pl1.getNbrOfWins());
            hs.fillArea();
        }
        if(e.getSource() == extGame){
            this.cardlayout.show(this.cardsMainLayout, (String)"Index");
        }
    }
}
