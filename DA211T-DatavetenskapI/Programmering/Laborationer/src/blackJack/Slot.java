package blackJack;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.FlowLayout;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Represents a seat / chair / slot where a player can login to.
 */
@SuppressWarnings("serial")
public class Slot extends JPanel
{
    private Logger logger = new Logger(this);

    private BlackJackTable table;
    private BlackJackDatabase database;
    private Player player;

    private JPanel handsPanel;
    private JPanel infoPanel;

    private ArrayList<Hand> hands;
    private NameTextField nameTextField;
    private ExitButton exitButton;
    private JLabel nameLabel;
    private JLabel stackLabel;

    private String defaultName = "John Doe";

    private JPanel historyPanel;
    private JLabel historyLabel;

    /**
     * Instantiates a Slot.
     * 
     * @param p_database A reference to a database.
     * @param table The table to which the slot belongs.
     */
    public Slot(BlackJackDatabase p_database, BlackJackTable table)
    {
        this.table = table;
        logger.log("Slot(BlackJackDatabase):");
        logger.debug("Slot(): table='" + table + "'");

        database = p_database;

        setLayout(new BorderLayout());
        setBackground(new Color(100, 255, 100));

        add(new JLabel("<Slot>"));

        // info panel
        infoPanel = new JPanel();
        add(infoPanel, BorderLayout.NORTH);
        // login
        nameTextField = new NameTextField();
        nameTextField.addActionListener(nameTextField);
        nameTextField.setText(defaultName);
        infoPanel.add(nameTextField);
        // exit
        exitButton = new ExitButton();
        exitButton.setVisible(false);
        exitButton.addMouseListener(exitButton);
        infoPanel.add(exitButton);
        // name
        nameLabel = new JLabel("<noName>");
        nameLabel.setVisible(false);
        infoPanel.add(nameLabel);
        // stack
        stackLabel = new JLabel("<noStack>");
        stackLabel.setVisible(false);
        infoPanel.add(stackLabel);

        // hands panel
        hands = new ArrayList<Hand>();
        handsPanel = new JPanel();
        handsPanel.setLayout(new FlowLayout());
        add(handsPanel, BorderLayout.CENTER);

        // history panel - shows previous hands
        historyPanel = new JPanel();
        historyLabel = new JLabel();
        historyPanel.add(historyLabel);
        add(historyPanel, BorderLayout.SOUTH);
    }

    /**
     * Add a hand to the slot.
     * 
     * @param hand The hand to add.
     */
    public void addHand(Hand hand)
    {
        logger.debug("addHand():");
        hands.add(hand);
        handsPanel.add(hand);
        handsPanel.revalidate();
    }

    /**
     * Gets the table for this instance.
     *
     * @return The table.
     */
    public BlackJackTable getTable()
    {
        logger.debug("getTable(): table='" + table + "'");
        return table;
    }

    /**
     * Gets the player for this instance.
     *
     * @return The player.
     */
    public Player getPlayer()
    {
        return this.player;
    }

    /**
     * Gets the hands for this instance.
     *
     * @return The hands.
     */
    public ArrayList<Hand> getHands()
    {
        return this.hands;
    }

    /**
     * Removes all hands from the slot.
     */
    public void clearHands()
    {
        logger.debug("clearHands():");
        logger.debug("clearHands():" + hands.size());
        hands.clear();
        handsPanel.removeAll();
        logger.debug("clearHands():" + hands.size());
    }


    /**
     * Sets the player for this instance.
     *
     * @param player The player.
     */
    public void setPlayer(Player player)
    {
        logger.debug("setPlayer(player.getName()='" + player.getName() + ", 'player.getStack()='" + player.getStack() + "'"); 
        this.player = player;
        nameLabel.setText(this.player.getName());
        //stackLabel.setText(Integer.toString(this.player.getStack()));
        setStack(this.player.getStack());
    }

    /**
     * Add to history.
     * 
     * @param string What to add.
     */
    public void addHistory(String string)
    {
        historyLabel.setText(historyLabel.getText() + string);
    }

    /**
     * Set the stack label.
     * 
     * @param amount New stack amount to show.
     */
    public void setStack(int amount)
    {
        stackLabel.setText(Integer.toString(amount));
    }

    private void logIn(String p_name)
    {
        logger.log("logIn(String='" + p_name + "'):");

        for (Slot slot : getTable().getSlots())
            if (slot.getPlayer() != null)
                if (slot.getPlayer().getName().equals(p_name))
                {
                    logger.info("That player is already logged in.");
                    return;
                }

        setPlayer(database.getPlayerByName(p_name));
 
        nameTextField.setVisible(false);

        exitButton.setVisible(true);
        stackLabel.setVisible(true);
        nameLabel.setVisible(true);
    }


    private void logOut()
    {
        logger.log("logOut():");

        database.storePlayer(player);

        player = null;

        clearHands();

        exitButton.setVisible(false);
        stackLabel.setVisible(false);
        nameLabel.setVisible(false);

        nameTextField.setText(defaultName);
        nameTextField.setVisible(true);

    }


    private class ExitButton extends JLabel implements MouseListener
    {
        @SuppressWarnings("unused")
        private Logger logger = new Logger(this);

        /**
         * Constructs a new instance.
         */
        public ExitButton()
        {
            setText("[X]");
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mouseClicked(MouseEvent)
         */
        public void mouseClicked(MouseEvent e)
        {
            logOut();
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mousePressed(MouseEvent)
         */
        public void mousePressed(MouseEvent e)
        {
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mouseReleased(MouseEvent)
         */
        public void mouseReleased(MouseEvent e)
        {
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mouseEntered(MouseEvent)
         */
        public void mouseEntered(MouseEvent e)
        {
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mouseExited(MouseEvent)
         */
        public void mouseExited(MouseEvent e)
        {
        }
    }


    private class NameTextField extends JTextField implements ActionListener
    {
        @SuppressWarnings("unused")
        private Logger logger = new Logger(this);

        /**
         * {@inheritDoc}
         * @see ActionListener#actionPerformed(ActionEvent)
         */
        public void actionPerformed(ActionEvent e)
        {
            logIn(this.getText());
        }
    }
}
