package p5;

public interface Dice {
    /*
     * Kastar t�rningen en g�ng och returnerar resultatet.
     * @returns Antalet prickar som t�rningen visar vid kastet
     **/
    public int throwDice();
    
    /*
     * Returnerar antalet sidor som t�rningen har
     * @returns Antalet sidor p� t�rningen
     **/
    public int getSides();
}
