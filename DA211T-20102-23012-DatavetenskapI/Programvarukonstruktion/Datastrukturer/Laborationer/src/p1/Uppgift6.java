package p1;

import java.util.Arrays;
import java.util.Comparator;

public class Uppgift6 implements Comparator<Candy>
{
    public static void main(String[] argv)
    {
        CandyShop candyShop = new CandyShop();
        Arrays.sort(candyShop.getCandies(), new Uppgift6());
        CandyShop.printArray(candyShop.getCandies());
    }

    /**
     * {@inheritDoc}
     * @see Comparator#compare(T,T)
     */
    public int compare(Candy candy1, Candy candy2)
    {
        return candy1.getName().compareTo(candy2.getName());
    }
}
