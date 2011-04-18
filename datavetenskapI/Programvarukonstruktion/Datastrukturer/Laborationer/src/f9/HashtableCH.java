package f9;

/**
 * Hashtabellen anv�nder sluten hashing
 * @author Rolf Axelsson
 **/
public class HashtableCH {
    private Bucket[] table;
    private int size;
    
    public HashtableCH( int size ) { 
        table = new Bucket[ size ];
        for( int i = 0; i < size; i++ ) {
            table[ i ] = new Bucket();
        }
    }
    
    private int hashIndex( Object key ) {
        int hashCode = key.hashCode();
        hashCode = hashCode % table.length;
        return ( hashCode < 0 ) ? -hashCode : hashCode;
    }
    
    
    public int size() {
        return size;
    }
    
    public boolean contains( Object key ) {
        return ( get( key ) != null );
    }
    
    public Object get( Object key ) {
        int hashIndex = hashIndex( key ), i;
        for( i = 0; ( i < table.length ) && ( table[ hashIndex ].state != Bucket.empty ) && !key.equals( table[ hashIndex ].key ); i++) {
            hashIndex = hashIndex + 1;
            if( hashIndex >= table.length )
                hashIndex = 0;
        }
        if( key.equals( table[ hashIndex ].key ) )
            return table[ hashIndex ].value;
        return null;
    }
    
    public void put( Object key, Object value ) {
        if( !contains( key ) ) {
            int hashIndex = hashIndex( key ), i;
            for( i = 0; ( i < table.length ) && ( table[ hashIndex ].state == Bucket.occupied ); i++) {
                hashIndex = hashIndex + 1;
                if( hashIndex >= table.length )
                    hashIndex = 0;
            }
            if( ( i < table.length ) && ( table[ hashIndex ].state != Bucket.occupied ) ) {
                table[ hashIndex ].key = key;
                table[ hashIndex ].value = value;
                table[ hashIndex ].state = Bucket.occupied;
                size++;
            }
        }
    }
    
    public void remove( Object key ) {
        int hashIndex = hashIndex( key ), i;
        for( i = 0; ( i < table.length ) && ( table[ hashIndex ].state != Bucket.empty ) && !key.equals( table[ hashIndex ].key ); i++) {
            hashIndex = hashIndex + 1;
            if( hashIndex >= table.length )
                hashIndex = 0;
        }
        if( key.equals( table[ hashIndex ].key ) ) {
            table[ hashIndex ].key = null;
            table[ hashIndex ].value = null;
            table[ hashIndex ].state = Bucket.removed;
            size--;
        }
    }
    
    public void list() {
        System.out.println( "Tabellen innehåller " + size() + " element:" );
        for(int i=0; i<table.length; i++)
            System.out.println(i+": key=" + table[ i ].key +" value=" + table[ i ].value + " state=" + table[ i ].state );
    }
    
    
    public static void main(String[] args) {
        HashtableCH table = new HashtableCH(10);
        table.put("hej", "hallo");      // 1
        table.put("röd", "red");        // 0
        table.put("vit", "white");      // 9
        table.put("säng", "bed");       // 6
        table.put("svart", "black");    // 0 -> 2
        table.put("gul", "yellow");     // 8
        table.put("dator", "computer"); // 8 -> 3
        table.put("snö", "snow");       // 1 -> 4
        table.put("blå", "blue");       // 5
        table.put("grön", "green");     // 3 -> 7
        table.put("hus", "house");      // fullt
        table.put("springa", "run");    // fullt
        table.list();
        System.out.println("----------------------------------");
        System.out.println( "programmera: " + table.get("programmera"));
//        table.remove("röd");
//        table.list();
//        System.out.println( "gul: " + table.get("gul"));
//        System.out.println( "dator: " + table.get("dator"));
//        table.remove("gul");
//        table.list();
//        System.out.println("----------------------------------");
//        System.out.println( "gul: " + table.get("gul"));
//        System.out.println( "dator: " + table.get("dator"));
//
//        table.remove("hej");
//        table.remove("röd");
//        table.remove("vit");
//        table.remove("säng");
//        table.remove("svart");
//        table.remove("gul");
//        table.remove("dator");
//        table.remove("snö");
//        table.remove("blå");
//        table.remove("grön");
//        table.remove("hus");
//        table.remove("springa");
//        table.list();
//        System.out.println("----------------------------------");
//        table.put("hej", "hallo");
//        table.put("röd", "red");
//        table.put("vit", "white");
//        table.put("säng", "bed");
//        table.put("svart", "black");
//        table.put("gul", "yellow");
//        table.put("dator", "computer");
//        table.put("snö", "snow");
//        table.put("blå", "blue");
//        table.put("grön", "green");
//        table.put("hus", "house");
//        table.put("springa", "run");
//        table.list();
//        System.out.println("----------------------------------");
//        System.out.println( "hej: " + table.get("hej") );
//        System.out.println( "röd: " + table.get("röd") );
//        System.out.println( "vit: " + table.get("vit") );
//        System.out.println( "säng: " + table.get("säng") );
//        System.out.println( "svart: " + table.get("svart") );
//        System.out.println( "gul: " + table.get("gul") );
//        System.out.println( "dator: " + table.get("dator") );
//        System.out.println( "blå: " + table.get("blå") );
//        System.out.println( "grön: " + table.get("grön") );
//        System.out.println( "hus: " + table.get("hus") );
//        System.out.println( "springa: " + table.get("springa") );
    }
}

