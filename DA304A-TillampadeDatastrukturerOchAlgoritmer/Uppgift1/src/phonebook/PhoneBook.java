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

    String defaultFile = "blah.txt";

    GUI gui;

    public PhoneBook()
    {
        byName = new AVLTree<PhoneBookEntry>();
        byNumber = new AVLTree<PhoneBookEntry>();

        gui = new GUI(this);
        loadFromDisk(defaultFile);
        this.showAll();
    }

    public void addRandom()
    {
        int random = (int)(Math.random() * 1000000000);
        String number = Integer.toString(random);
        String name = Integer.toHexString(random);
        add(new PhoneBookEntry(name, number));
    }
    public void add(PhoneBookEntry entry)
    {
        if (byName.find(entry.getName()) != null)
        {
            gui.log.append("Key '" + entry.getName() + "' already exists in name index.\n");
            gui.display.repaint();
            return;
        }
        else if (byNumber.find(entry.getNumber()) != null)
        {
            gui.log.append("Key '" + entry.getNumber() + "' already exists in number index.\n");
            gui.display.repaint();
            return;
        }

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

    public void allByName()
    {
        gui.list.setText("    * All By Name *\n");

        AVLTreeNode<PhoneBookEntry> node = byName.getFirst();
        while (node != null)
        {
            gui.list.append(node.value.getName() + " - " + node.value.getNumber() + "\n");
            node = byName.getNext(node);
        }
    }

    public void allByNumber()
    {
        gui.list.setText("    * All By Number *\n");

        AVLTreeNode<PhoneBookEntry> node = byNumber.getFirst();
        while (node != null)
        {
            gui.list.append(node.value.getName() + " - " + node.value.getNumber() + "\n");
            node = byNumber.getNext(node);
        }
    }

    public void loadFromDisk(String filepath)
    {
        // clear current
        byName.root = null;
        byNumber.root = null;

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

    public void showAll()
    {
        System.out.println("showAll():");
        gui.list.setText("");
        AVLTreeNode<PhoneBookEntry> node = byName.getFirst();
        while (node != null)
        {
            System.out.println("node.toString():" + node.toString());
            gui.list.append(node.value.toString() + "\n");
            node = byName.getNext(node);
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

            AVLTreeNode<PhoneBookEntry> node = tree.getFirst();
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
        new PhoneBook();
    }
}
