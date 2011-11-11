package graph;

import java.util.Comparator;

/**
 * Represents a node in the <code>Graph</code>.
 */
public class Node<T>// implements Comparator<Node<T>>
{
    protected T value;

    /* The distance traveled while getting shortest path. */
    protected double distance = 0;

    /* The previous node while getting shortest path. */
    protected Node<T> previous;

    public Node(T value)
    {
        this.value = value;
    }

    public T getValue()
    {
        return value;
    }

    public int hashCode()
    {
        System.out.println("hashc0ding ... value = " + value  + ", code = " + value.toString().hashCode());
        return value.toString().hashCode();
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    public String toString()
    {
        return value.toString();
    }

    //public int compare(Node<T> o1, Node<T> o2)
    //{
        //System.out.println("compaarrzz??" + o1.getValue() + " == " + o2.getValue());
        //return 0;
    //}

    //public boolean equals(Node<T> node)
    //{
        //System.out.println("equalszzz??" + value + " == " + node.getValue());
        //return (value.equals(node.getValue()));
    //}
}
