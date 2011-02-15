package laboration1;
import java.io.*;
import java.util.*;

public class Uppgift3 {
    public static ArrayList<Person> readPersons( String fileName ) {
        ArrayList<Person> list = new ArrayList<Person>();
        try {
            BufferedReader br = new BufferedReader( new FileReader( fileName ) );
            String[] parts;
            Person person;
            
            String txt = br.readLine();
            while( txt != null ) {
                parts = txt.split( "," );

                for (int i = 0 ; i < parts.length ; i++)
                {
                    list.add(new Person(parts[i++], parts[i++], parts[i]));
                }

                //int entry = 0;
                //while (parts.length > entry)
                //{
                    //System.out.println(parts[entry*3] + parts[entry*3 + 1] + parts[entry*3 + 2]);
                    //list.add(new Person(parts[entry*3], parts[entry*3 + 1], parts[entry*3 + 2]));
                    //entry++;
                //}

                // Lägg till Person-objekt för alla personer som lagras på raden
                // Det krävs en loop vilken använder tre element varje iteration, dvs. 
                // loop-variabeln bör räknas upp med 3 varje iteration
                // Exempel: 
                // txt = "531019-1234,Sven,Andersson,660503-8901,Birgitta,Asp,861213-0123,Eva,Flood"
                // parts = {"531019-1234","Sven","Andersson","660503-8901","Birgitta","Asp","861213-0123","Eva","Flood"}
                // första upprepningen ska programmet hantera: "531019-1234", "Sven",     "Andersson"
                // andra upprepningen ska programmet hantera:  "660503-8901", "Birgitta", "Asp"
                // tredje upprepningen ska programmet hantera: "861213-0123", "Eva",      "Flood"

                txt = br.readLine();
            }
            br.close();
        } catch( IOException e ) {
            System.out.println( "läsPersoner: " + e );
        }
        return list;
    }
    
    public static void main( String[] args ) {
        ArrayList<Person> boende = Uppgift3.readPersons("src/laboration1/uppgift3.txt");
        System.out.println( boende.toString() ); // A
    }
}
