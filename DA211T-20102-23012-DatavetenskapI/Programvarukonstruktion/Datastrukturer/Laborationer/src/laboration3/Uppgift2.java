package laboration3;

public class Uppgift2
{
    public static void varannanBaklanges(String txt, int pos)
    {
        if (pos>=0 && pos<txt.length())
        {
            System.out.print(txt.charAt(pos));
            varannanBaklanges(txt, pos-2);
        }
    }

    public static void main (String [] args)
    {
        varannanBaklanges("Student", 6);
        System.out.println();
        varannanBaklanges("Lärare", 3);
        System.out.println();
        varannanBaklanges("Förälder", 17);
        System.out.println();
        varannanBaklanges("Barn", -2);
        System.out.println();
    }
}
