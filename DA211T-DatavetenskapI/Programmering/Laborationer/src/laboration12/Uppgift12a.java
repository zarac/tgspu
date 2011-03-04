package laboration12;


public class Uppgift12a
{
    public void action()
    {
        System.out.println();
        System.out.println("Uppgift12a.action()");
        System.out.println("===========");
        double[] tal = {23.2, 14.7, 17.0, -5.9, -11.1, 26.3, 8.3, 7.6 };
        //double[] tal = { -1, 2, 5, 8, 11, 14, 10, 6, 2, -4 };
        int antal = 0;
        //double summa = 0;
        //String res = "";

        for (int i = 0; i < tal.length; i++)
        {
            if(tal[i] < 10)
            {
                System.out.print(tal[i] + " ");
                antal++;
            }
        }

        System.out.print(", antal = " + antal);
        System.out.println();

        //System.out.println("summa = " + summa);
        //System.out.println("res = " + res);
    }

    public void a1(double[] tal)
    {
        System.out.println();
        System.out.println("Uppgift12a1");
        System.out.println("===========");
        System.out.println("tal.length() = " + tal.length);
    }

    public void a2(double[] tal)
    {
        System.out.println();
        System.out.println("Uppgift12a2");
        System.out.println("===========");

        double summa = 0;

        for (int i = 0; i < tal.length; i++)
        {
            summa += tal[i];
        }
        System.out.println("summa = " + summa);
    }

    public void a3(double[] tal)
    {
        System.out.println();
        System.out.println("Uppgift12a3");
        System.out.println("===========");

        for (double t_tal : tal)
        {
            if (t_tal > 8)
            {
                System.out.print(" " + t_tal);
            }
        }
        System.out.println();
    } 

    public void a4(double[] tal)
    {
        System.out.println();
        System.out.println("Uppgift12a4");
        System.out.println("===========");

        int antal = 0;

        for (double t_tal : tal)
        {
            if (t_tal > 8)
            {
                antal++;
            }
        }

        System.out.println("antalet tal strörre än 8: " + antal);
    }

    public void a5(double[] tal)
    {
        System.out.println();
        System.out.println("Uppgift12a5");
        System.out.println("===========");

        for (double t_tal : tal)
        {
            if (t_tal < 0)
            {
                System.out.print(" " + t_tal);
            }
        }
        System.out.println();
    }


    public void a6(double[] tal)
    {
        System.out.println();
        System.out.println("Uppgift12a6");
        System.out.println("===========");

        double summa = 0;

        for (double t_tal : tal)
        {
            if (t_tal < 0)
            {
                summa += t_tal;
            }
        }

        System.out.println("summan av tal mindre än 0: " + summa);
    }

    public void a7(double[] tal)
    {
        System.out.println();
        System.out.println("Uppgift12a7");
        System.out.println("===========");

        for (int i = tal.length - 1; i >= 0; i--)
        {
            System.out.print(" " + tal[i]);
        }
        System.out.println();
    }

    public void a8(double[] tal)
    {
        System.out.println();
        System.out.println("Uppgift12a8");
        System.out.println("===========");

        for (int i = tal.length - 1; i >= 0; i-=3)
        {
            System.out.print(" " + tal[i]);
        }
        System.out.println();
    }

    public void a9(double[] tal)
    {
        System.out.println();
        System.out.println("Uppgift12a9");
        System.out.println("===========");

        for (double t_tal : tal)
        {
            System.out.print(" " + t_tal);
        }
        System.out.println();
    }

    public void a10(double[] tal)
    {
        System.out.println();
        System.out.println("Uppgift12a9");
        System.out.println("===========");

        System.out.println("se uppgift 4");
    }


    public static void main(String[] argv)
    {
        Uppgift12a jao = new Uppgift12a();

        jao.action();
        
        double[] tal1 = new double[] {23.2, 14.7, 17.0, -5.9, -11.1, 26.3, 8.3, 7.6 };
        double[] tal2 = new double[] { -1, 2, 5, 8, 11, 14, 10, 6, 2, -4 };

        jao.a1(tal1);
        jao.a1(tal2);

        jao.a2(tal1);
        jao.a2(tal2);

        jao.a3(tal1);
        jao.a3(tal2);

        jao.a4(tal1);
        jao.a4(tal2);

        jao.a5(tal1);
        jao.a5(tal2);

        jao.a6(tal1);
        jao.a6(tal2);

        jao.a7(tal1);
        jao.a7(tal2);

        jao.a8(tal1);
        jao.a8(tal2);

        jao.a9(tal1);
        jao.a9(tal2);

        jao.a10(tal1);
        jao.a10(tal2);
    }
}
