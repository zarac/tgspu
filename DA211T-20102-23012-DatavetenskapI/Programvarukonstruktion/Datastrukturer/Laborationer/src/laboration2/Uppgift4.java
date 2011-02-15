package laboration2;

public class Uppgift4
{
    public static void shuffle(Object[] objects)
    {
        for (int i = 0; i < objects.length; i++)
            swap(objects, i, (int)(Math.random() * objects.length));
    }

    public static void swap(Object[] objects, int index1, int index2)
    {
        Object object = objects[index1];
        objects[index1] = objects[index2];
        objects[index2] = object;
    }

    public static void main (String [] args)
    {
        Integer[] arr = new Integer[5]; 
        for( int i=0; i<arr.length; i++ ) { 
            arr[i] = new Integer(i); 
        } 
        Uppgift4.shuffle( arr ); 
        for( Integer elem : arr ) 
            System.out.println(elem); 
    }
}
