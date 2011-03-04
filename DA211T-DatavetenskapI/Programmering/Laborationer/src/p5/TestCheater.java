package p5;

public class TestCheater {
    public static void kasta( Player player, int antalKast ) {
        // Typkonvertera referensen till "Referens till OrdinaryPlayer"
        Cheater ch = ( Cheater )player;
        // Använda Cheater-referensen för att erhålla spelarens Dice-objekt
        // Här går det inte att använda Player-referensen (player) eftersom metoden 
        // getDice() inte är deklarerad i klassen Player (eller i Object).
        Dice dice = ch.getDice();
        int sides = dice.getSides(), aThrow;
        // Skapa en array för att räkna antalet 1:or, 2:or ocv som tärningen visar vid kasten nedan 
        int[] result = new int[ sides ];
        System.out.println( "----- " + antalKast + " kast av " + player.getName() + 
                " med " + sides + "-sidig tärning -----" );
        for( int i = 0; i < antalKast; i++ ) {
            // Låta spelaren kasta sin tärning
            aThrow = player.throwDice();
            // Öka räknare för antalet prickar i kastet.
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
