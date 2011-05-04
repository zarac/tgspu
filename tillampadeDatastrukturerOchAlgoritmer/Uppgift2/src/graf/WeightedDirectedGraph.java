package graf;

/**
 * A weighted directed graph. <N> Is the node to be stored and <K> is the key
 * used to find a node.
 * 
 * @author 
 */
public interface WeightedDirectedGraph<N,K>
{
    public void insertNode(N node, K key);
    public N findNode(K key);
    public void deleteNode(N node);
    public void insertArc(N from, N to, int weight);
    public N[] getNeighbours(N node);
    public int getWeight(N from, N to);
    public WeightedDirectedGraph<N,K> shortestPath(N from, N to);
}
