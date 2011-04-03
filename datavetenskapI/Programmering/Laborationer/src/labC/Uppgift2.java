package labC;
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Uppgift2
{ 
    private ArrayList<Population> befolkning = new ArrayList<Population>(); 
     
    public void setBefolkning( Population[] bef )
    { 
        befolkning.clear(); // så att befolkning är tom från början 
        for(int i = 0; i < bef.length; i++)
        { 
            befolkning.add(bef[ i ]); 
        } 
    } 
     
    public void lista()
    { 
        for( int i = 0; i < befolkning.size(); i++ )
        { 
            System.out.println(befolkning.get(i).toString()); 
        } 
    } 
     
    public void skrivBefolkning( String filnamn )
    { 
        try
        {
            FileWriter fileWriter = new FileWriter(filnamn);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            Population population;
            String string;

            for(int i = 0; i < befolkning.size(); i++)
            {
                population = befolkning.get(i);
                string = population.getName() + "," + population.getInhabitants();
                bufferedWriter.write(string);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    } 

    public void hämtaBefolkning( String filnamn )
    { 
        try
        {
            FileReader fileReader = new FileReader(filnamn);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String inStr;
            long invanare;
            String[] delar;

            befolkning.clear();

            inStr = bufferedReader.readLine();
            while (inStr != null)
            {
                delar = inStr.split(",");
                invanare = Long.parseLong(delar[1]);
                befolkning.add(new Population(delar[0], invanare));
                inStr = bufferedReader.readLine();
            }

            bufferedReader.close();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    } 

    public void uppgift2a()
    { 
        Population[] bef = { new Population( "Kina", 1313973713 ),  
                             new Population( "Mexico", 107449525 ), 
                             new Population( "Brasilien", 188078227 ), 
                             new Population( "USA", 298444215 ), 
                             new Population( "Bangladesh", 147365352 ), 
                             new Population( "Nigeria", 131859731 ) }; 
        setBefolkning( bef ); 
        lista(); 
        skrivBefolkning("Z:/me/studies/mah-spelutveckling/da211t/src/labC/population.txt"); 
    }

    public void uppgift2b()
    {
        hämtaBefolkning("Z:/me/studies/mah-spelutveckling/da211t/src/labC/population.txt"); 
        lista();
    }

    public static void main(String[] args)
    {
        Uppgift2 upp2 = new Uppgift2(); 
        //upp2.uppgift2a();
        upp2.uppgift2b();
    } 
}
