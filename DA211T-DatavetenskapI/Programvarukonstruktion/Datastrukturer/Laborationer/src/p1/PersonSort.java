package p1;

public class PersonSort { 

    private static void swap( Object[] array, int i, int j ) {
        Utility.log("SWAP:    " + array[j].toString() + " <--> " + array[i].toString());
        Object temp = array[ i ];
        array[ i ] = array[ j ];
        array[ j ] = temp;
    }

    public static void insertionsort(Object[] array) { 
        for( int i = 1; i < array.length; i++ ) {
            for ( int j = i; ( j > 0 ) && ( ( Comparable )array[j-1] ).compareTo( array[j] ) > 0 ; j--) {
                swap( array, j, j-1 );
            }
        }
    }
    
    public static void main(String[] args) {
        Person[] personer = { new Person( "Bo", "Al", 52 ), new Person( "Anna", "Bos", 17 ),
                              new Person( "Anders", "Al", 44 ), new Person( "Eva", "Bok", 33 ) };
        System.out.println( "JÄMFÖRELSER OCH SWAPPAR" );
        PersonSort.insertionsort( personer );
        System.out.println( "EFTER SORTERING" );
        for( int i = 0; i < personer.length; i++ )
            System.out.println( personer[ i ] );
    }
}
