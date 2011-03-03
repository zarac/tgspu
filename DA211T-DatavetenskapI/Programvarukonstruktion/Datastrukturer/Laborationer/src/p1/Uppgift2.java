package p1;

public class Uppgift2
{
    public static void mergeSortDec(long[] array)
    {
        mergeSortDec(array, 0, array.length);
    }

    public static void mergeSortDec(long[] array, int first, int length)
    {
        if (length > 1)
        {
            int half = length/2;
            mergeSortDec(array, first, half);
            mergeSortDec(array, first + half, length - half);
            mergeDec(array, first, half, length-half);
        }
    }

    public static void mergeDec(
            long[] array,
            int firstElem,
            int firstHalfLength,
            int secondHalfLength)
    {
        Utility.log(" > mergeDec(firstElem='" + firstElem + "', firstHalfLength='" + firstHalfLength + "', secondHalfLength='" + secondHalfLength + "'):");

        int counter = 0;
        // Position in first sub-array.
        int cursor1 = 0;
        // Position in second sub-array.
        int cursor2 = firstHalfLength;
        int end = firstHalfLength + secondHalfLength;
        Utility.log("end='" + end + "'");

        // Temporary array to contains the sorted elements.
        long[] temp = new long[end];

        // Add the smallest of the first elements in each "sub-array".
        Utility.log("Main merge...");
        while (cursor1 < firstHalfLength && cursor2 < end)
        {
            Utility.log("cursor1='" + cursor1 + "', cursor2='" + cursor2 + "'");
            if (array[firstElem + cursor1] > array[firstElem + cursor2])
            {
                temp[counter++] = array[firstElem + cursor1];
                cursor1++;
            }
            else
            {
                temp[counter++] = array[firstElem + cursor2];
                cursor2++;
            }
            Utility.printArray(temp);
        }

        // Add the rest from first sub-array, if any.
        Utility.log("Rest of first half...");
        while (cursor1 < firstHalfLength)
        {
            temp[counter++] = array[firstElem + cursor1];
            cursor1++;
            Utility.printArray(temp);
        }

        // Add the rest from second sub-array, if any.
        Utility.log("Rest of second half...");
        while (cursor2 < end)
        {
            temp[counter++] = array[firstElem + cursor2];
            cursor2++;
            Utility.printArray(temp);
        }

        // Overwrite the scrambled elements in the array with our sorted elements.
        Utility.log("Transferring from temprary to main array.");
        Utility.printArray(array);
        for (int i = 0; i < firstHalfLength + secondHalfLength; i++)
        {
            array[firstElem + i] = temp[i];
            Utility.printArray(array);
        }
    }

    public static void main(String[] argv)
    {
        long[] array = {7, 11, 3, 5, 2 };
        System.out.print("ARRAY = ");
        Utility.printArray(array);

        mergeSortDec(array);
        System.out.print("MERGE SORTED ARRAY = ");
        Utility.printArray(array);
    }
}
