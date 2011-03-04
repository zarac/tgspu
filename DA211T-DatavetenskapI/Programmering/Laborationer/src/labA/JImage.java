package labA;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

/**
 * Komponenten visar en bild. Bilden kan visas p� tre s�tt: <br>
 * JImage.NORMAL - normal storlek p� bilden <br>
 * JImage.FIT - bilden fyller hela komponenten <br>
 * JImage.SCALE - bilden nerskalad s� att hela bilden ryms i komponenten
 * <p>Company: Malm� h�gskola</p>
 * @author Rolf Axelsson
 * @version 1.0
 */
public class JImage extends JLabel {
  private ImageIcon image;
  private int state;
  public static final int NORMAL=0,FIT=1,SCALE=2;

  /**
   * En komponent utan bild skapas
   */
  public JImage() {
    this(null);
  }

  /**
   * En komponent skapas och bifogad bild visas i normal storlek, dvs JImage.NORMAL
   * @param image Bilden som ska visas i komponenten
   */
  public JImage(ImageIcon image) {
    this(image,NORMAL);
  }

  /**
   * En komponent skapas och bifogad bild visas p� angivet s�tt. <p>
   * JImage.NORMAL - bilden visas i normal storlek <br>
   * JImage.FIT - bilden t�cker hela komponenten <br>
   * JImage.SCALE - bilden skalas om s� att hela bilden ryms i komponenten
   * @param image Bilden som ska visas i komponenten
   * @param state S�ttet som bilden ska visas p�
   */
  public JImage(ImageIcon image, int state) {
    this.state = state;
    setImage(image);
  }

  /**
   * Bilden visas p� angivet s�tt. <p>
   * JImage.NORMAL - bilden visas i normal storlek <br>
   * JImage.FIT - bilden t�cker hela komponenten <br>
   * JImage.SCALE - bilden skalas om s� att hela bilden ryms i komponenten
   * @param state S�ttet som bilden ska visas p�
   */
  public void setState(int state) {
    this.state = state;
    repaint();
  }

  /**
   * Returnerar s�ttet som bilden visas p�. f�ljande v�rden �r m�jliga: <p>
   * JImage.NORMAL - bilden visas i normal storlek <br>
   * JImage.FIT - bilden t�cker hela komponenten <br>
   * JImage.SCALE - bilden skalas om s� att hela bilden ryms i komponenten
   * @return Returnerar s�ttet som bilden visas p�
   */
  public int getState() {
    return state;
  }

  /**
   * Anger den bild som ska visas i komponenten
   * @param image Bilden som ska visas i komponenten
   */
  public void setImage(ImageIcon image) {
    this.image = image;
    repaint();
  }

  /**
   * Returnerar referens till det ImageIcon-objekt som anv�nds av komponenten.
   * @return Referens till ImageIcon-objektet
   */
  public ImageIcon getImage() {
    return image;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if(image!=null && image.getImage()!=null)
      switch(state) {
        case NORMAL : g.drawImage(image.getImage(),0,0,null); break;
        case FIT    : g.drawImage(image.getImage(),0,0,getSize().width,getSize().height,null);  break;
        case SCALE  : drawScale(g);
      }
  }

  private void drawScale(Graphics g) {
    Image im = image.getImage();
    int compWidth = getWidth(), compHeight = getHeight(),
        imageWidth = im.getWidth(null), imageHeight = im.getHeight(null);
    double scaleFactor = Math.min((double)compWidth/imageWidth,
                                  (double)compHeight/imageHeight);
    int x = (int)((compWidth - imageWidth*scaleFactor)/2);
    int y = (int)((compHeight - imageHeight*scaleFactor)/2);
    g.drawImage(im,x,y,(int)(imageWidth*scaleFactor),(int)(imageHeight*scaleFactor),null);
  }
}
