package p1;
import java.util.*;

public class LastName implements Comparator<Person> {
    public int compare( Person p1, Person p2 ) {
        return p1.getLastName().compareTo( p2.getLastName() );
    }
}
