package alfabet;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JOptionPane;

/**
 *
 * @author zarac
 */
public class Alfabet implements KeyListener, ActionListener
{
	JFrame jFrame;

	// Instructions to user on what to do
	JLabel instructions;
	// Next-character-to-type label and text
	JLabel nextCharLabel;
	String nextCharText = "Next character to type: ";

	JLabel timerLabel;

	// Typed and next character
	String typedChars;
	char nextChar;

	// The status of the game
	boolean isRunning;

	// The timer
	Timer timer;

	long startTid;


	public Alfabet()
	{
		jFrame = new JFrame();
		jFrame.setSize(600, 100);
		jFrame.setBackground(Color.RED);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.addKeyListener(this);

		jFrame.setLayout(new FlowLayout());

		// Set up texts..
		instructions = new JLabel("Type alfabet from a-z. It's timed, so type quickly!");
		instructions.setForeground(Color.BLACK);
		instructions.setFont(new Font("Serif", Font.BOLD, 14));
		jFrame.add(instructions);

		nextCharLabel = new JLabel();
		nextCharLabel.setForeground(Color.RED);
		nextCharLabel.setFont(new Font("Serif", Font.BOLD, 12));
		jFrame.add(nextCharLabel);

		timerLabel = new JLabel();
		timerLabel.setForeground(Color.GRAY);
		timerLabel.setFont(new Font("Serif", Font.BOLD, 12));
		jFrame.add(timerLabel);

		timer = new Timer(60, this);



		resetGame();
	}


	public void resetGame()
	{
		System.out.println("resetting game...");

		isRunning = false;
		typedChars = "";
		timer.stop();

		// Set next character to 'A'
		nextChar = 65;
		nextCharLabel.setText("Hit 'a' to begin!");
	}

	// Called when a character is typed
	public void stepNext()
	{
		System.out.println((char)nextChar);
		typedChars += nextChar;
		nextChar++;
		nextCharLabel.setText(nextCharText + nextChar);
	}

	// Checks if a key pressed is correct
	public boolean isNextKey(int p_charCode)
	{
		if (p_charCode == nextChar || p_charCode == nextChar + 32)
			return true;
		else
			return false;
	}

	public void updateTimerText()
	{
		timerLabel.setText("TID: " + (System.currentTimeMillis() - startTid));
	}

	public void startGame()
	{
		startTid = System.currentTimeMillis();
		timer.start();
		nextCharLabel.setText(nextCharText + nextChar);
		// Start timer...
	}

	public void gameWon()
	{
		timer.stop();
		nextCharLabel.setText("You Won!");
//		JOptionPane.showMessageDialog(null, "You won!");
	}

	public void keyTyped(KeyEvent e)
	{
		int keyCode = (int)e.getKeyChar();

		System.out.println(keyCode);

		// Exit program if <ESC>
		if (keyCode == 27)
		{
			System.exit(0);
		}

		if (!isRunning)
		{
			// If hitting 'a' or 'A', start game
			if (keyCode == 65 || keyCode == 97)
			{
				this.startGame();
				stepNext();
				isRunning = true;
			}
		}
		else if (isNextKey(keyCode))
		{
			// If next key was 'z' or 'Z', finish game
			if (keyCode == 90 || keyCode == 122)
				gameWon();
			else if (keyCode == nextChar || keyCode == nextChar + 32)
				stepNext();
		}
	}

	public void keyPressed(KeyEvent e)
	{
//		System.out.println("keyPressed");
	}

	public void keyReleased(KeyEvent e)
	{
//		System.out.println("keyReleased");
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == timer)
			updateTimerText();
	}


	public static void main(String[] args)
	{
		Alfabet alfabet = new Alfabet();
	}
}
