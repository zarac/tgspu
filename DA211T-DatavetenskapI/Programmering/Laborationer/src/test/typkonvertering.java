package test;

public class typkonvertering
{
    public static void main(String[] argv)
    {
        int a = 10;
        double b = 20;
        long c = 30;

        a = b;
        a = c;
        b = a;
        b = c;
        c = a;
        c = b;
    }
}
