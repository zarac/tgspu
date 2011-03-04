package laboration8;

/**
 * 3 metoder med samma mening
 * @author Rolf Axelsson
 */
public class Uppgift8d {
    public void forloop(int min, int max, int increase) {
        System.out.println("for loop");
        for(int i = min ; i <= max; i += increase ) {
            System.out.print( i + " " );
        }
        System.out.println();
    }
    
    public void whileloop(int min, int max, int increase)
    {
        System.out.println("whileloop");

        int i = min;
        while(i <= max)
        {
            System.out.print( i + " " );
            i += increase;
        }
        System.out.println();
    }

    public void doloop(int min, int max, int increase)
    {
        System.out.println("do-while loop");
        int i = min;
        do
        {
            System.out.print(i + " ");
            i += increase;
        }
        while(i <= max);
        System.out.println();
    }

    public static void main(String[] args) {
        Uppgift8d u8d = new Uppgift8d();
        u8d.forloop(10,25,3);
        u8d.whileloop(10,25,3);
        u8d.doloop(10,25,3);
    }
}
