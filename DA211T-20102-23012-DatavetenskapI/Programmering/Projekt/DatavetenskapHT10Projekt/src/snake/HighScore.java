/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import sql.MysqlDataBase;

/**
 *
 * @author wiik
 */
public class HighScore extends JPanel implements ActionListener {
    private JPanel cardsMainLayout;
    private CardLayout cardlayout;
    /**
     * Klassens instans variabler de första lagrar information
     * relevant för datbas hanteringen. De andra för att utforma
     * jpanel objektet.
     */
    private MysqlDataBase sql;
    private JPanel cards;
    private Board board;
    private JTextArea txt = new JTextArea();
    private JLabel label = new JLabel();
    private JButton button = new JButton("Spela igen?");
    private JButton quit = new JButton("Quit");
    /**
     * HighScore konstruktorn den har en jPanel som inparameter
     * för att kunna ändra panel. I konstruktorn utformas
     * även HighScore JPanelen.
     *
     * @param cards     JPanel objekt av typen Card
     */
    public HighScore(JPanel cards){
        sql = new MysqlDataBase();
        this.cards = cards;
        button.addActionListener(this);
        quit.addActionListener(this);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setText("High Score");
        label.setFont(new Font("Serif", Font.BOLD, 25));
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.NORTH);
        txt.setEditable(false);
        this.add(txt, BorderLayout.CENTER);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1,2));
        p.add(button);
        p.add(quit);
        this.add(p, BorderLayout.SOUTH);
    }

    public void setCards(JPanel cards, CardLayout cardlayout){
        this.cardsMainLayout = cards;
        this.cardlayout= cardlayout;
    }
    /**
     * fillArea metoden tömer text arean och fyller den
     * med ny information från databasen.
     */
    public void fillArea(){
        txt.setText("");
        txt.setText(sql.getResultSetString(sql.getAllOrderByHighscoreDesc("snake")));
    }
    /**
     * sendScore metoden skicker en querry till databasen.
     */
    public void sendScore(String name, int score){
        sql.insertRow(sql.createInsert("snake", name, score));
    }
    /**
     * setBoard metoden sätter en board variabel till instans variabeln board.
     * @param board     variablen som sätts till instans variabeln.
     */
    public void setBoard(Board board){
        this.board = board;
    }
    /**
     * actionPerformed metoden startar ett nytt spel
     *
     * @param e        Actionevent
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, (String)"Snake");
            board.initGame();
        }
        if(e.getSource() == quit){
            this.cardlayout.show(this.cardsMainLayout, (String)"Index");
        }
    }
}
