package labA;
import java.util.*;
import javax.swing.*;

public class Uppgift3 {
    public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>(); 
		String res = "";
		list.add("Adil");
		list.add("Henrik");
		list.add("Sasha");
		list.add("Gökhan");
		list.add("Johnny");
		list.add("Karin");
		list.add("Abtin");
		
		for( int i = 0; i < list.size(); i++ ) {
			res += list.get(i)+"\n";
		}
		JOptionPane.showMessageDialog(null, res);
		
		// skriv kod här
        for(int i = 0; i < list.size(); i++)
        {
            list.set(i, list.get(i) + " " + JOptionPane.showInputDialog("Hur gammal e du, " + list.get(i) + "?"));
        }
		
		res = "";
		for( int i = 0; i < list.size(); i++ ) {
			res += list.get(i)+"\n";
		}
		JOptionPane.showMessageDialog(null, res);
	}
}
