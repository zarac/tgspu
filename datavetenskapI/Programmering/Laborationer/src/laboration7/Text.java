package laboration7;
import java.awt.*;
import java.awt.font.*;
import javax.swing.Icon;

/**
 * Klassen Text kan användas för att visa text i ett PaintWindow. Texten visas
 * som en bild och är därmed flyttbar.
 * @author TSROAX
 */
public class Text implements Icon {
    private String text;
//    private int x;
//    private int y;
    private Font font;
    private Color foreground;
    private Color background;
    private boolean opaque;
    private int iconWidth;
    private int iconHeight;
    private int textDY;
    
    public Text(String text) {
        this(text,new Font("SansSerif",Font.PLAIN,12));
    }
    
    public Text(String text, Font font) {
        this(text,font,Color.BLACK,Color.WHITE,false);
    }
    
    public Text(String text, Font font, Color foreground) {
        this(text,font,foreground,null,false);
    }
    
    public Text(String text, Font font, Color foreground, Color background) {
        this(text,font,foreground,background,true);
    }

    private Text(String text, Font font, Color foreground, Color background, boolean opaque) {
        LineMetrics lm;
        this.text = text;
        this.font = font;
        this.foreground = foreground;
        this.background = background;
        this.opaque = opaque;
        FontRenderContext frc = new FontRenderContext(null, false, false);		
        iconWidth = (int)font.getStringBounds(text, frc).getWidth();
        iconHeight = (int)font.getStringBounds(text, frc).getHeight();
        lm = font.getLineMetrics(text, frc);
        textDY = (int)(lm.getHeight() - lm.getDescent());
    }
    
    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }
    
    public void setBackground(Color background) {
        if(background!=null) {
            this.background = background;
            this.opaque = true;
        } else {
            this.opaque = false;
        }
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();
        if(this.opaque) {
            g.setColor(this.background);
            g.fillRect(x, y, this.iconWidth, this.iconHeight);
        }
        g.setColor(this.foreground);
        g.setFont(this.font);
        g.drawString(text, x, y+this.textDY);
        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    public int getIconWidth() {
        return iconWidth;
    }

    public int getIconHeight() {
        return iconHeight;
    }
    
}
