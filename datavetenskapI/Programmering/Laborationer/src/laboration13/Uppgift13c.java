package laboration13; 
 
public class Uppgift13c { 
     
    public int antalLanderMedMindreAn1000000Invanare( Population[] countries ) { 
        // Komplettera med kod 
        int numCountriesLessThan1Million = 0;

        for (Population country : countries)
            if (country.getPopulation() < 1000000)
                numCountriesLessThan1Million++;

        return numCountriesLessThan1Million;
        //System.out.println(numCountriesLessThan1Million + " har mindre än 1 miljon invånare.");
    } 
     
    public int antalLanderSomBorjarMedK( Population[] countries ) { 
        // Komplettera med kod 
        int numCountriesStartingWithK = 0;

        for (Population country : countries)
            if (country.getCountry().charAt(0) == 'K')
                numCountriesStartingWithK++;

        return numCountriesStartingWithK;
        //System.out.println(numCountriesStartingWithK + " börjar med bokstaven 'K'.");
    } 
     
    public Population[] getLanderMed10Till12MiljonerInvanare( Population[] countries ) { 
        // Komplettera med kod 
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

        return countriesFiltered;
        //for (Population country : countriesFiltered)
            //System.out.println(country.toString());
    } 
     
    public Population[] getLanderSomBorjarMedK( Population[] countries ) { 
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

        return countriesFiltered;
        //for (Population country : countriesFiltered)
            //System.out.println(country.toString());
    } 
     
    public void program() { 
        Population[] lander = Populations.readPopulations("z:/me/studies/mah-spelutveckling/da211t/static/laboration13/befolkning.txt");
        Population[] res; 
 
        // Aktivera testerna en i taget, men först när du kompletterat 
        // metoderna med kod. 
 
        // test antalLänderMedMindreÄn1000000Invånare 
       int antal = antalLanderMedMindreAn1000000Invanare( lander ); 
       System.out.println( antal + " länder har mindre än 1 miljon invånare"); 
 
        // antalLänderSomBörjarMedK 
       antal = antalLanderSomBorjarMedK( lander ); 
       System.out.println( antal + " länder börjar på bokstaven 'K'"); 
 
        // test getLänderMed10Till12MiljonerInvånare 
       res = getLanderMed10Till12MiljonerInvanare( lander ); 
       for( int i = 0; i < res.length; i++ ) { 
           System.out.println( res[ i ].toString() ); 
       } 
 
        // test getLänderSomBörjarMedK 
       res = getLanderSomBorjarMedK( lander ); 
       for( int i = 0; i < res.length; i++ ) { 
           System.out.println( res[ i ].toString() ); 
       } 
    } 
     
    public static void main( String[] args ) { 
        Uppgift13c u13c = new Uppgift13c(); 
        u13c.program(); 
    } 
} 
