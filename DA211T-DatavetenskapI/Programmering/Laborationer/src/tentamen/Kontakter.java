package tentamen;

public class Kontakter
{
    public static void main(String[] argv)
    {
        Worker kontakt1 = new Worker();
        Person kontakt2 = new Person();
        kontakt2.setLength(2);

        if (kontakt1 instanceof Worker)
        {
            System.out.println("bajs");
        }

        if (kontakt2 instanceof Worker)
        {
            System.out.println("korv");
        }

        if (-1 == kontakt1.compareTo(kontakt2))
        {
            System.out.println("mindre");
        }
        if (0 == kontakt1.compareTo(kontakt2))
        {
            System.out.println("lika");
        }
    }
}
