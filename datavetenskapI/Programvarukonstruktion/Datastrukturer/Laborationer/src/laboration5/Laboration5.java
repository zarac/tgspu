package laboration5;

class Laboration5 
{
    public static double[] randomArray(int n, double min, double max)
    {
        double[] returnValue = new double[n];

        for (int i = 0; i < returnValue.length; i++)
            returnValue[i] = min + Math.random() * (max-min);
            //returnValue[i] = Math.random() * (min-max) + min;

        return returnValue;
    }

    public static void insertionSortAsc(double[] array)
    {
        for (int i = 1; i < array.length; i++)
            for (int j = i; (j > 0) && (array[j-1] > array[j]); j--)
                swap(array, j, j-1);
    }

    public static void insertionSortDec(double[] array)
    {
        for (int i = 1; i < array.length; i++)
            for (int j = i; (j > 0) && (array[j-1] < array[j]); j--)
                swap(array, j, j-1);
    }

    public static void swap(double[] array, int i, int j)
    {
        double temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void bubbleSortAsc(double[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
            for (int j = array.length - 1; j > i; j--)
                if (array[j] < array[j-1])
                    swap(array, j, j-1);
    }

    public static void bubbleSortDec(double[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
            for (int j = array.length - 1; j > i; j--)
                if (array[j] > array[j-1])
                    swap(array, j, j-1);
    }

    public static void mergeSortAsc(double[] array)
    {
        double[] temp = new double[array.length];
        mergeSortAsc(array, 0, array.length, temp);
        temp = null;
    }

    public static void mergeSortAsc(double[] array, int first, int n, double[] temp)
    {
        int n1, n2;
        if (n > 1)
        {
            n1 = n/2;
            n2 = n - n1;
            mergeSortAsc(array, first, n1, temp);
            mergeSortAsc(array, first + n1, n2, temp);
            mergeAsc(array, first, n1, n2, temp);
        }
    }

    public static void mergeAsc(double[] array, int first, int n1, int n2, double[] temp)
    {
        int counter = 0, cursor1 = 0, cursor2 = n1, end = n1+n2;

        while ((cursor1 < n1) && (cursor2 < end))
        {
            if (array[first + cursor1] > array[first + cursor2])
            {
                temp[counter] = array[first + cursor1];
                cursor1++;
            }
            else
            {
                temp[counter] = array[first + cursor2];
                cursor2++;
            }
            counter++;
        }

        while (cursor1 < n1)
        {
            temp[counter] = array[first + cursor1];
            cursor1++;
            counter++;
        }

        while (cursor2 < end)
        {
            temp[counter] = array[first + cursor2];
            cursor2++;
            counter++;
        }

        for (int i = 0; i < n1 + n2; i++)
        {
            array[first + i] = temp[i];
        }
    }

    public static void main(String [] args)
    {
        double[] randomArray = randomArray(10, 0.2, 0.6);

        System.out.println("THE ARRAY");
        for (double value : randomArray)
            System.out.println(value);

        //System.out.println("THE ARRAY - INSERTION SORT DECENDING");
        //insertionSortDec(randomArray);
        //for (double value : randomArray)
            //System.out.println(value);

        //System.out.println("THE ARRAY - INSERTION SORT ASCENDING");
        //insertionSortAsc(randomArray);
        //for (double value : randomArray)
            //System.out.println(value);

        //randomArray = randomArray(10, 0.2, 0.6);
        //System.out.println("THE ARRAY - BUBBLE SORT DECENDING");
        //bubbleSortDec(randomArray);
        //for (double value : randomArray)
            //System.out.println(value);

        //System.out.println("THE ARRAY - BUBBLE SORT ASCENDING");
        //bubbleSortAsc(randomArray);
        //for (double value : randomArray)
            //System.out.println(value);

        System.out.println("THE ARRAY - MERGE SORT ASCENDING");
        mergeSortAsc(randomArray);
        for (double value : randomArray)
            System.out.println(value);
    }
}
