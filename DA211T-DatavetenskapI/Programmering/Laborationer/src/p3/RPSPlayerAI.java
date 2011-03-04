package p3;

import java.util.Random;

public class RPSPlayerAI extends RPSPlayer
{
    private Random random;


    public RPSPlayerAI()
    {
        name = "Computer";
        random = new Random();
        reset();
    }

    
    public int newChoice()
    {
        return random.nextInt(3) + 1;
    }
}
