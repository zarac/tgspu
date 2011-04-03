package laboration8;

/**
 * Tr�na do-while-loop
 * @author Rolf Axelsson
 */
public class Uppgift8c {
    public void uppgift8c0() {
        int i = 0;
        do {
            System.out.print( 'A' + " ");
            i++;
        } while( i < 10 );
    }
    
    public void uppgift8c1()
    {
        int i=0;
        do
        {
            System.out.print("H ");
            i++;
        }
        while (i<10);
    }

    public void uppgift8c2()
    {
        int i=0;
        do
        {
            System.out.print(i+" ");
            i++;
        }
        while (i<10);
    }

    public void uppgift8c3()
    {
        int i=1;
        do
        {
            System.out.print(i+" ");
            i++;
        }
        while (i<=10);
    }

    public void uppgift8c4()
    {
        int i=9;
        do
        {
            System.out.print(i+" ");
            i--;
        }
        while (i>=0);
    }

    public void uppgift8c5()
    {
        int i=0;
        do
        {
            System.out.print(i+" ");
            i+=2;
        }
        while (i<=8);
    }

    public void uppgift8c6()
    {
        int i=30;
        do
        {
            System.out.print(i+" ");
            i-=5;
        }
        while (i>=10);
    }

    public void uppgift8c7(char p_char, int p_times)
    {
        int i=0;
        do
        {
            System.out.print(p_char + " ");
            i++;
        }
        while (i<=p_times);
    }

    public void uppgift8c8(int int1, int int2)
    {
        do
        {
            System.out.print(int2 + " ");
            int2--;
        }
        while (int2>=int1);
    }


    public static void main(String[] args) {
        Uppgift8c u8c = new Uppgift8c();
        u8c.uppgift8c0();
        System.out.println();
        u8c.uppgift8c1();
        System.out.println();
        u8c.uppgift8c2();
        System.out.println();
        u8c.uppgift8c3();
        System.out.println();
        u8c.uppgift8c4();
        System.out.println();
        u8c.uppgift8c5();
        System.out.println();
        u8c.uppgift8c6();
        System.out.println();
        u8c.uppgift8c7('A',20);
        System.out.println();
        u8c.uppgift8c7('m',7);
        System.out.println();
        u8c.uppgift8c8(1,10);
        System.out.println();
        u8c.uppgift8c8(9,12);
    }
}
