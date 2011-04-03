package laboration2;

public class Person {
    private String firstName;
    private String familyName;
    private int age;

    public Person(String firstName, String familyName, int age) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getFirstName() {
        return this.firstName;
    }
    
    public String toString() {
        return this.firstName + " " + this.familyName;
    }
}
