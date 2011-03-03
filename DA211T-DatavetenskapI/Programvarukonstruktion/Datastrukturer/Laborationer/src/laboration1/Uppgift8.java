package laboration1;

import javax.swing.*;
import java.util.*;
import java.io.*;

import javax.swing.JOptionPane;

public class Uppgift8 {
    private TreeMap<String, String> dictonary = new TreeMap<String, String>();
    
    public Uppgift8(String fileName) {
        readDictonary(fileName);
    }

    private void readDictonary(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String[] parts;
            String skoenska, english, swedish;
            String txt = br.readLine();
            while (txt != null) {
                parts = txt.split(",");
                english = parts[2];
                swedish = parts[1];
                skoenska = parts[0];
                dictonary.put(swedish, skoenska);
                txt = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("läsPersoner: " + e);
        }
    }

    public void list() {
        String strList = dictonary.toString(); // format: "{ eng1=swe1, eng2=swe2, ...}"
        strList = strList.substring(1, strList.length() - 1);  // ta bort { och }
        String[] parts = strList.split(",");

        System.out.println("------------------------------------------------");
        for (String str : parts) {
            System.out.println(str);
        }
        System.out.println("------------------------------------------------");
    }

    public void translate() {
        String word = JOptionPane.showInputDialog("Mata in ett svenskt ord.");
        
        String translation = dictonary.get(word);

        if (translation== null)
            JOptionPane.showMessageDialog(null, "FINNS INTE!");
        else
            JOptionPane.showMessageDialog(null, word + " " + translation);
    }

    public static int menu(String[] choices) {
        int res = 0;
        String input;
        String message = "VÄLJ ETT ALTERNATIV\n";
        for (int i = 0; i < choices.length; i++) {
            message += "\n" + (i + 1) + ". " + choices[i];
        }

        do {
            try {
                input = JOptionPane.showInputDialog(message);
                if (input == null) {
                    return 0;
                }
                res = Integer.parseInt(input);
            } catch (NumberFormatException e) {
            }
        } while (res < 1 || res > choices.length);

        return res;
    }

    public static void main(String[] args) {
        Uppgift8 upp8 = new Uppgift8("src/laboration1/SkSvEn.txt");
        String[] menuStrings = {"Översätt ord", "Skriv ut ordlista"};
        int choice = Uppgift8.menu(menuStrings);
        while (choice != 0) {
            switch (choice) {
                case 1:
                    upp8.translate();
                    break;
                case 2:
                    upp8.list();
                    break;
            }
            choice = Uppgift8.menu(menuStrings);
        }
    }
}
