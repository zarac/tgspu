package laboration7;

import javax.swing.*;

/**
 *
 * @author zarac
 */
public class Program7b
{
	public static void main(String[] args)
	{
		if (Integer.parseInt(JOptionPane.showInputDialog("Skriv ett tal...")) > 100)
			JOptionPane.showMessageDialog(null, "Talet är större än 100.");
		else
			JOptionPane.showMessageDialog(null, "Talet är högst 100.");
	}
}
