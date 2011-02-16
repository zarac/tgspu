package f2;

//public class RealNbr implements Comparable<RealNbr> {
public class RealNbr {
    private double value;
    
    public RealNbr(double value) {
        this.value = value;
    }
    
    public double getValue() {
        return value;
    }
    
    // Hur blir körresultatet om equals-metoden tas bort?
    public boolean equals(Object obj) {
        boolean res = (this==obj);
        if( !res && (obj instanceof RealNbr) ) {
            RealNbr t = ( RealNbr ) obj;
            res = ( this.value == t.value );
        }
        return res;
    }
    
    public int compareTo( RealNbr nbr ) {
        double difference = this.value - nbr.value;
        if( difference < 0 )
            return -1;
        else if( difference == 0 )
            return 0;
        else
            return 1;
    }
    
    public static void main(String[] args) {
        RealNbr n1 = new RealNbr(17.9);
        RealNbr n2 = new RealNbr(-11.3);
        RealNbr n3 = new RealNbr(17.9);
        RealNbr n4 = n1;
        
        System.out.println( "n1.equals( n2 ): " + n1.equals( n2 ) );
        System.out.println( "n1.equals( n3 ): " + n1.equals( n3 ) );
        System.out.println( "n1.equals( n4 ): " + n1.equals( n4 ) );
        System.out.println( "n1.equals( null ): " + n1.equals( null ) );
    }
}
