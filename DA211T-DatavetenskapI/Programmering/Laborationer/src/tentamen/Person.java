package tentamen;

public class Person implements Comparable
{
    private String name;
    private String socSecNbr;
    private double length;

    public Person()
    {
        name = "";
        socSecNbr = "";
    }

    public Person(String name, String socSecNbr, double length)
    {
        //Random random = new Random();
        //random.next;

        this.name = name;

        if (socSecNbr.length() > 10)
            this.socSecNbr = socSecNbr;
        else
            this.socSecNbr = "19" + socSecNbr;

        this.length = length;
    }

    public String toString()
    {
        return "Namn: " + name + " Personnummer: " + socSecNbr + " Längd: " + length + " cm";
    }


    public static void main(String[] argv)
    {
        Person p1 = new Person();
        Person p2 = new Person("Sven Alberg", "582031-3414", 182.5);
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p1.getAge(2011));
        System.out.println(p2.getAge(2011));
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
     * Gets the socSecNbr for this instance.
     *
     * @return The socSecNbr.
     */
    public String getSocSecNbr()
    {
        return this.socSecNbr;
    }

    /**
     * Sets the length for this instance.
     *
     * @param length The length.
     */
    public void setLength(double length)
    {
        this.length = length;
    }

    /**
     * Gets the length for this instance.
     *
     * @return The length.
     */
    public double getLength()
    {
        return this.length;
    }

    public int getAge(int year)
    {
        if (socSecNbr.length() == 0)
            return -1;

        return year - 1900 - Integer.parseInt(socSecNbr.substring(0,2));
    }

    /**
     * {@inheritDoc}
     * @see Comparable#compareTo(T)
     */
    public int compareTo(Person o)
    {
        if (length == o.getLength())
            return 0;
        else if (length < o.getLength())
            return -1;
        else
            return 1;
    }
}
