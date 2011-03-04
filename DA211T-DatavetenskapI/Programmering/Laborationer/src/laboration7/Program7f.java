package laboration7;
import javax.swing.*;

/**
 *
 * @author zarac
 */
public class Program7f {
	public static void main(String[] args)
	{
		int int1 = Integer.parseInt(JOptionPane.showInputDialog("Mata in ett tal..."));
		int int2 = Integer.parseInt(JOptionPane.showInputDialog("Mata in ett till tal..."));
		int highestInt;

		if (int1 > int2)
			highestInt = int1;
		else
			highestInt = int2;

		JOptionPane.showMessageDialog(null, highestInt);
	}
}
