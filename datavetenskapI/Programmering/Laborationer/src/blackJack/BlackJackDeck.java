package blackJack;

import java.util.ArrayList;

/**
 * A 52-card-deck.
 */
@SuppressWarnings("serial")
public class BlackJackDeck extends ArrayList<Card>
{
    private Logger logger = new Logger(this);

    /**
     * Creates a new BlackJackDeck instance.
     */
    public BlackJackDeck()
    {
        logger.log("BlackJackDeck():");

        // Spades
        add(new Card('S', "2", 2));
        add(new Card('S', "3", 3));
        add(new Card('S', "4", 4));
        add(new Card('S', "5", 5));
        add(new Card('S', "6", 6));
        add(new Card('S', "7", 7));
        add(new Card('S', "8", 8));
        add(new Card('S', "9", 9));
        add(new Card('S', "10", 10));
        add(new Card('S', "J", 10));
        add(new Card('S', "Q", 10));
        add(new Card('S', "K", 10));
        add(new Card('S', "A", 11));
        // Clubs
        add(new Card('C', "2", 2));
        add(new Card('C', "3", 3));
        add(new Card('C', "4", 4));
        add(new Card('C', "5", 5));
        add(new Card('C', "6", 6));
        add(new Card('C', "7", 7));
        add(new Card('C', "8", 8));
        add(new Card('C', "9", 9));
        add(new Card('C', "10", 10));
        add(new Card('C', "J", 10));
        add(new Card('C', "Q", 10));
        add(new Card('C', "K", 10));
        add(new Card('C', "A", 11));
        // Diamonds
        add(new Card('D', "2", 2));
        add(new Card('D', "3", 3));
        add(new Card('D', "4", 4));
        add(new Card('D', "5", 5));
        add(new Card('D', "6", 6));
        add(new Card('D', "7", 7));
        add(new Card('D', "8", 8));
        add(new Card('D', "9", 9));
        add(new Card('D', "10", 10));
        add(new Card('D', "J", 10));
        add(new Card('D', "Q", 10));
        add(new Card('D', "K", 10));
        add(new Card('D', "A", 11));
        // Hearts
        add(new Card('H', "2", 2));
        add(new Card('H', "3", 3));
        add(new Card('H', "4", 4));
        add(new Card('H', "5", 5));
        add(new Card('H', "6", 6));
        add(new Card('H', "7", 7));
        add(new Card('H', "8", 8));
        add(new Card('H', "9", 9));
        add(new Card('H', "10", 10));
        add(new Card('H', "J", 10));
        add(new Card('H', "Q", 10));
        add(new Card('H', "K", 10));
        add(new Card('H', "A", 11));

        //for (int i = 0; i < 13; i++)
        //{
            //// Spades
            //add(new Card(i + 2, 'S'));
            //// Clubs
            //add(new Card(i + 2, 'C'));
            //// Diamonds
            //add(new Card(i + 2, 'D'));
            //// Hearts
            //add(new Card(i + 2, 'H'));

            ////// Spades
            ////add(new Card(i + 2, '\u2660'));
            ////// Clubs
            ////add(new Card(i + 2, '\u2663'));
            ////// Diamonds
            ////add(new Card(i + 2, '\u2666'));
            ////// Hearts
            ////add(new Card(i + 2, '\u2665'));
        //}
    }
}

