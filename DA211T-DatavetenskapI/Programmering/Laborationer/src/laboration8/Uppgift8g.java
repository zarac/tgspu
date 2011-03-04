/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package laboration8;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author zarac
 */
public class Uppgift8g {

    public void uppgift8g() {
        int antal, slump;
        Random rand = new Random();
        antal = Integer.parseInt(JOptionPane.showInputDialog("Antal slumptal"));
        System.out.println("Slumptal: ");
        for (int i = 1; i <= antal; i++) {
            slump = rand.nextInt(20) + 5;
            System.out.print(slump + " ");
        }
    }

    public static void main(String[] argv)
    {
        new Uppgift8g().uppgift8g();
    }
}
