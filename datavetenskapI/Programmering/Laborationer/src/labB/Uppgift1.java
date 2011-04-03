package labB;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Font;

import java.util.ArrayList;

import javax.swing.Timer;

public class Uppgift1 implements ActionListener
{
    private ArrayList<Text> texter = new ArrayList<Text>();
    private TextViewer viewer = new TextViewer();
    private Timer timer = new Timer(3000, this);
    int index = 0;

    public Uppgift1()
    { 
        String s1 = "Att lära utan att tänka är meningslöst, att tänka utan att lära är farligt"; 
        String s2 = "Den, som tror sig veta allt, vet ingenting "; 
        String s3 = "Hellre olärd och klok, än lärd och dum "; 
        String s4 = "Sanna ord är inte alltid vackra, vackra ord är inte alltid sanna "; 
        texter.add(new Text(s1, Color.blue, Color.yellow, new Font("Serif", Font.BOLD, 14))); 
        texter.add(new Text(s2, Color.white, Color.darkGray, new Font("SansSerif", Font.PLAIN, 24))); 
        texter.add(new Text(s3, Color.yellow, Color.darkGray, new Font("Monospaced", Font.ITALIC, 28))); 
        texter.add(new Text(s4, Color.red, Color.blue, new Font("Dialog", Font.BOLD + Font.ITALIC, 20))); 
        timer.start(); 
    }

    /**
     * {@inheritDoc}
     * @see ActionListener#actionPerformed(ActionEvent)
     */
    public void actionPerformed(ActionEvent e)
    {
        if (index < texter.size())
        {
            viewer.setText(texter.get(index));
            index++;
        }
        else
        {
            timer.stop();
        }
    } 


    public static void main(String[] argv)
    {
        new Uppgift1();
    }
}
