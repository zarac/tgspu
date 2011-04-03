package labC;

import java.io.*;
import java.io.IOException;
 
public class Uppgift1
{ 
    private Vara[] varor; 
     
    public void setVaror(Vara[] inVaror)
    { 
        varor = inVaror; 
    } 
     
    public void lista()
    { 
        for(int i = 0; i < varor.length; i++)
        { 
            System.out.println(varor[ i ].toString()); 
        } 
    } 
     
    public void skrivVaror(String filnamn)
    { 
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(filnamn);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);

            dataOutputStream.writeInt(varor.length);
            for (int i = 0; i < varor.length; i++)
            {
                dataOutputStream.writeLong(varor[i].getId());
                dataOutputStream.writeUTF(varor[i].getNamn());
                dataOutputStream.writeInt(varor[i].getAntal());
                dataOutputStream.writeDouble(varor[i].getPris());
            }

            dataOutputStream.close();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    } 
     
    public void hämtaVaror(String filename)
    { 
        try
        {
            FileInputStream fileInputStream = new FileInputStream(filename);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

            int antalVaror = dataInputStream.readInt();
            long id;
            String namn;
            int antal;
            double pris;

            varor = new Vara[antalVaror];

            for (int i = 0; i < antalVaror; i++)
            {
                id = dataInputStream.readLong();
                namn = dataInputStream.readUTF();
                antal = dataInputStream.readInt();
                pris = dataInputStream.readDouble();

                varor[i] = new Vara(id, namn, antal, pris);
            }

            dataInputStream.close();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    } 

    public void uppgift1a()
    { 
        Vara[] arr = { new Vara( 19874655, "Fotboll", 23, 299.95 ),  
                       new Vara( 48993365, "Vas", 52, 38.75 ), 
                       new Vara( 24355293, "Stol", 18, 795 ), 
                       new Vara( 76762292, "Cykel", 3, 2990 ) }; 

        setVaror(arr); 
        lista(); 
        skrivVaror("Z:/me/studies/mah-spelutveckling/da211t/Laborationer/src/labC/varor.dat"); 
    }

    public void uppgift1b()
    {
        hämtaVaror("Z:/me/studies/mah-spelutveckling/da211t/Laborationer/src/labC/varor.dat");
        lista();
    }

    public static void main(String[] args)
    {
        Uppgift1 upp1 = new Uppgift1();
        upp1.uppgift1b();
    } 
} 
