package labB;
//import labA.Foto;
/**
 *
 * @author Rolf
 */
public class DemoFotovisare {
    public static void main( String[] args ) {
        Foto f1 = new Foto("Spanska trappan i Rom", 2003, Foto.FAMILJ,"Z:/me/studies/mah-spelutveckling/da211t/src/labB/dr-evil.JPG");
        Foto f2 = new Foto("Höst", 2002, Foto.OVRIGT, "Z:/me/studies/mah-spelutveckling/da211t/src/labB/Evil_Monkey_301.gif");
        Fotovisare visare = new Fotovisare( ScaleIcon.SCALE );
        visare.setFoto( f1 );
        visare.setVisible( true );
        try {
            Thread.sleep( 5000 );
            visare.setFoto( f2 );
        } catch(InterruptedException e) {}
    }
}
