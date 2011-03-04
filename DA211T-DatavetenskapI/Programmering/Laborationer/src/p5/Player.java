package p5;

public abstract class Player
{
    private String name;
    private Dice dice;

    public Player(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
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

    public abstract int throwDice();
}
