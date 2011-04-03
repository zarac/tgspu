package laboration1;

import javax.swing.*;
import java.util.*;
import java.io.*;

import javax.swing.JOptionPane;

public class Uppgift6 {
    private HashMap<String, String> dictonary = new HashMap<String, String>();
    
    public Uppgift6(String fileName) {
        readDictonary(fileName);
    }

    private void readDictonary(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String[] parts;
            String english, swedish;
            String txt = br.readLine();
            while (txt != null) {
                parts = txt.split(",");
                english = parts[2];
                swedish = parts[1];
                dictonary.put(english, swedish);
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
        String word = JOptionPane.showInputDialog("Mata in ett engelskt ord.");
        
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
        Uppgift6 upp6 = new Uppgift6("src/laboration1/SkSvEn.txt");
        String[] menuStrings = {"Översätt ord", "Skriv ut ordlista"};
        int choice = Uppgift6.menu(menuStrings);
        while (choice != 0) {
            switch (choice) {
                case 1:
                    upp6.translate();
                    break;
                case 2:
                    upp6.list();
                    break;
            }
            choice = Uppgift6.menu(menuStrings);
        }
    }
}
