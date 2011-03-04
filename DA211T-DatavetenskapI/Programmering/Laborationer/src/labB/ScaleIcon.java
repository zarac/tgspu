package labB;
import javax.swing.ImageIcon;
import java.net.URL;
import java.awt.*;

/**
 * ScaleIcon-objektet kapslar in en bild. Bilden kan visas på tre sätt: <br>
 * ScaleIcon.NORMAL - normal storlek på bilden <br>
 * ScaleIcon.FIT - bilden fyller hela komponenten <br>
 * ScaleIcon.SCALE - bilden nerskalad så att hela bilden ryms i komponenten
 * @author Rolf Axelsson
 * @version 1.0
 */
public class ScaleIcon extends ImageIcon {
    private int state;
    public static final int NORMAL=0,FIT=1,SCALE=2;
    Component parent;
    
    /**
     * Ett Icon-objekt skapas och bilden visas i normal storlek, dvs ScaleImage.NORMAL
     * @param filename Bildfilen som ska visas
     */
    public ScaleIcon( String filename ) {
        this( filename, ScaleIcon.NORMAL );
    }
    
    /**
     * Ett Icon-objekt skapas och bilden visas i normal storlek, dvs ScaleImage.NORMAL
     * @param url Bildfilen som ska visas
     */
    public ScaleIcon( URL url ) {
        this( url, ScaleIcon.NORMAL );
    }
    
    /**
     * Ett ScaleIcon-objekt skapas och bilden visas på angivet sätt. <p>
     * ScaleIcon.NORMAL - bilden visas i normal storlek <br>
     * ScaleIcon.FIT - bilden täcker hela komponenten som den visas i <br>
     * ScaleIcon.SCALE - bilden skalas om så att hela bilden ryms i komponenten
     * @param filename Bildfilen som ska visas
     * @param state Sättet som bilden ska visas på
     */
    public ScaleIcon( String filename, int state ) {
        super( filename );
        this.state = state;
    }
    
    /**
     * Ett ScaleIcon-objekt skapas och bilden visas på angivet sätt. <p>
     * ScaleIcon.NORMAL - bilden visas i normal storlek <br>
     * ScaleIcon.FIT - bilden täcker hela komponenten som den visas i <br>
     * ScaleIcon.SCALE - bilden skalas om så att hela bilden ryms i komponenten
     * @param url Bildfilen som ska visas
     * @param state Sättet som bilden ska visas på
     */
    public ScaleIcon( URL url, int state ) {
        super( url );
        this.state = state;
    }
    
    /**
     * Returnerar sättet som bilden visas på. följande värden är möjliga: <p>
     * JImage.NORMAL - bilden visas i normal storlek <br>
     * JImage.FIT - bilden täcker hela komponenten <br>
     * JImage.SCALE - bilden skalas om så att hela bilden ryms i komponenten
     * @return Returnerar sättet som bilden visas på
     */
    public int getState() {
        return state;
    }
    
    public void setState( int state ) {
        this.state = state;
        if( parent != null ) {
            parent.repaint();
        }
    }
    
    public void paintIcon( Component c, Graphics g, int x, int y ) {
        parent = c;
        if(this.getImage()!=null)
            switch(state) {
                case NORMAL : g.drawImage(this.getImage(), x, y, null); break;
                case FIT    : g.drawImage(this.getImage(), 0, 0, c.getWidth(), c.getHeight(),null);  break;
                case SCALE  : drawScale(c, g);
            }
    }
    
    private void drawScale(Component c, Graphics g) {
        Image im = this.getImage();
        int compWidth = c.getWidth(), compHeight = c.getHeight(),
                imageWidth = im.getWidth(null), imageHeight = im.getHeight(null);
        double scaleFactor = Math.min((double)compWidth/imageWidth,
                (double)compHeight/imageHeight);
        int x = (int)((compWidth - imageWidth*scaleFactor)/2);
        int y = (int)((compHeight - imageHeight*scaleFactor)/2);
        g.drawImage(im,x,y,(int)(imageWidth*scaleFactor),(int)(imageHeight*scaleFactor),null);
    }
}
