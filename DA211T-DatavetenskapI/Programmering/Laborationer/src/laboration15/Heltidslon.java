package laboration15;

public class Heltidslon extends AnstalldLon
{
    private double manadslon;

    public Heltidslon(long id, double manadslon)
    {
        super(id);
        this.manadslon = manadslon;
    }

    /**
     * Gets the manadslon for this instance.
     *
     * @return The manadslon.
     */
    public double getManadslon()
    {
        return this.manadslon;
    }

    /**
     * Sets the manadslon for this instance.
     *
     * @param manadslon The manadslon.
     */
    public void setManadslon(double manadslon)
    {
        this.manadslon = manadslon;
    }

    /**
     * {@inheritDoc}
     * @see AnstalldLon#lon()
     */
    public double lon()
    {
        return manadslon;
    }
}
