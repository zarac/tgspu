package blackJack;

import java.awt.Color;
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlackJackTable extends JPanel
{
    private Logger logger = new Logger(this);

    private BlackJackDatabase database;
    private Dealer dealer;
    private House house;
    private ArrayList<Slot> slots;

    private JPanel slotPanel;

    private int minimumBet = 5;

    /**
     * Constructs a new instance.
     * @param p_database Somewhere to store the players and their stacks.
     * @param p_house Every black jack table needs a house.
     * @param p_numSlots Number of available slots. Too many won't fint. Not enough is not fun.
     */
    public BlackJackTable(BlackJackDatabase p_database, House p_house, int p_numSlots)
    {
        this.database = p_database;
        this.house = p_house;

        setBackground(new Color(80, 80, 200));

        // | house
        // | slots
        setLayout(new GridLayout(2, 1));

        // house
        add(house);

        // slots, chairs, or whatever you want to call it.
        slotPanel = new JPanel();
        add(slotPanel);
        slots = new ArrayList<Slot>();
        initSlots(p_numSlots);
    }

    public void initSlots(int p_numSlots)
    {
        slotPanel.setLayout(new GridLayout(1,p_numSlots));

        for (int i = 0; i < p_numSlots; i++)
        {
            addSlot(new Slot(database, this));
        }
    }

    public void addSlot(Slot p_slot)
    {
        logger.log("adding slot...");
        slots.add(p_slot);
        slotPanel.add(p_slot);
    }

    /**
     * Sets the house for this instance.
     *
     * @param house The house.
     */
    public void setHouse(House house)
    {
        this.house = house;
    }

    public ArrayList<Hand> getHands()
    {
        ArrayList<Hand> hands = new ArrayList<Hand>();

        for (Slot slot : getSlots())
        {
            hands.addAll(slot.getHands());
        }
        
        return hands;
    }

    /**
     * Sets the dealer for this instance.
     *
     * @param dealer The dealer.
     */
    public void setDealer(Dealer dealer)
    {
        this.dealer = dealer;
    }
    
    /**
     * Gets the dealer for this instance.
     *
     * @return The dealer.
     */
    public Dealer getDealer()
    {
        return this.dealer;
    }

    /**
     * Gets the house for this instance.
     *
     * @return The house.
     */
    public House getHouse()
    {
        return this.house;
    }

    /**
     * Gets the active slots for this instance. An active slot is a slot with a player.
     *
     * @return The active slots.
     */
    public ArrayList<Slot> getActiveSlots()
    {
        logger.debug("getActiveSlots():");

        ArrayList<Slot> activeSlots = new ArrayList<Slot>();
        for (Slot slot : slots)
            if (slot.getPlayer() != null)
                activeSlots.add(slot);

        return activeSlots;
    }

    /**
     * Gets the slots for this instance.
     *
     * @return The slots.
     */
    public ArrayList<Slot> getSlots()
    {
        return this.slots;
    }

    /**
     * Gets the minimumBet for this instance.
     *
     * @return The minimumBet.
     */
    public int getMinimumBet()
    {
        return this.minimumBet;
    }

    /**
     * Sets the minimumBet for this instance.
     *
     * @param minimumBet The minimumBet.
     */
    public void setMinimumBet(int minimumBet)
    {
        this.minimumBet = minimumBet;
    }
}
