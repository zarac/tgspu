package resurser;

import javax.swing.JOptionPane;

public class InOut
{
    public static double readDouble(String p_message)
    {
        double number = 0;
        boolean allOk = false;
        do
        {
            try
            {
                number = Double.parseDouble(JOptionPane.showInputDialog(p_message));
                allOk = true;
            }
            catch (NumberFormatException e)
            {
            }
            catch (NullPointerException e)
            {
            }
        }
        while (!allOk);

        return number;
    }


    public static double readDouble()
    {
        return readDouble("Mata in ett decimaltal.");
    }


    public static int readInt(String p_message)
    {
        int number = 0;
        boolean allOk = false;
        do
        {
            try
            {
                number = Integer.parseInt(JOptionPane.showInputDialog(p_message));
                allOk = true;
            }
            catch (NumberFormatException e)
            {
            }
        }
        while (!allOk);

        return number;
    }


    public static int readInt()
    {
        return readInt("Mata in ett heltal.");
    }
}
