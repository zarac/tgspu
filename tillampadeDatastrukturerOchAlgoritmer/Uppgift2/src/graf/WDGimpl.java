package graf;

import RiskyHash.RiskyHash;
import RiskyHash.Entry;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class WDGimpl<K> implements WeightedDirectedGraph<K, Node<K>> {

    static int MAX_SIZE = 1501;
    //RiskyHash<String, Node> nodes = new RiskyHash<String, Node>(MAX_SIZE);
    public RiskyHash<K, Node<K>> nodes = new RiskyHash<K, Node<K>>(MAX_SIZE);
    public RiskyHash<Node<K>, LinkedList<Arc>> arcs = new RiskyHash<Node<K>, LinkedList<Arc>>(MAX_SIZE);

    @Override
    public void insertNode(K key, Node<K> node) {
        nodes.put(key, node);
    }

    public Node<K> findNode(K key) {
        return nodes.get(key);
    }

    public void deleteNode(Node<K> p_node) {
        for (int i = 0; i < nodes.size(); i++) {
            Iterator<Entry<K, Node<K>>> it = nodes.table[i].iterator();
            while (it.hasNext()) {
                Entry<K, Node<K>> node = it.next();
                LinkedList<Arc> arcs = this.arcs.get(node);
                //LinkedList<Arc> tempList = it.next().value.arcs;
                Iterator<Arc> itArc = arcs.iterator();
                while (itArc.hasNext()) {
                    if (itArc.next().to == p_node) {
                        itArc.remove();
                    }
                }
            }
        }
        //nodes.remove(p_node.key, p_node);
        nodes.remove(p_node.key);
    }

    public void insertArc(Node<K> from, Node<K> to, int weight) {
//        System.out.println("insert arc: " + from + ", " + to + ", " + weight);
        LinkedList<Arc> arcs = this.arcs.get(from);
//        System.out.println("insertArc(): arcs = " + arcs);
        if (arcs != null) {
//            System.out.println("ej unik avgångstid");
            arcs.add(new Arc(from, to, weight));
        } else {
//            System.out.println("unik avgångstid");
            arcs = new LinkedList<Arc>();
            arcs.add(new Arc(from, to, weight));
            this.arcs.put(from, arcs);
        }
    }

    @SuppressWarnings("unchecked")
    public Node<K>[] getNeighbours(Node<K> node) {
        LinkedList<Arc> arcs = this.arcs.get(node);
        Node<K>[] returnValue;

        if (arcs == null) {
//            System.out.println("getNeighbours: no neighbours :(" );
            returnValue = new Node[0];
        } else {
            Iterator<Arc> it = arcs.iterator();
            returnValue = new Node[arcs.size()];
            int i = 0;
            while (it.hasNext()) {
                Arc arrrc = it.next();
//                System.out.println(arrrc);
                returnValue[i] = arrrc.to;
                i++;
            }
        }

        return returnValue;
    }

    public int getWeight(Node<K> from, Node<K> to) {
        LinkedList<Arc> arcs = this.arcs.get(from);
        Iterator<Arc> it = arcs.iterator();
        while (it.hasNext()) {
            Arc temp = it.next();
            if (temp.to == to) {
                return temp.weight;
            }
        }
        return Integer.MAX_VALUE;
    }

    public WeightedDirectedGraph<K, Node<K>> shortestPath(Node<K> from, Node<K> to) {
        // TODO : ? Use heap
        System.out.println("shortestPath() search started...");
        System.out.println("Node: " + from);
        //PriorityQueue<Node<K>> Q = new PriorityQueue<Node<K>>();
        SuperQueue<Node<K>> Q = new SuperQueue<Node<K>>();
        Node<K> u;
        from.dist = 0;
        from.mark = true;
        Q.add(from, from.dist);

        // Reset marks
        int counter = 0;
        for (LinkedList<Entry<K, Node<K>>> list : this.nodes.table) {
            for (Entry<K, Node<K>> entry : list) {
                entry.value.mark = false;
                counter++;
            }
        }
        System.out.println("reset " + counter + " nodes.mark to false out of " + nodes.size());

        String toKey = (String)to.getKey();
        int toKeyLength = toKey.length();

        while (!Q.isEmpty()) {
            //u = Q.poll();
            u = Q.remove();
            System.out.println("AT NODE u=" + u);
            // TODO : fix UGLY H4x h4x!
//            String uKey = (String)u.getKey();
//            int uKeyLength = uKey.length();
//            String uSubKey = ((String)u.getKey()).substring(0, toKeyLength);
//
//            System.out.println("toKey = " + toKey);
//            System.out.println("uSubKey = " + uSubKey);
//            if (uSubKey.equals(toKey))
            if(u.equals(to))
            {
                // TODO: weight etc.
//                to.dist = u.dist + getWeight(u,to);
                to = u;

                System.out.println("CONGRATZ!½ U HAFE REASERCHED UR LOCATION!!!11one qq pew pew :pPPPPPpppPPPp");

                WeightedDirectedGraph<K, Node<K>> returnGraph = new WDGimpl<K>();
                returnGraph.insertNode(to.getKey(), to);
                Node<K> current = to;
                while (current.ref != null) {
                    returnGraph.insertNode(current.getRef().getKey(), current.getRef());
                    returnGraph.insertArc(current.getRef(), current, getWeight(current.getRef(), current));
                    current = current.getRef();
                }

                return returnGraph;
            }

            //for (Node<K> v : getNeighbours(u)) {
            Node<K>[] neighbours = getNeighbours(u);
            for (int i = 0; i < neighbours.length; i++) {
                Node<K> v = neighbours[i];
                System.out.println("    NEIGHBOUR v=" + v);
                // mark means node has been added to queue
                if (!v.mark) {
                    System.out.println("        wasn't marked, adding '" + v + "' to queue and marking");
                    v.dist = Double.MAX_VALUE;
                    v.mark = true;
                    Q.add(v, v.dist);
                }
                // done means... ?
                if (!v.done) {
                    System.out.println("        wasn't done, checking if distance is shorter");
                    double distanceToV = u.dist + getWeight(u, v);
                    if (distanceToV < v.dist) {
                        System.out.println(Q.remove(v));
                        v.dist = distanceToV;
                        v.ref = u;
                        System.out.println("        New shortest path, distance = " + distanceToV);
                        // Change the priority for v
                        Q.add(v, v.dist);
                    }
                }
            }
            System.out.println("    Marking u '" + u + "' as done");
            u.done = true;
        }

        return null;
    }

    public void dumpArcs() {
        for (LinkedList<Entry<Node<K>, LinkedList<Arc>>> list : arcs.table) {
            System.out.println("Array");
            for (Entry<Node<K>, LinkedList<Arc>> entry : list) {
                System.out.println("LinkedList");
                if (entry != null) {
                    for (Arc arc : entry.value) {

                        System.out.println(arc + " with hashIndex " + arcs.hashIndex(arc.from) + arcs.hashIndex(arc.from));
                    }
                }
            }
        }
    }
//    public void dumpNeighbours()
//    {
//        for(LinkedList<Entry<Node<K>,LinkedList<Node<K>>>> list : nodes.table)
//        {
//            System.out.println("Array");
//            for(Entry<Node<K>,LinkedList<Node>> entry : list)
//            {
//                System.out.println("LinkedList");
//                if(entry != null)
//                {
//                    for(Node node : entry.value)
//                    {
//
//                        System.out.println(node + " with hashIndex " + nodes.hashIndex(node.from) + nodes.hashIndex(node.from));
//                    }
//                }
//            }
//        }
//    }
}
