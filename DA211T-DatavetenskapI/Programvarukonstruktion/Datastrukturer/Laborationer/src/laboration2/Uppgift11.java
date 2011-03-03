package laboration2;

public class Uppgift11 
{
    public static int binarySearch(String[] stack, String needle)
    {
        int min = 0, max = stack.length, pos = stack.length/2;

        while (min < max)
        {
            if (stack[pos].compareTo(needle) == 0)
                return pos;
            else if (stack[pos].compareTo(needle) == -1)
                min = pos;
            else
                max = pos;

            pos = (max-min)/2 + min;
        }

        return -1;
    }

    public static void main (String [] args)
    {
        long start = System.currentTimeMillis();
        System.out.println(Uppgift11.binarySearch(new String[]{"hej", "hopp", "snopp"}, "snopp"));
        long stop = System.currentTimeMillis();

        System.out.println((stop-start) + "ms");
    }
}
