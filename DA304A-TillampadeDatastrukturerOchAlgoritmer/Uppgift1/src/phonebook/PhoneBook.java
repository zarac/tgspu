package phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Random;

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

    public PhoneBookEntry removeByName(String key)
    {
        //System.out.println("removeByName():");
        PhoneBookEntry entry = byName.remove(key);

        if (entry == null)
            return null;

        return byNumber.remove(entry.getNumber());
    }

    public PhoneBookEntry removeByNumber(String key)
    {
        //System.out.println("removeByNumber():");
        PhoneBookEntry entry = byNumber.remove(key);

        if (entry == null)
            return null;

        return byName.remove(entry.getName());
    }

    public PhoneBookEntry findByName(String key)
    {
        //System.out.println("findByName():");
        return byName.find(key);
    }

    public PhoneBookEntry findByNumber(String key)
    {
        //System.out.println("findByNumber():");
        return byNumber.find(key);
    }

    public void allByName()
    {
        //System.out.println("allByName():");
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
        //System.out.println("allByNumber():");
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
        //System.out.println("loadFromDisk():");
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
        //System.out.println("showAll():");
        gui.list.setText("");
        AVLTreeNode<PhoneBookEntry> node = byName.getFirst();
        while (node != null)
        {
            //System.out.println("node.toString():" + node.toString());
            gui.list.append(node.value.toString() + "\n");
            node = byName.getNext(node);
        }
    }

    static public void saveToDisk(String filepath, AVLTree<PhoneBookEntry> tree)
    {
        //System.out.println("saveToDisk()");
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

    public String randomString(int len) 
    {
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String ab = "abcdefghijklmnopqrstuvwxyz";
        //String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        sb.append(AB.charAt(rnd.nextInt(AB.length())));
        for(int i = 1;i < len; i++) 
            sb.append(ab.charAt(rnd.nextInt(ab.length())));
        return sb.toString();
    }

    public void addRandom()
    {
        // keep trying if duplicate
        while (add(new PhoneBookEntry(
                        randomString(9),
                        Long.toString((long)(Math.random() * Long.MAX_VALUE))))
                > 0)
        {
            // nothing...
        }
    }

    /**
     * @param entry
     * @return Returns 0 if all went OK, 1 if name exists and 2 if number exists.
     */
    public int add(PhoneBookEntry entry)
    {
        //System.out.println("add():");
        if (byName.find(entry.getName()) != null)
        {
            System.out.println("Key '" + entry.getName() + "' already exists in name index.\n");
            //gui.log.append("Key '" + entry.getName() + "' already exists in name index.\n");
            gui.display.repaint();
            return 1;
        }
        else if (byNumber.find(entry.getNumber()) != null)
        {
            System.out.println("Key '" + entry.getNumber() + "' already exists in number index.\n");
            //gui.log.append("Key '" + entry.getNumber() + "' already exists in number index.\n");
            gui.display.repaint();
            return 2;
        }

        byName.add(entry.getName().toLowerCase(), entry);
        byNumber.add(entry.getNumber().toLowerCase(), entry);

        return 0;
    }

    /**
     * @param filename
     * @param count
     * @return Information about the test done.
     */
    public SpeedTest generateTestFile(String filename, int size)
    {
        // save tree state
        AVLTreeNode<PhoneBookEntry> byNameRoot = byName.root;
        int byNameSize = byName.size;
        byName.root = null;
        byName.size = 0;

        AVLTreeNode<PhoneBookEntry> byNumberRoot = byNumber.root;
        int byNumberSize = byNumber.size;
        byNumber.root = null;
        byNumber.size = 0;

        SpeedTest test = new SpeedTest();

        long start = System.currentTimeMillis();

        for (int i = 1; i <= size; i++) 
            addRandom();

        // disabled root references for now, might be hungry for RAM
        //test.byNameRoot = byName.root;
        //test.byNumberRoot = byNumber.root;
        test.time = System.currentTimeMillis() - start;
        test.size = size;
        test.filename = filename;

        // restore tree state
        byName.root = byNameRoot;
        byName.size = byNameSize;

        byNumber.root = byNumberRoot;
        byNumber.size = byNumberSize;

        return test;
    }

    public static void main(String[] args)
    {
        new PhoneBook();
    }
}
