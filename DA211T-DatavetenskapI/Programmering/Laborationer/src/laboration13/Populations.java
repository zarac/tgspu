package laboration13;
import java.util.*;
import java.io.*;

/**
 * Metoden readPopultaions l�ser in ett antal l�nder och deras inv�nareantal i objekt av
 * typen Population. Popultation-objekten returneras i en Popultation-array.
 * @author Rolf
 */
public class Populations {
    private static Population[] read( String filename ) throws IOException {
        ArrayList population = new ArrayList();
        BufferedReader br = new BufferedReader( new FileReader( filename ) );
        String input;
        String[] data;
        
        while( ( input = br.readLine() ) != null ) {
            data = input.split( "," );
            population.add( new Population( data[1], Long.parseLong( data[2].replaceAll(" ","") ) ) );
        }
        return ( Population[] )population.toArray( new Population[]{} );
    }
    
    public static Population[] readPopulations( String filename ) {
        Population[] populations = {};
        try {
            populations = read( filename );
        }
        catch( IOException e ) {
            System.out.println( e );
        }
        return populations;
    }
    
    public static void main( String[] args ) {
        Population[] inv�ndare = Populations.readPopulations( "z:/me/studies/mah-spelutveckling/da211t/static/laboration13/befolkning.txt" );
        System.out.printf( "%-30s%15s\n", "LAND", "INV�NARE" );
        for(int i = 0; i < inv�ndare.length; i++ ) {
            System.out.println( inv�ndare[ i ].toString() );
        }
    }
}
