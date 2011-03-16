package phonebook;

public class PhoneBookEntry
{
    String name;
    String number;

    public PhoneBookEntry(String name, String number)
    {
        this.name = name;
        this.number = number;
    }

    /**
     * Gets the name for this instance.
     *
     * @return The name.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Sets the name for this instance.
     *
     * @param name The name.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the number for this instance.
     *
     * @return The number.
     */
    public String getNumber()
    {
        return this.number;
    }

    /**
     * Sets the number for this instance.
     *
     * @param number The number.
     */
    public void setNumber(String number)
    {
        this.number = number;
    }
}
