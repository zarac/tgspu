package labA;

public class Foto {
    public static final int OVRIGT=0, FAMILJ=1, BARNBARN=2, RESOR=3;
    private String motiv;
    private int artal;
    private int kategori;
    private String lagringsplats;

    public Foto(String motiv, int artal, int kategori, String lagringsplats) {
        this.motiv = motiv;
        this.artal = artal;
        if( (kategori<OVRIGT) || (kategori>RESOR) )
            kategori = OVRIGT;
        this.kategori = kategori;
        this.lagringsplats = lagringsplats;
    }
    
    public void setLagringsplats(String lagringsplats) {
        this.lagringsplats = lagringsplats;
    }
 
    public String getMotiv() {
        return motiv;
    }
    
    public int getArtal() {
        return artal;
    }
    
    public int getKategori() {
        return kategori;
    }
    
    public String getLagringsplats() {
        return lagringsplats;
    }
    
    public String toString() {
        return "Motiv: " + motiv + "  Årtal: " + artal;
    }    
}
