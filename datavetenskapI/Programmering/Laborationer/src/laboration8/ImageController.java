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
     * Metoden flyttar bilden en bildbredd till vänster. Bilden flyttas aldrig 
     * förbi vänster fönsterkant.
     */
    public void left() {
        int imageWidth = image.getIconWidth();  // Bildens bredd
        if (this.x > imageWidth) {  // om bildens x-koordinat är större än bildens bredd
            move(-imageWidth, 0);   //     flytta hel bildbredd
        } else {                    // annars
            move(-this.x, 0);       // flytta lika långt som bilden är till höger om fönstrets vänsterkant
        }
    }

    public void right() {
    }

    public void up() {
    }

    /**
     * Metoden flyttar bilden en bildhöjd nedåt i fönstret. Bilden flyttas
     * aldrig förbi fönstrets nedre kant.
     */
    public void down() {
        int imageHeight = image.getIconHeight();  // Bildens höjd
        int maxY = window.getBackgroundHeight() - imageHeight;  // Största y-värde som bilden kan ha och ändå vara helt synlig
        if (imageHeight < maxY - this.y) {  // om det går att flytta hel bildhöjd  
            move(0, imageHeight);           //   flytta hel bildhöjd  
        } else {                            // annars
            move(0, maxY - this.y);         //   flytta så att bildens y-koordinat blir samma som maxY
        }
    }

    /**
     * Metoden flyttar bilden så att den är i kant med vänster fönsterkant.
     * Bilden flyttas med en bildbredd åt gången. Mellan varje flytt pausar 
     * programmet 0,5 sekunder
     */
    public void leftBorder() {
        int steps = 1 + this.x / image.getIconWidth(); // antal steg som ska flyttas
        for(int i=0; i<steps; i++) {  // flytta beräknat antal steg
            PaintWindow.pause(500);   // pausa 500 ms = 0,5 sek
            left();                   // flytta en bildbredd åt vänster (eller så långt det går)
        }
    }

    public void rightBorder() {
    }

    public void upBorder() {
    }

    /**
     * Metoden flyttar bilden så att den är i kant med övre fönsterkant.
     * Bilden flyttas med en bildhöjd åt gången. Mellan varje flytt pausar 
     * programmet 0,5 sekunder
     */
    public void downBorder() {
        int steps = 1 + (window.getBackgroundHeight()-this.y) / image.getIconHeight(); // antal steg som ska flyttas
        for(int i=0; i<steps; i++) {  // flytta beräknat antal steg
            PaintWindow.pause(500);   // pausa 500 ms = 0,5 sek
            down();                   // flytta en bildhöjd uppåt (eller så långt det går)
        }
    }
}
