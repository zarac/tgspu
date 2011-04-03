package laboration3;

public class Uppgift7
{
    public static void printArray1(int[] array, int elem)
    {
        System.out.print("[elem:" + elem + "]");
        if (elem >= array.length)
            return;

        System.out.print(array[elem]);
        printArray1(array, ++elem);
    }

    public static void main (String [] args)
    {
        int[] arr = {3, 7, -2, 6, 9};

        printArray1(arr, 0);
        System.out.println();
        printArray1(arr, 3);
        System.out.println();
        printArray1(arr, 10);
        System.out.println();
        printArray1(arr, -3);
        System.out.println();
    }
}
