package p2;

import javax.swing.ImageIcon;

public class Car {
    private ImageIcon image;
    private int x;
    private int y;

    public Car(ImageIcon image) {
        this.image = image;
    }
    
    public ImageIcon getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
