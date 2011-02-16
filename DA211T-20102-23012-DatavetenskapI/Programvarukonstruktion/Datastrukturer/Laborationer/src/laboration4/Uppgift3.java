package laboration4;

class Uppgift3 
{
    static boolean medlem(int tal, int[] array, int pos)
    {
        if (pos < 0 || pos >= array.length)
            return false;
        else if (tal == array[pos])
            return true;
        else
            return medlem(tal, array, pos+1);
    }

    public static void main (String [] args)
    {
        int[] arr = { 23, -45, -20, 10, 8 }; 
        System.out.println( medlem( 17, arr, 0 ) );  // Resultat: false 
        System.out.println( medlem( 23, arr, 0 ) );  // Resultat: true 
        System.out.println( medlem( 23, arr, 2 ) );  // Resultat: false 
        System.out.println( medlem( 10, arr, 0 ) );  // Resultat:  true 
    }
}
