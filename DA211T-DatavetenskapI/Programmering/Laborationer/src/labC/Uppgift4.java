package labC; 
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Uppgift4
{ 
    private Vara[] varor; 
     
    public void setVaror( Vara[] inVaror )
    { 
        varor = inVaror; 
    } 
     
    public void lista()
    { 
        for( int i = 0; i < varor.length; i++ )
        { 
            System.out.println( varor[ i ].toString() ); 
        } 
    } 
     
    public void skrivVaror( String filnamn )
    { 
        try
        {
            FileWriter fileWriter = new FileWriter(filnamn);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            fileWriter.write(String.valueOf(varor.length));
            bufferedWriter.newLine();
            
            for (int i = 0; i < varor.length; i++)
            {
                bufferedWriter.write(varor[i].getId() + "," + varor[i].getNamn() + "," + varor[i].getAntal() + "," + varor[i].getPris());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    } 
     
    public void hämtaVaror( String filnamn )
    { 
        try
        {
            FileReader fileReader = new FileReader(filnamn);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int numRecords = Integer.parseInt(bufferedReader.readLine());

            varor = new Vara[numRecords];

            String[] delar;
            long id;
            String namn;
            int antal;
            double pris;

            for (int i = 0; i < numRecords; i++)
            {
                delar = bufferedReader.readLine().split(",");
                id = Long.parseLong(delar[0]);
                namn = delar[1];
                antal = Integer.parseInt(delar[2]);
                pris = Double.parseDouble(delar[3]);

                varor[i] = new Vara(id, namn, antal, pris);
            }

            bufferedReader.close();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    } 

    public void uppgift4_1()
    {
        Vara[] arr = { new Vara( 19874655, "Fotboll", 23, 299.95 ),  
                       new Vara( 48993365, "Vas", 52, 38.75 ), 
                       new Vara( 24355293, "Stol", 18, 795 ), 
                       new Vara( 76762292, "Cykel", 3, 2990 ) }; 

        setVaror(arr);
        lista();
        skrivVaror("Z:/me/studies/mah-spelutveckling/da211t/src/labC/varor.txt"); 
    }

    public void uppgift4_2()
    {
        hämtaVaror("Z:/me/studies/mah-spelutveckling/da211t/src/labC/varor.txt"); 
        lista();
    }

    public static void main(String[] args)
    {
        Uppgift4 jao = new Uppgift4();
        //jao.uppgift4_1();
        jao.uppgift4_2();
    }
}
