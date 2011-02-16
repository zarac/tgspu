package laboration4;

class Uppgift8 
{
    static int mystery2(int n, int res)
    {
        System.out.println(n + " " + res);

        if (n == 1)
            return res;

        return mystery2(n-1, n*res);

        // 4,1
        // 3,4
        // 2,12
        // 1,24
        //
    }

    public static void main (String [] args)
    {
        System.out.println(mystery2(4,1));
    }
}
