package laboration1;
import java.util.*;
import java.io.*;

public class Uppgift2 {
    private ArrayList<Person> persons = new ArrayList<Person>();
    
    public void readPersons( String filnamn ) {
        try {
            BufferedReader br = new BufferedReader( new FileReader( filnamn ) );
            String[] parts;
            Person person;
            String txt = br.readLine();
            while( txt != null ) {
                parts = txt.split( "," );
                person = new Person( parts[ 0 ], parts[ 1 ], parts[ 2 ] );
                persons.add( person );
                txt = br.readLine();
            }
            br.close();
        } catch( IOException e ) {
            System.out.println( "readPersons: " + e );
        }
    }

    public String toString() {
        return "Lagrade personer: " + persons.toString();
    }
    
    public void printPersons() {
        for (Person person : persons)
            System.out.println(person.toString());
        // Skriv kod
    }
    
    public int position( String socialSecurityNumber ) {
        for (int i = 0 ; i < persons.size() ; i++)
            if (persons.get(i).getSocialSecurityNumber().equals(socialSecurityNumber))
                return i;
        return -1;
    }
    
    public void printName( String socialSecurityNumber ) {
        for (Person person : persons)
            if (person.getSocialSecurityNumber().equals(socialSecurityNumber))
            {
                System.out.println(person.getFirstName() + " " + person.getLastName());
                return;
            }
        System.out.println("NEJ!");
    }
    
    public boolean containsFirstName( String firstName ) {
        for (Person person : persons)
            if (person.getFirstName().equals(firstName))
                return true;

        return false; // Ta bort och skriv kod
    }
    
    public boolean changeLastName( String socialSecurityNumber, String lastName ) {
        for (Person person : persons)
            if (person.getSocialSecurityNumber().equals(socialSecurityNumber))
            {
                person.setLastName(lastName);
                return true;
            }

        return false; // Ta bort och skriv kod
    }


    
    public static void main( String[] args ) {
        Uppgift2 upp2 = new Uppgift2();
        upp2.readPersons( "src/laboration1/personer.txt" );
        System.out.println( upp2.toString() );
        upp2.printPersons();
       System.out.println( upp2.position( "840102-4567" ) );
       System.out.println( upp2.position( "111111-2222" ) );
       upp2.printName( "840102-4567" );
       upp2.printName( "111111-2222" );
       System.out.println( upp2.containsFirstName( "Edit" ) );
       System.out.println( upp2.containsFirstName( "Anna" ) );
       upp2.changeLastName( "660503-8901", "Trädrot" );
       System.out.println( upp2.toString() );
    }
}
