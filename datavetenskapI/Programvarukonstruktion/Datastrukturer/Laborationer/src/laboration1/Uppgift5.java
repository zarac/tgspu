package laboration1;
import java.util.*; // ArrayList
import java.io.*; // BufferedReader, FileReader, IOException

public class Uppgift5 {
    
    public static HashMap<String,Population> readPopulation( String filnamn ) {
        HashMap<String,Population> map = new HashMap<String,Population>();
        try {
            BufferedReader br = new BufferedReader( new FileReader( filnamn ) );
            String[] parts;
            Population population;
            long inhabitants;
            String txt = br.readLine();
            while( txt != null ) {
                parts = txt.split( "," );
                inhabitants = Long.parseLong( parts[ 1 ] ); // befolkningen konverteras till long
                population = new Population( parts[ 0 ], inhabitants  ); // Skapa ett Population-objekt
                map.put( parts[ 0 ], population  ); // (key=landets namn, value=Population-objekt) lagras i HashMappen
                txt = br.readLine();
            }
            br.close();
        } catch( IOException e ) {
            System.out.println( "readPersons: " + e );
        }
        return map;
    }
    
    public static void info( HashMap map ) {
        Iterator<String> iter = map.keySet().iterator();
        Object value;
        Object key;
        while( iter.hasNext() ) {
            key = iter.next();
            value = map.get( key );
            System.out.println( value.toString() );
        }
    }
    
    public static void main( String[] args ) {
        HashMap<String,Population> populations = Uppgift5.readPopulation( "src/laboration1/befolkning.txt" );
        Population pop1 = new Population( "Ladonia", 0 ); // Land på nordsidan av Kullaberg
        
        System.out.println("-------- A --------");
        System.out.println( populations.toString() ); // A
        System.out.println("-------- B --------");
        System.out.println( populations.size() ); // B
        System.out.println("-------- C --------");
        System.out.println( populations.get( "Liberia" ) ); // C
        System.out.println("-------- D --------");
        System.out.println( populations.containsKey( "Sweden" ) ); // D
        System.out.println( populations.containsKey( "Sverige" ) );
        System.out.println("-------- E --------");
        populations.put( "Ladonia", pop1 ); // E
        System.out.println( populations.toString() );
        System.out.println("-------- F --------");
        System.out.println( populations.remove( "Sverige" ) ); // F
        System.out.println("-------- H --------");
        Uppgift5.info( populations  ); // H
        System.out.println("-------- G --------");
        populations.clear(); // G
        System.out.println( populations.toString() );
    }
}
