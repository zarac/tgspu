package graph;

public class Arc<T>
{
    protected Node<T> from, to;
    protected double weight = 0;

    public Arc(Node<T> from, Node<T> to, double weight)
    {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Node<T> getFrom()
    {
        return from;
    }

    public void setFrom(Node<T> from)
    {
        this.from = from;
    }

    public Node<T> getTo()
    {
        return to;
    }

    public void setTo(Node<T> to)
    {
        this.to = to;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public String toString()
    {
        return "Arc[" + from + "   -- " + weight + " -->   " + to + "]";
    }
}
