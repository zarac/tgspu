package laboration7;
import javax.swing.*;

/**
 *
 * @author zarac
 */
public class Program7e
{
	public static void main(String[] args)
	{
		double price = Integer.parseInt(JOptionPane.showInputDialog("Ange pris på varan."));
		double payed = Integer.parseInt(JOptionPane.showInputDialog("Hur mycket pengar betalar du med?"));
		boolean isMember = JOptionPane.showConfirmDialog(null, "Är du medlem?", "MEDLEM?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		double change;

		if (isMember)
			price = price * 0.9;

		if (payed == price)
			JOptionPane.showMessageDialog(null, "Du gav exakta pengar. Tack och hej!");
		else if (payed < price)
			JOptionPane.showConfirmDialog(null, "Försöker du luras? Gief more pengar!");
		else
		{
			change = payed - price;
			JOptionPane.showMessageDialog(null, "Din växel är " + change + ".");
		}
	}
}
