package labA;
import java.util.*;

public class Uppgift1 {
    public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("aldrig");
		list.add("god morgon");
		list.add("s�ndag");
		list.add("ballong");
		list.add("springa");
		list.add("bil");
		list.add("farmor");
		 
		//a
		// skriv kod h�r
        for (String string : list)
        {
            System.out.println(string);
        }

		System.out.println("------------");

		//b
		// skriv kod h�r
        for (int i = list.size() -1 ; i >= 0; i--)
        {
            System.out.println(list.get(i));
        }

		System.out.println("------------");

		//c
		// skriv kod h�r
        for (int i = 0; i < list.size(); i+=2)
        {
            System.out.println(list.get(i));
        }

		System.out.println("------------");

		//d
		// skriv kod h�r
        for (int i = list.size() -1 ; i >= 0; i-=3)
        {
            System.out.println(list.get(i));
        }
 
		System.out.println("------------");
		
	}
}
