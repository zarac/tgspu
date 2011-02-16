package laboration3;

public class Uppgift1
{
    public static void skrivTal(int start, int slut)
    {
        if (start<=slut)
        {
            System.out.print(start + " ");
            skrivTal(start+1, slut);
            skrivTal(start+1, slut);
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }
    }

    public static void main (String [] args)
    {
        skrivTal(10, 15);
        System.out.println();
        skrivTal(15, 10);
        System.out.println();
        skrivTal(-3, 4);
        System.out.println();
    }
}
