package laboration3;

public class Uppgift9 
{
    public static void printArray2(int[] array, int elem)
    {
        if (elem < array.length && elem >=0)
        {
            System.out.print(array[elem]);
            printArray2(array, elem-1);
        }
    }

    public static void main (String [] args)
    {
        int[] arr = {3, 7, -2, 6, 9};

        printArray2(arr, 4);
        System.out.println();
        printArray2(arr, 1);
        System.out.println();
        printArray2(arr, -1);
        System.out.println();
        printArray2(arr, 7);
        System.out.println();
    }
}
