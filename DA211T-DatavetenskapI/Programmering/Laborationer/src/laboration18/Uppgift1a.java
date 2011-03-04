package laboration18;

import javax.swing.JOptionPane;

public class Uppgift1a
{
    public static void main(String[] argv)
    {
        int tal = 0; 
        boolean inmatningOK = false; 
        do { 
            try { 
                tal = Integer.parseInt(JOptionPane.showInputDialog( "Mata in ett heltal" ) ); 
                inmatningOK = true; 
            }  
            catch( NumberFormatException ex ) {} 
        } while( inmatningOK == false ); 
        System.out.println("Inmatat tal: " + tal); 
    }
}
