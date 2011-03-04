package laboration15;

public class Timlon extends AnstalldLon
{
    private double timlon;
    private double arbetadeTimmar;

    /**
     * Constructs a new instance.
     */
    public Timlon(long id, double timlon)
    {
        super(id);
        this.timlon = timlon;
    }

    /**
     * {@inheritDoc}
     * @see AnstalldLon#lon()
     */
    public double lon()
    {
        return arbetadeTimmar * timlon;
    }

    /**
     * Gets the timlon for this instance.
     *
     * @return The timlon.
     */
    public double getTimlon()
    {
        return this.timlon;
    }

    /**
     * Sets the timlon for this instance.
     *
     * @param timlon The timlon.
     */
    public void setTimlon(double timlon)
    {
        this.timlon = timlon;
    }

    /**
     * Gets the arbetadeTimmar for this instance.
     *
     * @return The arbetadeTimmar.
     */
    public double getArbetadeTimmar()
    {
        return this.arbetadeTimmar;
    }

    /**
     * Sets the arbetadeTimmar for this instance.
     *
     * @param arbetadeTimmar The arbetadeTimmar.
     */
    public void setArbetadeTimmar(double arbetadeTimmar)
    {
        this.arbetadeTimmar = arbetadeTimmar;
    }
}
