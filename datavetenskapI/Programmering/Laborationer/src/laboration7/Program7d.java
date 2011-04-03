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
public class Program7d {
	public static void main(String[] args)
	{
		String namn = JOptionPane.showInputDialog("Hur gammal är du?");

		if (namn.length() > 4)
			JOptionPane.showMessageDialog(null, namn + ", ditt namn är långt och svårstavat.");
		else
			JOptionPane.showMessageDialog(null, namn + ", ditt namn är kort och fint!");
	}
}
