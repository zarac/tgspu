package graph;

public class Arc
{
    protected Node from, to;
    protected double weight;

    public Arc(Node from, Node to, double weight)
    {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Node getFrom()
    {
        return from;
    }

    public void setFrom(Node from)
    {
        this.from = from;
    }

    public Node getTo()
    {
        return to;
    }

    public void setTo(Node to)
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
        return "From '" + from + "' to '" + to + "' weighing '" + weight + "'.";
    }
}
