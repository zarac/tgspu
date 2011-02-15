package laboration2;
import java.util.*;

public class Laboration2 {
    private void swap( int[] array, int elem1, int elem2 ) {
        int temp = array[ elem1 ];
        array[ elem1 ] = array[ elem2 ];
        array[ elem2 ] = temp;
    }
    
    private void shuffle( int[] array ) {
        int newPos;
        for( int i = array.length - 1; i > 0; i-- ) {
            newPos = ( int )( Math.random() * ( i + 1 ));
            swap( array, i, newPos );
        }
    }
    
    public int[] randomIntArray( int count ) {
        int[] res = new int[ count ];
        for( int i = 0; i < res.length; i++ )
            res[i] = i;
        shuffle( res );
        return res;
    }
    
    public ArrayList<Integer> fillInteger( int n, int min, int max ) {
        int random;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for( int i = 0; i < n; i++ ) {
            random = ( int )( Math.random() * ( max - min + 1 ) ) + min;
            list.add( new Integer( random ) );
        }
        return list;
    }
    
    public ArrayList<String> permutera(String str) {
        ArrayList<String> list = new ArrayList<String>();
        List<String> queue = new LinkedList<String>();
        String toUse, res;
        queue.add(str);
        queue.add("");
        while(queue.size()>0) {
            toUse = queue.remove(0);
            res = queue.remove(0);
            if(toUse.length() == 0) {
                list.add(res);
            } else {
                for(int i=0; i<toUse.length(); i++) {
                    queue.add(toUse.substring(0,i) + toUse.substring(i+1, toUse.length()));
                    queue.add(res + toUse.charAt(i));
                }
            }
        }
        return list;
    }
    
    public static void main(String[] args) {
        Laboration2 l2 = new Laboration2();
        ArrayList<String> res = l2.permutera("ROLF");
        for( String s : res )
            System.out.println(s);
    }
}

