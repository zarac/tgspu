package graf;

public class IntNode {
    private int data;
    private IntNode next;
    
    public IntNode( int data, IntNode next ) {
        this.data = data;
        this.next = next;
    }
    
    public int getData() {
        return this.data;
    }
    
    public void setData( int data ) {
        this.data = data;
    }
    
    public IntNode getNext() {
        return this.next;
    }
    
    public void setNext( IntNode next ) {
        this.next = next;
    }

    public static void println(IntNode node) {
        System.out.print( "[ ");
        while( node!=null ) {
            System.out.print( node.getData() );
            node = node.getNext();
            if( node != null )
                System.out.print(", ");
        }
        System.out.println( " ]");
    }
}