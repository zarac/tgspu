package f9;
import java.util.LinkedList;

/**
 * Hashtabellen anv�nder �ppen hashing
 * @author Rolf Axelsson
 */
public class HashtableOH { 
    private LinkedList<Entry>[] table;
    private int size;
    
    /** Creates a new instance of HashtabellOH */
    public HashtableOH( int size ) {
        table = new LinkedList[ size ];
        for( int i = 0; i < size; i++ ) {
            table[ i ] = new LinkedList();
        }
    }
    
    private int hashIndex( Object key ) {
        int hashCode = key.hashCode();
        hashCode = hashCode % table.length;
        return ( hashCode < 0 ) ? -hashCode : hashCode;
    }
    
    public void put( Object key, Object value ) {
        int hashIndex = hashIndex( key );
        Entry entry = new Entry( key, value );
        int index = table[ hashIndex ].indexOf( entry );
        if( index == -1 ) {
            table[ hashIndex ].addFirst( entry ); // effektivt �ven vid enkell�nkad lista
            size++;
        }
//        else
//            table[ hashIndex ].set( index, entry );
    }
    
    public void list() {
        Entry entry;
        for(int i=0; i<table.length; i++) {
            System.out.print( i + ":");
            for( int j = 0; j < table[ i ].size(); j++ ) {
                entry = table[ i ].get( j );
                System.out.print(" <" + entry.key +"," + entry.value + ">" );
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        HashtableOH table = new HashtableOH(10);
        table.put("hej", "hallo");
        table.put("röd", "red");
        table.put("vit", "white");
        table.put("säng", "bed");
        table.put("svart", "black");
        table.put("gul", "yellow");
        table.put("dator", "computer");
        table.put("snö", "snow");
        table.put("blå", "blue");
        table.put("grön", "green");
        table.put("hus", "house"); 
        table.put("springa", "run"); 
        table.list();
    }
}
