package f2;
import java.util.*;

public class BinarySearch {
    
/************* Binär sökning i int-array **********************/
    public int binarySearch( int[] array, int key ) {
        int res = -1, min = 0, max = array.length - 1, pos;
        while( ( min <= max ) && ( res == -1 ) ) {
            pos = (min + max) / 2;
            if( key == array[ pos ] )
                res = pos;
            else if( key < array[ pos ])
                max = pos - 1;
            else
                min = pos + 1;
        }
        return res;
    }
        
/**************************** Testmetoder ************************************/
    /* sökning i int-array */
    public void testBinarySearch() {
        int[] arr = {11, 14, 19, 20, 21, 22, 24, 26, 29, 30};
        System.out.println( binarySearch( arr, 25) );
        System.out.println( binarySearch( arr, 19) );
    }

    /* linjär sökning och binär sökning i int-array */
    public void testLinBin() {
        Shuffle sh = new Shuffle();
        LinearSearch ls = new LinearSearch();
        int[] arr = new int[100000];
        for( int i=0; i<arr.length; i++ )
            arr[i] = (i+1);
        sh.shuffle(arr);
        
        long start = System.currentTimeMillis();
        for( int i = 1; i <= 10000; i++ ) {
            ls.indexOf( arr, i );
        }
        long stop = System.currentTimeMillis();
        System.out.println( "Linj�r s�kning: " + ( stop - start ) );
        
        Arrays.sort( arr );
        start = System.currentTimeMillis();
        for( int i = 1; i <= 10000; i++ ) {
            binarySearch( arr, i );
        }
        stop = System.currentTimeMillis();
        System.out.println( "Bin�r s�kning: " + ( stop - start ) );
    }
    
    
    /* binärsökning och Comparable */
    public void testComparable() {
        int[] arr = {11, 14, 19, 20, 21, 22, 24, 26, 29, 30};
        Object[] integerArray = new Object[ arr.length ];
        Object[] realnbrArray = new Object[ arr.length ];
        for(int i=0; i<arr.length; i++) {
            integerArray[ i ] = new Integer( arr [ i ] );
            realnbrArray[ i ] = new RealNbr( arr [ i ] );
        }
        System.out.println( Arrays.binarySearch( integerArray, new Integer( 19 ) ) );
        System.out.println( Arrays.binarySearch( realnbrArray, new RealNbr( 19 ) ) );
    }

    /* binärsökning och Comparator */    
    public void testComparator() {
        int[] arr = {11, 14, 19, 20, 21, 22, 24, 26, 29, 30};
        RealNbr[] realnbrArray = new RealNbr[ arr.length ];
        Decrease dec = new Decrease();
        for(int i=0; i<arr.length; i++) {
            realnbrArray[ i ] = new RealNbr( arr [ i ] );
        }
//        Arrays.sort( realnbrArray, dec );
        System.out.println( Arrays.binarySearch( realnbrArray, new RealNbr( 19 ), dec ) );
    }

    
    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        
        bs.testBinarySearch();
//        bs.testLinBin();
//        bs.testComparable();
//        bs.testComparator();
    }
}
