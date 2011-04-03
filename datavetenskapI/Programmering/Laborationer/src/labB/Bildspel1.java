package labB;

public class Bildspel1 implements Runnable
{
    private Album album;
    private Thread thread;
    private Fotovisare visare;
    private long delay;
    private int bildNr = 0;

    public Bildspel1( Album inAlbum, long inDelay ) 
    { 
        album = inAlbum;  // Lagra Album-objektet i variabeln album 
        delay = inDelay;  // Lagra visningstid av bild i delay 
        visare = new Fotovisare( ScaleIcon.SCALE );  // Skapa Fotovisare 
        // Hämta Foto-objekt ur album (foto nr 0) och visa det i Fotovisaren 
        visare.setFoto( album.hamtaFoto( bildNr ) );   
        visare.setVisible( true );  // göra Fotovisaren synlig 

        thread = new Thread(this);
        thread.start();
    } 

    public void run()
    {
        while (true)
        {
            try
            {
                Thread.sleep(delay);
                bildNr = (bildNr + 1) % album.antalFoto();
                visare.setFoto(album.hamtaFoto(bildNr));
            }
            catch (InterruptedException e)
            {
            }
        }
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
        Bildspel1 prog = new Bildspel1( album, 3000 );
    }
}
