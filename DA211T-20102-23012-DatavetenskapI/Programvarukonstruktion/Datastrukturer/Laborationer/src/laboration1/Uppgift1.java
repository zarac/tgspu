package laboration1;
import java.util.*; // ArrayList
import java.io.*; // BufferedReader, FileReader, IOException

public class Uppgift1 {
    public static ArrayList<Person> readPersons( String fileName ) {
        ArrayList<Person> list = new ArrayList<Person>();
        try {
            BufferedReader br = new BufferedReader( new FileReader( fileName ) );
            String[] parts;
            Person person;
            String txt = br.readLine();
            while( txt != null ) {
                parts = txt.split( "," );
                person = new Person( parts[ 0 ], parts[ 1 ], parts[ 2 ] );
                list.add( person );
                txt = br.readLine();
            }
            br.close();
        } catch( IOException e ) {
            System.out.println( "läsPersoner: " + e );
        }
        return list;
    }
    
    public static void main( String[] args ) {
        ArrayList<Person> persons = Uppgift1.readPersons( "src/laboration1/personer.txt" );
        //ArrayList<Person> persons = Uppgift1.readPersons( "M:/filer/personer.txt" );
        Person pers1 = new Person( "761201-7654", "Henry", "Smith" );
        Person pers2 = new Person( "011003-4444", "Alma", "Björk" );
        Person p;
        int a;
        System.out.println("-------- A ---------");
        System.out.println( persons.toString() ); // A
        
        System.out.println("-------- B ---------");
        p = persons.get( 2 ); // B
        System.out.println( p.toString() ); 
        
        System.out.println("-------- C ---------");
        persons.add( pers1 ); // C
        System.out.println( persons.toString() );
        
        System.out.println("-------- D ---------");
        persons.add( 4, pers2 ); // D
        System.out.println( persons.toString() );
        
        System.out.println("-------- E ---------");
        a = persons.size(); // E
        System.out.println( a );
        
        System.out.println("-------- F ---------");
        a = persons.indexOf( pers2 ); // F
        System.out.println( a );
        
        System.out.println("-------- G ---------");
        if( persons.contains( pers1 ) ) { // G
            System.out.println( pers1.toString() + " FINNS" );
        } else {
            System.out.println( pers1.toString() + " FINNS EJ" );
        }
        
        System.out.println("-------- H ---------");
        persons.remove( 1 ); // H
        System.out.println( persons.toString() );
        
        System.out.println("-------- I ---------");
        persons.clear(); // I
        System.out.println( persons.toString() );
    }
}
