package laboration2;

import java.util.Arrays;

public class Uppgift9
{
    public static void main (String [] args)
    {
        Laboration2 lab2 = new Laboration2();
        int[] randomIntArray = lab2.randomIntArray(10000);

        LinearSearch linearSearch = new LinearSearch();

        // Linear search
        long start = System.currentTimeMillis();

        for (int i = 0; i < randomIntArray.length; i++) 
            linearSearch.indexOf(randomIntArray, randomIntArray[i]);

        long stop = System.currentTimeMillis();
        System.out.println("Total time (linear search) = '" + (stop - start) + "ms'");

        // Binary search
        // TODO Why isn't binary search faster?
        Arrays.sort(randomIntArray);
        start = System.currentTimeMillis();

        for (int i = 0; i < randomIntArray.length; i++) 
            linearSearch.indexOf(randomIntArray, randomIntArray[i]);

        stop = System.currentTimeMillis();

        System.out.println("Total time (binary search) = '" + (stop - start) + "ms'");
    }
}
