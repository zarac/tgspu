package labC;
import java.io.*;

public class Vara {
    private long id;
    private String namn;
    private int antal;
    private double pris;

    public Vara( long id, String namn, int antal, double pris) {
        this.id = id;
        this.namn = namn;
        this.antal = antal;
        this.pris = pris;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamn() {
        return this.namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public int getAntal() {
        return this.antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public double getPris() {
        return this.pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }
    
    public String toString() {
        return "Id=" + this.id + ", " + this.namn + ", antal=" + this.antal + ", pris=" + this.pris;
    }
}
