/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author wiik
 */
public class ScoreBoard extends JPanel implements ActionListener {
    /**
     * instans variablerna som JPanelen består av.
     */
    private JPanel cards;
    private HighScore scorePanel;
    private String headline = "Snake: ";
    private JLabel score = new JLabel();
    private JLabel scoreLbl = new JLabel();
    private JTextField name = new JTextField();
    private JButton submit = new JButton();
    private Font head = new Font("Monospaced", Font.BOLD, 20);
    private int apples = 0;
    /**
     * ScoreBoard konstruktorn sätter ihop fönstret
     */
    public ScoreBoard(JPanel cards, HighScore scorePanel){
        this.cards = cards;
        this.scorePanel = scorePanel;
        submit.setText("Submit");
        score.setText("Score");
        name.setText("");
        this.setLayout(new GridLayout(1,5));
        this.add(score);
        this.add(scoreLbl);
        this.add(name);
        this.add(submit);
        this.setBackground(Color.yellow);
        score.setFont(head);
        scoreLbl.setFont(head);
        name.setEnabled(false);
        submit.setEnabled(false);
        submit.addActionListener(this);
    }
    /**
     * itScore metoden plsar ett till apples variablen och sätter
     * texten till labeln scoreLbl
     */
    public void itScore(){
        apples++;
        scoreLbl.setText(""+apples);
    }
    /**
     * zeroApples metoden för att nollställa apples variabeln
     */
    public void zeroApples(){
        apples = 0;
        scoreLbl.setText(""+apples);
    }
    /**
     * subName metoden sätts name textfield och submit knappen
     * till enabled true.
     */
    public void subName(boolean in){
        name.setEnabled(in);
        submit.setEnabled(in);
    }
    /**
     * metoden actionPerformed används när användaren ska skicka in
     * sitt resultat till highscore klassen.
     * @param ae
     */
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == submit){
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, (String)"High Score");
            scorePanel.sendScore(name.getText().trim(), apples);
            scorePanel.fillArea();
        }
    }
}
