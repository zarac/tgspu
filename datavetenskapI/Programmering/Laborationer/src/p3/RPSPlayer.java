package p3;

public class RPSPlayer
{
    protected String name;

    // 0 = none
    // 1 = rock
    // 2 = paper
    // 3 = scissor
    private int choice;

    private int gamesWon;
    private int gamesLost;

    private int handsWon;
    private int handsLost;


    public RPSPlayer()
    {
        name = "Player";
        reset();
    }


    public void reset()
    {
        gamesWon    = 0;
        gamesLost   = 0;
        handsWon    = 0;
        handsLost   = 0;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String p_name)
    {
        name = p_name;
    }

    public int getChoice()
    {
        return choice;
    }

    public String getChoiceName()
    {
        if (choice == 1)
        {
            return "Rock";
        }
        else if (choice == 2)
        {
            return "Paper";
        }
        else if (choice == 3)
        {
            return "Scissor";
        }
        else
        {
            return "";
        }
    }

    public void setChoice(int p_choice)
    {
        choice = p_choice;
    }

    public int getGamesWon()
    {
        return gamesWon;
    }

    public void increaseGamesWon()
    {
        gamesWon++;
    }

    public int getGamesLost()
    {
        return gamesLost;
    }

    public int getHandsWon()
    {
        return handsWon;
    }

    public void setHandsWon(int p_handsWon)
    {
        handsWon = p_handsWon;
    }

    public void increaseHandsWon()
    {
        handsWon++;
    }

    public int getHandsLost()
    {
        return handsLost;
    }

    public void setHandsLost(int p_handsLost)
    {
        handsLost = p_handsLost;
    }
}
