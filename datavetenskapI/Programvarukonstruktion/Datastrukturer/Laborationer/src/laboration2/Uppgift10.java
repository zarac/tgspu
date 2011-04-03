package laboration2;

public class Uppgift10
{
    public static int binarySearch(long[] stack, long needle)
    {
        int min = 0, max = stack.length, pos;

        while (min < max)
        {
            System.out.println("a " + (max+min)/2);
            System.out.println("b " + (max-min)/2 + min);

            pos = (max+min)/2;

            if (stack[pos] == needle)
                return pos;
            else if (stack[pos] < needle)
                min = pos;
            else
                max = pos;
        }

        return -1;
    }

    public static void main (String [] args)
    {
        long start = System.currentTimeMillis();
        System.out.println("Result = " + Uppgift10.binarySearch(new long[]{23, 43, 55, 57, 61, 65}, 43));
        long stop = System.currentTimeMillis();

        System.out.println((stop-start) + "ms");
    }
}
