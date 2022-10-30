package com.Ali;

import java.util.Iterator;

public interface Graph {
    /**
     * Insert a new edge into graph.
     * @param edge The new edge
     */
    void insert(Edge edge);

    /**
     * Determine whether an edge exists.
     * @param source The source vertex
     * @param dest The destination vertex
     * @return true if there is an edge from source to dest
     */
    boolean isEdge(int source , int dest);

    /**
     * Get the edge between two vertices.
     * @param source The source vertex
     * @param dest The destination vertex
     * @return  The Edge between these two vertices
     *          or an Edge with weight of Double.POSITIVE_INFINITY if there is no edge
     */
    Edge getEdge(int source , int dest);

    /**
     * Returns an iterator to the edges connected to a given index.
     * @param source The source vertex
     * @return An Iterator<Edge> to the vertices connected to source
     */
    Iterator<Edge> edgeIterator(int source);
}
