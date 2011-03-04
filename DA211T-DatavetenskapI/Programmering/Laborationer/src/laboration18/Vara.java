package laboration18;

/**
 * 
 * Representerar en vara i en butik.
 * @author Hannes Landstedt (hannes.landstedt@gmail.com)
 * @version null
 */
public class Vara {
    private String namn;     // varans namn
    private String kategori; // varans avdelning
    private int antal;       // antal av varan i butiken
    private double pris;     // varans pris
    
    /**
     * Konstruerar och initialiserar en vara med namn="", kategori="",  
     * antal=0 och pris=0.0. 
     */
    public Vara() {
        this.namn = "";
        this.kategori = "";
    }
    
    /**
     * 
     * 
     * @param inNamn Name of the product.
     * @param inKategori Category of product.
     * @param inAntal Amount.
     * @param inPris Price of product.
     */
    public Vara( String inNamn, String inKategori, int inAntal, double inPris ) {
        this.namn = inNamn;
        this.kategori = inKategori;
        this.antal = inAntal;
        this.pris = inPris;
    }
    
    /**
     * 
     * Returnerar varans namn.
     * @return varans namn
     */
    public String getNamn() {
        return this.namn;
    }
    
    /**
     * 
     * Returnerar varans kategori.
     * @return varans kategori
     */
    public String getKategori() {
        return this.kategori;
    }
    
    /**
     * 
     * Returnerar antalet varor.. ?
     * @return Antalet varor?
     */
    public int getAntal() {
        return this.antal;
    }
    
    /**
     * 
     * Returnerar varans pris.
     * @return varans pris
     */
    public double getPris() {
        return this.pris;
    }
        
    /**
     * 
     * Ändra antalet varor..?
     * @param inAntal Antal av varan?
     */
    public void setAntal( int inAntal ) {
        this.antal = inAntal;
    }
    
    /**
     * 
     * Ändra varans pris.
     * @param inPris varans pris
     */
    public void setPris( double inPris ) {
        this.pris = inPris;
    }
        
    /**
     * 
     * Beräkna och returnera varans lagervärde exklusive moms.
     * @param momssats Varans momssats i decimalform.
     * @return Varans lagervärde exklusive moms.
     */
    public double lagervärde(double momssats) {
        double totalt = (this.antal * this.pris) / (1+momssats);
        return totalt;
    }
}
