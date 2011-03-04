package laboration18;
import resurser.InOut;

/**
 * jaoooo testar lite
 * 
 * @author Hannes Landstedt (hannes.landstedt@gmail.com)
 * @version null
 */
public class Uppgift2 {
    public static void main( String[] args ) {
        double decTal;
        int heltal;
        decTal = InOut.readDouble();
        System.out.println( "Inmatat tal: " + decTal );
        decTal = InOut.readDouble( "Mata in ett stort decimaltal" );
        System.out.println( "Inmatat tal: " + decTal );
        heltal = InOut.readInt();
        System.out.println( "Inmatat tal: " + heltal );
        heltal = InOut.readInt("Mata in ett positivt heltal");
        System.out.println( "Inmatat tal: " + heltal );
    }
}
