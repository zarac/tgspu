package laboration15;

public class Provisionslon extends AnstalldLon
{
    private double provision;
    private double forsaljning;

    /**
     * Constructs a new instance.
     */
    public Provisionslon(long id, double provision)
    {
        super(id);
        this.provision = provision;
    }

    /**
     * {@inheritDoc}
     * @see AnstalldLon#lon()
     */
    public double lon()
    {
        return provision * forsaljning;
    }

    /**
     * Gets the provision for this instance.
     *
     * @return The provision.
     */
    public double getProvision()
    {
        return this.provision;
    }

    /**
     * Sets the provision for this instance.
     *
     * @param provision The provision.
     */
    public void setProvision(double provision)
    {
        this.provision = provision;
    }

    /**
     * Gets the forsaljning for this instance.
     *
     * @return The forsaljning.
     */
    public double getForsaljning()
    {
        return this.forsaljning;
    }

    /**
     * Sets the forsaljning for this instance.
     *
     * @param forsaljning The forsaljning.
     */
    public void setForsaljning(double forsaljning)
    {
        this.forsaljning = forsaljning;
    }
}
