package laboration1;

public class Person {
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    
    public Person( String socialSecurityNumber, String firstName, String lastName ) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecutriyNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String toString() {
        return socialSecurityNumber + " " + firstName + " " + lastName;
    }
}
