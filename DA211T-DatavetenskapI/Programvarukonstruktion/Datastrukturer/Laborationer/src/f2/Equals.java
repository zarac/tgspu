package f2;

public class Equals {
/***************** == och equals ***********************/
    public void compareString1() {
//        String s1 = "Student", s2 = "Student", s3;
        String s1 = new String( "Student" ), s2 = new String( "Student" ), s3;
        s3 = s1;
        System.out.println( "s1 == s2: " + ( s1 == s2 ) );
        System.out.println( "s1 == s3: " + ( s1 == s3 ) );
    }
    
    public void compareString2() {
        String s1 = new String( "Student" ), s2 = new String( "Student" ), s3;
        s3 = s1;
        System.out.println( "s1.equals( s2 ): " + s1.equals( s2 ) );
        System.out.println( "s1.equals( s3 ): " + s1.equals( s3 ) );
    }
/*******************************************************/
        

    public static void main(String[] args) {
        Equals prog = new Equals();
//        prog.compareString1();
        prog.compareString2();
    }
}
