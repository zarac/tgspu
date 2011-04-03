package laboration3;

import javax.swing.*;

public class Program3 {
    public void count() {
        String name, res="";
        int nbr1,nbr2,sum;
        
        name = JOptionPane.showInputDialog( "Ange ditt namn" );
        nbr1 = Integer.parseInt( JOptionPane.showInputDialog( "Ange ett tal" ) );
        nbr2 = Integer.parseInt( JOptionPane.showInputDialog( "Ange ett tal till" ) );
        sum = nbr1+nbr2;
        
        // res = name + " s� h�r blir det:\n" + nbr1 + "+" + nbr2 + "=" + sum;
        // res = name + " s� h�r blir det:\n" + nbr1 + "+" + nbr2 + "=" + (nbr1 + nbr2);
        res = name + ", this is how it goes down!\n";
        res += "\tAddition: " + nbr1 + "+" + nbr2 + "=" + (nbr1+nbr2) + "\n";
        res += "\tSubtraktion: " + nbr1 + "-" + nbr2 + "=" + (nbr1-nbr2) + "\n";
        res += "\tMultiplikation: " + nbr1 + "*" + nbr2 + "=" + (nbr1*nbr2) + "\n";
        // nbr1 and nbr2 are both integers, doubleize for full division!
        res += "\tDivision: " + nbr1 + "/" + nbr2 + "=" + ((double)nbr1/nbr2) + "\n";
        res += "\tHeltalsdivision: " + nbr1 + "/" + nbr2 + "=" + (nbr1/nbr2) + "\n";
        res += "\tRest: " + nbr1 + "%" + nbr2 + "=" + (nbr1%nbr2) + "\n";
        
        JOptionPane.showMessageDialog( null, res );
        //JOptionPane.showMessageDialog( null, new ImageIcon("M:\Personal\Spelutveckling\datavetenskap1\NetBeansProjects\Lab3\data");
        JOptionPane.showMessageDialog( null, new ImageIcon("D:/me/dev/Spelutveckling/DA211T/NetBeansProjects/DA211T/static/laboration3/filmlogga.jpg"));
        
        /* Output..
         * Elin så här blir det:
         * Addition: 3+5=8
         * Subtraktion: ...
         * Multiplikation: ...
         * Division: ...
         * Heltalsdivision: ...
         * Rest: ...
         */
    }
    
    // Funkar också... but why!?
    //public static void main(String[] args) {
    public static void main(String[] args) {
        Program3 prog = new Program3();
        prog.count();
    }
}
