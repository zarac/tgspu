package p5;

public class TestCheater {
    public static void kasta( Player player, int antalKast ) {
        // Typkonvertera referensen till "Referens till OrdinaryPlayer"
        Cheater ch = ( Cheater )player;
        // Anv�nda Cheater-referensen f�r att erh�lla spelarens Dice-objekt
        // H�r g�r det inte att anv�nda Player-referensen (player) eftersom metoden 
        // getDice() inte �r deklarerad i klassen Player (eller i Object).
        Dice dice = ch.getDice();
        int sides = dice.getSides(), aThrow;
        // Skapa en array f�r att r�kna antalet 1:or, 2:or ocv som t�rningen visar vid kasten nedan 
        int[] result = new int[ sides ];
        System.out.println( "----- " + antalKast + " kast av " + player.getName() + 
                " med " + sides + "-sidig t�rning -----" );
        for( int i = 0; i < antalKast; i++ ) {
            // L�ta spelaren kasta sin t�rning
            aThrow = player.throwDice();
            // �ka r�knare f�r antalet prickar i kastet.
            result[ aThrow - 1 ]++;
        }
        // Utskrift av antalet 1:r, 2:or osv
        TestSimpleDice.printResult( result );
    }
    
    public static void main(String[] args) {
        Cheater player1 = new Cheater( "Stefan" , new SimpleDice(8) );
        Cheater player2 = new Cheater( "Signe" );
        
        TestCheater.kasta( player1, 1000000 );
        TestCheater.kasta( player2, 1000000 );
        ( (Cheater)player1 ).setDice( new SimpleDice( 2 ) );
        TestCheater.kasta( player1, 1000000 );
        ( (Cheater)player2 ).setDice( new SimpleDice( 1 ) );
        TestCheater.kasta( player2, 1000000 );
    }
}
