package p5;

import java.util.Random;

public class SimpleDice implements Dice
{
    private int sides;
    private Random random = new Random();

    public SimpleDice()
    {
        sides = 6;
    }

    public SimpleDice(int p_sides)
    {
        if (p_sides <= 0)
            throw new NegativeSidesException();

        sides = p_sides;
    }

    /**
     * {@inheritDoc}
     * @see Dice#throwDice()
     */
    public int throwDice()
    {
        return random.nextInt(sides) + 1;
    }

    /**
     * {@inheritDoc}
     * @see Dice#getSides()
     */
    public int getSides()
    {
        return sides;
    }
}
