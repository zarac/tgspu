package laboration13;

/**
 * Klassen lagrar ett lands befolkning
 * @author Rolf
 */
public class Population implements Comparable
{
    private String country;
    private long population;
    
    public Population( String country, long population ) {
        this.country = country;
        this.population = population;
    }
    
    public String getCountry() {
        return this.country;
    }
    
    public long getPopulation() {
        return this.population;
    }
    
    public String toString() {
        return String.format( "%-30s%15d", this.country, this.population );
    }

    /**
     * {@inheritDoc}
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(Object p_object)
    {
        Population country = (Population)p_object;
        long comparePopulation = country.getPopulation();

        if (population < comparePopulation)
            return -1;
        else if (population > comparePopulation)
            return 1;
        else
            return 0;
    }
}



//package laboration13;

/**
 * Klassen lagrar ett lands befolkning
 * @author Rolf
 */
//public class Population {
    //private String country;
    //private long population;
    
    //public Population( String country, long population ) {
        //this.country = country;
        //this.population = population;
    //}
    
    //public String getCountry() {
        //return this.country;
    //}
    
    //public long getPopulation() {
        //return this.population;
    //}
    
    //public String toString() {
        //return String.format( "%-30s%15d", this.country, this.population );
    //}
//}
