package labA;
import java.util.*;

public class Uppgift5 {
	public void framlanges( ArrayList<Foto> foton ) {
		// skriv kod här
        System.out.println("Antal foton: " + foton.size());
        for (Foto foto : foton)
            System.out.println(foto.toString());
	}
	
	public void baklanges( ArrayList<Foto> foton ) {
		// skriv kod här
        for (int i = foton.size()-1; i >= 0; i--)
            System.out.println(foton.get(i).toString());
        System.out.println("Antal foton: " + foton.size());
	}
	
	public void rensaAr( ArrayList<Foto> foton, int artal ) {
		// skriv kod här
        for (int i = foton.size()-1; i >= 0; i--)
            if (foton.get(i).getArtal() == artal)
                foton.remove(i);
	}

	public static void main(String[] args) {
		Uppgift5 u5 = new Uppgift5();
		ArrayList<Foto> list = new ArrayList<Foto>();
		Foto f1 = new Foto("Nu är det vår", 2003, Foto.FAMILJ, "C:/bilder/spring.jpg");
		Foto f2 = new Foto("Vi cyklar", 2004, Foto.FAMILJ, "C:/bilder/tandem.jpg");
		Foto f3 = new Foto("Vinter i Italien", 2003, Foto.RESOR, "C:/bilder/italien.jpg");
		Foto f4 = new Foto("Hugo i sandlådan", 2007, Foto.BARNBARN, "C:/bilder/hugo2007.jpg");
		list.add(f1);
		list.add(f2);
		list.add(f3);
		list.add(f4);
		
		// a
		u5.framlanges( list );

		System.out.println("------------");

		// b
		u5.baklanges( list );

		System.out.println("------------");

		// c
		u5.rensaAr( list, 2003);
		u5.framlanges( list );
	}
}
