package laboration2;

public class Uppgift2
{
    public static int indexOf(String[] stack, String needle)
    {
        for (int i = 0; i < stack.length; i++) 
            if (stack[i] == needle)
                return i;

        return -1;
    }

    public static void main (String [] args)
    {
        //System.out.println(new Uppgift2().indexOf(new String[]{"hej", "hopp", "snopp"}, "hopp"));
        System.out.println(Uppgift2.indexOf(new String[]{"hej", "hopp", "snopp"}, "hopp"));
    }
}
