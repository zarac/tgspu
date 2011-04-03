package labB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Bildspel2 implements ActionListener
{
    private Album album;
    private Fotovisare visare;
    private int bildNr = 0;
    private Timer timer;

    public Bildspel2( Album inAlbum, int inDelay ) 
    { 
        album = inAlbum;  // Lagra Album-objektet i variabeln album 
        visare = new Fotovisare( ScaleIcon.SCALE );  // Skapa Fotovisare 
        // Hämta Foto-objekt ur album (foto nr 0) och visa det i Fotovisaren 
        visare.setFoto( album.hamtaFoto( bildNr ) );   
        visare.setVisible( true );  // göra Fotovisaren synlig 

        timer = new Timer(inDelay, this);
        timer.start();
    } 

    public void actionPerformed(ActionEvent e)
    {
        bildNr = (bildNr + 1) % album.antalFoto();
        visare.setFoto(album.hamtaFoto(bildNr));
    }

    public static void main(String[] argv)
    {
        Album album = new Album(); 
        Foto f1 = new Foto("På badstranden", 2003, Foto.FAMILJ,"Z:/me/studies/mah-spelutveckling/da211t/src/labB/555-a-lesser-evil.jpg"); 
        Foto f2 = new Foto("Utsikt", 2002, Foto.OVRIGT, "Z:/me/studies/mah-spelutveckling/da211t/src/labB/14702-Evil-And-Greedy-Devil-Smoking-And-Grinning-Black-And-White-Clipart-Illustration.jpg"); 
        Foto f3 = new Foto("Greklandskarta", 2005, Foto.RESOR, "Z:/me/studies/mah-spelutveckling/da211t/src/labB/dr-evil.JPG"); 
        Foto f4 = new Foto("Sommarfötter", 2002, Foto.BARNBARN, "Z:/me/studies/mah-spelutveckling/da211t/src/labB/Evil.gif"); 
        Foto f5 = new Foto("Glassdags", 2003, Foto.FAMILJ, "Z:/me/studies/mah-spelutveckling/da211t/src/labB/Evil_Monkey_301.gif"); 
        album.laggTill( f1 ); 
        album.laggTill( f2 ); 
        album.laggTill( f3 ); 
        album.laggTill( f4 ); 
        album.laggTill( f5 ); 
        new Bildspel2( album, 3000 );
    }
}
