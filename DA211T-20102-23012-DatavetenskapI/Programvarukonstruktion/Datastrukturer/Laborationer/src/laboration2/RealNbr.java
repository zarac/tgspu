package laboration2;

public class RealNbr implements Comparable<RealNbr> {
    private double value;
    
    public RealNbr(double value) {
        this.value = value;
    }
    
    public double getValue() {
        return value;
    }
    
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
}
