package laboration3;

public class Uppgift8 
{
    public static void mystery3(int[] array, int elem)
    {
        if ((elem >= 0) && (elem < array.length))
        {
            mystery3(array, elem+1);
            System.out.print(array[elem] + "");
        }
    }

    public static void main (String [] args)
    {
        int[] arr = {3, 7, -2, 6, 9};

        mystery3(arr, 0);
        System.out.println();
        mystery3(arr, 3);
        System.out.println();
        mystery3(arr, 10);
        System.out.println();
        mystery3(arr, -3);
        System.out.println();
    }
}
