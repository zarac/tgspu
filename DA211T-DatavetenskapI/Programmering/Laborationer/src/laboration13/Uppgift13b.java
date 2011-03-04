package laboration13;

public class Uppgift13b
{
    public void skrivLander(Population[] countries)
    {
        for (Population country : countries)
            System.out.println(country.getCountry());
    }


    public void merAn100000000(Population[] countries)
    {
        for (Population country : countries)
            if (country.getPopulation() > 100000000)
                System.out.println(country.toString());
    }


    public void landerSomBorjarMedM(Population[] countries)
    {
        for (Population country : countries)
            if (country.getCountry().charAt(0) == 'M')
                System.out.println(country.toString());
    }


    public void landerMed8Till10MiljonerInvanare(Population[] countries)
    {
        for (Population country : countries)
            if (country.getPopulation() > 8000000 && country.getPopulation() < 10000000)
                System.out.println(country.toString());
    }


    public static void main(String[] args)
    {
        Population[] countries = Populations.readPopulations("z:/me/studies/mah-spelutveckling/da211t/static/laboration13/befolkning.txt");

        Uppgift13b jao = new Uppgift13b();

        System.out.println();
        System.out.println();
        System.out.println("skrivLander");
        jao.skrivLander(countries);
        System.out.println();
        System.out.println();
        System.out.println("merAn100000000");
        jao.merAn100000000(countries);
        System.out.println();
        System.out.println();
        System.out.println("landerSomBorjarMedM");
        jao.landerSomBorjarMedM(countries);
        System.out.println();
        System.out.println();
        System.out.println("landerMed8Till10MiljonerInvanare");
        jao.landerMed8Till10MiljonerInvanare(countries);
    }
}
