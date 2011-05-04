package steinerland;

import java.util.Comparator;

public class SteinerlandNode extends graf.Node<String> implements Comparator<SteinerlandNode>
{
    protected String city;
    protected short minutesFromMidnight;

    public SteinerlandNode(String city)
    {
        // TODO : ? Can we have -1 here, in order to represent an invalid/no
        // time? In other words a "city node".
        this(city, (short)-1);
    }

    public SteinerlandNode(String city, short minutesFromMidnight)
    {
        super(city);
        this.city = city;
        this.minutesFromMidnight = minutesFromMidnight;
    }

    /**
     * {@inheritDoc}
     * @see Comparator#compare(SteinerlandNode,SteinerlandNode)
     */
    public int compare(SteinerlandNode o1, SteinerlandNode o2)
    {
        return 0;
    }

    /**
     * {@inheritDoc}
     * @see Comparator#equals(Object)
     */
    public boolean equals(Object obj)
    {
        return (city.equals(((SteinerlandNode)obj).getCity()));
    }

    /**
     * Gets the city for this instance.
     *
     * @return The city.
     */
    public String getCity()
    {
        return this.city;
    }
}
