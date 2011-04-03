package p5;

public class OrdinaryPlayer extends Player
{
    private Dice dice;

    public OrdinaryPlayer(String p_name)
    {
        super(p_name);
        dice = new SimpleDice(6);
    }

    public OrdinaryPlayer(String p_name, Dice p_dice)
    {
        super(p_name);
        dice = p_dice;
    }

    /**
     * {@inheritDoc}
     * @see Player#throwDice()
     */
    public int throwDice()
    {
        return dice.throwDice();
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
}
