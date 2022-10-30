package com.Ali;

import java.util.*;

/**
 * MyGraph class for the implementation of DynamicGraph interface.
 * @author Ali Kaya
 */
public class MyGraph implements DynamicGraph{

    /** The number of vertices */
    private int numV;

    /** Flag to indicate whether this is a directed graph */
    private boolean directed;

    /** Initial capacity of array of lists */
    private static final int INITIAL_CAP = 20;

    /** An ArrayList to contain the vertices of the graph*/
    protected ArrayList<Vertex> vertexArr;

    /** An array of Lists to contain the edges that originate with each vertex */
    protected List<Edge>[] edges;

    /**
     * Constructs a directed or undirected graph based on user input.
     * @param directed - boolean true(directed) false(undirected)
     */
    public MyGraph(boolean directed) {
        this.numV = -1;
        this.directed = directed;

        vertexArr = new ArrayList<>();
        edges = new List[INITIAL_CAP];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new LinkedList <Edge> ();
        }
    }

    /**
     * Returns numV value of the graph.
     * @return - int numV value
     */
    public int getNumV() {
        return numV;
    }

    /**
     * Returns a boolean value indicating whether graph is directed
     * @return boolean true(directed) false(undirected)
     */
    public boolean isDirected() {
        return directed;
    }

    /**
     * Insert a new edge into graph.
     * @param edge The new edge
     */
    @Override
    public void insert(Edge edge) {
        if (isEdge(edge.getSource() , edge.getDest())) return;
        edges[edge.getSource()].add(edge);
        if (!isDirected()) {
            edges[edge.getDest()].add(new Edge(edge.getDest(), edge.getSource(), edge.getWeight()));
        }
    }

    /**
     * Determine whether an edge exists.
     * @param source The source vertex
     * @param dest The destination vertex
     * @return true if there is an edge from source to dest
     */
    @Override
    public boolean isEdge(int source, int dest) {
        if(source> numV || dest > numV || vertexArr.get(source) == null || vertexArr.get(dest) == null){
            return false;
        }
        return edges[source].contains(new Edge(source , dest));
    }

    /**
     * Get the edge between two vertices.
     * @param source The source vertex
     * @param dest The destination vertex
     * @return  The Edge between these two vertices
     *          or an Edge with weight of Double.POSITIVE_INFINITY if there is no edge
     */
    @Override
    public Edge getEdge(int source, int dest) {
        if(source> numV || dest > numV || vertexArr.get(source) == null || vertexArr.get(dest) == null){
            return null;
        }

        Edge target = new Edge(source , dest , Double.POSITIVE_INFINITY);
        for (Edge edge : edges[source]) {
            if (edge.equals(target)){
                return edge;
            }
        }
        return target;
    }

    /**
     * Returns an iterator to the edges connected to a given index.
     * @param source The source vertex
     * @return An Iterator<Edge> to the vertices connected to source
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) {
        if(vertexArr.get(source) == null || source > numV)
            return null;
        return edges[source].listIterator();
    }

    /**
     * Generate a new vertex by given parameters
     * @param label - label of the new vertex
     * @param weight - weight value of the new vertex
     * @return - returns the vertex created
     */
    @Override
    public Vertex newVertex(String label, double weight) {
        numV++;
        return new Vertex(numV, label, weight);
    }

    /**
     * Add the given vertex to the graph
     * @param new_vertex - The new vertex to be added to the graph
     * @return - if add operation done successfully returns true ; otherwise returns false
     */
    @Override
    public boolean addVertex(Vertex new_vertex) {
        if (vertexArr.contains(new_vertex)) return false;
        vertexArr.add(new_vertex);
        return true;
    }

    /**
     * Remove the vertex from the graph with respect to the given vertex id.
     * @param vertexID - vertex's ID value
     * @return - if remove operation done successfully returns deleted vertex ; otherwise returns null
     */
    @Override
    public Vertex removeVertex(int vertexID) {
        if(vertexID > numV || vertexArr.get(vertexID) == null){
            return null;
        }

        if(!isDirected()){  // our graph is undirected 2 edges for each 2 vertex
            for (Edge edge : edges[vertexID]) {
                edgeRemover(edge.getDest(), vertexID);
            }
        }
        else{               // our graph is directed 1 edges for each 2 vertex
            for (int i = 0; i <= numV; i++) {
                if(vertexArr.get(i) == null) continue;
                for (Edge edge : edges[i]) {
                    if (edge.getDest() == vertexID)
                        removeEdge(edge.getSource(), edge.getDest());
                }
            }
        }
        edges[vertexID] = null;

        Vertex result = vertexArr.get(vertexID);
        vertexArr.set(vertexID , null);
        return result;
    }

    /**
     * Remove the vertices that have the given label from the graph.
     * @param label - given label value
     */
    @Override
    public void removeVertex(String label) {
        for (int i = 0; i <= numV; i++) {
            if(vertexArr.get(i) == null) continue;
            if (vertexArr.get(i).label.equals(label)){
                removeVertex(i);
                return;
            }
        }
    }

    /**
     * Add an edge between the given two vertices in the graph.
     * @param vertexID1 - source vertex's id number
     * @param vertexID2 - destination vertex's id number
     * @param weight - weight value of the edge
     * @return - if add operation done successfully returns true ; otherwise returns false
     */
    @Override
    public boolean addEdge(int vertexID1, int vertexID2, double weight) {
        if (vertexID1 == vertexID2){
            System.out.println("Self edge not allowed!!");
            return false;
        }
        if(vertexID1 > numV || vertexID2 > numV || vertexArr.get(vertexID1) == null || vertexArr.get(vertexID2) == null){
            System.out.printf("Graph does not have vertex with id:%d or vertex with id:%d !..\n" ,vertexID1 , vertexID2);
            return false;
        }
        insert(new Edge(vertexID1,  vertexID2, weight));
        return true;
    }

    /**
     * Remove the edge between the given two vertices.
     * @param vertexID1 - source vertex's id number
     * @param vertexID2 - destination vertex's id number
     * @return if remove operation done successfully returns deleted edge ; otherwise returns null
     */
    @Override
    public Edge removeEdge(int vertexID1, int vertexID2) {
        if(!isEdge(vertexID1 , vertexID2)){
            return null;
        }
        Edge result = getEdge(vertexID1 , vertexID2);
        Iterator<Edge> iter1 = edgeIterator(vertexID1);
        while(iter1.hasNext()){
            if(iter1.next().getDest() == vertexID2){
                iter1.remove();
                break;
            }
        }
        if (!isDirected()){
            Iterator<Edge> iter2 = edgeIterator(vertexID2);
            while(iter2.hasNext()){
                if(iter2.next().getDest() == vertexID1){
                    iter2.remove();
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Filter the vertices by the given user-defined property and returns a subgraph of the graph.
     * @param key - the property name
     * @param filter - property's content
     * @return - returns a filtered subgraph of the graph.
     */
    @Override
    public DynamicGraph filterVertices(String key, String filter) {

        MyGraph temp = new MyGraph(isDirected());
        temp.numV = this.numV;
        temp.vertexArr = new ArrayList<>(numV);
        for (int i = 0; i <= temp.numV; i++) {
//            temp.vertexArr.add(new Vertex(vertexArr.get(i).ID ,vertexArr.get(i).label , vertexArr.get(i).weight));
            temp.vertexArr.add(vertexArr.get(i));
        }
        for (int i = 0; i <= numV; i++) {
            if(vertexArr.get(i) == null) continue;
            for (Edge edge : edges[i]) {
                temp.insert(edge);
            }
        }

        for (int i = 0; i <= numV; i++) {
            if (temp.vertexArr.get(i) == null) continue;
            if (!filter.equals(temp.vertexArr.get(i).properties.get(key))){
                temp.removeVertex(i);
            }
        }

        return temp;
    }

    /**
     * Generate the adjacency matrix representation of the graph and returns the matrix.
     * @return returns the matrix.
     */
    @Override
    public double[][] exportMatrix() {
        double[][] matrix = new double[numV+1][numV+1];
        for (int i = 0; i <= numV; i++) {
            for (int j = 0; j <= numV; j++) {
                if (isEdge(i , j)){
                    matrix[i][j] = getEdge(i , j).getWeight();
                }
                else
                    matrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        return matrix;
    }

    /**
     * Print the graph in adjacency list format.
     */
    @Override
    public void printGraph() {
        for (int i = 0; i <= numV; i++) {
            if (vertexArr.get(i) == null || edges[i].size() == 0){
                System.out.println("null");
                continue;
            }
            for (Edge edge : edges[i]) {
                System.out.print(edge.toString() + " ");
            }
            System.out.println();
        }
    }

    /**
     * This method is helper method of removeVertex method
     * it removes just one side of the edges
     * @param vertexID1 - source vertex's id number
     * @param vertexID2 - destination vertex's id number
     */
    private void edgeRemover(int vertexID1, int vertexID2) {   //helper method of removeVertex
        if(!isEdge(vertexID1 , vertexID2)){
            return;
        }
        Iterator<Edge> iter1 = edgeIterator(vertexID1);
        while(iter1.hasNext()){
            if(iter1.next().getDest() == vertexID2){
                iter1.remove();
                break;
            }
        }
    }
    /**
     * Print the graph in adjacency matrix format.
     */
    public void printMatrix(double[][] matrix){

        System.out.println();
        System.out.printf(" %4s  "," ");
        for (int i = 0; i <= numV; i++) {
            System.out.printf(" %4s  ",i);
        }
        System.out.println();
        for (int i = 0; i <= numV; i++) {
            System.out.printf(" %4s |",i);
            for (int j = 0; j <= numV; j++) {
                if (isEdge(i , j)){
                    System.out.printf(" %4s |", matrix[i][j] );
                }
                else
                    System.out.printf(" %4s |","#");
            }
            System.out.println();
        }
    }

    /** Dijkstra's Shortest-Path algorithm.
     @param myGraph The weighted graph to be searched
     @param start The start vertex
     @param pred Output array to contain the predecessors
                in the shortest path
     @param dist Output array to contain the distance
                in the shortest path
     */
    public static void modifiedDijkstra(MyGraph myGraph, Vertex start, int[] pred, double[] dist) {

        int numV = myGraph.getNumV();
        HashSet <Integer> vMinusS = new HashSet <Integer> (numV+1);
        // Initialize V-S.
        for (int i = 0; i <= numV; i++) {
//            if (myGraph.vertexArr.get(i) == null) continue;
            if (i != start.getID()) {
                vMinusS.add(i);
            }
        }
        // Initialize pred and dist.
        for (int v : vMinusS) {
            pred[v] = start.getID();
            dist[v] = myGraph.getEdge(start.getID(), v).getWeight();
        }
        // Main loop
        while (vMinusS.size() != 0) {
            // Find the value u in V-S with the smallest dist[u].
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;
            for (int v : vMinusS) {
                if (dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }
            // Remove u from vMinusS.
            vMinusS.remove(u);
            // Update the distances.
            for (int v : vMinusS) {
                if (myGraph.isEdge(u, v)) {
                    double weight = myGraph.getEdge(u, v).getWeight();
                    if(u != start.getID() && myGraph.vertexArr.get(u).properties.containsKey("Boosting")){
                        if (dist[u] + weight - Double.parseDouble(myGraph.vertexArr.get(u).properties.get("Boosting")) < dist[v]) {
                            dist[v] = dist[u] + weight - Double.parseDouble(myGraph.vertexArr.get(u).properties.get("Boosting"));
                            pred[v] = u;
                        }
                    }
                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pred[v] = u;
                    }
                }
            }
        }
    }

}
