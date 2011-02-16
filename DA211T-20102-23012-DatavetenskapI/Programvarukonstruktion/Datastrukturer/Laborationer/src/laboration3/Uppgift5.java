package laboration3;

public class Uppgift5 
{
    public static void talBaklanges(int start, int slut)
    {
        if (start <= slut)
        {
            talBaklanges(start + 1, slut);
            System.out.print(start + " ");
        }
    }
    public static void main (String [] args)
    {
        talBaklanges(4, 10);
        System.out.println();
        talBaklanges(5, 2);
        System.out.println();
        talBaklanges(-2, 1);
        System.out.println();
    }
}
