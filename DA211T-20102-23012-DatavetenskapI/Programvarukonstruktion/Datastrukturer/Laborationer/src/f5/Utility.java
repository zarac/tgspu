package f5;
import f2.RealNbr;

/**
 * Klassen innehåller ett antal metoder som bl.a. används i försläsning 5.
 * @author tsroax
 *
 */
public class Utility {
	/**
	 * Metoden skapar en array med angiven kapacitet (n). Samtliga element i arrayen
	 * slumpas i intervallet [min,max].
	 * @param n antal element i arrayen
	 * @param min lägsta värde på element i arrayen
	 * @param max största värde på element i arrayen
	 * @return array med n element. Samtliga element i arrayen har ett värde i intervallet [min,max]
	 */
    public static int[] randomArray( int n, int min, int max ) { 
        int[] res = new int[ n ];
        for( int i = 0; i < n; i++ )
            res[ i ] = ( int )( Math.random() * (max - min + 1) ) + min;
        return res;
    }
    
    /**
	 * Metoden skapar en array med angiven kapacitet (n). Arrayen fylls med referenser till RealNbr-objekt.
	 * Samtliga RealNbr-objekt ges ett slumpvärde i intervallet [min,max].
	 * @param n antal element i arrayen
	 * @param min lägsta värde på RealNbr-objekt i arrayen
	 * @param max största värde på RealNbr-objekt i arrayen
	 * @return array med n element. Samtliga element i arrayen refererar till RealNbr-objekt.
	 * RealNbr-objekten har ett värde i intervallet [min,max]
     */
    public static RealNbr[] randomDecTal( int n, int min, int max ) {
        RealNbr[] res = new RealNbr[ n ];
        for( int i = 0; i < n; i++ )
            res[ i ] = new RealNbr( Math.random() * ( max - min ) + min ) ;
        return res;
    }
    
    /**
     * Metoden blandar ordningen i en int-array.
     * @param array array i vilken elementen ska blandas
     */
    private static void shuffle( int[] array ) {
        int pos;
        for( int i = array.length - 1; i > 0; i-- ) {
            pos = ( int )( Math.random() * ( i + 1 ) );
            swap( array, i, pos );
        }
    }
    
    /**
     * Metoden returnerar en array vars element har ett värde i intervallet [min,max]. Varje värde i
     * intervallet finns i exakt ett element. Värdena kommer i slumpmässig ordning. 
     * Arrayen har kapaciteten (max - min + 1).
     * @param min lägsta värde i arrayen
     * @param max största värde i arrayen
     * @return en array med värden i intervallet [min,max]. Varje värde i intervallet finns i exakt ett element.
     * Värdena är blandade i arrayen.
     */
    public static int[] completeArray( int min, int max ) {
        int[] res = new int[ Math.max(0, max-min+1) ];
        for( int i = 0; i < res.length; i++ ) {
            res[ i ] = min;
            min++;
        }
        shuffle( res );
        return res;
    }
    
    /**
     * Metoden skriver ut elementen i en int-array med angivet antal element per rad (rowCount).
     * @param array arrayen vars element ska skrivas ut
     * @param rowCount antal värden per rad i utskriften.
     */
    public static void printArray(int[] array, int rowCount ) {
        for(int i = 0; i< array.length; i++ ) {
            System.out.print( array[ i ] + " " );
            if( ( i + 1 ) % rowCount == 0 )
                System.out.println();
        }
    }
    
    /**
     * Metoden skiftar värdet på två element i en int-array
     * @param array arrayen i vilken värdet på två element ska skiftas
     * @param i position för det första elementet
     * @param j position för det andra elementet
     */
    public static void swap( int[] array, int i, int j ) {
        int temp = array[ i ];
        array[ i ] = array[ j ];
        array[ j ] = temp;
    }
    
    /**
     * Metoden skiftar värdet på två element i en Object-array
     * @param array arrayen i vilken värdet på två element ska skiftas
     * @param i position för det första elementet
     * @param j position för det andra elementet
     */
    public static void swap( Object[] array, int i, int j ) {
        Object temp = array[ i ];
        array[ i ] = array[ j ];
        array[ j ] = temp;
    }
}
