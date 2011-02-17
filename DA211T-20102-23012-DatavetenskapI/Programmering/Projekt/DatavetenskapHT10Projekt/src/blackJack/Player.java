package blackJack;

public class Player
{
    private Logger logger = new Logger(this);

    private int id;
    private String name;
    private int stack;

    /**
     * Constructs a new instance.
     */
    public Player(String name, int stack)
    {
        logger.debug("Player(" + name + ", " + stack + "):");

        this.name = name;
        this.stack = stack;
    }

    /**
     * Gets the id for this instance.
     *
     * @return The id.
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Sets the id for this instance.
     *
     * @param id The id.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Gets the name for this instance.
     *
     * @return The name.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Sets the name for this instance.
     *
     * @param name The name.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the stack for this instance.
     *
     * @return The stack.
     */
    public int getStack()
    {
        return stack;
    }

    /**
     * Sets the stack for this instance.
     *
     * @param stack The stack.
     */
    public void setStack(int stack)
    {
        this.stack = stack;
    }

    /**
     * Lägger till marker till spelarens stack
     *
     * @param amount Amount to remove from player stack.
     */
    public void incStack(int amount)
    {
        stack += amount;
    }

    /**
     * Tar bort marker från spelarens stack
     *
     * @param amount Amount to add to player stack.
     */
    public void decStack(int amount)
    {
        stack -= amount;
    }
}
