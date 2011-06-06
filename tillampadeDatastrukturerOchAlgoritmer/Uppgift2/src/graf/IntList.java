package graf;

public class IntList {
    private IntNode list = null;
    
    private IntNode locate(int index) {
        IntNode node = list;
        for( int i = 0; i < index; i++)
            node = node.getNext();
        return node;
    }  
    
    public int size() {
        int n = 0;
        IntNode node = list;
        while( node != null ) {
            n++;
            node = node.getNext();
        }
        return n;
    }

    public int get( int index ) {
        if( ( index < 0 ) || ( index >= size() ) )
            throw new IndexOutOfBoundsException( "size=" + size() + ", index=" + index );
        
        IntNode node = locate( index );
        return node.getData();
    }
    
    public void add( int index, int data ) {
        if( ( index < 0 ) || ( index > size() ) )
            throw new IndexOutOfBoundsException( "size=" + size() + ", index=" + index );
        
        if( index == 0 )
            list = new IntNode( data, list );
        else {
            IntNode node = locate( index - 1 );
            IntNode newNode = new IntNode( data, node.getNext() );
            node.setNext( newNode );
        }
    }
    
    public int remove( int index ) {
        if( ( index < 0 ) || ( index >= size() ) )
            throw new IndexOutOfBoundsException( "size=" + size() + ", index=" + index );
        
        int res;
        if( index == 0 ) {
            res = list.getData();
            list = list.getNext();
        } else {
            IntNode node = locate( index - 1 );
            res = node.getNext().getData();
            node.setNext( node.getNext().getNext() );
        }
        return res;
    }
    
    public void printList() {
        IntNode node = list;
        System.out.print("[ ");
        while( node != null ) {
            System.out.print( node.getData() );
            node = node.getNext();
            if( node != null )
                System.out.print(", ");
        }
        System.out.println(" ]");
    }
    
    public String toString() {
        IntNode node = list;
        String res = "[";
        while( node != null ) {
            res += node.getData();
            node = node.getNext();
            if( node != null )
                res += ", ";
        }
        return res + "]";
    }
    
    public static void main( String[] args ) {
        IntList list = new IntList();
        for( int i = 0; i < 50; i++ ) {
            list.add( i, i+1 ); // Vad händer med list.add( 0, i+1 ); ?
        }
        System.out.println( list );
    }
}

