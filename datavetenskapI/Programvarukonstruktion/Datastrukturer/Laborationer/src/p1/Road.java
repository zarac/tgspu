package p1;

public class Road {
    private String from;
    private String to;
    private int dist;
    
    public Road( String from, String to, int cost ) {
        this.from = from;
        this.to = to;
        this.dist = cost;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public int getDist() {
        return this.dist;
    }
    
    public String toString() {
        return this.from + " - " + this.to + ": " + this.dist;
    }
}
