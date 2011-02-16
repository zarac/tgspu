package p1;

public class Uppgift1
{
    public static void bubbleSort(long[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.println("iteration (i)" + i);
            for (int j = array.length - 1; j > i; j--)
            {
                System.out.println("iteration (j)" + j);
                if (array[j] < array[j-1])
                    Utility.swap(array, j, j-1);
            }
        }
    }

    public static void main(String[] argv)
    {
        long[] array = {7, 11, 3, 5, 2 };
        Utility.printArray(array);

        bubbleSort(array);
        Utility.printArray(array);
    }
}
