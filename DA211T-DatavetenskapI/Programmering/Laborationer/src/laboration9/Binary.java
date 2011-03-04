package laboration9;

public class Binary {
	
	/**
	 * Metoden toBinary tar som argument ett tal i intervallet 0-255. Metoden skriver
	 * ut en text i Output-f�nstret. Texten �r p� formen:  
	 * 19 = 00010011   
	 * dvs. parameter = bin�r representation  
	 * 
	 * @param decimal ett tal i intervallet 0-255
	 */
	public void toBinary( int decimal ) {
        int value = 128;
//        int argument = decimal;
        String res = "";

        while (value > 0)
        {
            if (decimal >= value)
            {
                res += "1";
                decimal -= value;
            }
            else
            {
                res += "0";
            }
            value /= 2;
        }

        System.out.println(res);
	}
	
	/**
	 * Metoden toDecimal tar som argument ett tal i bin�r form - som en String. 
	 * Metoden skriver ut en text i Output-f�nstret. Texten �r p� formen:  
	 * 00010011 = 19  
	 * dvs. parameter = decimal representation  
	 * 
	 * @param binary en str�ng som representerar ett bin�rt tal
	 */
	public void toDecimal( String binary ) {
        int res = 0;
        int value = 1;

//        System.out.println("binary = " + binary);

        for (int i = binary.length()-1; i>=0; i--)
        {
//            System.out.print(binary.charAt(i));
            if (binary.charAt(i) == '1')
            {
                res += value;
            }

            value *= 2;
        }
//        System.out.println();

        System.out.println(">>" + binary + " = " + res);
	}
	
	/**
	 * Metoden charCode tar som argument en str�ng. Metoden skriver ut
	 * tecknen i str�ngen tillsammans med deras interna representation,
	 * ett tecken per rad.   
	 * 
	 * @param str String-objekt som inneh�ller de tecken som ska skrivas ut
	 */
	public void charCode( String str ) {
		
	}
}
