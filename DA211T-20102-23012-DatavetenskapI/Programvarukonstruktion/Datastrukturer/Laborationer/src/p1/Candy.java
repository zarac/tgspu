package p1;

import java.awt.Color;

/**
 * A candy shop jao!
 * 
 * @author Hannes Landstedt
 */
public class Candy implements Comparable<Candy>
{
    private String name;
    private double weight;
    private String flavor;
    private Color color;
    private boolean isWrapped;

    protected int kind;
    public static final int SWEET = 0;
    public static final int SOUR = 1;
    public static final int SALTY = 2;
    public static final int CHOCOLATE = 3;
    public static final int BOOGER = 4;

    /**
     * Creates a new instance of a candy.
     * 
     * @param name The name of the candy.
     * @param weight Weight in grams.
     * @param kind The kind/type of candy.
     * @param flavor A more specific description for the flavor of the candy.
     * @param color The candy's color.
     * @param isWrapped If the candy has a wrapper or not.
     */
    public Candy(
            String name,
            double weight,
            int kind,
            String flavor,
            Color color,
            boolean isWrapped)
    {
        this.name = name;
        this.weight = weight;
        this.kind = kind;
        this.flavor = flavor;
        this.color = color;
        this.isWrapped = isWrapped;
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
     * Gets the weight for this instance.
     *
     * @return The weight.
     */
    public double getWeight()
    {
        return this.weight;
    }

    /**
     * Sets the weight for this instance.
     *
     * @param weight The weight.
     */
    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    /**
     * Gets the flavor for this instance.
     *
     * @return The flavor.
     */
    public String getFlavor()
    {
        return this.flavor;
    }

    /**
     * Sets the flavor for this instance.
     *
     * @param flavor The flavor.
     */
    public void setFlavor(String flavor)
    {
        this.flavor = flavor;
    }

    /**
     * Gets the color for this instance.
     *
     * @return The color.
     */
    public Color getColor()
    {
        return this.color;
    }

    /**
     * Sets the color for this instance.
     *
     * @param color The color.
     */
    public void setColor(Color color)
    {
        this.color = color;
    }

    /**
     * Determines if this instance is isWrapped.
     *
     * @return The isWrapped.
     */
    public boolean isIsWrapped()
    {
        return this.isWrapped;
    }

    /**
     * Sets whether or not this instance is isWrapped.
     *
     * @param isWrapped The isWrapped.
     */
    public void setIsWrapped(boolean isWrapped)
    {
        this.isWrapped = isWrapped;
    }

    /**
     * {@inheritDoc}
     * @see Object#toString()
     */
    public String toString()
    {
        return "name='" + name + "', weight='" + weight + "', kind='" + kind + "', flavor='" + flavor + "', color='" + color + "', isWrapped='" + isWrapped + "'";
    }

    /**
     * {@inheritDoc}
     * @see Comparable#compareTo(T)
     */
    public int compareTo(Candy candy)
    {
        if (kind != candy.kind)
            return kind - candy.kind;

        return (int)((weight - candy.weight)*1000);
    }
}
