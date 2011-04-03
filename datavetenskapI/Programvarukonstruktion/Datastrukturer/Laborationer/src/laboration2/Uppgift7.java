package laboration2;

public class Uppgift7 
{
    public static int indexOf(Object[] stack, Object needle)
    {
        for (int i = 0; i < stack.length; i++)
            if (stack[i] == needle)
                return i;

        return -1;
    }

    public static void main (String [] args)
    {
        RealNbr[] realNbrs = Uppgift5.getRealNbrArray(5);
        System.out.println(Uppgift7.indexOf(realNbrs, realNbrs[3]));

        System.out.println(Uppgift7.indexOf(new String[]{"hej", "hopp", "snopp"}, "hopp"));
    }
}
