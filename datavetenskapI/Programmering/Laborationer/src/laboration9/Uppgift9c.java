package laboration9;

public class Uppgift9c {
    public void program() {
        Binary bin = new Binary();
        bin.toBinary( 3 );
        bin.toBinary( 7 );
        bin.toBinary( 127 );
        bin.toBinary( 128 );
        bin.toBinary( 32 );
        bin.toBinary( 33 );
        bin.toBinary( 113 );
        bin.toBinary( 197 );
    }

    public static void main(String[] args) {
        Uppgift9c u9c = new Uppgift9c();
        u9c.program();
    }
}
