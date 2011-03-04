package labA;
import javax.swing.*;
import java.awt.*; 

/*
Programmet visar hur man skapar ett JImage-objekt vilket placeras i en container (h�r JFrame). JImage-objektet visar en bild. Denna bild skalas om s� att hela bilden syns i komponenten. Alla proportioner beh�lls.

Tredje raden i main-metoden l�ser in en bild fr�n h�rddisken. �ndra till en bild som finns p� din h�rddisk. Bildformatet ska var jpg, gif eller png.

Fj�rde raden i main-metoden skapar ett JImage-objekt. Det f�rsta argumentet till konstruktorn �r den inl�sta bilden och det andra argumentet avg�r det s�tt som bilden visas p�. Bilden kan visas p� tre s�tt:
* JImage.SCALE - bilden skalas om s� att hela bilden syns. Proportionerna i bilden beh�lls.
* JImage.FIT - Bilden skalas om till JImage-komponentens storlek
* JImage.NORMAL - Bilden visas i normal storlek

F�r att visa en bild beh�ver du allts�:
1. Skapa ett ImageIcon-objekt d�r du anger filens namn + fullst�ndig s�kv�g som argument
2. Skapa ett JImage-objekt med ImageIcon-objektet i punkt 1 som input. Du b�r �ven ange p� vilket s�tt bilden ska visas (se ovan)

N�dv�ndiga filer f�r kompilering:
* JImageTest.java
* JImage.java
B�da filerna ska placeras i paketet projekt.

Testk�rning:
Starta programmet med JImageTest. N�r ett f�nster inneh�llande en bild visar sig s� �ndra storlek p� f�nstret. Du ser d� hur bildens storlek f�r�ndras.

Ett JImage-objekt kan placeras i en Container p� samma s�tt som andra grafiska komponenter.
*/

public class JImageTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame("SCALE"); 
        Container c = frame.getContentPane();
        ImageIcon imageIcon = new ImageIcon("C:/bilder/London06.jpg"); // Ers�tt med bild p� din h�rddisk
        JImage image = new JImage(imageIcon, JImage.SCALE); // Testa �ven JImage.FIT och JImage.NORMAL
        c.add(image);
        frame.setSize(500,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
