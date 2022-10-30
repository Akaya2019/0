package com.Ali;

import java.util.*;

/**
 * DistanceDiff class is used for Q2
 */
public class DistanceDiff {

    // Data Fields
    /** A reference to the graph being searched. */
    private MyGraph myGraph;

    /** Array of parents in the depth-first search tree. */
    private int[] parent;

    /** Flag to indicate whether this vertex has been visited. */
    private boolean[] visited;

    /** The array that contains each vertex in discovery order. */
    private int[] discoveryOrder;

    /** The array that contains each vertex in finish order. */
    private int[] finishOrder;

    /** The index that indicates the discovery order. */
    private int discoverIndex = 0;

    /** The index that indicates the finish order. */
    private int finishIndex = 0;

    /** The id num that indicates the start vertex's id. */
    private int start ;

    /** Counter of the total path distance of Breadth First Search */
    private double totalDistanceBFS ;

    /** Counter of the total path distance of Depth First Search */
    private double totalDistanceDFS ;

    // Constructors
    /** Construct the depth-first search of a Graph
     starting at vertex 0 and visiting the start vertices in
     ascending order.
     @param myGraph The graph
     */
    public DistanceDiff(MyGraph myGraph) {
        this.myGraph = myGraph;
        int n = myGraph.getNumV();
        parent = new int[n+1];
        visited = new boolean[n+1];
        discoveryOrder = new int[n+1];
        finishOrder = new int[n+1];

        start = 0;
        totalDistanceBFS = 0;
        totalDistanceDFS = 0;

        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /** Recursively depth-first search the graph
     starting at vertex current.
     @param current The start vertex
     */
    public void depthFirstSearch(int current) {

        visited[current] = true;
        discoveryOrder[discoverIndex++] = current;

        Iterator< Edge > itr = myGraph.edgeIterator(current);
        while (itr.hasNext()) {
            int neighbor = itr.next().getDest();
            if (!visited[neighbor]) {
                parent[neighbor] = current;
                totalDistanceDFS = totalDistanceDFS + myGraph.getEdge(current , neighbor).getWeight();
                depthFirstSearch(neighbor);
            }
        }
        finishOrder[finishIndex++] = current;
    }

    /** Get the finish order
     @return finish order
     */
    public int[] getFinishOrder() {
        return finishOrder;
    }

    /**
     * The method calculates the total distance of the path for accessing each vertex
     * during the traversal, and it returns the difference between
     * the total distances of two traversal methods.
     * @return - int totalDistanceBFS - totalDistanceDFS value
     */
    public int calculate(){
        Queue<Integer> theQueue = new LinkedList<Integer>();
        int[] parent2 = new int[myGraph.getNumV()+1];
        for (int i = 0; i < myGraph.getNumV(); i++) {
            parent2[i] = -1;
        }

        for (int i = 0; i <= myGraph.getNumV(); i++) {
            if (myGraph.vertexArr.get(i) != null){
                start = i;
                break;
            }
        }

        boolean[] identified = new boolean[myGraph.getNumV()+1];
        identified[start] = true;
        theQueue.offer(start);

        while(!theQueue.isEmpty()){
            int current = theQueue.remove();
            Iterator<Edge> iter = myGraph.edgeIterator(current);
            while(iter.hasNext()){
                Edge edge = iter.next();
                int neighbor = edge.getDest();
                if (!identified[neighbor]){
                    identified[neighbor] = true;
                    //make sum maybe current-neighbor edge
                    totalDistanceBFS = totalDistanceBFS + edge.getWeight();
                    theQueue.offer(neighbor);
                    parent2[neighbor] = current;
                }
            }
        }

        for (int i = 0; i < myGraph.getNumV(); i++) {
            if (myGraph.vertexArr.get(i) == null) continue;
            Collections.sort(myGraph.edges[i]);
        }
        for (int i = 0; i < myGraph.getNumV(); i++) {
            if (myGraph.vertexArr.get(i) == null) continue;
            if (!visited[i])
                depthFirstSearch(i);
        }
        return (int) (totalDistanceBFS - totalDistanceDFS);
    }
}

