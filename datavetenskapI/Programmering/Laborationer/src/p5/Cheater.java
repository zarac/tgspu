package p5;

import java.util.Random;

public class Cheater extends Player
{
    private Dice dice;
    private Random random = new Random();

    public Cheater(String p_name)
    {
        super(p_name);
        dice = new SimpleDice(6);
    }

    public Cheater(String p_name, Dice p_dice)
    {
        super(p_name);
        dice = p_dice;
    }

    /**
     * Adds one to the result about half the time.
     * {@inheritDoc}
     * @see Player#throwDice()
     */
    public int throwDice()
    {
        if (random.nextInt(2) == 1)
        {
            return dice.throwDice();
        }
        else
        {
            int returnValue = dice.throwDice();
            if (returnValue < dice.getSides())
                returnValue++;
            return returnValue;
        }
    }

    /**
     * Gets the dice for this instance.
     *
     * @return The dice.
     */
    public Dice getDice()
    {
        return this.dice;
    }

    /**
     * Sets the dice for this instance.
     *
     * @param dice The dice.
     */
    public void setDice(Dice dice)
    {
        this.dice = dice;
    }
    public static void main(String[] args) {
        Player spelare1 = new OrdinaryPlayer( "Viktor",new SimpleDice( 6 ) ); 
        Player spelare2 = new Cheater( "Signe", new SimpleDice( 6 ) ); 
        Game spel = new Game( spelare1, spelare2 ); 
        System.out.println( "\nResultatet av tio spel" ); 
        for( int i=0; i<10; i++ ) 
            spel.play( false );
    }
}
