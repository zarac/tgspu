package p2;

class Country
{
    public String name;
    public String capital;

    public Country(String name, String capital)
    {
        this.name = name;
        this.capital = capital;
    }

    public String toString()
    {
        return "Country: name='" + name + "', capital='" + capital + "'";
    }
}
