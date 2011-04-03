package laboration8;

/**
 * Tr�na for-loop 
 * @author Rolf Axelsson
 */
public class Uppgift8a {
    public void uppgift8a0() {
        for( int i = 0 ; i < 10 ; i++ ) {
            System.out.print( 'A' + " ");
        }
    }

    public void uppgift8a1()
    {
        for (int i=0; i<10; i++)
        {
            System.out.print("H ");
        }
    }

    public void uppgift8a2()
    {
        for (int i=0; i<10; i++)
        {
            System.out.print(i+" ");
        }
    }

    public void uppgift8a3()
    {
        for (int i=1; i<=10; i++)
        {
            System.out.print(i+" ");
        }
    }

    public void uppgift8a4()
    {
        for (int i=9; i>=0; i--)
        {
            System.out.print(i+" ");
        }
    }

    public void uppgift8a5()
    {
        for (int i=0; i<=8; i+=2)
        {
            System.out.print(i+" ");
        }
    }

    public void uppgift8a6()
    {
        for (int i=30; i>=10; i-=5)
        {
            System.out.print(i+" ");
        }
    }

    public void uppgift8a7(char p_char, int p_times)
    {
        for (int i=0; i<=p_times; i++)
        {
            System.out.print(p_char + " ");
        }
    }

    public void uppgift8a8(int int1, int int2)
    {
        for (; int2>=int1; int2--)
        {
            System.out.print(int2 + " ");
        }
    }

    public static void main(String[] args) {
        Uppgift8a u8a = new Uppgift8a();
        u8a.uppgift8a0();
        System.out.println();
        u8a.uppgift8a1();
        System.out.println();
        u8a.uppgift8a2();
        System.out.println();
        u8a.uppgift8a3();
        System.out.println();
        u8a.uppgift8a4();
        System.out.println();
        u8a.uppgift8a5();
        System.out.println();
        u8a.uppgift8a6();
        System.out.println();
        u8a.uppgift8a7('A',20);
        System.out.println();
        u8a.uppgift8a7('h',7);
        System.out.println();
        u8a.uppgift8a8(1,10);
        System.out.println();
        u8a.uppgift8a8(9,12);
    }
}