package f2;

public class Shuffle {

    /* Skifta tv� element i int-array */
    private void swap( int[] array, int elem1, int elem2 ) {
        int temp = array[ elem1 ];
        array[ elem1 ] = array[ elem2 ];
        array[ elem2 ] = temp;
    }
    
    /* Blanda elementen i int-array */
    public void shuffle( int[] array ) {
        int pos;
        for( int i = array.length - 1; i > 0; i-- ) {
            pos = ( int )( Math.random() * ( i + 1 ) );
            swap( array, i, pos );
        }
    }

    /* Skriva int-array med best�mt antal v�rden per rad */    
    public void printArray(int[] array, int rowCount, int width ) {
        for(int i = 0; i< array.length; i++ ) {
            System.out.printf( "%"+width+"d", array[ i ] );
            if( ( i + 1 ) % rowCount == 0 )
                System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Shuffle prog = new Shuffle();
        int[] arr = new int[100];
        for(int i=0; i<arr.length; i++)
            arr[i] = i; // Tilldelning: arr[0]=0, arr[1]=1, arr[2]=2 osv
        prog.printArray(arr,50,3);
        prog.shuffle(arr);
        System.out.println();
        prog.printArray(arr,50,3);        
    }
}
