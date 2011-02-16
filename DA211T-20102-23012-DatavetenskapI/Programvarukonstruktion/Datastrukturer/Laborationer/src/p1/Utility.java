package p1;

public class Utility
{
    public static void printArray(long[] array)
    {
        for (long element : array)
            System.out.print(element + " ");

        System.out.println();
    }

    public static void swap(long[] array, int position1, int position2)
    {
        System.out.println(" > Swapping item " + position1 + "(" + array[position1] + ") with item " + position2 + "(" + array[position2] + ")");
        long temp = array[position1];
        array[position1] = array[position2];
        array[position2] = temp;
    }
}
