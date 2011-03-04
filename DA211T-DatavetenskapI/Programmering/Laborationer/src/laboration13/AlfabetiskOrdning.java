package laboration13; 
import java.util.*; 
 
public class AlfabetiskOrdning implements Comparator
{
    public int compare(Object obj1, Object obj2)
    {
        Population land1 = (Population)obj1; 
        Population land2 = (Population)obj2; 
        String namn1 = land1.getCountry(); 
        String namn2 = land2.getCountry(); 

        return namn1.compareTo(namn2);

        // Här ska du jämföra namn1 med namn2 
        // Är namn1 mindre än namn2 så ska metoden returnera -1. Denna jämförelse  
        // gör du så här: 
        // if( namn1.compareTo( namn2 ) < 0 ) osv  
        // Är namn1 större än namn2 så ska metoden returnera 1 
        //if (namn1.compareTo(namn2) < 0)
            //return 1;

        //// Är namn1 och namn2 lika stora så ska metoden returnera 0 
        //else if (namn1.compareTo(namn2) == 0)
            //return 0;

        //else
            //return -1;

        // Ovanståend jämförelse görs korrektare med hjälp av ett Collator-objekt.  
        // Skulle det funnits länder som börjar med Å eller Ä så skulle även dessa  
        // ordnats på avsett sätt. 
        // * importera java.text.*; 
        // * Efter ovanstående fyra rader lägg till  
        //   Collator coll = Collator.getInstance();  
        // * Jämför sedan med coll.compare( namn1, namn2 ) 
    }
} 

