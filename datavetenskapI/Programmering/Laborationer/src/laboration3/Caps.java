/*
 * Lab3 ...
 * BAJS! *teehee*
 */

/**
 *
 * @author Jag
 */

package laboration3;

/*
 * We need JOptionPane
 */
import javax.swing.*;

public class Caps
{
    String name;

    public void showName()
    {
        //System.out.println("I detta program ska du skriva in ditt namn, så säger vi vad du heter.. fast med VERSALER.");
        JOptionPane.showMessageDialog(null, "I detta program ska du skriva in ditt namn, så säger vi vad du heter.. fast med VERSALER.");
        this.name = JOptionPane.showInputDialog("Vad är du heter?");
        JOptionPane.showMessageDialog(null, "Du heter " + name.toUpperCase());
    }


    public static void main(String[] argv)
    {
        Caps upperCase = new Caps();
        upperCase.showName();
    }
}
