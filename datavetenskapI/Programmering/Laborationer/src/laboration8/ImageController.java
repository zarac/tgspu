package laboration8;
import javax.swing.ImageIcon;
import laboration7.PaintWindow;

public class ImageController {
    private PaintWindow window;
    private ImageIcon image;
    private int x;
    private int y;

    public ImageController(PaintWindow window, ImageIcon image, int x, int y) {
        this.window = window;
        this.image = image;
        this.x = x;
        this.y = y;
        this.window.showImage(image, x, y);
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        this.window.showImage(image, x, y);
    }

    /**
     * Metoden flyttar bilden en bildbredd till v�nster. Bilden flyttas aldrig 
     * f�rbi v�nster f�nsterkant.
     */
    public void left() {
        int imageWidth = image.getIconWidth();  // Bildens bredd
        if (this.x > imageWidth) {  // om bildens x-koordinat �r st�rre �n bildens bredd
            move(-imageWidth, 0);   //     flytta hel bildbredd
        } else {                    // annars
            move(-this.x, 0);       // flytta lika l�ngt som bilden �r till h�ger om f�nstrets v�nsterkant
        }
    }

    public void right() {
    }

    public void up() {
    }

    /**
     * Metoden flyttar bilden en bildh�jd ned�t i f�nstret. Bilden flyttas
     * aldrig f�rbi f�nstrets nedre kant.
     */
    public void down() {
        int imageHeight = image.getIconHeight();  // Bildens h�jd
        int maxY = window.getBackgroundHeight() - imageHeight;  // St�rsta y-v�rde som bilden kan ha och �nd� vara helt synlig
        if (imageHeight < maxY - this.y) {  // om det g�r att flytta hel bildh�jd  
            move(0, imageHeight);           //   flytta hel bildh�jd  
        } else {                            // annars
            move(0, maxY - this.y);         //   flytta s� att bildens y-koordinat blir samma som maxY
        }
    }

    /**
     * Metoden flyttar bilden s� att den �r i kant med v�nster f�nsterkant.
     * Bilden flyttas med en bildbredd �t g�ngen. Mellan varje flytt pausar 
     * programmet 0,5 sekunder
     */
    public void leftBorder() {
        int steps = 1 + this.x / image.getIconWidth(); // antal steg som ska flyttas
        for(int i=0; i<steps; i++) {  // flytta ber�knat antal steg
            PaintWindow.pause(500);   // pausa 500 ms = 0,5 sek
            left();                   // flytta en bildbredd �t v�nster (eller s� l�ngt det g�r)
        }
    }

    public void rightBorder() {
    }

    public void upBorder() {
    }

    /**
     * Metoden flyttar bilden s� att den �r i kant med �vre f�nsterkant.
     * Bilden flyttas med en bildh�jd �t g�ngen. Mellan varje flytt pausar 
     * programmet 0,5 sekunder
     */
    public void downBorder() {
        int steps = 1 + (window.getBackgroundHeight()-this.y) / image.getIconHeight(); // antal steg som ska flyttas
        for(int i=0; i<steps; i++) {  // flytta ber�knat antal steg
            PaintWindow.pause(500);   // pausa 500 ms = 0,5 sek
            down();                   // flytta en bildh�jd upp�t (eller s� l�ngt det g�r)
        }
    }
}
