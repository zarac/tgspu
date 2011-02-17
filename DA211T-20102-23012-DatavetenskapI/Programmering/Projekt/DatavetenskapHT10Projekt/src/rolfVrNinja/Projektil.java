
package rolfVrNinja;


import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Projektil {

    private ImageIcon image;
    private int x;
    private int y;

    //Konstruktor
    public Projektil(String filename) {
        this.image = new ImageIcon(filename);
    }

    public Projektil(Image img) {
        this.image = new ImageIcon();
        image.setImage(img);
    }

    //Retunerar url
    private URL getURL(String filename){
        URL url = null;
        try{
            url = this.getClass().getResource(filename);
        }
        catch(Exception e){ }
        return url;
    }

    //Retunerar image
    public ImageIcon getImage() {
        return image;
    }

    //Retunerar x
    public int getX() {
        return x;
    }

    //Retunerar y
    public int getY() {
        return y;
    }

    //Sätter x och y
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }


}

