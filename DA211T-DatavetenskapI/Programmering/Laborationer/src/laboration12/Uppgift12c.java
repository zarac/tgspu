package laboration12;

public class Uppgift12c
{
    public void action()
    {
        double[] tal = {23.2, 14.7, 17.0, -5.9, -11.1, 26.3, 8.3, 7.6};
        // double[] tal = { -1, 2, 5, 8, 11, 14, 10, 6, 2, -4 };
        double summa;
        System.out.println("Talens summa är " + sumOfArray(tal));
        System.out.println("Antal tal större än 8: " + amountBiggerThan8(tal));
        summa = sumOfNegativeNumbers(tal);
        System.out.println("Summan av de negativa talen är: " + summa);
    }

    public double sumOfArray(double[] array)
    {
        double sum = 0;

        for (double number : array)
        {
            sum += number;
        }
        
        return sum;
    }

    public int amountBiggerThan8(double[] array)
    {
        int amount = 0;

        for (double number : array)
        {
            if (number > 8)
            {
                amount++;
            }
        }
        
        return amount;
    }

    public double sumOfNegativeNumbers(double[] array)
    {
        double sum = 0;

        for (double number : array)
        {
            if (number < 0)
            {
                sum += number;
            }
        }
        
        return sum;
    }


    public static void main(String[] argv)
    {
        new Uppgift12c().action();
    }
}
