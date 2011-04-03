package labC; 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList; 
 
public class Uppgift3
{ 
    private ArrayList<Population> befolkning = new ArrayList<Population>(); 
 
    public void setBefolkning(Population[] bef)
    { 
        befolkning.clear(); // så att befolkning är tom från början 
        for (int i = 0; i < bef.length; i++)
        { 
            befolkning.add(bef[i]); 
        } 
    } 
 
    // används i Uppgift 6 
    public ArrayList<Population> getBefolkning()
    { 
        return befolkning; 
    } 
 
    public void lista()
    { 
        for (int i = 0; i < befolkning.size(); i++)
        { 
            System.out.println(befolkning.get(i).toString()); 
        } 
    } 
 
    public void skrivBefolkning(String filnamn)
    { 
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(filnamn);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);

            dataOutputStream.writeInt(befolkning.size());
            for (int i = 0; i < befolkning.size(); i++)
            {
                dataOutputStream.writeUTF(befolkning.get(i).getName());
                dataOutputStream.writeLong(befolkning.get(i).getInhabitants());
            }

            dataOutputStream.close();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    } 
 
    public void hämtaBefolkning(String filename)
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream(filename);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

            int antalBefolkning = dataInputStream.readInt();

            
            befolkning.clear();
            for (int i = 0; i < antalBefolkning; i++)
            {
                befolkning.add(new Population(dataInputStream.readUTF(), dataInputStream.readLong()));
            }

            dataInputStream.close();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    } 

    public void uppgift3_1()
    { 
        Population[] bef = { new Population( "Kina", 1313973713 ),  
                             new Population( "Mexico", 107449525 ), 
                             new Population( "Brasilien", 188078227 ), 
                             new Population( "USA", 298444215 ), 
                             new Population( "Bangladesh", 147365352 ), 
                             new Population( "Nigeria", 131859731 ) }; 
        setBefolkning( bef ); 
        lista(); 
        skrivBefolkning("Z:/me/studies/mah-spelutveckling/da211t/src/labC/population.dat"); 
    }
    
    public void uppgift3_2()
    {
        hämtaBefolkning("Z:/me/studies/mah-spelutveckling/da211t/src/labC/population.dat"); 
        lista();
    }

    public static void main(String[] args)
    {
        Uppgift3 uppgift3 = new Uppgift3();
        //uppgift3.uppgift3_1();
        uppgift3.uppgift3_2();
    }
} 
