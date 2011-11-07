package graph;

/**
 * Represents a node in the <code>Graph</code>.
 */
public class Node<T>
{
    protected T value;

    public Node(T value)
    {
        this.value = value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    public T getValue()
    {
        return value;
    }

    public String toString()
    {
        return value.toString();
    }
}
