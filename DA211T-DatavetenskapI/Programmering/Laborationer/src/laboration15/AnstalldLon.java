package laboration15; 
 
public abstract class AnstalldLon implements Comparable<AnstalldLon>
//public abstract class AnstalldLon implements Comparable
{ 
    private long id; 
     
    public AnstalldLon( long id ) { 
        this.id = id; 
    } 
     
    public long getId() { 
        return this.id; 
    } 
     
    public String toString() { 
        return "Id: " + this.id + ", lön denna månad: " + lon() + " kr"; 
    } 
     
    public abstract double lon();

    /**
     * {@inheritDoc}
     * @see Comparable#compareTo(Object)
     */
    public int compareTo(AnstalldLon o)
    {
        AnstalldLon anstalld = (AnstalldLon)o;
        double lon1 = this.lon();
        double lon2 = anstalld.lon();

        return (int)(lon1 - lon2);

        //if (lon1 < lon2)
            //return -1;
        //else if (lon1 == lon2)
            //return 0;
        //else        
            //return 1;
    } 
}
