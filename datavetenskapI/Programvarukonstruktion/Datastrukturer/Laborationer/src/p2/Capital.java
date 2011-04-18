package p2;

class Capital
{
    HashTable<String,Country> byCountry;

    public Capital()
    {
        byCountry = new HashTable<String,Country>();
    }

    public void add(String country, String capital)
    {
        byCountry.put(country, new Country(country, capital));
    }

    public static void main(String[] argv)
    {
        Capital capital = new Capital();
        capital.add("Country1", "Capital1");
        capital.add("Country2", "Capital2");
        capital.add("Country3", "Capital3");
        capital.add("Country4", "Capital4");
    }
}
