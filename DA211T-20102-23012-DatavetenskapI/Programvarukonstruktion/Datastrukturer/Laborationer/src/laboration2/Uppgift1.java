package laboration2;

public class Uppgift1
{
    public static int indexOf(double[] stack, double needle)
    {
        for (int i = 0; i < stack.length; i++) 
            if (stack[i] == needle)
                return i;

        return -1;
    }

    public static void main (String [] args)
    {
        //System.out.println(new Uppgift1().indexOf(new double[]{1.0, 3.2, 4.5, 1, 6.5}, 6.5));
        System.out.println(Uppgift1.indexOf(new double[]{1.0, 3.2, 4.5, 1, 6.5}, 6.5));
    }
}
