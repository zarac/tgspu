package p3;

public class RPSController
{
    private RPSViewer viewer;
    public RPSPlayer player;
    public RPSPlayerAI computer;

    private int rounds;
    private int firstTo;

    // 0 = Draw
    // 1 = Player
    // 2 = Computer
    private int handWinner;
    private int roundWinner;

    public RPSController(RPSPlayer p_player, RPSPlayerAI p_computer, RPSViewer p_viewer)
    {
        player      = p_player;
        computer    = p_computer;
        viewer      = p_viewer;
        handWinner  = -1;
        roundWinner = -1;
        rounds      = 5;
        firstTo     = rounds/2 + 1;
        newGame();
    }

    public String getHandWinner()
    {
        if (handWinner == 0)
        {
            return "Draw";
        }
        else if (handWinner == 1)
        {
            return player.getName();
        }
        else if (handWinner == 2)
        {
            return computer.getName();
        }
        // If there is no hand played.
        else
            return "";
    }

    public String getRoundWinner()
    {
        if (roundWinner == 0)
        {
            return "Draw";
        }
        else if (roundWinner == 1)
        {
            return player.getName();
        }
        else if (roundWinner == 2)
        {
            return computer.getName();
        }
        // If there is no hand played.
        else
            return "";
    }

    public void setRounds(int p_rounds)
    {
        rounds = p_rounds;
        firstTo = rounds/2 + 1;
    }

    public void restart()
    {
        player.setChoice(0);
        computer.setChoice(0);
        handWinner = -1;
        newGame();
    }


    public void playHands(int p_playerChoice, int p_computerChoice)
    {
        player.setChoice(p_playerChoice);
        computer.setChoice(p_computerChoice);

        compareHands();
        checkRounds();
        updateViewer();
    }

    public void compareHands()
    {
        // Draw
        if (player.getChoice() == computer.getChoice())
        {
            handWinner = 0;
        }
        // Player wins
        else if ((player.getChoice() == 1 && computer.getChoice() == 3) || 
                (player.getChoice() == 2 && computer.getChoice() == 1) || 
                (player.getChoice() == 3 && computer.getChoice() == 2))
        {
            handWinner = 1;
            player.increaseHandsWon();
        }
        // Computer wins
        else if ((player.getChoice() == 1 && computer.getChoice() == 2) || 
                (player.getChoice() == 2 && computer.getChoice() == 3) || 
                (player.getChoice() == 3 && computer.getChoice() == 1))
        {
            handWinner = 2;
            computer.increaseHandsWon();
        }
    }

    public void checkRounds()
    {
        if (player.getHandsWon() >= firstTo)
        {
            player.increaseGamesWon();
            roundWinner = 1;
            newGame();
        }
        else if (computer.getHandsWon() >= firstTo)
        {
            computer.increaseGamesWon();
            roundWinner = 2;
            newGame();
        }
    }

    public void updateViewer()
    {
        // Status / Winner
        viewer.statusGameWinner.setText("Game winner : " + getRoundWinner());
        viewer.statusHandWinner.setText("Hand winner : " + getHandWinner());

        // Player
        viewer.playerLabel.setText(player.getName());
        viewer.playerScoreGamesWon.setText("Games : " + String.valueOf(player.getGamesWon()));
        viewer.playerScoreHandsWon.setText("Hands : " + String.valueOf(player.getHandsWon()));
        viewer.playerChoice.setText(String.valueOf(player.getChoiceName()));

        // Computer
        viewer.computerLabel.setText(computer.getName());
        viewer.computerScoreGamesWon.setText("Games : " + String.valueOf(computer.getGamesWon()));
        viewer.computerScoreHandsWon.setText("Hands : " + String.valueOf(computer.getHandsWon()));
        viewer.computerChoice.setText(String.valueOf(computer.getChoiceName()));
    }

    public void newGame()
    {
        player.setHandsWon(0);
        player.setHandsLost(0);

        computer.setHandsWon(0);
        computer.setHandsLost(0);

        updateViewer();
    }

    public void quit()
    {
        System.exit(0);
    }
}
