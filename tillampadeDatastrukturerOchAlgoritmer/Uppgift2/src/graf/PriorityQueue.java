package graf;
import java.util.NoSuchElementException;

public class PriorityQueue {
    private IntList queue = new IntList();
    
    public int size() {
        return queue.size();
    }
    
    public boolean empty() {
        return queue.size() == 0;
    }
    
    public void enqueue( int data ) {
        int pos=0;
        int size = queue.size();
        while(pos<size && queue.get(pos)>=data)
            pos++;
        queue.add( pos, data );
    }
    
    public int dequeue() throws NoSuchElementException {
        if( empty() )
            throw new NoSuchElementException();
        return queue.remove( 0 );
    }
    
    public int peek() throws NoSuchElementException {
        if( empty() )
            throw new NoSuchElementException();
        return queue.get( 0 );
    }
}
