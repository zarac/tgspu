package laboration14; 
 
public class Uppgift14_1
{

    // 1e
    public static void main(String[] argv)
    {
        String[] mel = { "Mel 1", "Melodi 2", "Melodi 3", "Mel 4" }; 
        CD cd = new CD( 39488852, "TITEL" ,"ARTIST", mel ); 
        System.out.println( "----- Test av get-metoder -----" ); 
        System.out.println( cd.getId() + ", " + cd.getArtist() + ", " +  
                cd.getTitel() ); 
        System.out.println( "----- Test av toString -----" ); 
        System.out.println( cd.toString() ); 
        System.out.println( "----- Test av set-metoder -----" ); 
        cd.setArtist( "Ulf Lundell" ); 
        cd.setTitel( "Vargmåne" ); 
        cd.setMelodier( new String[]{ "M1", "M2", "M3", "M4", "M5", "M6" } ); 
        System.out.println( cd.toString() ); 
    }

    // 1d-2
    //public static void main(String[] argv)
    //{
        //Bok media = new Bok( 837884976, "En fantastisk värld", "Alice Green" ); 
        //String res = media.getTitel() + " av " + media.getAuthor() + "\n" +
            //"toString-metoden: " + media.toString(); 
        //javax.swing.JOptionPane.showMessageDialog( null, res ); 
    //}

    //// 1d
    //public static void main(String[] argv)
    //{
        //Media media = new Media(837884976, "En fantastisk värld" ); 
        //String res = media.getTitel() + " är ett '" + media.getClass().getName() + 
            //"'-objekt" + "\n" + "toString-metoden: " + media.toString(); 
        //javax.swing.JOptionPane.showMessageDialog( null, res ); 
    //}

    //// 1c
    //public static void main(String[] argv)
    //{
        //Bok media = new Bok(); 
        //media.setId( 837884976 ); 
        //media.setTitel( "En fantastisk värld" ); 
        //media.setAuthor( "Alice Green" ); 
        //String res = media.getTitel() + " av " + 
            //media.getAuthor() + "\n" + 
            //"toString-metoden: " + media.toString(); 
        //javax.swing.JOptionPane.showMessageDialog( null, res );
    //}

    //// 1b
    //public static void main(String[] argv)
    //{
        //Bok media = new Bok(); 
        //media.setId( 837884976 ); 
        //media.setTitel( "En fantastisk värld" ); 
        //String res = media.getTitel() + " är ett '" + media.getClass().getName() + 
            //"'-objekt" + "\n" + "toString-metoden: " + media.toString(); 
        //javax.swing.JOptionPane.showMessageDialog( null, res );
    //}

    // 1a
    //public static void main(String[] argv)
    //{
        //Media media = new Media(); 
        //media.setId( 837884976 ); 
        //media.setTitel( "En fantastisk värld" ); 
        //String res = media.getTitel() + " är ett '" + media.getClass().getName() + 
            //"'-objekt" + "\n" + "toString-metoden: " + media.toString(); 
        //javax.swing.JOptionPane.showMessageDialog( null, res );
    //}
} 
