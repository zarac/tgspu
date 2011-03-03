package laboration1;

public class Population {
    private String name;
    private long inhabitants;
    
    public Population( String inName, long inInhabitants ) {
        this.name = inName;
        this.inhabitants = inInhabitants;
    }

    public String getName() {
        return name;
    }

    public long getInhabitants() {
        return inhabitants;
    }
    
    public String toString() {
        return name + " " + inhabitants;
    }
}
