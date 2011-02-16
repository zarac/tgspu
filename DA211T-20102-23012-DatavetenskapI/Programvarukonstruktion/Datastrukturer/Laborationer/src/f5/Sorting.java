package f5;
import java.util.*;

public class Sorting {

    // ------------------------ BUBBLESORT ----------------------------------------
    public static void bubblesort(int[] array) {
        for( int i=0; i < array.length - 1; i++ ) {
            for( int j = array.length - 1; j > i; j-- ) {
                if( array[ j ] < array[ j - 1 ] )
                    Utility.swap( array, j, j - 1 );
            }
        }
    } 
    public static void bubblesortC(int[] array) {
        int comp = 0, swap = 0;
        for( int i=0; i < array.length - 1; i++ ) {
            for( int j = array.length - 1; j > i; j-- ) {
                comp++;
                if( array[ j ] < array[ j - 1 ] ) {
                    swap++;
                    Utility.swap( array, j, j - 1 );
                }
            }
        }
        System.out.println("Bubblesort, element = " + array.length + " jämförelser = " + comp + " byten = " + swap);
    }
    
    public static void bubblesort(Object[] array, Comparator comp) {
        for( int i=0; i < array.length - 1; i++ ) {
            for( int j = array.length - 1; j > i; j-- ) {
                if( comp.compare( array[ j - 1 ], array[ j ] ) > 0 )
                    Utility.swap( array, j, j - 1 );
            }
        }
    }
      
// ------------------------ INSERTIONSORT ----------------------------------------
    public static void insertionsort(int[] array) { // Används på små arrayer i java (mindre än 7 element)
        for( int i = 1; i < array.length; i++ ) {
            for ( int j = i; ( j > 0 ) && ( array[j-1] > array[j] ) ; j--) {
                Utility.swap( array, j, j-1 );
            }
        }
    }
    
    public static void insertionsortC(int[] array) { 
        int comp = 0, swap = 0;
        for( int i = 1; i < array.length; i++ ) {
            comp++;
            for ( int j = i; ( j > 0 ) && ( array[j-1] > array[j] ) ; j--) {
                swap++;
                Utility.swap( array, j, j-1 );
                comp++;
            }
        }
        System.out.println("Insertionsort, element = " + array.length + " jämförelser = " + comp + " byten = " + swap);
    }
    
    public static void insertionsort1(Object[] array) { // Används på små arrayer i java (mindre än 7 element)
    	Comparable comp;
    	for( int i = 1; i < array.length; i++ ) {
    		comp = (Comparable)array[i-1];
            for ( int j = i; ( j > 0 ) && ( comp.compareTo( array[j] ) > 0 ) ; j--) {
                Utility.swap( array, j, j-1 );
                comp = (Comparable)array[j-1];
            }
        }
    }
    
    public static void insertionsort2(Object[] array) { // Används på små arrayer i java (mindre än 7 element)
    	for( int i = 1; i < array.length; i++ ) {
            for ( int j = i; ( j > 0 ) && ( ( (Comparable)array[j-1] ).compareTo( array[j] ) > 0 ) ; j--) {
                Utility.swap( array, j, j-1 );
            }
        }
    }
    
// -------------------------- MERGESORT ----------------------------------------
    public static void mergesort( int[] array ) {
        int[] temp = new int[ array.length ];
        mergesort( array, 0, array.length, temp );
        temp = null;
    }
    
    private static void mergesort( int[] array, int start, int n, int[] temp ) {
        int n1,n2;
        if( n > 1 ) {
            n1 = n / 2;
            n2 = n - n1;
            mergesort( array, start, n1, temp );
            mergesort( array, start + n1, n2, temp );
            merge( array, start, n1, n2, temp );
        }
    }
    
    private static void merge(int[] array, int first, int n1, int n2, int[] temp) {
        int counter = 0,cursor1 = 0, cursor2 = n1, last = n1 + n2;
        while( ( cursor1 < n1 ) && ( cursor2 < last ) ) {
            if( array[ first + cursor1 ] < array[ first + cursor2 ] ) {
                temp[ counter ] = array[ first + cursor1];
                cursor1++;
            } else {
                temp[ counter ] = array[ first + cursor2 ];
                cursor2++;
            }
            counter++;
        }
        while( cursor1 < n1 ) {
            temp[ counter ] = array[ first + cursor1 ];
            cursor1++;
            counter++;
        }
        while( cursor2 < last ) {
            temp[ counter ] = array[ first + cursor2 ];
            cursor2++;
            counter++;
        }
        for( int i = 0; i < n1 + n2; i++ )
            array[ first + i ] = temp[ i ];
    }
    
    
    
    public void compTime() {
        int[] arr1 = Utility.randomArray(20000, 1000000, 2000000);
        int[] arr2 = arr1.clone();
        int[] arr3 = arr1.clone();
        int[] arr4 = arr1.clone();
        long startTime,stopTime;
        
        startTime = System.currentTimeMillis();
        bubblesort( arr1 );
        stopTime = System.currentTimeMillis();
        System.out.println( "Bubblesort: " + ( stopTime - startTime ) + " ms" );
        
        startTime = System.currentTimeMillis();
        insertionsort( arr2 );
        stopTime = System.currentTimeMillis();
        System.out.println( "Insertionsort: " + ( stopTime - startTime ) + " ms" );
        
        startTime = System.currentTimeMillis();
        mergesort( arr3 );
        stopTime = System.currentTimeMillis();
        System.out.println( "Mergesort: " + ( stopTime - startTime ) + " ms" );
    }
    
    public void compSwap() {
        int[] arr1 = Utility.completeArray( 100, 199 );
        int[] arr2 = arr1.clone();
        bubblesortC( arr1 );
        insertionsortC( arr2 );
    }
    
    public void testBubblesort() {
        int[] arr1 = Utility.completeArray( 100, 199 );
        Utility.printArray( arr1, 20 );
        Sorting.bubblesort( arr1 );
        System.out.println();
        Utility.printArray( arr1, 20 );
    }
    
    public void testInsertionsort() {
        int[] arr1 = Utility.completeArray( 100, 199 );
        Utility.printArray( arr1, 20 );
        Sorting.insertionsort( arr1 );
        System.out.println();
        Utility.printArray( arr1, 20 );
    }
    
    public void testMergesort() {
        int[] arr1 = Utility.completeArray( 100, 199 );
        Utility.printArray( arr1, 20 );
        Sorting.mergesort( arr1 );
        System.out.println();
        Utility.printArray( arr1, 20 );
    }
    
    public static void main(String[] args) {
        Sorting sort = new Sorting();
        sort.testBubblesort();
//        sort.testInsertionsort();
//        sort.compSwap();
//        sort.testMergesort();
//        sort.compTime();
    }
}
