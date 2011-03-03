/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author wiik
 */
public class Snake extends JPanel {
    /**
     * Instans variablerna i klassen
     */
    private JPanel cardsMain;
    private CardLayout cardlayout;
    private JPanel cards;
    private ScoreBoard submit;
    private HighScore score;
    private Board board;
    /**
     * Konstruktorn utformar gränssnittet.
     */
    public Snake(JPanel cardsMain, CardLayout cardlayout) {
        this.cardsMain = cardsMain;
        this.cardlayout= cardlayout;
        cards = new JPanel(new CardLayout());
        score = new HighScore(cards);
        score.setCards(cardsMain,cardlayout);
        submit = new ScoreBoard(cards, score);
        board = new Board(submit, cards);
        score.setBoard(board);

        setLayout(new BorderLayout());
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(320, 340);
//        setLocationRelativeTo(null);
//        setTitle("Snake");

        cards.add(board, "Snake");
        cards.add(score, "High Score");
        this.add(cards, BorderLayout.CENTER);
        add(submit,BorderLayout.NORTH);

//        setResizable(false);
        setVisible(true);
    }

}
