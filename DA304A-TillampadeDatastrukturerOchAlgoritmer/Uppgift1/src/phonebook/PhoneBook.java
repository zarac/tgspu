package phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PhoneBook
{
    AVLTree<PhoneBookEntry> byName;
    AVLTree<PhoneBookEntry> byNumber;

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
        //AVLTreeNode node = byName.find(key);
        //PhoneBookEntry entry = byName.find(key);
        return byName.find(key);
    }

    public PhoneBookEntry findByNumber(String key)
    {
        return byNumber.find(key);
    }

    public void dumpByName()
    {
        AVLTreeNode<PhoneBookEntry> node = byName.getFirst();
        while (node != null)
        {
            System.out.println("NODE VALUE = " + ((PhoneBookEntry)byName.pointer.value).getName());
            System.out.println("HEIGHT = " + byName.pointer.height);
            node = ((AVLTree<PhoneBookEntry>)byName).getNext();
        }
            
    }

    public void loadFromDisk(String filepath)
    {
        try
        {
            FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // assuming csv file: name,number
            String line = bufferedReader.readLine();
            while (line != null) 
            {
                String[] parts = line.split(",");
                add(new PhoneBookEntry(parts[0], parts[1]));
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    }

    static public void saveToDisk(String filepath, AVLTree<PhoneBookEntry> tree)
    {
        System.out.println("saveToDisk()");
        try
        {
            FileWriter fileWriter = new FileWriter(filepath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //FileOutputStream fileOutputStream = new FileOutputStream(filepath);
            //BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            //DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);

            AVLTreeNode<PhoneBookEntry> node = ((AVLTree<PhoneBookEntry>)tree).getFirst();
            while (node != null)
            {
                bufferedWriter.write(node.value.getName() + "," + node.value.getNumber());
                bufferedWriter.newLine();
                node = ((AVLTree<PhoneBookEntry>)tree).getNext();
            }

            bufferedWriter.close();
            //dataOutputStream.close();
        }
        catch (IOException e)
        {
            System.out.println("[ERROR] Exception: " + e);
        }
    }

    public void saveByName(String filepath)
    {
        saveToDisk(filepath, byName);
    }

    public void saveByNumber(String filepath)
    {
        saveToDisk(filepath, byNumber);
    }

    public static void main(String[] args)
    {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.loadFromDisk("blah.txt");

        //phoneBook.add(new PhoneBookEntry("Hannes", "0739-903348"));
        //phoneBook.add(new PhoneBookEntry("Shwan", "0707-133337"));
        //phoneBook.add(new PhoneBookEntry("Anders", "0703-133337"));
        phoneBook.add(new PhoneBookEntry("Kalle", "0705-133337"));
        //phoneBook.add(new PhoneBookEntry("Mongo", "0701-133337"));
        //phoneBook.add(new PhoneBookEntry("Neger", "0700-133337"));

        phoneBook.dumpByName();

        //System.out.println(phoneBook.findByName("Mongo").name);
        System.out.println(phoneBook.findByName("Kalle").name);
        System.out.println(phoneBook.removeByName("Kalle"));

        phoneBook.saveByName("blah.txt");

        System.out.println(((AVLTree<PhoneBookEntry>)(phoneBook.byName)).getFirst().key);
        System.out.println(((AVLTree<PhoneBookEntry>)(phoneBook.byNumber)).getFirst().key);
    }
}
