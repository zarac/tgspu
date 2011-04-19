package p2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;

public class Capital
{
    protected HashTable<String,Country> byCountry;
    protected HashTable<String,Country> byCapital;

    public Capital(int size)
    {
        byCountry = new HashTable<String, Country>(size);
        byCapital = new HashTable<String, Country>(size);
    }

    public void add(Country country)
    {
        // TODO : ? Don't allow duplicates.
        byCountry.put(country.name.toLowerCase(), country);
        byCapital.put(country.capital.toLowerCase(), country);
    }

    public Country findByCountry(String key)
    {
        return byCountry.get(key.toLowerCase());
    }

    public Country findByCapital(String key)
    {
        return byCapital.get(key.toLowerCase());
    }

    public void loadFile(String path)
    {
        try
        {
            FileInputStream inputStream = new FileInputStream(path);
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            String line = bufferedReader.readLine();
            String[] parts;
            while(line != null)
            {
                parts = line.split(",");
                add(new Country(parts[0], parts[1]));
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public Country removeByCountry(String key)
    {
        Country country = byCountry.remove(key);
        if (country == null)
            return null;

        return byCapital.remove(country.capital);
    }

    public static void main(String[] argv)
    {
        Capital capital = new Capital(1000);

        System.out.println("Testing some...");
        capital.add(new Country("Country1", "Capital1"));
        capital.add(new Country("Country2", "Capital2"));
        capital.add(new Country("Country3", "Capital3"));
        capital.add(new Country("Country4", "Capital4"));
        System.out.println(capital.findByCountry("Country3"));
        System.out.println(capital.removeByCountry("Country2"));
        System.out.println(capital.removeByCountry("Country3"));
        System.out.println(capital.findByCountry("Country3"));
        System.out.println(capital.removeByCountry("Country4"));
        System.out.println(capital.removeByCountry("Country1"));
        System.out.println("Looks the HashTable works prima!");

        capital.loadFile("src/p2/capital.txt");

        new GUI(capital);
    }
}
