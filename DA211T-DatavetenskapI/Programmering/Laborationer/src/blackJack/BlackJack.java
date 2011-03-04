package blackJack;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Launch the great game of Black Jack.
 *
 * TODO bug, hands stay behind new hands
 */
@SuppressWarnings("serial")
public class BlackJack extends JPanel
{
    private Logger logger = new Logger(this);

    // for testing and debugging
    // however; controls > startButton and exitButton are needed for now
    private JPanel controls;
    //private Console console;

    private BlackJackTable table;
    //private ArrayList<Player> players;
    private Dealer dealer;
    private BlackJackDatabase database;

    /**
     * Constructs a new instance.
     */
    public BlackJack()
    {
        // database connection
        logger.debug("Connecting to database...");
        String host = "195.178.232.7";
        int port = 3306;
        String username = "DA211T10C4062119";
        String password = "4062119";
        String databaseName = "da211t10c4062119";
        database = new BlackJackDatabase(host, port, username, password, databaseName);
        database.connect();
        database.dumpPlayerTable();

        // controls, status, info, debug
        // | table |
        // console
        setLayout(new BorderLayout());

        // controls
        controls = new JPanel();
        add(controls, BorderLayout.NORTH);
        controls.setBackground(new Color(110, 220, 150));
        controls.setLayout(new FlowLayout());
        // start
        StartButton startButton = new StartButton();
        startButton.addMouseListener(startButton);
        controls.add(startButton);
        // close
        CloseButton closeButton = new CloseButton();
        closeButton.addMouseListener(closeButton);
        controls.add(closeButton);
        // test
        TestButton testButton = new TestButton();
        testButton.addMouseListener(testButton);
        controls.add(testButton);
        // revalidate
        RevalidateButton revalidateButton = new RevalidateButton();
        revalidateButton.addMouseListener(revalidateButton);
        controls.add(revalidateButton);

        // table
        House house = new House();
        table = new BlackJackTable(database, house, 3);
        add(table, BorderLayout.CENTER);

        // console, debugger
        //console = new Console();
        //add(console, BorderLayout.NORTH);

        dealer = new Dealer(table);

        // TODO fix, doesn't work, why??
        // perhaps 'cause something else has focus..
        KeyBindings keyBindings = new KeyBindings();
        addKeyListener(keyBindings);
    }

    public void exitGame()
    {
        logger.debug("exitGame():");

        // TODO update players', who are still logged in, stacks in database
        //logger.debug("saving still logged in players' stacks");
        for (Slot slot : table.getSlots())
            if (slot.getPlayer() != null)
                database.storePlayer(slot.getPlayer());

        System.exit(0);
    }

    /**
     * Used internally, will not be used when launched through portal.
     */
    private class BlackJackFrame extends JFrame implements WindowListener
    {
        /**
         * Constructs a new instance.
         */
        public BlackJackFrame()
        {
            setTitle("BlackJackFrame");
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            addWindowListener(this);
            setLayout(new BorderLayout());
            //setBounds(150, 50, 640, 480);
            setBounds(150, 50, 800, 600);
            setResizable(false);
            setVisible(true);

            //add(new JLabel("Black Jack"));
            addKeyListener(new KeyBindings());
        }

        /**
         * {@inheritDoc}
         * @see WindowListener#windowOpened(WindowEvent)
         */
        public void windowOpened(WindowEvent e)
        {
            logger.debug("windowOpened():");
        }

        /**
         * {@inheritDoc}
         * @see WindowListener#windowClosing(WindowEvent)
         */
        public void windowClosing(WindowEvent e)
        {
            logger.debug("windowClosing():");
            exitGame();
        }

        /**
         * {@inheritDoc}
         * @see WindowListener#windowClosed(WindowEvent)
         */
        public void windowClosed(WindowEvent e)
        {
            logger.debug("windowClosed():");
        }

        /**
         * {@inheritDoc}
         * @see WindowListener#windowIconified(WindowEvent)
         */
        public void windowIconified(WindowEvent e)
        {
            logger.debug("windowIconified():");
        }

        /**
         * {@inheritDoc}
         * @see WindowListener#windowDeiconified(WindowEvent)
         */
        public void windowDeiconified(WindowEvent e)
        {
            logger.debug("windowDeiconified():");
        }

        /**
         * {@inheritDoc}
         * @see WindowListener#windowActivated(WindowEvent)
         */
        public void windowActivated(WindowEvent e)
        {
            logger.debug("windowActivated():");
        }

        /**
         * {@inheritDoc}
         * @see WindowListener#windowDeactivated(WindowEvent)
         */
        public void windowDeactivated(WindowEvent e)
        {
            logger.debug("windowDeactivated():");
        }
    }


    /**
     * Used mostly for debugging and testing.
     */
    public class Console extends JPanel implements KeyListener
    {
        private JTextArea output;
        private JTextField input;

        /**
         * Constructs a new instance.
         */
        public Console()
        {
            setBackground(new Color(0, 0, 0));

            // label
            // | output |
            // input
            setLayout(new BorderLayout());

            // label
            add(new JLabel("console"), BorderLayout.NORTH);

            // output
            output = new JTextArea();
            add(output, BorderLayout.CENTER);
            output.setText("feedback");

            // input
            input = new JTextField();
            add(input, BorderLayout.SOUTH);
            input.addKeyListener(this);
        }

        /**
         * {@inheritDoc}
         * @see KeyListener#keyTyped(KeyEvent)
         */
        public void keyTyped(KeyEvent e)
        {
            logger.debug("keyTyped");
        }

        /**
         * {@inheritDoc}
         * @see KeyListener#keyPressed(KeyEvent)
         */
        public void keyPressed(KeyEvent e)
        {
            logger.debug("keyPressed");
        }

        /**
         * {@inheritDoc}
         * @see KeyListener#keyReleased(KeyEvent)
         */
        public void keyReleased(KeyEvent e)
        {
            logger.debug("keyReleased");
        }
    }


    public class KeyBindings implements KeyListener
    {
        private Logger logger = new Logger(this);

        /**
         * {@inheritDoc}
         * @see KeyListener#keyTyped(KeyEvent)
         */
        public void keyTyped(KeyEvent e)
        {
            logger.debug("e.getKeyCode() -> " + e.getKeyChar());
        }

        /**
         * {@inheritDoc}
         * @see KeyListener#keyPressed(KeyEvent)
         */
        public void keyPressed(KeyEvent e)
        {
            logger.debug("keyPressed(code='" + e.getKeyCode() + "')'");
            logger.debug("keyPressed(char='" + e.getKeyChar() + "')'");
        }

        /**
         * {@inheritDoc}
         * @see KeyListener#keyReleased(KeyEvent)
         */
        public void keyReleased(KeyEvent e)
        {
            logger.debug("keyReleased() : e.getKeyCode() -> " + e.getKeyChar());
        }
    }


    public class RevalidateButton extends BlackJackButton
    {
        private Logger logger = new Logger(this);

        /**
         * Constructs a new instance.
         */
        public RevalidateButton()
        {
            super("[REVALIDATE]");
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mouseClicked(MouseEvent)
         */
        public void mouseClicked(MouseEvent e)
        {
            logger.debug("revalidatin...");
            revalidate();
        }
    }


    public class TestButton extends BlackJackButton
    {
        private Logger logger = new Logger(this);

        /**
         * Constructs a new instance.
         */
        public TestButton()
        {
            super("[TEST]");
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mouseClicked(MouseEvent)
         */
        public void mouseClicked(MouseEvent e)
        {
            logger.debug("adding a card for each hand...");
            for (Hand hand : table.getHands())
            {
                hand.addCard(new Card('H', "E", 11));
                hand.revalidate();
            }
        }
    }


    public class CloseButton extends BlackJackButton
    {
        private Logger logger = new Logger(this);

        /**
         * Constructs a new instance.
         */
        public CloseButton()
        {
            super("[CLOSE]");
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mouseClicked(MouseEvent)
         */
        public void mouseClicked(MouseEvent e)
        {
            exitGame();
        }
    }


    /**
     * Button to start a new game of Black Jack.
     */
    public class StartButton extends BlackJackButton
    {
        private Logger logger = new Logger(this);

        public StartButton()
        {
            super("[Start]");
            logger.debug("StartButton");
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mousePressed(MouseEvent)
         */
        public void mousePressed(MouseEvent e)
        {
            logger.debug("Play Game");
            dealer.startGame();
        }
    }


    /**
     * A helper class for buttons in Black Jack.
     */
    public class BlackJackButton extends JLabel implements MouseListener
    {
        private Logger logger = new Logger(this);

        /**
         * Constructs a new instance.
         */
        public BlackJackButton(String p_text)
        {
            super(p_text);
            logger.debug("BlackJackButton");

            setOpaque(true);
            setBackground(new Color(255, 255, 255));
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mouseClicked(MouseEvent)
         */
        public void mouseClicked(MouseEvent e)
        {
            //logger.debug("mouseClicked(source='" + e.getSource() + "')'");
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mousePressed(MouseEvent)
         */
        public void mousePressed(MouseEvent e)
        {
            //logger.debug("mousePressed(source='" + e.getSource() + "')'");
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mouseReleased(MouseEvent)
         */
        public void mouseReleased(MouseEvent e)
        {
            //logger.debug("mouseReleased(source='" + e.getSource() + "')'");
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mouseEntered(MouseEvent)
         */
        public void mouseEntered(MouseEvent e)
        {
            //logger.debug("mouseEntered(source='" + e.getSource() + "')'");
        }

        /**
         * {@inheritDoc}
         * @see MouseListener#mouseExited(MouseEvent)
         */
        public void mouseExited(MouseEvent e)
        {
            //logger.debug("mouseExited(source='" + e.getSource() + "')'");
        }
    }


    public static void main(String[] argv)
    {
        BlackJack blackJack = new BlackJack();

        BlackJackFrame frame = blackJack.new BlackJackFrame();
        frame.addKeyListener(blackJack.new KeyBindings());

        frame.add(blackJack, BorderLayout.CENTER);
    }
}
