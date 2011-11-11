package graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * An implementation of the Graph data structure.
 */
public class Graph<T>
{
    protected Map<T, Node<T>> nodes;
    protected Map<T, Map<T, Arc<T>>> arcs;

    public Graph()
    {
        arcs = new TreeMap<T, Map<T, Arc<T>>>();
        nodes = new TreeMap<T, Node<T>>();
    }

    /**
     * Greate a new arc, add it to arcs and return it.
     */
    public Arc<T> addArc(Node<T> from, Node<T> to, double weight)
    {
        //* create arc
        Arc<T> arc = new Arc<T>(from, to, weight);
        //* get arcs (map) from from
        Map<T, Arc<T>> arcsFrom = arcs.get(from.getValue());
        if (arcsFrom == null)
        {
            //System.out.println("+ creating arcs map for " + from);
            arcsFrom = new TreeMap<T, Arc<T>>();
            arcs.put(from.getValue(), arcsFrom);
        }
        //System.out.println("+ adding arc " + arc + " to arcs map for " + from);
        arcsFrom.put(to.getValue(), arc);
        return arc;
    }

    public void addNode(Node<T> node)
    {
        Node<T> theNode = nodes.get(node.getValue());
        if (theNode == null)
        {
            //System.out.println("+ adding node " + node);
            nodes.put(node.getValue(), node);
        }
        else
        {
            //System.out.println("- did not add node " + theNode);
        }
    }

    public boolean contains(T key)
    {
        if (nodes.get(key) == null)
        {
            //System.out.println("! does not contain key " + key);
            return false;
        }
        //System.out.println(". contains key " + key);
        return true;
        //return nodes.contains(node);
    }

    /**
     * Dumps the graph, its nodes and arcs, for debugging purpose.
     */
    public String toString()
    {
        String str = " o-o-o-o-o-o-o-o-o \n";
        int nodeCount = 0;
        int arcCount = 0;
        for (Map.Entry<T, Node<T>> kv : nodes.entrySet())
        {
            str += " o [" + ++nodeCount + "] node = " + kv.getKey() + "\n";
            Map<T, Arc<T>> arcs = getArcs(kv.getKey());
            if (arcs == null)
                continue;
            for (Map.Entry<T, Arc<T>> kv2 : arcs.entrySet())
                str += "   o [" + ++arcCount + "] arc = " + kv2.getValue() + "\n";
        }
        return str;
    }

    public Arc<T> getArc(T from, T to)
    {
        Map<T, Arc<T>> arcsFrom = arcs.get(from);
        if (arcsFrom == null)
        {
            //System.out.println("! no arcs map from " + from);
            return null;
        }
        return arcsFrom.get(to);
    }

    public Map<T, Map<T, Arc<T>>> getArcs()
    {
        return arcs;
    }

    public Map<T, Arc<T>> getArcs(T from)
    {
        return arcs.get(from);
    }

    public Node<T> getNode(T key)
    {
        return nodes.get(key);
    }

    public Map<T, Node<T>> getNodes()
    {
        return nodes;
    }

    public Node<T> newNode(T value)
    {
        Node<T> node = new Node<T>(value);
        nodes.put(node.getValue(), node);
        return node;
    }

    //* TODO : test removal of incoming arcs
    public Node<T> removeNode(T value)
    {
        //* the node
        Node<T> node = nodes.remove(value);
        if (node == null)
            return null;
        //* outgoing arcs
        arcs.remove(value);
        //* incoming arcs
        for (Map.Entry<T, Map<T, Arc<T>>> kv : arcs.entrySet())
        {
            for (Map.Entry<T, Arc<T>> kv2 : kv.getValue().entrySet())
            {
                //System.out.println("kv2.getValue().getTo().getValue()" + kv2.getValue().getTo().getValue());
                //System.out.println("node.getValue()" + node.getValue());
                if (kv2.getValue().getTo().getValue().equals(node.getValue()))
                {
                    kv.getValue().remove(kv2.getValue().getTo());
                    //System.out.println(" removing arc = " + kv2.getValue());
                }
            }
        }
        return node;
    }

    public Path<T> shortestPath(Node<T> from, Node<T> goal)
    {
        List<Node<T>> goals = new LinkedList<Node<T>>();
        goals.add(goal);
        return shortestPath(from, goals);
    }

    /**
     * Implementation of Dijkstra's shortest path algorithm.
     */
    public Path<T> shortestPath(Node<T> start, List<Node<T>> goals)
    {
        Node<T> goal = goals.get(0);
        //System.out.println("shortestPath(start = " + start + ", goal = " + goal + ")");
        Path<T> path = new Path<T>();
        path.setStart(start);
        path.setGoal(goal);
        //* init
        for (Map.Entry<T, Node<T>> kv : nodes.entrySet())
        {
            kv.getValue().distance = Double.MAX_VALUE;
            kv.getValue().previous = null;
        }
        List<Node<T>> closed = new LinkedList<Node<T>>();
        //* TODO : priority queue
        Queue<Node<T>> opened = new PriorityQueue<Node<T>>(100, new DistanceComparator());
        opened.add(start);
        start.distance = 0;
        Node<T> current = null;
        while (opened.size() > 0)
        {
            Node<T> previous = current;
            current = opened.remove();
            //System.out.println("-opened : " + current);
            if (current == goal)
            {
                //System.out.println("Found goal!");
                int hops = 0;
                while (current.previous != null)
                {
                    //System.out.println(" ..tracing back : [" + ++hops + "] " + current + " > " + current.previous);
                    path.addNode(current.previous);
                    path.addArc(current.previous, current,
                            getWeight(current.previous, current));
                    current = current.previous;
                }
                break;
            }
            //* add arcs to opened
            Map<T, Arc<T>> departures = arcs.get(current.getValue());
            if (departures != null)
            {
                for (Map.Entry<T, Arc<T>> kv : departures.entrySet())
                {
                    Arc<T> arc = kv.getValue();
                    Node<T> to = arc.getTo();
                    if (!closed.contains(to))
                    {
                        double alternativeDistance = current.distance + getWeight(current, to);
                        if (!opened.contains(to)) //* first time visited
                        {
                            //System.out.println("+opened : " + to);
                            to.distance = alternativeDistance;
                            to.previous = current;
                            opened.add(to);
                        }
                        else if (alternativeDistance < to.distance)
                        {
                            //System.out.println("Found shorter path!");
                            to.distance = alternativeDistance;
                            to.previous = current;
                            //* update position in queue
                            opened.remove(to);
                            opened.add(to);
                        }
                    }
                }
            }
            //System.out.println("+closed : " + current);
            closed.add(current);
        }
        return path;
    }

    public double getWeight(Node<T> a, Node<T> b)
    {
        Map<T, Arc<T>> arcs = this.arcs.get(a.getValue());
        if (arcs == null)
        {
            System.out.println("returning max value (shouldn't really happen)");
            return Double.MAX_VALUE;
        }
        Arc<T> arc = arcs.get(b.getValue());
        //System.out.println("arc = " + arc);
        if (arc == null)
        {
            System.out.println("returning max value (shouldn't really happen)");
            return Double.MAX_VALUE;
        }
        return arc.getWeight();
    }

    protected class DistanceComparator implements Comparator<Node<T>>
    {
        public int compare(Node<T> o1, Node<T> o2)
        {
            //System.out.println("compaarrzz??" + o1.getValue() + " == " + o2.getValue());
            return (int)(o1.distance*1000) - (int)(o2.distance*1000);
        }
    }

    public class Path<T> extends Graph<T>
    {
        protected Node<T> start;
        protected Node<T> goal;
        //protected short distance;
        //protected short hops;

        protected Path()
        {
            super();
        }

        //protected String getPath()
        //{
            //distance = 0;
            //hops = 0;
            //String path = getPathTo(goal);
            //return path;
        //}

        //protected String getPathTo(Node<T> current)
        //{
            //Node<T> previous = current.previous;
            //if (previous == null)
                //return current.toString();
            //else
                //return getPathTo(previous) + "\n  > " + current;
        //}

        public Node<T> getGoal()
        {
            return goal;
        }

        public Node<T> getStart()
        {
            return start;
        }

        protected void setGoal(Node<T> goal)
        {
            this.goal = goal;
        }

        protected void setStart(Node<T> start)
        {
            this.start = start;
        }

        public String toString()
        {
            return "A path from " + start + " to " + goal + ".";
        }
    }
}
