package laboration15;

import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JSlider;

public class Uppgift15_1
{
    JSlider bajs = new JSlider();
    // 1e
    public static void main(String[] argv)
    {
        AnstalldLon[] anstallda = { new Heltidslon( 19938278, 21500 ),  
            new Provisionslon( 19278865, 0.10 ), 
            new Timlon( 17233534, 95.0 ) }; 
        String res = "FÖRE SORTERING\n"; 
        (( Provisionslon )anstallda[ 1 ]).setForsaljning( 208000 ); 
        (( Timlon )anstallda[ 2 ]).setArbetadeTimmar( 128 ); 
        for( int i = 0; i < anstallda.length; i++ ) { 
            res += anstallda[ i ].toString() + "\n"; 
        } 
        Arrays.sort( anstallda ); 
        res += "------------------------------------\n"; 
        res += "EFTER SORTERING\n"; 
        for( int i = 0; i < anstallda.length; i++ ) { 
            res += anstallda[ i ].toString() + "\n"; 
        } 
        JOptionPane.showMessageDialog( null, res );
    }


    //// 1b
    //public static void main(String[] argv)
    //{
        //Provisionslon anstalld = new Provisionslon( 19278865, 0.10 ); 
        //anstalld.setForsaljning( 208000 ); 
        //System.out.println( anstalld.toString() ); 
        //System.out.println( "Anställd med id " + anstalld.getId() +  
                //" har sålt för " + anstalld.getForsaljning() +  
                //" kr till provisionen " + anstalld.getProvision()*100 +  
                //" %" ); 
        //anstalld.setProvision( 0.12 ); 
        //System.out.println( "Lön denna månad: " + anstalld.lon() + " kr");
    //}

    //// 1b
    //public static void main(String[] argv)
    //{
        //Timlon anstalld = new Timlon( 17233534, 95.0 ); 
        //anstalld.setArbetadeTimmar( 128 ); 
        //System.out.println( anstalld.toString() ); 
        //System.out.println( "Anställd med id " + anstalld.getId() +  
                //" har arbetat " + anstalld.getArbetadeTimmar() +  
                //" timmar till lönen " + anstalld.getTimlon() + " kr" ); 
        //anstalld.setTimlon( 98.50 ); 
        //System.out.println( "Lön denna månad: " + anstalld.lon() + " kr");
    //}

    //// 1a
    //public static void main(String[] argv)
    //{
        //Heltidslon anstalld = new Heltidslon( 19938278, 21500 ); 
        //System.out.println( anstalld.toString() ); 
        //System.out.println( "Anställd med id " + anstalld.getId() +  
                //" har månadslönen " + anstalld.getManadslon() + " kr"); 
        //anstalld.setManadslon( 22250 ); 
        //System.out.println( "Lön denna månad: " + anstalld.lon() + " kr");
    //}
}
