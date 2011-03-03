package laboration2;

public class Uppgift3
{
    public static void main (String [] args)
    {
        Laboration2 lab2 = new Laboration2();
        int[] randomIntArray = lab2.randomIntArray(10000);

        LinearSearch linearSearch = new LinearSearch();

        long start = System.currentTimeMillis();

        for (int i = 0; i < randomIntArray.length; i++) 
            linearSearch.indexOf(randomIntArray, randomIntArray[i]);
            //System.out.print(" " + linearSearch.indexOf(randomIntArray, randomIntArray[i]) + "=" + randomIntArray[i]);

        //System.out.println();

        long stop = System.currentTimeMillis();

        System.out.println("Total time = '" + (stop - start) + "ms'");
    }
}
