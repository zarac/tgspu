package p3;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import javax.swing.JPanel;

/**
 * @author Hannes Landstedt (hannes.landstedt@gmail.com)
 * @version null
 */
public class RPSViewer
{
    private JFrame frame;

    public JPanel statusPanel;
    public JLabel statusGameWinner;
    public JLabel statusHandWinner;

    public JPanel playerPanel;
    public JLabel playerLabel;
    public JPanel playerScorePanel;
    public JLabel playerScoreGamesWon;
    public JLabel playerScoreHandsWon;
    public JLabel playerChoice;

    public JPanel computerPanel;
    public JLabel computerLabel;
    public JPanel computerScorePanel;
    public JLabel computerScoreGamesWon;
    public JLabel computerScoreHandsWon;
    public JLabel computerScore;
    public JLabel computerChoice;


    public RPSViewer()
    {
        // The window itself
        frame = new JFrame("Rock Paper Scissor - Score Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 400, 200);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        // Status text, showing winner etc..
        statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(2, 1));
        frame.add(statusPanel, BorderLayout.CENTER);

        statusGameWinner = new JLabel("<statusGameWinner>");
        statusGameWinner.setVerticalAlignment(JLabel.CENTER);
        statusGameWinner.setHorizontalAlignment(JLabel.CENTER);
        statusPanel.add(statusGameWinner);

        statusHandWinner = new JLabel("<statusHandWinner>");
        statusHandWinner.setVerticalAlignment(JLabel.CENTER);
        statusHandWinner.setHorizontalAlignment(JLabel.CENTER);
        statusPanel.add(statusHandWinner);

        // Player
        playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(3, 1));
        frame.add(playerPanel, BorderLayout.WEST);

        playerLabel = new JLabel("Player");
        playerLabel.setVerticalAlignment(JLabel.CENTER);
        playerLabel.setHorizontalAlignment(JLabel.CENTER);
        playerPanel.add(playerLabel);

        playerScorePanel = new JPanel();
        playerScorePanel.setLayout(new GridLayout(2, 1));
        playerPanel.add(playerScorePanel);

        playerScoreGamesWon = new JLabel("<playerScoreGamesWon>");
        playerScoreGamesWon.setVerticalAlignment(JLabel.CENTER);
        playerScoreGamesWon.setHorizontalAlignment(JLabel.CENTER);
        playerScorePanel.add(playerScoreGamesWon);

        playerScoreHandsWon = new JLabel("<playerScoreHandsWon>");
        playerScoreHandsWon.setVerticalAlignment(JLabel.CENTER);
        playerScoreHandsWon.setHorizontalAlignment(JLabel.CENTER);
        playerScorePanel.add(playerScoreHandsWon);

        playerChoice = new JLabel("<playerChoice>");
        playerChoice.setVerticalAlignment(JLabel.CENTER);
        playerChoice.setHorizontalAlignment(JLabel.CENTER);
        playerPanel.add(playerChoice);

        // AI / Computer
        computerPanel = new JPanel();
        computerPanel.setLayout(new GridLayout(3, 1));
        frame.add(computerPanel, BorderLayout.EAST);

        computerLabel = new JLabel("Computer");
        computerLabel.setVerticalAlignment(JLabel.CENTER);
        computerLabel.setHorizontalAlignment(JLabel.CENTER);
        computerPanel.add(computerLabel);

        computerScorePanel = new JPanel();
        computerScorePanel.setLayout(new GridLayout(2, 1));
        computerPanel.add(computerScorePanel);

        computerScoreGamesWon = new JLabel("<computerScoreGamesWon>");
        computerScoreGamesWon.setVerticalAlignment(JLabel.CENTER);
        computerScoreGamesWon.setHorizontalAlignment(JLabel.CENTER);
        computerScorePanel.add(computerScoreGamesWon);

        computerScoreHandsWon = new JLabel("<computerScoreHandsWon>");
        computerScoreHandsWon.setVerticalAlignment(JLabel.CENTER);
        computerScoreHandsWon.setHorizontalAlignment(JLabel.CENTER);
        computerScorePanel.add(computerScoreHandsWon);
        
        computerChoice = new JLabel("<computerChoice>");
        computerChoice.setVerticalAlignment(JLabel.CENTER);
        computerChoice.setHorizontalAlignment(JLabel.CENTER);
        computerPanel.add(computerChoice);
    }
}
