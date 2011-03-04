package labA;

import java.util.*;

import javax.swing.JOptionPane;

public class Uppgift4 {
    public static void main(String[] args) {
    	ArrayList<Person> personer = new ArrayList<Person>();
		ArrayList<String> list = new ArrayList<String>();
		String res = "";
		list.add("Adil");
		list.add("Henrik");
		list.add("Sasha");
		list.add("Gökhan");
		list.add("Johnny");
		list.add("Karin");
		list.add("Abtin");
		
		// skriv kod här
        for (int i = 0; i < list.size(); i++)
        {
            personer.add(new Person(list.get(i), Integer.parseInt(JOptionPane.showInputDialog("Hur gammal e du, " + list.get(i) + "?"))));
        }
		
		for( int i = 0; i < list.size(); i++ ) {
			System.out.printf( "%-15s%-15s\n", list.get(i), personer.get(i).toString());
		}
	}
}
