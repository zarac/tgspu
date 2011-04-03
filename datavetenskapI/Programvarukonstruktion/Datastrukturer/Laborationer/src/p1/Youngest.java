package p1;
import java.util.*;

public class Youngest implements Comparator<Person> {
    public int compare( Person p1, Person p2 ) {
        return p1.getAge() - p2.getAge();
    }
}
