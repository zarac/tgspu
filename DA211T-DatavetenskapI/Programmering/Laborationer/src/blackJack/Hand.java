package blackJack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Represents a hand as well as some options on what to do with the hand.
 *
 * TODO buttons should only be enabled when they'll do something when pressed
 * TODO ? disable hit when 21 is reached
 */
@SuppressWarnings("serial")
public class Hand extends JPanel implements ActionListener, ChangeListener
{
    private Logger logger = new Logger(this);

    // the slot this hand belongs to
    private Slot slot;

    // Contains the amount bet. Whatever is in the pot, if you will.
    private int currentBet = 0;
    private JLabel currentBetLabel;

    private JPanel cardPanel;
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Color colorEnabled = new Color(80, 240, 80);
    private Color colorDisabled = new Color(240, 80, 80);

    private JPanel inputPanel = new JPanel();

    private JPanel sliderPanel = new JPanel();
    private JSlider betSlider = new JSlider();
    //private JTextField betField = new JTextField();
    private JLabel betField = new JLabel();

    private JPanel buttonPanel = new JPanel();
    private JButton hitButton = new JButton("Hit");
    private JButton stayButton = new JButton("Stay");
    private JButton splitButton = new JButton("Split");

    /**
     * Create new instance of Hand.
     * 
     * @param slot The slot the hand belongs to.
     */
    public Hand(Slot slot)
    {
        this.slot = slot;

        setBackground(colorDisabled);
        setLayout(new BorderLayout());

        // current bet
        currentBetLabel = new JLabel("no bet");
        currentBetLabel.setHorizontalAlignment(JLabel.CENTER);
        add(currentBetLabel, BorderLayout.NORTH);

        // cards
        cardPanel = new JPanel();
        cardPanel.setOpaque(false);
        cardPanel.setLayout(new FlowLayout());
        add(cardPanel, BorderLayout.CENTER);

        // input
        inputPanel.setOpaque(false);
        inputPanel.setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.SOUTH);
        // bet slider
        inputPanel.setOpaque(false);
        inputPanel.add(sliderPanel, BorderLayout.NORTH);
        sliderPanel.setOpaque(false);
        sliderPanel.setLayout(new FlowLayout());
        betSlider.setOpaque(false);
        betSlider.setPreferredSize(new Dimension(100, 20));
        betSlider.addChangeListener(this);
        updateBetSlider();
        sliderPanel.add(betSlider);
        betField.setText(Integer.toString(slot.getTable().getMinimumBet()));
        //betField.setEditable(false);
        sliderPanel.add(betField);
        // buttons
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout());
        // hit
        hitButton.setPreferredSize(new Dimension(40, 20));
        hitButton.setMargin(new Insets(1, 1, 1, 1));
        hitButton.addActionListener(this);
        buttonPanel.add(hitButton);
        // stay
        stayButton.setPreferredSize(new Dimension(40, 20));
        stayButton.setMargin(new Insets(1, 1, 1, 1));
        stayButton.addActionListener(this);
        buttonPanel.add(stayButton);
        // split
        splitButton.setPreferredSize(new Dimension(40, 20));
        splitButton.setMargin(new Insets(1, 1, 1, 1));
        splitButton.addActionListener(this);
        // TODO ? enable when implemented, if implemented
        //buttonPanel.add(splitButton);
    }

    /**
     * Add a card to the hand.
     * 
     * @param card The Card to add to hand.
     */
    public void addCard(Card card)
    {
        logger.debug("addCard(card='" + card.toString() + "'):");
        cards.add(card);
        cardPanel.add(card);

        if (getValue() > 21)
        {
            Card ace = getFirstAceWorth11();
            if (ace != null)
                ace.setValue(1);
        }
                
        cardPanel.revalidate();
    }

    /**
     * Get the total value of the hand.
     * 
     * @return Total value of hand.
     */
    public int getValue()
    {
        int handValue = 0;

        for (Card card : cards) 
        {
            handValue += card.getValue();
        }

        return handValue;
    }

    /**
     * @return First ace worth 11 or null if there is no ace on hand.
     */
    private Card getFirstAceWorth11()
    {
        for (Card card : cards) 
            if (card.getValue() == 11)
                return card;

        return null;
    }

    /**
     * Get the slot to which the hand belongs.
     * 
     * @return The slot to which the hand belongs.
     */
    public Slot getSlot()
    {
        return this.slot;
    }

    /**
     * Set the slot to which the hand belongs.
     * 
     * @param p_slot The slot to which the hand belongs.
     */
    public void setSlot(Slot p_slot)
    {
        this.slot = p_slot;
    }

    //public void setMaxBet(int p_maxValue)
    //{
        //betSlider.setMaximum(p_maxValue);
    //}

    /**
     * Handle input for hand.
     *
     * {@inheritDoc}
     * @see ActionListener#actionPerformed(ActionEvent)
     */
    public void actionPerformed(ActionEvent e)
    {
        logger.debug("actionPerformed():");

        Dealer dealer = slot.getTable().getDealer();

        if (e.getSource() == hitButton)
        {
            doBet();
            dealer.doHit();
        }
        else if (e.getSource() == stayButton)
        {
            doBet();
            dealer.doStay();
        }
        else if (e.getSource() == splitButton)
            dealer.doSplit();
    }

    /**
     * Bet whatever the bet slider is set to.
     */
    private void doBet()
    {
        doBet(betSlider.getValue());
    }

    /**
     * Bet an arbitrary number. Will remove bet from player's stack and add it
     * to the hand's current bet.
     * 
     * @param p_bet The bet to be made.
     */
    public void doBet(int p_bet)
    {
        logger.debug("doBet(" + p_bet + ")");

        slot.getPlayer().decStack(p_bet);
        slot.setStack(slot.getPlayer().getStack());

        currentBet = p_bet + getCurrentBet();
        currentBetLabel.setText(Integer.toString(currentBet));

        updateBetSlider();
    }

    /**
     * Get the current bet.
     * 
     * @return Returns the current bet.
     */
    public int getCurrentBet()
    {
        return currentBet;
    }

    /**
     * Updates the numeric bet field to match the slider.
     *
     * {@inheritDoc}
     * @see ChangeListener#stateChanged(ChangeEvent)
     */
    public void stateChanged(ChangeEvent e)
    {
        betField.setText(Integer.toString(betSlider.getValue()));
    }

    /**
     * Enables or disables input from hand.
     * 
     * @param isTurn Whether to enable or disable.
     */
    public void setTurn(boolean isTurn)
    {
        logger.debug("setTurn(isTurn='" + isTurn + "'):");

        if (isTurn)
            setBackground(colorEnabled);
        else
            setBackground(colorDisabled);

        hitButton.setEnabled(isTurn);
        stayButton.setEnabled(isTurn);
        splitButton.setEnabled(isTurn);
        betSlider.setEnabled(isTurn);
    }

    private void updateBetSlider()
    {
        betSlider.setMinimum(0);
        //betSlider.setMinimum(slot.getTable().getMinimumBet());
        betSlider.setMaximum(slot.getPlayer().getStack());

        betSlider.setValue(0);
        //betSlider.setValue(slot.getTable().getMinimumBet());
    }
}
