package graf;



import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Queue;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Magnus Grönvall
 */
public class Graph {
    int[][] matrix;
    int size;
    Node[] nlist;
    public Graph(){
       matrix = new int [100][100];
       size = 0;
       nlist = new Node[100];
    }

    public int insertNode(Node node, ){
        nlist[size] = node;
        size++;
        return size-1;
    }

    public void insertEdge(int i, int j){
        matrix[i][j] = 1;
        matrix[j][i] = 1;
    }
    public int[] getNeighbours(int i){
        int k = 0;
        int[] neighbours = new int[100];
        for (int j=0; j<size; j++){
            if (matrix[i][j] == 1){
                neighbours[k] = j;
                k++;
            }
        }
        neighbours[k] = -1;
        return neighbours;
    }

    public void DFS(Node start){
        Stack s = new Stack();
        int u;
        for (u=0; u<nlist.length; u++){
            nlist[u].mark = false;
        }
        s.push(start);
        start.mark = true;
        while (s.empty() != true){
            // nlist[u] = (Node)s.pop; ??
            Node uNode = (Node)s.pop();
            int[] uNeighbours = getNeighbours(u);
            for (Node v : getNeighbours(nlist.)){
                if (!v.mark){
                    v.mark = true;
                    s.push(v);
                }
            }
        }
    }

    public void BFS (Node start){
        int u;
        for (u=0; u<nlist.length; u++){
            nlist[u].mark = false;
            nlist[u].ref = null;
        }
        Queue q = new Queue();
        start.mark = true;
        q.enqueue(start);
        while (!q.isEmpty()){
            try {
                Node uNode = (Node) q.dequeue();
            } catch (InterruptedException ex) {
                Logger.getLogger(Graph.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void BFShortestPath(Node s){
        for (int u=0; u<nlist.length; u++){
            nlist[u].mark = false;
            nlist[u].ref = null;
        }
        s.dist = 0;
        for (int i = 1; i<n; i++){

        }

    }

}
