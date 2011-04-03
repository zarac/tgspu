package p1;

import java.awt.Color;

public class CandyShop
{
    private Candy[] candies;

    public CandyShop()
    {
        System.out.println("Welcome to the Candy Shop.");

        candies = new Candy[10];

        candies[0] = new Candy("Jell-O Hjärt", 3.4, Candy.SWEET, "Like your loved one / your mother.", Color.RED, false);
        candies[1] = new Candy("Spenatmask", 30.4, Candy.BOOGER, "Something you probably don't want to eat.", Color.GREEN, false);
        candies[2] = new Candy("Kolanapp", 8.1, Candy.SWEET, "Tastes better than Coca-Cola.", new Color(188, 143, 143), false);
        candies[3] = new Candy("Risbräck", 4.20, Candy.CHOCOLATE, "Like your loved one / your mother.", new Color(188, 143, 143), false);
        candies[4] = new Candy("Jordnötsruta", 30.3, Candy.CHOCOLATE, "Peanuts sticking in your teeth.", new Color(188, 143, 143), false);
        candies[5] = new Candy("Surblåflaska", 16.3, Candy.SOUR, "Your mind will blow!", Color.BLUE, false);
        candies[6] = new Candy("Dumle", 18.3, Candy.SOUR, "Fudgy", new Color(188, 143, 143), true);
        candies[7] = new Candy("Dödskalle", 59.3, Candy.SALTY, "Salty and sweet.", Color.BLACK, false);
        candies[8] = new Candy("Harpluttar", 1.3, Candy.SALTY, "Salty, sweet and slightly pooish.", Color.BLACK, false);
        candies[9] = new Candy("Lollipop", 7.3, Candy.SWEET, "The sweetest you've ever tasted.", Color.CYAN, true);
    }

    public static void insertionSort(Candy[] array)
    { 
        for(int i = 1; i < array.length; i++)
            for (int j = i; (j > 0) && ((Comparable)array[j-1]).compareTo(array[j]) > 0 ;j--)
                swap(array, j,j-1);
    }

    public static void swap(Candy[] array, int position1, int position2)
    {
        System.out.println(" > Swapping item " + position1 + "(" + array[position1] + ") with item " + position2 + "(" + array[position2] + ")");
        Candy temp = array[position1];
        array[position1] = array[position2];
        array[position2] = temp;
    }

    public static void printArray(Candy[] array)
    {
        for (Candy candy : array)
            System.out.println(candy.toString());

        System.out.println();
    }

    public static void main(String[] argv)
    {
        CandyShop candyShop = new CandyShop();

        System.out.println("Unsorted candy shop inventory...");
        CandyShop.printArray(candyShop.getCandies());
        System.out.println("Sorting the inventory.");
        CandyShop.insertionSort(candyShop.getCandies());
        System.out.println("Sorted candy shop inventory...");
        CandyShop.printArray(candyShop.getCandies());
    }

    /**
     * Gets the candies for this instance.
     *
     * @return The candies.
     */
    public Candy[] getCandies()
    {
        return this.candies;
    }

    /**
     * Gets the candies for this instance.
     *
     * @param index The index to get.
     * @return The candies.
     */
    public Candy getCandies(int index)
    {
        return this.candies[index];
    }
}
