package labA;

import java.util.*;

import javax.swing.JOptionPane;

public class Uppgift2 {
    public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("aldrig");
		list.add("god morgon");
		list.add("söndag");
		list.add("ballong");
		list.add("springa");
		list.add("bil");
		list.add("farmor");
		
		// skriv kod här
        for (int i = 0; i < 5; i++)
        {
            list.add(JOptionPane.showInputDialog("Mata in en String..."));
        }
		
		for( int i = 0; i < list.size(); i++ ) {
			System.out.println(list.get(i));
		}
	}
}
