package p5;

/**
 * Klassen implementerar Dice. T�rningskasten �r f�ruts�gbara genom att ett kast 
 * alltid �r ett st�rre �n f�reg�ende. Enda undantaget fr�n denna regel �r om 
 * f�rra kastet visade st�rsta antalet prickar. D� kommer kastet att visa en prick.
 * @author Rolf Axelsson
 */
public class PredictableDice implements Dice {
    private int sides;
    private int lastResult;
    
    /*
     * Skapar en 6-sidig t�rning
     **/
    public PredictableDice() {
        this( 6 );  
    }
         
    /*
     * Skapar en t�rning med s� m�nga sidor som anges av argumentet.
     * @param sides Antal sidor p� t�rningen. Antalet sidor m�ste vara st�rre 
     * �n 0.
     * @throws NegativeSidesException Om v�rdet p� <code>sides</code> �r mindre eller
     *                                lika med 0
     **/
    public PredictableDice( int sides ) {
        if( sides <= 0) 
            throw new NegativeSidesException("Antalet sidor m�ste vara minst 1, sides="+sides);
        this.sides = sides;
        lastResult = -1;
    }
    
    /*
     * Returnerar antalet sidor p� t�rningen
     * @return Antalet sidor p� t�rningen
     **/
    public int getSides() {
        return sides;
    }
    
    // 
    /*
     * Returnerar resultatet av ett t�rningskast. Resultatet �r altid i intervallet 
     * 1 - <code>sides</code> och i ordningen: 1, 2, 3, ..., sides-1, sides, 1, 2, osv
     * @return Resultatet av t�rningskastet
     **/
    public int throwDice() {
        lastResult = (lastResult + 1) % sides; // 0, 1, 2, ..., sides-2, sides-1, 0, 1, osv
        return lastResult + 1; 
    }
}
