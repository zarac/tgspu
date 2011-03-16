package phonebook;

public class PhoneBook
{
    Dictionary<PhoneBookEntry> byName;
    Dictionary<PhoneBookEntry> byNumber;

    public PhoneBook()
    {
        byName = new AVLTree<PhoneBookEntry>();
        byNumber = new AVLTree<PhoneBookEntry>();
    }

    public void add(PhoneBookEntry entry)
    {
        byName.add(entry.getName(), entry);
        byNumber.add(entry.getNumber(), entry);
    }

    public PhoneBookEntry removeByName(String key)
    {
        PhoneBookEntry entry = byName.remove(key);

        if (entry == null)
            return null;

        return byNumber.remove(entry.getNumber());
    }

    public PhoneBookEntry removeByNumber(String key)
    {
        PhoneBookEntry entry = byNumber.remove(key);

        if (entry == null)
            return null;

        return byName.remove(entry.getName());
    }

    public PhoneBookEntry findByName(String key)
    {
        return byName.find(key);
    }

    public PhoneBookEntry findByNumber(String key)
    {
        return byNumber.find(key);
    }

    public void loadFromDisk(String filepath)
    {
    }

    public void saveToDisk(String filepath)
    {
    }

    public static void main(String[] args)
    {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add(new PhoneBookEntry("Hannes", "0739-903348"));
        phoneBook.add(new PhoneBookEntry("Shwan", "0707-133337"));
        phoneBook.add(new PhoneBookEntry("Anders", "0703-133337"));
        phoneBook.add(new PhoneBookEntry("Kalle", "0705-133337"));
        phoneBook.add(new PhoneBookEntry("Mongo", "0701-133337"));
        phoneBook.add(new PhoneBookEntry("Neger", "0700-133337"));

        System.out.println(((AVLTree<PhoneBookEntry>)(phoneBook.byName)).getFirst().key);
        System.out.println(((AVLTree<PhoneBookEntry>)(phoneBook.byNumber)).getFirst().key);
    }
}
