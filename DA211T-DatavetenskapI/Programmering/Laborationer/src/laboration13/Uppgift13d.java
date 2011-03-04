package laboration13; 
import java.util.*; 
 
public class Uppgift13d { 
    public void program() { 
        Population[] countries = Populations.readPopulations("z:/me/studies/mah-spelutveckling/da211t/static/laboration13/befolkning.txt");
        Arrays.sort( countries ); 
        for( int i = 0; i < countries.length; i++ ) { 
            System.out.println( countries[ i ].toString() ); 
        } 
    } 
     
    public static void main( String[] args ) { 
        Uppgift13d u13d = new Uppgift13d(); 
        u13d.program(); 
    } 
} 
