package laboration5;
import java.util.*;

public class PersonnummerVaxande implements Comparator<Person> {
    public int compare( Person p1, Person p2 ) {
        String personnummer1 = p1.getPersonnummer();
        String personnummer2 = p2.getPersonnummer();
        return personnummer1.compareTo( personnummer2 );  // använder metoden compareTo( String )
    }
}
