package f2;
import java.util.*;

public class LinearSearch {
    
/***************** Sökning i osorterad int-array ***********************/
    /* Söker efter värde i int-array */
    public int indexOf( int[] array, int value ) {
        int res = -1;
        for( int i=0; ( i<array.length ) && ( res == -1 ); i++ ) {
            if( value == array[ i ] )
                res = i;
        }
        return res;
    }
    
    /* Söker efter värde i int-array */
    public int indexOf2( int[] array, int value ) {
        for( int i = 0;  i<array.length; i++  ) {
            if( value == array[ i ] )
                return i;
        }
        return -1;
    }
    
    /* Skapa int-array med slumpvärden i intervallet [min,max] */
    public int[] randomArray( int n, int min, int max ) {
        int[] res = new int[ n ];
        for( int i = 0; i < n; i++ )
            res[ i ] = ( int )( Math.random() * (max - min + 1) ) + min;
        return res;
    }
    
    /* Skriva int-array med bestämt antal värden per rad */    
    public void printArray(int[] array, int rowCount, int width ) {
        for(int i = 0; i< array.length; i++ ) {
            System.out.printf( "%"+width+"d", array[ i ] );
            if( ( i + 1 ) % rowCount == 0 )
                System.out.println();
        }
    }
        
    public void searchArray() {
        int[] array = randomArray( 100, 10, 50 );
        printArray(array, 10, 4);
        for( int i=10; i<=50; i+=5 )
            System.out.println( i + ": " + indexOf2( array, i ));
    }    
/***********************************************************************/
   

    public static void main(String[] args) {
        LinearSearch ls = new LinearSearch();
        ls.searchArray();
    }
}
