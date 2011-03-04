package p4;

import javax.swing.SwingUtilities;

public class Countries
{
    public static void main(String[] argv)
    {
        SwingUtilities.invokeLater(new Runnable()
                { 
                    public void run()
        { 
            CountryViewer viewer = new CountryViewer(); 
            CountryIO io = new CountryIO("z:/me/studies/mah-spelutveckling/da211t/Laborationer/src/p4/befolkning.txt");
            CountryController controller = new CountryController(viewer, io); 
            new CountryUserInput(controller); 
        } 
        }); 
    }
}
