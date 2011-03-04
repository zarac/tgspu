package laboration8;

/**
 * Tr�na while-loop
 * @author Rolf Axelsson
 */
public class Uppgift8b {
    public void uppgift8b0() {
        int i = 0;
        while( i < 10 ) {
            System.out.print( 'A' + " ");
            i++;
        }
    }
    
    public void uppgift8b1()
    {
        int i=0;
        while (i<10)
        {
            System.out.print("H ");
            i++;
        }
    }

    public void uppgift8b2()
    {
        int i=0;
        while (i<10)
        {
            System.out.print(i+" ");
            i++;
        }
    }

    public void uppgift8b3()
    {
        int i=1;
        while (i<=10)
        {
            System.out.print(i+" ");
            i++;
        }
    }

    public void uppgift8b4()
    {
        int i=9;
        while (i>=0)
        {
            System.out.print(i+" ");
            i--;
        }
    }

    public void uppgift8b5()
    {
        int i=0;
        while (i<=8)
        {
            System.out.print(i+" ");
            i+=2;
        }
    }

    public void uppgift8b6()
    {
        int i=30;
        while (i>=10)
        {
            System.out.print(i+" ");
            i-=5;
        }
    }

    public void uppgift8b7(char p_char, int p_times)
    {
        int i=0;
        while (i<=p_times)
        {
            System.out.print(p_char + " ");
            i++;
        }
    }

    public void uppgift8b8(int int1, int int2)
    {
        while (int2>=int1)
        {
            System.out.print(int2 + " ");
            int2--;
        }
    }

    public static void main(String[] args) {
        Uppgift8b u8b = new Uppgift8b();
        u8b.uppgift8b0();
        System.out.println();
        u8b.uppgift8b1();
        System.out.println();
        u8b.uppgift8b2();
        System.out.println();
        u8b.uppgift8b3();
        System.out.println();
        u8b.uppgift8b4();
        System.out.println();
        u8b.uppgift8b5();
        System.out.println();
        u8b.uppgift8b6();
        System.out.println();
        u8b.uppgift8b7('A',20);
        System.out.println();
        u8b.uppgift8b7('m',7);
        System.out.println();
        u8b.uppgift8b8(1,10);
        System.out.println();
        u8b.uppgift8b8(9,12);
    }
}
