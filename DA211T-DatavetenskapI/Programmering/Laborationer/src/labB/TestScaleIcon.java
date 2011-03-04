package labB;
import javax.swing.*;
import java.awt.*;

/*
Programmet visar hur man skapar ett ScaleIcon-objekt vilket placeras som en Icon i t.ex. en JLabel eller en JButton.
 
Tredje och fjärde raden i main-metoden skapar ett ScaleIcon-objekt. En bild läses in från hårddisken och visningssättet av bilden anges. 
Ändra till en bild som finns på din hårddisk. Bildformatet ska var jpg, gif eller png.
Bilden kan visas på tre sätt:
 * ScaleIcon.SCALE - bilden skalas om så att hela bilden syns. Och bilden görs så stor som möjligt. Proportionerna i bilden behålls.
 * ScaleIcon.FIT - Bilden skalas om till storleken på komponenten som visar bilden (t.ex. JLabel eller JButton)
 * ScaleIcon.NORMAL - Bilden visas i normal storlek
 
För att visa en bild behöver du alltså:
1. Skapa ett ScaleIcon-objekt där du anger filens namn (fullständig sökväg) och visningssätt som argument
3. Placera ScaleIcon-objektet i en komponent, t.ex. JLabel eller JButton
 
Nödvändiga filer för kompilering:
 * TestScaleIcon.java
 * ScaleIcon.java
Båda filerna ska placeras i paketet projekt.
 
Testkörning:
Starta programmet med ScaleIconTest. När ett fönster innehållande en bild visar sig så ändra storlek på fönstret. 
Du ser då hur bildens storlek förändras. Men bildens proportioner behålls.
Efter 10 sekunder visas en ny bild i visningsläget FIT, dvs hela JLabel-lomponenten fylls av bilden. Bildens proportioner
skalas om så att hela bilden syns i JLabel-komponenten.
*/

public class TestScaleIcon {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("SCALE");
        Container c = frame.getContentPane();
        ScaleIcon image1 = new ScaleIcon("Z:/me/studies/mah-spelutveckling/da211t/src/labB/555-a-lesser-evil.jpg", ScaleIcon.SCALE); // Ersätt med bild på din hårddisk
        ScaleIcon image2 = new ScaleIcon("Z:/me/studies/mah-spelutveckling/da211t/src/labB/14702-Evil-And-Greedy-Devil-Smoking-And-Grinning-Black-And-White-Clipart-Illustration.jpg", ScaleIcon.FIT); // Ersätt med bild på din hårddisk
        //ScaleIcon image1 = new ScaleIcon("C:/bilder/IMGP2623.JPG", ScaleIcon.SCALE); // Ersätt med bild på din hårddisk
        //ScaleIcon image2 = new ScaleIcon("C:/bilder/IMGP3366.JPG", ScaleIcon.FIT); // Ersätt med bild på din hårddisk
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
