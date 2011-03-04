package p4;
import java.util.*;
import java.io.*;
/**
 * Klassen läser information om länder och dess befolkning från en fil på årddisken.
 * Den inlästa informationen placeras i en array med Country-objekt. <br>
 * Klassen skriver information i en Country-array till en fil på hårddisken.
 * @author Rolf Axelsson
 */
public class CountryIO {
    private String filename;
    
    /**
     * Konstruerar ett CountryIO-objekt.
     * @param filename namn på den fil från vilken information ska läsas
     */
    public CountryIO( String filename ) {
        this.filename = filename;
    }
    
    /**
     * Anger namnet på den fil från vilken information ska läsas.
     * @param filename namn på den fil från vilken information ska läsas
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
     * Läser information från en fil på hårddisken. Informationen lagras i en Country-array.
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
     * Skriver informationen i en Country-arrayen till en fil på hårddisken.
     * @param countries array med county-objekt vilken lagras på hårddisken
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
        //Skapa CountryIO-objekt. Som argument anges den fil i vilken länder-informationen finns. 
        // I detta exempel finns länder-informationen i filen befolkning.txt som lagras i M:/java.
        CountryIO io = new CountryIO( "M:/java/befolkning.txt" ); 
//        CountryIO io = new CountryIO( "C:/java/befolkning.txt" ); 
        
        // Hämta länder-information från hårddisken. Informationen lagras i en Country-array.
        Country[] countries = io.readCountries();
        // Skriva ut info om alla länder
        for(int i = 0; i < countries.length; i++ ) {
            System.out.println( countries[ i ].toString() );
        }
        
        // Ändra informationen i ett element i Country-arrayen
        countries[ 11 ] = new Country("Narnia", 2370 );
        // Skriva ut info om alla länder
        for(int i = 0; i < countries.length; i++ ) {
            System.out.println( countries[ i ].toString() );
        }
        
        // Spara den ändrade country-arrayen till hårddisken. Nästa gång du kör programmet kan du
        // se att land nummer 12 i listan är Narnia.
        // Om du ersätter befolkning.txt (den med ändringen) med den ursprungliga från kurssidan så 
        // slipper du ändringen. Men kör inte detta program på nytt / avmarkera raden som gör ändringen.
        io.saveCountries( countries );
    }
}
