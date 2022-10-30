package com.Ali;

/**
 * DynamicGraph interface contains the methods we need to create a dynamic graph
 * @author Ali Kaya
 */
public interface DynamicGraph extends Graph{
    /**
     * Generate a new vertex by given parameters
     * @param label - label of the new vertex
     * @param weight - weight value of the new vertex
     * @return - returns the vertex created
     */
    Vertex newVertex (String label, double weight);

    /**
     * Add the given vertex to the graph
     * @param new_vertex - The new vertex to be added to the graph
     * @return - if add operation done successfully returns true ; otherwise returns false
     */
    boolean addVertex (Vertex new_vertex);

    /**
     * Remove the vertex from the graph with respect to the given vertex id.
     * @param vertexID - vertex's ID value
     * @return - if remove operation done successfully returns deleted vertex ; otherwise returns null
     */
    Vertex removeVertex (int vertexID);

    /**
     * Remove the vertices that have the given label from the graph.
     * @param label - given label value
     */
    void removeVertex (String label);

    /**
     * Add an edge between the given two vertices in the graph.
     * @param vertexID1 - source vertex's id number
     * @param vertexID2 - destination vertex's id number
     * @param weight - weight value of the edge
     * @return - if add operation done successfully returns true ; otherwise returns false
     */
    boolean addEdge (int vertexID1, int vertexID2, double weight);

    /**
     * Remove the edge between the given two vertices.
     * @param vertexID1 - source vertex's id number
     * @param vertexID2 - destination vertex's id number
     * @return if remove operation done successfully returns deleted edge ; otherwise returns null
     */
    Edge removeEdge (int vertexID1, int vertexID2);

    /**
     * Filter the vertices by the given user-defined property and returns a subgraph of the graph.
     * @param key - the property name
     * @param filter - property's content
     * @return - returns a filtered subgraph of the graph.
     */
    DynamicGraph filterVertices (String key, String filter);

    /**
     * Generate the adjacency matrix representation of the graph and returns the matrix.
     * @return returns the matrix.
     */
    double[][] exportMatrix();

    /**
     * Print the graph in adjacency list format.
     */
    void printGraph();
}
