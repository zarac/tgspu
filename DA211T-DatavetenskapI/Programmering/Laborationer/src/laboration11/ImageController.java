package laboration11;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.util.regex.*;

/**
 * 
 * @author Hannes Landstedt (hanneslandstedt@gmail.com)
 * @version null
 */
public class ImageController
{
    private ImageViewer imageViewer;

    private Pattern filePattern;
    //private Matcher fileMatcher;


    public ImageController(ImageViewer imageViewer)
    {
        this.imageViewer = imageViewer;
        // RegExp to match *.jpg, *.gif and *.png
        filePattern = Pattern.compile(".*(jpg|gif|png)");
    }


    public void newImage(String filepath)
    {
        if (filePattern.matcher(filepath).matches())
        {
            imageViewer.showImage(filepath);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error: Invalid filetype...");
        }
    }

    public void eraseImage()
    {
        imageViewer.noImage();
    }

    public void autoSize()
    {
        imageViewer.autoSize();
    }


    public static void main(String[] args)
    {
        System.out.println("ImageController");
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        ImageController imageController = new ImageController(new ImageViewer());
                        imageController.newImage("D:/me/dev/Spelutveckling/DA211T/Matrial/Laborationer/DA211TL11HT10/filmlogga.jpg");
                    }
                }
                );
    }
}
