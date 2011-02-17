/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datavetenskapht10projekt;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author wiik & Marcus
 */

public class IndexGames extends JPanel implements ActionListener {
   /**
    * Instansvariabler för hantering av data och utseendet på klassen.
    */

    private JPanel cards;
    private CardLayout cardlayout;
    private JButton snake;
    private JButton trePaRad = new JButton();
    private JButton rolfVrNinja = new JButton();
    private JButton blackJack = new JButton();
    private Font font = new Font("Dialog", Font.BOLD, 20);
    /**
     * Sätter referans till cardlayout variabeln.
     * @param cardlayout
     */
    public void setCardlayout(CardLayout cardlayout) {
        this.cardlayout = cardlayout;
    }
    /**
     * Sätter referens till cards och cardlayout variabeln.
     * Gör JButtons synliga och skapar ActionListener
     * @param cardlayout
     * @param cards
     */
    public IndexGames(CardLayout cardlayout, JPanel cards){
        snake = new JButton("Snake");
        snake.setFont(font);
        trePaRad = new JButton("TrePaRad");
        trePaRad.setFont(font);
        rolfVrNinja = new JButton("Rolf vr Ninja");
        rolfVrNinja.setFont(font);
        blackJack = new JButton("Black Jack");
        blackJack.setFont(font);
        this.cards = cards;
        this.cardlayout = cardlayout;
        this.setLayout(new GridLayout(2,2));
        this.add(snake);
        this.add(trePaRad);
        this.add(rolfVrNinja);
        this.add(blackJack);

        snake.addActionListener(this);
        trePaRad.addActionListener(this);
        rolfVrNinja.addActionListener(this);
        blackJack.addActionListener(this);
    }
    /**
     * Om actionEvent inparameter är snake startas spelet Snake.
     * Det samma gäller även Tre Pa Rad, Rolf VS Ninja, BlackJack
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == snake){
            cardlayout.show(cards, (String)"Snake");
        }
        if(e.getSource() == trePaRad){
            cardlayout.show(cards, (String)"Tre Pa Rad");
        }
        if(e.getSource() == rolfVrNinja){
            cardlayout.show(cards, (String)"RolfvrNinja");
        }
        if(e.getSource() == blackJack){
            cardlayout.show(cards, (String)"Black Jack");
        }

    }
}
