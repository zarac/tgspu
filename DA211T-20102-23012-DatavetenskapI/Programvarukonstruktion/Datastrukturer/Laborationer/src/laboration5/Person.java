package laboration5;

public class Person {
    private String personnummer;
    private String fornamn;
    private String efternamn;
    private int langd;
    private double vikt;

    public Person(String personnummer, String fornamn, String efternamn, int längd, double vikt) {
        this.personnummer = personnummer;
        this.fornamn = fornamn;
        this.efternamn = efternamn;
        this.langd = langd;
        this.vikt = vikt;
    }
    
    public String getPersonnummer() {
        return this.personnummer;
    }
    
    public String getFornamn() {
        return this.fornamn;
    }
    
    public String getEfternamn() {
        return this.efternamn;
    }
    
    public int getLangd() {
        return this.langd;
    }
    
    public double getvikt() {
        return this.vikt;
    }
    
    public String toString() {
        return this.personnummer + " " + this.fornamn + " " + this.efternamn + ", längd=" + this.langd +" vikt=" + this.vikt;
    }
}
