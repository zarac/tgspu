package laboration7;
import javax.swing.*;

public class Program7g {
   public void uppgift() {
      String namn;
      int tal1,tal2 = 0;
      System.out.println();
      namn = JOptionPane.showInputDialog( "Ange ditt namn" );
	  tal1 = Integer.parseInt( JOptionPane.showInputDialog("Ange ett tal...") );
	  while (tal2 == 0)
		  tal2 = Integer.parseInt( JOptionPane.showInputDialog("Ange ett tal till (inte 0)!") );
        
      System.out.println();
      System.out.println(namn+", s� h�r blir det: ");
      System.out.println("Addition:                 " + tal1 + "+" + tal2 + "=" + (tal1 + tal2));
      System.out.println("Subtraktion:              " + tal1 + "-" + tal2 + "=" + (tal1 - tal2));
      System.out.println("Multiplikation:           " + tal1 + "*" + tal2 + "=" + tal1 * tal2);
      System.out.println("Division:                 " + tal1 + "/" + tal2 + "=" +(double) tal1 / tal2);
      System.out.println("Heltalsdivision:          " + tal1 + "/" + tal2 + "=" + tal1 / tal2);
      System.out.println("Rest vid heltalsdivision: " + tal1 + "%" + tal2 + "=" + tal1 % tal2);
   }

   public static void main(String[] args) {
      Program7g p7g = new Program7g();
      p7g.uppgift();
   }
}
