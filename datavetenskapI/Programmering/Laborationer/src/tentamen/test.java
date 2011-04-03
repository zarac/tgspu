package tentamen;

public class test
{
    public static void main(String[] argv)
    {
        System.out.println(new B().c);
    }
}


class A { 
    private int a = 10; 
    protected double b = 3.2; 
    public long c = 34; 

    public String toString() { 
        return "a=" + a + " b=" + b + " c=" + c; 
    } 

    public A()
    {
        c = 480;
    }
} 

class B extends A { 
} 
