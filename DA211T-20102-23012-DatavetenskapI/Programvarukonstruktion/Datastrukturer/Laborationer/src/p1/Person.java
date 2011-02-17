package p1;

public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private int age;

    public Person( String firstName, String lastName, int age ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public String toString() {
        return firstName + " " + lastName + " " + age + " år";
    }
    
    public int compareTo( Person person ) {
        Utility.log("COMPARE: " + toString() + " jämförs med " + person.toString());
        int res = lastName.compareTo( person.getLastName() );
        if( res == 0) {
            res = firstName.compareTo( person.getFirstName() );
        }
        return res;
    }
}
