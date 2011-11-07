package graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * An implementation of the Graph data structure.
 */
public class Graph<T>
{
    protected LinkedList<Node<T>> nodes;
    protected LinkedList<Arc> arcs;

    public Graph()
    {
        arcs = new LinkedList<Arc>();
        nodes = new LinkedList<Node<T>>();
    }

    /**
     * Greate a new arc, add it to arcs and return it.
     */
    public Arc addArc(Node<T> from, Node<T> to, double weight)
    {
        Arc arc = new Arc(from, to, weight);
        arcs.add(arc);
        return arc;
    }

    public void addNode(Node<T> node)
    {
        nodes.add(node);
    }

    public Node<T> findNode(String key)
    {
        // TODO : implement
        return null;
    }

    public Node<T> getNode(Node<T> node)
    {
        // TODO : implement
        return null;
    }

    public Arc getArc(Node<T> from, Node<T> to)
    {
        Arc arc = null;
        // TODO : implement
        return arc;
    }

    public List<Arc> getArcs(Node<T> from)
    {
        return arcs;
    }

    public Node<T> getNode(T value)
    {
        Node<T> node = null;
        // TODO : implement
        return node;
    }

    public List<Node<T>> getNodes()
    {
        return nodes;
    }

    public Node<T> newNode(T value)
    {
        Node<T> node = new Node<T>(value);
        nodes.add(node);
        return node;
    }
}
