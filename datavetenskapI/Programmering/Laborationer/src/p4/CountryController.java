package p4;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CountryController
{
    private CountryViewer countryViewer;
    private CountryIO countryIO;
    private Country[] countries;

    public CountryController(CountryViewer p_countryViewer, CountryIO p_countryIO)
    {
        countryViewer = p_countryViewer;
        countryIO = p_countryIO;

        loadCountries();
        showAllCountries();
    }

    public void loadCountries()
    {
        System.out.println("loadCountries();");
        countries = countryIO.readCountries();
    }

    public void saveCountries()
    {
        System.out.println("saveCountries();");
        countryIO.saveCountries(countries);
    }

    public void showAllCountries()
    {
        System.out.println("showAllCountries();");
        countryViewer.showCountries(countries);
    }

    public void showSelection(long p_min, long p_max)
    {
        System.out.println("showSelection();");

        ArrayList<Country> filteredCountries = new ArrayList<Country>();
        //ArrayList filteredCountries = new ArrayList();

        for (Country country : countries)
        {
            if (country.getPopulation() >= p_min &&
                    country.getPopulation() <= p_max)
                filteredCountries.add(country);
        }

        countryViewer.showCountries((Country[])filteredCountries.toArray());
        //countryViewer.showCountries((Country[])filteredCountries.toArray(new Country[]{}));
        
        //return (Country[]) filteredCountries.toArray(
        
        //// Count countries that match filter, to find out size of array to make.
        //int numFiltered = 0;
        //for (Country country : countries)
        //{
            //if (country.getPopulation() >= p_min &&
                    //country.getPopulation() <= p_max)
                //numFiltered++;
        //}

        //// Create array with filtered countries.
        //Country[numFiltered] filteredCountires;
    }

    public void changePopulation(String p_countryName, long p_countryPopulation)
    {
        System.out.println("changePopulation();");
        for (Country country : countries)
        {
            // Why can i not compare two strings with ==? Shall i not be able to?
            //System.out.println("changePopulation() > country.getName()='" + country.getName() + "', p_countryName='" + p_countryName + "'");
            //if (country.getName() == p_countryName)
            if (country.getName().equals(p_countryName))
            {
                System.out.println("jao");
                country.setPopulation(p_countryPopulation);
                countryViewer.showCountries(countries);
                return;
            }
        }
        
        JOptionPane.showMessageDialog(null, p_countryName + " hittades inte! : (");
    }
}
