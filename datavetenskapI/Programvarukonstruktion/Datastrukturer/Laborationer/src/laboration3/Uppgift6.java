package laboration3;

public class Uppgift6
{
    public static void varjeTecken1(String txt, int pos)
    {
        System.out.print("txt:" + txt + " pos:" + pos + " :: ");
        if (pos >= txt.length()-1)
            return;

        System.out.println(txt.substring(pos, txt.length()));
        varjeTecken1(txt, ++pos);
    }


    public static void main (String [] args)
    {
        varjeTecken1("Student", 0);
        varjeTecken1("Malmö högskola!", 6);
        varjeTecken1("Hubert", 10);
        varjeTecken1("Negative position", -6);
        varjeTecken1("Student", 3);
    }
}
