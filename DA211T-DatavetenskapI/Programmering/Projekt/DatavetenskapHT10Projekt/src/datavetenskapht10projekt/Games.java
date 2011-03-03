/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datavetenskapht10projekt;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import rolfVrNinja.RolfVrNinja;
import snake.Snake;
import tre_pa_rad.TrePaRad;
import blackJack.BlackJack;

/**
 *
 * @author wiik & Marcus
 */
public class Games extends JFrame {
/**
 * Instansvariabler för fönster till spelen.
 */
    private JPanel cards;
    private JPanel index;
    private JPanel snake;
    private JPanel trePArad;
    private BlackJack blackJack;
    private JPanel rolfvrninja;
    private TrePaRad tre;
    private RolfVrNinja rolf;

    /**
     * Skapar en CardLayout till varje spel samt lägger in dem I JFrame.
     */
    public Games(){
        cards = new JPanel(new CardLayout());
        index = new IndexGames((CardLayout) cards.getLayout(), cards);

        tre = new TrePaRad(cards, (CardLayout) cards.getLayout());
        trePArad = tre.getTrePaRad();

        snake = new Snake(cards, (CardLayout) cards.getLayout());

        blackJack = new BlackJack();
        blackJack.setPortal(cards);

        rolf = new RolfVrNinja();
        rolfvrninja = rolf.getGameBoard();
        rolf.setGameBoardCards(cards, (CardLayout) cards.getLayout());

        this.setSize(700, 500);
        //cards = new JPanel(new CardLayout());
        cards.add(index, "Index");
        cards.add(snake, "Snake");
        cards.add(trePArad, "Tre Pa Rad");
        cards.add(rolfvrninja, "RolfvrNinja");
        cards.add(blackJack, "Black Jack");
        this.add(cards, BorderLayout.CENTER);
        //index.setCardLayout((CardLayout) cards.getLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



}
