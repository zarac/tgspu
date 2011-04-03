package laboration9;

public class Uppgift9d {
    public void program() {
        Binary bin = new Binary();
        bin.toDecimal( "00101110" );
        bin.toDecimal( "00000000" );
        bin.toDecimal( "11111111" );
        bin.toDecimal( "1111111111111111" );
    }

    public static void main(String[] args) {
        Uppgift9d u9d = new Uppgift9d();
        u9d.program();
    }
}
