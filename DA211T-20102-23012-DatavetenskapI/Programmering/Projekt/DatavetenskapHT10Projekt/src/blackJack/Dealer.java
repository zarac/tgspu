package blackJack;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer
{
    private Logger logger = new Logger(this);

    private BlackJackTable table;
    private int minCardsToPlay;
    private int numDecks;
    private House house;
    private ArrayList<Card> deck;
    private Hand currentHand;

    /**
     * Constructs a new instance.
     */
    public Dealer(BlackJackTable table)
    {
        this.table = table;
        house = table.getHouse();

        minCardsToPlay = 50;
        numDecks = 3;

        // init. a deck of 3 x 52-card-deck
        deck = new ArrayList<Card>();
        initDeck();

        table.setDealer(this);
    }

    /**
     * Initialize the grand BlackJack deck.
     */
    public void initDeck()
    {
        for (int i = 0; i < numDecks; i++)
            addCards(new BlackJackDeck());

        shuffle();
    }

    /**
     * Add multiple cards to the deck.
     * 
     * @param cards The cards to add.
     */
    public void addCards(ArrayList<Card> cards)
    {
        logger.debug("addCards(cards='" + cards.toString() + "'):");

        for (Card card : cards)
        {
            logger.debug("card='" + cards.toString() + "'");
            deck.add(card);
        }
    }

    /**
     * Shuffle the deck.
     */
    public void shuffle() 
    {
        Collections.shuffle(deck);
    }

    /**
     * Start a new game.
     */
    public void startGame()
    {
        logger.debug("startGame():");

        dealGame();

        // set turn to first hand and wait for input from players
        setTurn(table.getHands().get(0));
    }

    public void dealGame() 
    {
        // TODO make sure there are hands to deal to before starting to deal
 
        logger.debug("dealGame():");

        // check if we have at least <minCardsToPlay> or shuffle()
        if (deck.size() < minCardsToPlay)
            initDeck();

        // clear house cards
        house.clearHand();

        // clear hands for each slot
        for (Slot slot : table.getSlots())
            slot.clearHands();

        // add hand for each active slot
        logger.debug("add hand for each active slot");
        for (Slot slot : table.getActiveSlots())
        {
            Hand hand = new Hand(slot);
            hand.doBet(table.getMinimumBet());
            slot.addHand(hand);
        }

        // deal card to each hand in each slot
        for (Hand hand : table.getHands())
            hand.addCard(deck.remove(0));

        // deal to house (hidden)
        Card hiddenCard = deck.remove(0);
        hiddenCard.setHidden(true);
        house.addCard(hiddenCard);

        // deal card to each hand in each slot
        for (Hand hand : table.getHands())
            hand.addCard(deck.remove(0));

        // deal to house (open)
        house.addCard(deck.remove(0));
    }

    /**
     * Set who's turn (what hand) it is to play. That hand will have input enabled and others will be disabled.
     * 
     * @param p_hand The hand to make active.
     */
    public void setTurn(Hand p_hand)
    {
        logger.debug("setTurn(p_hand='" + p_hand + "'):");

        currentHand = p_hand;

        for (Hand hand : table.getHands())
        {
            if (hand == p_hand)
            {
                logger.debug("enabling hand");
                hand.setTurn(true);
            }
            else
            {
                logger.debug("disabling hand");
                hand.setTurn(false);
            }
        }
    }

    /**
     * This should be called when a player is done with betting of a hand. It
     * will set the turn to the next hand or move on the the next step.
     */
    private void handDone()
    {
        logger.debug("handDone():");

        ArrayList<Hand> hands = table.getHands();

        for (int i = 0; i < hands.size(); i++)
            if (hands.get(i) == currentHand)
            {
                // is there another hand?
                if (i < hands.size()-1)
                {
                    setTurn(hands.get(i+1));
                    return;
                }
                break;
            }

        setTurn(null);

        finishGame();
    }

    private void finishGame()
    {
        logger.debug("finishGame():");

        house.revealHiddenCard();

        dealToHouse();
        logger.info("house has a final value of '" + house.getValue() + "'");

        for (Hand hand : table.getHands())
        {
            if (hand.getValue() > 21)
            {
                logger.debug("fat");
                doLoss(hand);
                hand.getSlot().addHistory("F");
            }
            else if (hand.getValue() > house.getValue() ||
                    house.getValue() > 21)
            {
                logger.debug("won");
                doWin(hand);
                hand.getSlot().addHistory("W");
            }
            else
            {
                logger.debug("lost");
                doLoss(hand);
                hand.getSlot().addHistory("L");
            }
        }
 
        // TODO ?remove hands from slots, perhaps after a few seconds
    }

    private void dealToHouse()
    {
        logger.debug("dealToHouse():");

        while (house.getValue() < 17)
            house.addCard(deck.remove(0));
    }

    /**
     * Declare a hand a win and reward it.
     * 
     * @param hand The hand to reward.
     */
    private void doWin(Hand hand)
    {
        logger.debug("doWin():");
        hand.getSlot().getPlayer().incStack(hand.getCurrentBet()*2);
        hand.getSlot().setStack(hand.getSlot().getPlayer().getStack());
    }

    /**
     * Declare a hand a loss.
     * 
     * @param hand The hand to punish.
     */
    private void doLoss(Hand hand)
    {
        logger.debug("doLoss():");
    }

    /**
     * Give the instruction to stay for current hand.
     */
    public void doStay()
    {
        logger.debug("doStay():");
        handDone();
    }

    /**
     * Give the instruction to hit the current hand. End hand's turn if new
     * hand value exceeds 20.
     */
    public void doHit()
    {
        logger.debug("doHit():");

        // hit the hand with one more card
        currentHand.addCard(deck.remove(0));

        if (currentHand.getValue() > 21)
            handDone();
    }

    /**
     * Give the instruction to split the current hand.
     *
     * TODO implement
     */
    public void doSplit()
    {
        logger.debug("doSplit(): not implemented...");
    }
}

