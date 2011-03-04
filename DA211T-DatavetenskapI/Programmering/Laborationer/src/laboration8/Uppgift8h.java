/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package laboration8;

import javax.swing.JOptionPane;

/**
 *
 * @author zarac
 */
public class Uppgift8h
{
    public int getIntWhile(int min, int max)
    {
        int value;
        do
        {
            value = Integer.parseInt(JOptionPane.showInputDialog(null, "Gief me an int between a magic interval! (while)"));
        }
        while (!(value>=min && value<= max));

        return value;
    }

    public int getIntDoWhile(int min, int max)
    {
        int value;
        do
        {
            value = Integer.parseInt(JOptionPane.showInputDialog(null, "Gief me an int between a magic interval! (do-while)"));
        }
        while (!(value>=min && value<= max));

        return value;
    }

    public static void main(String[] argv)
    {
        Uppgift8h uppgift8h = new Uppgift8h();

        uppgift8h.getIntWhile(8,10);
        uppgift8h.getIntDoWhile(8,10);
    }
}
