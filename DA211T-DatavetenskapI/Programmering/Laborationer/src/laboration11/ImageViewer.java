package laboration11;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ImageViewer
{
    private JFrame frame;
    public JLabel labelImage;


    public ImageViewer()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setBounds(170, 25, 640, 480);
        frame.setLayout(new BorderLayout());

        labelImage = new JLabel();
        labelImage.setBounds(0, 0, 640, 480);
        frame.add(labelImage);
    }


    //public void setSize(int x, int y)
    //{
        //frame.setSize(x, y);
    //}

    public void autoSize()
    {
        if (null != labelImage.getIcon())
            frame.setSize(labelImage.getIcon().getIconWidth(), labelImage.getIcon().getIconHeight());
        else
            frame.setSize(0,0);
    }

    public void showImage(String filepath)
    {
        labelImage.setText("");
        labelImage.setIcon( new ImageIcon(filepath));
    }

    public void noImage()
    {
        labelImage.setIcon(null);
        labelImage.setText("INGEN BILD VALD");
    }

    public static void main(String[] args)
    {
        System.out.println("ImageChooser");
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        ImageViewer imageViewer = new ImageViewer();
                        imageViewer.showImage("D:/me/dev/Spelutveckling/DA211T/Matrial/Laborationer/DA211TL11HT10/filmlogga.jpg");
                        //new ImageViewer().showImage("D:/me/dev/Spelutveckling/DA211T/Matrial/Laborationer/DA211TL11HT10/filmlogga.jpg");
                    }
                }
                );
    }
}
