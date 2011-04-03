/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package laboration7;
import javax.swing.*;

/**
 *
 * @author zarac
 */
public class Program7c
{
	public static void main(String[] args)
	{
		int age = Integer.parseInt(JOptionPane.showInputDialog("Hur gammal e' du?"));

//		if (0 <= age <= 17)
		if (0 <= age && age <= 17)
			JOptionPane.showMessageDialog(null, "Du är ett barn!");
		else
			JOptionPane.showMessageDialog(null, "Du är vuxen!");
	}
}
