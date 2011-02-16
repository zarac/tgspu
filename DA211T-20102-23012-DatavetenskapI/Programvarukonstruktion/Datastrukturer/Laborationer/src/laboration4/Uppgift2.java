package laboration4;

class Uppgift2 
{
    static long fakultet(long tal)
    {
        if (tal <= 1)
            return 1;

        return tal * fakultet(tal-1);
    }
    
    public static void main (String [] args)
    {
        System.out.println(fakultet(3));
        System.out.println(fakultet(-3));
        System.out.println(fakultet(6));
    }
}
