package blackJack;

import java.awt.Color;
import java.awt.FlowLayout;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class House extends JPanel
{
    private ArrayList<Card> cards = new ArrayList<Card>();

    private JButton startButton;

    /**
     * Constructs a new instance.
     */
    public House()
    {
        setBackground(new Color(60, 225, 60));
        setLayout(new FlowLayout());
    }

    /**
     * Add card to house's hand.
     * 
     * @param card The card to add.
     */
    public void addCard(Card card)
    {
        cards.add(card);
        add(card);
        revalidate();
    }

    /**
     * Remove all cards from house's hand.
     */
    public void clearHand()
    {
        cards.clear();
        removeAll();
        // TODO need revalidate() ?
    }

    /**
     * Get value of hand.
     * 
     * @return The total value of the hand.
     */
    public int getValue()
    {
        int value = 0;
        for (Card current: cards)
        {
            value += current.getValue();
        }

        return value;
    }

    /**
     * Will reveal the house's hidden card.
     */
    public void revealHiddenCard()
    {
        cards.get(0).setHidden(false);
    }
}
