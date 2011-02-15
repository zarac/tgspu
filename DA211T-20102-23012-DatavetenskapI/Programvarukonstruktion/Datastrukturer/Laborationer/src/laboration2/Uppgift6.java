package laboration2;

public class Uppgift6
{
    public static int indexOf(RealNbr[] stack, RealNbr needle)
    {
        for (int i = 0; i < stack.length; i++) 
            if (stack[i] == needle)
                return i;

        return -1;
    }
}
