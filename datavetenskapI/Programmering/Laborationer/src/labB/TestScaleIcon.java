package labB;
import javax.swing.*;
import java.awt.*;

/*
Programmet visar hur man skapar ett ScaleIcon-objekt vilket placeras som en Icon i t.ex. en JLabel eller en JButton.
 
Tredje och fj�rde raden i main-metoden skapar ett ScaleIcon-objekt. En bild l�ses in fr�n h�rddisken och visningss�ttet av bilden anges. 
�ndra till en bild som finns p� din h�rddisk. Bildformatet ska var jpg, gif eller png.
Bilden kan visas p� tre s�tt:
 * ScaleIcon.SCALE - bilden skalas om s� att hela bilden syns. Och bilden g�rs s� stor som m�jligt. Proportionerna i bilden beh�lls.
 * ScaleIcon.FIT - Bilden skalas om till storleken p� komponenten som visar bilden (t.ex. JLabel eller JButton)
 * ScaleIcon.NORMAL - Bilden visas i normal storlek
 
F�r att visa en bild beh�ver du allts�:
1. Skapa ett ScaleIcon-objekt d�r du anger filens namn (fullst�ndig s�kv�g) och visningss�tt som argument
3. Placera ScaleIcon-objektet i en komponent, t.ex. JLabel eller JButton
 
N�dv�ndiga filer f�r kompilering:
 * TestScaleIcon.java
 * ScaleIcon.java
B�da filerna ska placeras i paketet projekt.
 
Testk�rning:
Starta programmet med ScaleIconTest. N�r ett f�nster inneh�llande en bild visar sig s� �ndra storlek p� f�nstret. 
Du ser d� hur bildens storlek f�r�ndras. Men bildens proportioner beh�lls.
Efter 10 sekunder visas en ny bild i visningsl�get FIT, dvs hela JLabel-lomponenten fylls av bilden. Bildens proportioner
skalas om s� att hela bilden syns i JLabel-komponenten.
*/

public class TestScaleIcon {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("SCALE");
        Container c = frame.getContentPane();
        ScaleIcon image1 = new ScaleIcon("Z:/me/studies/mah-spelutveckling/da211t/src/labB/555-a-lesser-evil.jpg", ScaleIcon.SCALE); // Ers�tt med bild p� din h�rddisk
        ScaleIcon image2 = new ScaleIcon("Z:/me/studies/mah-spelutveckling/da211t/src/labB/14702-Evil-And-Greedy-Devil-Smoking-And-Grinning-Black-And-White-Clipart-Illustration.jpg", ScaleIcon.FIT); // Ers�tt med bild p� din h�rddisk
        //ScaleIcon image1 = new ScaleIcon("C:/bilder/IMGP2623.JPG", ScaleIcon.SCALE); // Ers�tt med bild p� din h�rddisk
        //ScaleIcon image2 = new ScaleIcon("C:/bilder/IMGP3366.JPG", ScaleIcon.FIT); // Ers�tt med bild p� din h�rddisk
        JLabel lbl = new JLabel();
        lbl.setIcon( image1 );
        c.add(lbl);
        frame.setSize(500,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        try {
            Thread.sleep( 10000 );
            image1.setState( ScaleIcon.NORMAL );
            Thread.sleep( 2000 );
            lbl.setIcon( image2 );
        } catch(InterruptedException e){}
    }
}
