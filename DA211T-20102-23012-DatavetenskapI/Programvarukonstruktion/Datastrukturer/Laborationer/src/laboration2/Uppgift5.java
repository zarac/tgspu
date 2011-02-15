package laboration2;

public class Uppgift5
{
    public static RealNbr[] getRealNbrArray(int size)
    {
        RealNbr[] realNbrs = new RealNbr[size];

        for (int i = 0; i < size; i++) 
            realNbrs[i] = new RealNbr(Math.random());

        Uppgift4.shuffle(realNbrs);

        return realNbrs;
    }

    public static void main (String [] args)
    {
        for (RealNbr realNbr : Uppgift5.getRealNbrArray(30))
            System.out.println(realNbr.getValue());
    }
}
