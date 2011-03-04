package p4;
import java.util.*;
import java.io.*;
/**
 * Klassen l�ser information om l�nder och dess befolkning fr�n en fil p� �rddisken.
 * Den inl�sta informationen placeras i en array med Country-objekt. <br>
 * Klassen skriver information i en Country-array till en fil p� h�rddisken.
 * @author Rolf Axelsson
 */
public class CountryIO {
    private String filename;
    
    /**
     * Konstruerar ett CountryIO-objekt.
     * @param filename namn p� den fil fr�n vilken information ska l�sas
     */
    public CountryIO( String filename ) {
        this.filename = filename;
    }
    
    /**
     * Anger namnet p� den fil fr�n vilken information ska l�sas.
     * @param filename namn p� den fil fr�n vilken information ska l�sas
     */
    public void setFilename( String filename ) {
        this.filename = filename;
    }
    
    private Country[] read() throws IOException {
        ArrayList countries = new ArrayList();
        BufferedReader br = new BufferedReader( new FileReader( filename ) );
        String input;
        String[] data;
        
        while( ( input = br.readLine() ) != null ) {
            data = input.split( "," );
            countries.add( new Country( data[1], Long.parseLong( data[2].replaceAll(" ","") ) ) );
        }
        br.close();
        return ( Country[] )countries.toArray( new Country[]{} );
    }
    
    private void save( Country[] countries ) throws IOException {
        BufferedWriter bw = new BufferedWriter( new FileWriter( filename ) );
        
        for( int i = 0; i < countries.length ; i++ ) {
            bw.write( (i+1) + "," + countries[ i ].getName() + "," + countries[ i ].getPopulation());
            bw.newLine();
        }
        bw.close();
    }

    /**
     * L�ser information fr�n en fil p� h�rddisken. Informationen lagras i en Country-array.
     * @return en array med Country-objekt
     * @throws IOException
     */
    public Country[] readCountries() {
        Country[] countries = {};
        try {
            countries = read();
        }
        catch( IOException e ) {
            System.out.println( e );
        }
        return countries;
    }
    
    /**
     * Skriver informationen i en Country-arrayen till en fil p� h�rddisken.
     * @param countries array med county-objekt vilken lagras p� h�rddisken
     * @throws IOException
     */
    public void saveCountries( Country[] countries ) {
        try {
            save( countries );
        }
        catch( IOException e ) {
            System.out.println( e );
        }
    }
    
    public static void main( String[] args ) {
        //Skapa CountryIO-objekt. Som argument anges den fil i vilken l�nder-informationen finns. 
        // I detta exempel finns l�nder-informationen i filen befolkning.txt som lagras i M:/java.
        CountryIO io = new CountryIO( "M:/java/befolkning.txt" ); 
//        CountryIO io = new CountryIO( "C:/java/befolkning.txt" ); 
        
        // H�mta l�nder-information fr�n h�rddisken. Informationen lagras i en Country-array.
        Country[] countries = io.readCountries();
        // Skriva ut info om alla l�nder
        for(int i = 0; i < countries.length; i++ ) {
            System.out.println( countries[ i ].toString() );
        }
        
        // �ndra informationen i ett element i Country-arrayen
        countries[ 11 ] = new Country("Narnia", 2370 );
        // Skriva ut info om alla l�nder
        for(int i = 0; i < countries.length; i++ ) {
            System.out.println( countries[ i ].toString() );
        }
        
        // Spara den �ndrade country-arrayen till h�rddisken. N�sta g�ng du k�r programmet kan du
        // se att land nummer 12 i listan �r Narnia.
        // Om du ers�tter befolkning.txt (den med �ndringen) med den ursprungliga fr�n kurssidan s� 
        // slipper du �ndringen. Men k�r inte detta program p� nytt / avmarkera raden som g�r �ndringen.
        io.saveCountries( countries );
    }
}
