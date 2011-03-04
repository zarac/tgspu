package laboration12;

public class Uppgift12b
{
    public void biggerThan8(double[] tal)
    {
        for (double t_tal : tal)
        {
            if (t_tal > 8)
            {
                System.out.print(" " + t_tal);
            }
        }
        System.out.println();
    }

    public void negativeNumbers(double[] tal)
    {
        for (double t_tal : tal)
        {
            if (t_tal < 0)
            {
                System.out.print(" " + t_tal);
            }
        }
        System.out.println();
    }

    public void reverse(double[] tal)
    {
        for (int i = tal.length - 1; i >= 0; i--)
        {
            System.out.print(" " + tal[i]);
        }
        System.out.println();
    }

    public void everyThirdReverse(double[] tal)
    {
        for (int i = tal.length - 1; i >= 0; i-=3)
        {
            System.out.print(" " + tal[i]);
        }
        System.out.println();
    }


    public static void main(String[] argv)
    {
        Uppgift12b jao = new Uppgift12b();

        double[] tal1 = { 23.2, 14.7, 17.0, -5.9, -11.1, 26.3, 8.3, 7.6 };
        double[] tal2 = { -1, 2, 5, 8, 11, 14, 10, 6, 2, -4 };
        //störreÄn8( tal );
        jao.biggerThan8(tal1);
        jao.negativeNumbers(tal1);
        jao.reverse(tal1);
        jao.everyThirdReverse(tal1);

        jao.biggerThan8(tal2);
        //negativaTal( tal );
        jao.negativeNumbers(tal2);
        //baklänges( tal );
        jao.reverse(tal2);
        //vartTredjeBaklänges( tal );
        jao.everyThirdReverse(tal2);
    }
}
