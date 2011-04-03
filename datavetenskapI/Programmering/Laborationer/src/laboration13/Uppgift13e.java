package laboration13; 
import java.util.*; 
 
public class Uppgift13e
{
    public void program()
    {
        Population[] countries = Populations.readPopulations("z:/me/studies/mah-spelutveckling/da211t/static/laboration13/befolkning.txt");
        Arrays.sort( countries, new AlfabetiskOrdning() ); 
        for( int i = 0; i < countries.length; i++ ) { 
            System.out.println( countries[ i ].toString() ); 
        } 
    } 
     
    public static void main( String[] args )
    {
        Uppgift13e u13e = new Uppgift13e(); 
        u13e.program(); 
    } 
} 
