package laboration11;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

//import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class ImageChooser implements ItemListener
{
    public ImageController imageController;
    public JFrame frame;

    private JRadioButton buttonLondon;
    private JRadioButton buttonFilmlogga;
    private JRadioButton buttonLugi;
    private JRadioButton buttonTandem;
    private JRadioButton buttonDettaProgram;
    private JRadioButton buttonNoImage;


    public ImageChooser(ImageController imageController)
    {
        this.imageController = imageController;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 100, 125, 150);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(6,1));
        //frame.setLayout(new FlowLayout());
        frame.setVisible(true);

        this.initRadioButtons();

        imageController.autoSize();
        buttonNoImage.setSelected(true);
    }


    private void initRadioButtons()
    {
        buttonLondon = new JRadioButton("london");
        buttonLondon.addItemListener(this);

        buttonFilmlogga = new JRadioButton("Filmlogga");
        buttonFilmlogga.addItemListener(this);

        buttonLugi = new JRadioButton("Lugi");
        buttonLugi.addItemListener(this);

        buttonTandem = new JRadioButton("Tandem");
        buttonTandem.addItemListener(this);

        buttonDettaProgram = new JRadioButton("Program");
        buttonDettaProgram.addItemListener(this);

        buttonNoImage = new JRadioButton("Ingen Bild");
        buttonNoImage.addItemListener(this);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(buttonLondon);
        buttonGroup.add(buttonFilmlogga);
        buttonGroup.add(buttonLugi);
        buttonGroup.add(buttonTandem);
        buttonGroup.add(buttonDettaProgram);
        buttonGroup.add(buttonNoImage);

        frame.add(buttonLondon);
        frame.add(buttonFilmlogga);
        frame.add(buttonLugi);
        frame.add(buttonTandem);
        frame.add(buttonDettaProgram);
        frame.add(buttonNoImage);
    }

    /**
     * {@inheritDoc}
     * @see ItemListener#itemStateChanged(ItemEvent)
     */
    public void itemStateChanged(ItemEvent e)
    {
        if (e.getSource() == buttonLondon && buttonLondon.isSelected())
        {
            imageController.newImage("z:/me/dev/Spelutveckling/DA211T/Matrial/Laborationer/DA211TL11HT10/london06.jpg");
            imageController.autoSize();
        }
        else if (e.getSource() == buttonFilmlogga && buttonFilmlogga.isSelected())
        {
            imageController.newImage("z:/me/dev/Spelutveckling/DA211T/Matrial/Laborationer/DA211TL11HT10/filmlogga.jpg");
            imageController.autoSize();
        }
        else if (e.getSource() == buttonLugi && buttonLugi.isSelected())
        {
            imageController.newImage("z:/me/dev/Spelutveckling/DA211T/Matrial/Laborationer/DA211TL11HT10/lugi.gif");
            imageController.autoSize();
        }
        else if (e.getSource() == buttonTandem && buttonTandem.isSelected())
        {
            imageController.newImage("z:/me/dev/Spelutveckling/DA211T/Matrial/Laborationer/DA211TL11HT10/tandem1.jpg");
            imageController.autoSize();
        }
        else if (e.getSource() == buttonDettaProgram && buttonDettaProgram.isSelected())
        {
            imageController.newImage("z:/me/dev/Spelutveckling/DA211T/Matrial/Laborationer/DA211TL11HT10/program.bmp");
            imageController.autoSize();
        }
        else if (e.getSource() == buttonNoImage && buttonNoImage.isSelected())
        {
            imageController.eraseImage();
            imageController.autoSize();
        }
    }


    public static void main(String[] args)
    {
        System.out.println("ImageChooser");
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                        new ImageChooser(new ImageController(new ImageViewer()));
                    }
                }
                );
    }
}
