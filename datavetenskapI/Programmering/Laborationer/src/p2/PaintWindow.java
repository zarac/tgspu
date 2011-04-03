package p2;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

/**
 * I ett fönster av typen PaintWindow kan man visa bilder och rita linjer, ellipser 
 * och rektanglar. När fönstret skapas kan man ange en bild som ska vara bakgrundsbild.
 * @author Rolf Axelsson
 */
public class PaintWindow extends JFrame {
    private PaintPanel panel = new PaintPanel();
    private BufferedImage bImage;
    private Graphics2D bg;
    private ImageIcon background;
    private LinkedList<IconXY> icons = new LinkedList<IconXY>();
    private LinkedList<DPShape> buffer = new LinkedList<DPShape>();

    // Ett fönster med vit bakgrund, 600x400 pixlar stort skapas
    public PaintWindow() {
        this(null);
    }

    public PaintWindow(ImageIcon background) {
        super("Paint Window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.background = background;
        if (background != null) {
            panel.setPreferredSize(new Dimension( Math.min(background.getIconWidth(),800), 
                                                  Math.min(background.getIconHeight(),800)));
        } else {
            panel.setPreferredSize(new Dimension(600, 400));
        }
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

    public void setBackground(ImageIcon icon) {
        this.background = icon;
    } 

    public void showImage(Icon icon, int x, int y) {
        int index;
        if (icon == null) {
            return;
        }
        IconXY newIcon = new IconXY(icon, x, y);
        index = icons.indexOf(newIcon);
        if (index == -1) {
            icons.add(new IconXY(icon, x, y));
        } else {
            icons.get(index).setX(x);
            icons.get(index).setY(y);
        }
        repaint();
    }
    
    public void hideImage(Icon icon) {
        int index;
        if (icon == null) {
            return;
        }
        IconXY newIcon = new IconXY(icon, 0, 0);
        index = icons.indexOf(newIcon);
        if (index != -1) {
            icons.remove(index);
            repaint();
        } 
    }
    
    public void clear() {
        if (background != null) {
            bg.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        } else {
            bg.setPaint(Color.WHITE);
            bg.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        repaint();
    }
    
    public int getBackgroundWidth() {
        return panel.getWidth();
    }
    
    public int getBackgroundHeight() {
        return panel.getHeight();
    }

    public void line(int x1, int y1, int x2, int y2, Color color, int lineWidth) {
        buffer.add(new Line(x1, y1, x2, y2, color, lineWidth));
        repaint();
    }

    public void drawRect(int x, int y, int width, int height, Color color, int lineWidth) {
        buffer.add(new DrawRect(x, y, width, height, color, lineWidth));
        repaint();
    }

    public void fillRect(int x, int y, int width, int height, Color color) {
        buffer.add(new FillRect(x, y, width, height, color));
        repaint();
    }

    public void drawOval(int x, int y, int width, int height, Color color, int lineWidth) {
        buffer.add(new DrawOval(x, y, width, height, color, lineWidth));
        repaint();
    }

    public void fillOval(int x, int y, int width, int height, Color color) {
        buffer.add(new FillOval(x, y, width, height, color));
        repaint();
    }

    public static void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
    
    private class PaintPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Point point;
            if (bImage == null) {
                bImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
                bg = bImage.createGraphics();
                if (background != null) {
                    bg.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
                } else {
                    bg.setPaint(Color.WHITE);
                    bg.fillRect(0, 0, this.getWidth(), this.getHeight());
                }
            }
            while (buffer.size() > 0) {
                buffer.get(0).draw(bg);
                buffer.remove(0);
            }
            g.drawImage(bImage, 0, 0, null);
            for (int i = 0; i < icons.size(); i++) {
                icons.get(i).paintIcon(g);
            }
        }
    }

    private class IconXY {

        private Icon icon;
        private int x;
        private int y;

        public IconXY(Icon icon, int x, int y) {
            this.icon = icon;
            this.x = x;
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public void paintIcon(Graphics g) {
            icon.paintIcon(PaintWindow.this, g, this.x, this.y);
        }
        
        public boolean equals(Object obj) {
            IconXY iconXY = (IconXY)obj;
            return this.icon.equals(iconXY.icon);
        }
    }

    private abstract class DPShape {

        public abstract void draw(Graphics2D g);
    }

    private class Line extends DPShape {

        private int x1,  y1,  x2,  y2,  lineWidth;
        private Color color;

        public Line(int x1, int y1, int x2, int y2, Color color, int lineWidth) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
            this.lineWidth = lineWidth;
        }

        public void draw(Graphics2D g) {
            g.setStroke(new BasicStroke(lineWidth));
            g.setPaint(color);
            g.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }

    private class DrawRect extends DPShape {

        private int x,  y,  width,  height,  lineWidth;
        private Color color;

        public DrawRect(int x, int y, int width, int height, Color color, int lineWidth) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
            this.lineWidth = lineWidth;
        }

        public void draw(Graphics2D g) {
            g.setStroke(new BasicStroke(lineWidth));
            g.setPaint(color);
            g.draw(new Rectangle2D.Double(x, y, width, height));
        }
    }

    private class FillRect extends DPShape {

        private int x,  y,  width,  height;
        private Color color;

        public FillRect(int x, int y, int width, int height, Color color) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
        }

        public void draw(Graphics2D g) {
            g.setPaint(color);
            g.fill(new Rectangle2D.Double(x, y, width, height));
        }
    }

    private class DrawOval extends DPShape {

        private int x,  y,  width,  height,  lineWidth;
        private Color color;

        public DrawOval(int x, int y, int width, int height, Color color, int lineWidth) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
            this.lineWidth = lineWidth;
        }

        public void draw(Graphics2D g) {
            g.setStroke(new BasicStroke(lineWidth));
            g.setPaint(color);
            g.draw(new Ellipse2D.Double(x, y, width, height));
        }
    }

    private class FillOval extends DPShape {

        private int x,  y,  width,  height;
        private Color color;

        public FillOval(int x, int y, int width, int height, Color color) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
        }

        public void draw(Graphics2D g) {
            g.setPaint(color);
            g.fill(new Ellipse2D.Double(x, y, width, height));
        }
    }
}
