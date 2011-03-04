package p2;
import javax.swing.JOptionPane;

/**
 * @author Hannes Landstedt
 */
public class Prog2 {

    /**
     * Metoden uppgift2a skriver ut alla udda tal i intervallet 1-100. Talen 
     * skrivs ut ordnade med det minsta f�rst.   
     */
    public void uppgift2a() {
        // komplettera
        for (int i = 1; i < 100; i+=2)
        {
            System.out.println(i);
        }
    }

    /**
     * Ask for an integer and say if it's negative, zero or positive.
     * It runs four times.
     */
    public void uppgift2b()
    {
        int number;

        for (int i = 0; i<4; i++)
        {
            number = Integer.parseInt(JOptionPane.showInputDialog("Gief me a number..."));

            if (number < 0)
                System.out.println("Talet " + number + " is negative.");
            else if (number == 0)
                System.out.println("Talet " + number + " is zero.");
            else
                System.out.println("Talet " + number + " is positive.");
        }
    }

    /**
     * Ask for two numbers and print out every seventh number in that range.
     */
    public void uppgift2c()
    {
        int min = Integer.parseInt(JOptionPane.showInputDialog("Give me a number... (min)"));
        int max = Integer.parseInt(JOptionPane.showInputDialog("Give me another number... (max)"));

        for (int i = min; i <= max; i+=7)
        {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    /**
     * Given the capital for january, interest and years, show the progress of the capital throughout these years.
     */
    public void sparande(double p_capitalJan, double p_interestRate, int p_years)
    {
        double capitalJan;
        double interest;
        double capitalDec;

        // initial capital
        capitalJan = p_capitalJan;

        for (int i = 1; i <= p_years; i++)
        {
            interest = capitalJan * p_interestRate;
            capitalDec = capitalJan + interest;
            System.out.printf("%2d%15.2f%11.2f%15.2f\n", i, capitalJan, interest, capitalDec);

            // set for use in next iteration
            capitalJan = capitalDec;
        }
    }
}
