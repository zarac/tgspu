package laboration13;

import java.util.ArrayList;

public class Uppgift13a
{
    public void program()
    {
        Population[] countries = Populations.readPopulations("z:/me/studies/mah-spelutveckling/da211t/static/laboration13/befolkning.txt");

        for(int i = 0; i < countries.length; i++)
            System.out.println(countries[i].getCountry() + ", " + countries[i].getPopulation());
    }


    /*
0.   Skriv ut samtliga Population-objekt med hjälp av toString-metoden. (se lösning ovan. Testkör 
     den innan du går vidare.) 
     */
    public void uppgift13a0(Population[] countries)
    {
        for (Population country : countries)
            System.out.println(country.toString());
    }

    /*
1.   Skriv ut samtliga länder (dock ej ländernas invånare). 
     Ledning: Använd getCountry-metoden för att erhålla landet, dvs 
     länder[ i ].getCountry(); 
     */
    public void uppgift13a1(Population[] countries)
    {
        for (Population country : countries)
            System.out.println(country.getCountry());
    }

    /*
2.   Skriv ut land + invånare för de länder där antalet invånare överstiger 100 miljoner. (11 st) 
     Ledning: Tilldela variabeln invånare värdet av antalet invånare: 
     long invånare = länder[ i ].getPopulation(); 
     */
    public void uppgift13a2(Population[] countries)
    {
        for (Population country : countries)
            if (country.getPopulation() > 100000000)
                System.out.println(country.toString());
    }

    /*
3.   Skriv ut de länder som börjar på bokstaven M (18 st). 
     Ledning: Tilldela variabeln land värdet vid anrop av getCountry()-metoden. 
     land = länder[ i ].getCountry(); 
     Med metoden charAt kan du få det första tecknet: 
     char förstaBokstaven = land.charAt( 0 ); 
     */
    public void uppgift13a3(Population[] countries)
    {
        for (Population country : countries)
            if (country.getCountry().charAt(0) == 'M')
                System.out.println(country.toString());
    }

    /*
4.   Skriv ut alla länder och invånare då länderna har 8-10 miljoner invånare. (12 st) 
     */
    public void uppgift13a4(Population[] countries)
    {
        for (Population country : countries)
            if (country.getPopulation() > 8000000 && country.getPopulation() < 10000000)
                System.out.println(country.toString());
    }

    /*
5.   Beräknar och skriver ut antalet länder som har mindre än 1 miljon invånare, t.ex. 
     AA länder har mindre än 1 miljon invånare 
     där AA ersätts med antalet länder. 
     */
    public void uppgift13a5(Population[] countries)
    {
        int numCountriesLessThan1Million = 0;

        for (Population country : countries)
            if (country.getPopulation() < 1000000)
                numCountriesLessThan1Million++;

        System.out.println(numCountriesLessThan1Million + " har mindre än 1 miljon invånare.");
    }

    /*
6.   Beräknar och skriver ut antalet länder som börjar med bokstaven ’K’, t.ex. 
     AA länder börjar på bokstaven 'K' 
     */
    public void uppgift13a6(Population[] countries)
    {
        int numCountriesStartingWithK = 0;

        for (Population country : countries)
            if (country.getCountry().charAt(0) == 'K')
                numCountriesStartingWithK++;

        System.out.println(numCountriesStartingWithK + " börjar med bokstaven 'K'.");
    }

    /*
7.   Lagra alla Population-objekt med 10-12 miljoner invånare i en ny Population-array. Skriv 
     sedan ut innehållet i den nya arrayen. Gör så här: 
     •  Deklarera variablerna antal, index och invånare. De två första ska vara av typen int och 
        initieras till 0. invånare ska vara av typen long. 
     •  Skriv en for-loop som beräkna antalet Population-objekt med 10-12 miljoner invånare. Lagra 
        resultatet i variabeln antal. 
     •  Skapa en array med korrekt storlek: 
        Population[] nyArray = new Population[ antal ]; 
     •  Skriv en for-loop som lagrar över aktuella Population-objekt. Variabeln index ska hålla reda på 
        positionen i den nya arrayen: 
        for( int i = 0; i < länder.length; i++ ) { 
              invånare = länder[ i ].getPopulation(); 
              if( ( invånare >= 10000000 ) && ( invånare <= 12000000 ) ) { 
                    nyArray[ index ] = länder[ i ]; 
                    index++; 
              } 
         } 
     •  Skriv ut den nya arrayen med en for-loop. 
     */
    public void uppgift13a7(Population[] countries)
    {
        int amount = 0;
        int index = 0;
        //long population = 0;

        for (Population country : countries)
            if (country.getPopulation() > 10000000 && country.getPopulation() < 12000000)
                amount++;

        Population[] countriesFiltered = new Population[amount];

        for (Population country : countries)
            if (country.getPopulation() > 10000000 && country.getPopulation() < 12000000)
            {
                countriesFiltered[index] = country;
                index++;
            }

        for (Population country : countriesFiltered)
        {
            System.out.println(country.toString());
        }
    }

    /*
8.   Lagra alla Population-objekt där landet börjar på bokstaven ’K’ i en ny Population-array. Skriv 
     sedan ut innehållet i den nya arrayen. 
     */
    public void uppgift13a8(Population[] countries)
    {
        int numToFilter = 0;
        for (Population country : countries)
            if (country.getCountry().charAt(0) == 'K')
                numToFilter++;

        Population[] countriesFiltered = new Population[numToFilter];
        int index = 0;
        for (Population country : countries)
            if (country.getCountry().charAt(0) == 'K')
            {
                countriesFiltered[index] = country;
                index++;
            }

        for (Population country : countriesFiltered)
            System.out.println(country.toString());
    }

    /*
9.   Skriv ut samtliga länder (dock ej ländernas invånare). Använd en förenklad for-loop. 
     */
    public void uppgift13a9(Population[] countries)
    {
        for (Population country : countries)
        {
            System.out.println(country.getCountry());
        }
    }

    /*
10.  Beräknar och skriver ut antalet länder som har mindre än 1 miljon invånare, t.ex. 
     AA länder har mindre än 1 miljon invånare 
     där AA ersätts med antalet länder. Använd en förenklad for-loop. 
     */
    public void uppgift13a10(Population[] countries)
    {
        int numCountriesLessThan1Million = 0;

        for (Population country : countries)
            if (country.getPopulation() < 1000000)
                numCountriesLessThan1Million++;

        System.out.println(numCountriesLessThan1Million + " länder har mindre än 1 miljon invånare.");
    }


    public static void main(String[] args)
    {
        Uppgift13a jao = new Uppgift13a();
        //jao.program();

        Population[] countries = Populations.readPopulations("z:/me/studies/mah-spelutveckling/da211t/static/laboration13/befolkning.txt");

        //jao.uppgift13a0(countries);
        //jao.uppgift13a1(countries);
        //jao.uppgift13a2(countries);
        //jao.uppgift13a3(countries);
        //jao.uppgift13a4(countries);
        //jao.uppgift13a5(countries);
        //jao.uppgift13a6(countries);
        //jao.uppgift13a7(countries);
        //jao.uppgift13a8(countries);
        //jao.uppgift13a9(countries);
        jao.uppgift13a10(countries);
    }
}
