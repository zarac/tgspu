package p5;
import javax.swing.*;

public class TestSimpleDice {
    public static void printResult( int[] result ) {
        for( int i = 0; i < result.length; i++ ) {
            System.out.printf( "%-4s%10d\n", (i + 1), result[ i ] );
        }
    }
    
    public static void main(String[] args) {
        Dice dice;
        int sides, aThrow;
        int[] result;
        sides = Integer.parseInt( JOptionPane.showInputDialog( "Hur många sidor ska tärningen ha? (-10 för avbrott)" ) );
        while( sides!=-10 ) {
            try {
                dice = new SimpleDice( sides );
                // Skapa en array för att räkna antalet 1:or, 2:or ocv som tärningen visar vid kasten nedan
                result = new int[ sides ];
                System.out.println( "----- 100000 kast med " + dice.getSides() + "-sidig tärning ----- " );
                // Loop som upprepas 100000 ggr. Vid varje upprepning kastas tärningen och räknaren för antalet
                // prickar vid kastet räknas upp. result[ 0 ] är räknare för antalet 1:or, result[ 1 ] är räknare
                // för antalet 2:or osv.
                for( int i = 0; i < 100000; i++ ) {
                    // Kasta tärningen
                    aThrow = dice.throwDice();
                    // Öka räknare för antalet prickar i kastet.
                    result[ aThrow - 1 ]++;
                }
                // Utskrift av antalet 1:r, 2:or osv
                TestSimpleDice.printResult( result );
            } catch( NegativeSidesException e1 ) {
                System.out.println( e1 );
            } catch( ArrayIndexOutOfBoundsException e2) {
                int res = Integer.parseInt( e2.getMessage() ) + 1;
                System.out.println( "Felaktigt resultat vid tärningskast: " + res );
            }          
            sides = Integer.parseInt( JOptionPane.showInputDialog( "Hur många sidor ska tärningen ha? (-10 för avbrott)" ) );
        }
    }
}
