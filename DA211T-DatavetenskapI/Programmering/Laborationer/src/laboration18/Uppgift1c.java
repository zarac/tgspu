package laboration18; 
import resurser.InOut; 
 
public class Uppgift1c { 
    public static void main( String[] args ) { 
        InOut io = new InOut(); 
        int ettTal = io.readInt( "Mata in ett tal utan decimaler" ); 
        System.out.println( "Inmatat tal: " + ettTal ); 
    } 
} 
