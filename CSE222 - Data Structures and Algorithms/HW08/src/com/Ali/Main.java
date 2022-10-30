package com.Ali;

import java.util.Arrays;

/**
 * Main class tests the MyGraph , DistanceDiff class' methods
 * @author Ali Kaya
 */
public class Main {
    /**
     * main method has Driver code which tests all methods of MyGraph class and DistanceDiff class
     * @param args - unused
     */
    public static void main(String[] args) {

        System.out.println("______________________________________________________");
        System.out.println("_____________________DRIVER CODE______________________");
        System.out.println("______________________________________________________\n");
        System.out.println("In this section Driver Code Tests all class' methods\n");

        System.out.println("________________TESTING MyGraph class________________\n");

        System.out.println("Let's create a MyGraph reference(undirected)\n");
        DynamicGraph myGraph = new MyGraph(false);

        System.out.println("Let's create some Vertex references with newVertex(label , weight) method. ");
        Vertex vertex0 = myGraph.newVertex("base1" , 2);
        Vertex vertex1 = myGraph.newVertex("base1" , 2);
        Vertex vertex2 = myGraph.newVertex("base2" , 2);
        Vertex vertex3 = myGraph.newVertex("base2" , 2);
        Vertex vertex4 = myGraph.newVertex("base3" , 2);
        Vertex vertex5 = myGraph.newVertex("base3" , 2);

        System.out.println("Let's add some new properties to the vertices we created");
        vertex0.addProperty("Color" , "Red");
        vertex0.addProperty("Boosting" , "2");
        vertex1.addProperty("Color" , "Purple");
        vertex1.addProperty("Boosting" , "2");
        vertex2.addProperty("Color" , "Purple");
        vertex2.addProperty("Boosting" , "2");
        vertex3.addProperty("Color" , "Purple");
        vertex3.addProperty("Boosting" , "3");
        vertex4.addProperty("Color" , "Purple");
        vertex4.addProperty("Boosting" , "3");
        vertex5.addProperty("Color" , "Red");
        vertex5.addProperty("Boosting" , "3");

        System.out.println("Let's put these vertices into our graph with addVertex(new_Vertex) method. ");
        myGraph.addVertex(vertex0);
        myGraph.addVertex(vertex1);
        myGraph.addVertex(vertex2);
        myGraph.addVertex(vertex3);
        myGraph.addVertex(vertex4);
        myGraph.addVertex(vertex5);

        System.out.println("Then let's add some edge to our graph with addEdge(vertexID1 , vertexID2 , weight) method " +
                "\nand print our graph with printGraph() method\n");
        myGraph.addEdge(0 , 1 , 3);
        myGraph.addEdge(0 , 4 , 7);
        myGraph.addEdge(1 , 4 , 2);
        myGraph.addEdge(1 , 2 , 1);
        myGraph.addEdge(1 , 3 , 9);
        myGraph.addEdge(3 , 4 , 8);
        myGraph.addEdge(2 , 3 , 2);
        myGraph.addEdge(5 , 0 , 5);
        myGraph.addEdge(5 , 4 , 6);
        myGraph.printGraph();

        System.out.println("\nThen let's tests removeEdge(vertexID1 , vertexID2) method with 1 , 2 values" +
                "\n(if this edge in our graph, method removes this edge and returns it; otherwise, returns null)" +
                "\nand print our graph again with printGraph() method\n");
        myGraph.removeEdge(1 , 2);
        myGraph.printGraph();

        System.out.println("\nThen let's tests filterVertices(key , filter) method with 'Boosting' , '2' values" +
                        "\n(Filters the vertices by the given user-defined property and returns a subgraph of the graph)" +
                "\nand print our SubGraph with printGraph() method\n");
        DynamicGraph filteredGraph1 = myGraph.filterVertices("Boosting" ,"2");
        filteredGraph1.printGraph();

        System.out.println("\nThen let's tests filterVertices(key , filter) method with 'Color' , 'Red' values" +
                "\n(Filters the vertices by the given user-defined property and returns a subgraph of the graph)" +
                "\nand print our SubGraph again with printGraph() method\n");
        DynamicGraph filteredGraph2 = myGraph.filterVertices("Color" ,"Red");
        filteredGraph2.printGraph();

        System.out.println("\nThen let's tests removeVertex(vertexID) method with value 0" +
                "\n(If this vertex in our graph, method removes this vertex along with the edges attached to this vertex" +
                "\nand returns this vertex ; otherwise, returns null)" +
                "\nand print our graph again with printGraph() method\n");
        myGraph.removeVertex(0);
        myGraph.printGraph();

        System.out.println("\nThen let's tests exportMatrix() method " +
                "\n(Generate the adjacency matrix representation of the graph and returns the matrix)\n");
        ((MyGraph)myGraph).printMatrix(myGraph.exportMatrix());

//        System.out.println("\nThen let's tests removeVertex(label) method with input 'base3'" +
//                "\n(If this vertex in our graph, method removes this vertex along with the edges attached to this vertex" +
//                "\nand returns this vertex ; otherwise, returns null)" +
//                "\nand print our graph again with printGraph() method\n");
//        myGraph.removeVertex("base3");
//        myGraph.printGraph();

        System.out.println("\n___________________TESTING Q2____________________\n");

        System.out.println("In this section we performs BFS and DFS traversals on our graph\n" +
                "and we calculate the difference between traverse method's total path distances\n");
        DistanceDiff q2 = new DistanceDiff((MyGraph) myGraph);
        System.out.print("The difference between the total distances of two traversal methods\n" +
                "totalDistanceBFS - totalDistanceDFS : ");
        System.out.println(q2.calculate());

        System.out.println("\n___________________TESTING Q3____________________\n");

        System.out.println("Let's create a graph again for third question(directed)\n");
        DynamicGraph myGraph2 = new MyGraph(true);

        Vertex v0 = myGraph2.newVertex("test" , 2);
        Vertex v1 = myGraph2.newVertex("test" , 2);
        Vertex v2 = myGraph2.newVertex("test" , 2);
        Vertex v3 = myGraph2.newVertex("test" , 2);
        Vertex v4 = myGraph2.newVertex("test" , 2);

        System.out.println("In order to test the 3rd question, we need to add a boosting properties to our vertexes.\n" +
                "Let's add this properties with addProperty() method\n");
        v0.addProperty("Boosting" , "2");
        v1.addProperty("Boosting" , "2");
        v2.addProperty("Boosting" , "2");
        v3.addProperty("Boosting" , "3");
        v4.addProperty("Boosting" , "3");

        System.out.println("Let's put these vertices into our graph with addVertex(new_Vertex) method. ");
        myGraph2.addVertex(v0);
        myGraph2.addVertex(v1);
        myGraph2.addVertex(v2);
        myGraph2.addVertex(v3);
        myGraph2.addVertex(v4);

        System.out.println("Then let's add some edge to our graph with addEdge(vertexID1 , vertexID2 , weight) method " +
                "\nand print our graph with printGraph() method\n");
        myGraph2.addEdge(0 , 1 , 10);
        myGraph2.addEdge(0 , 4 , 100);
        myGraph2.addEdge(0 , 3 , 30);
        myGraph2.addEdge(1 , 2 , 50);
        myGraph2.addEdge(2 , 4 , 10);
        myGraph2.addEdge(3 , 4 , 60);
        myGraph2.addEdge(3 , 2 , 20);
        myGraph2.printGraph();


        System.out.println("\nThen let's call the modifiedDijkstra() method " +
                "for calculating the shortest paths \n" +
                "from the given vertex to all other vertices in the graph.\n" +
                "In this method , the algorithm considers\n" +
                "boosting value of the vertices in addition to the edge weights.");
        Q3((MyGraph) myGraph2, vertex0);
    }

    /**
     * This method is used for testing modifiedDijkstra method
     * @param myGraph - a MyGraph reference
     * @param vertex - a Vertex reference
     */
    private static void Q3(MyGraph myGraph , Vertex vertex){

        int[] pred = new int[myGraph.getNumV()+1];
        double[] dist = new double[myGraph.getNumV()+1];
        MyGraph.modifiedDijkstra((MyGraph) myGraph, vertex , pred , dist);

        System.out.println();
        System.out.println("In order to see the distances print the dist array");
        System.out.println(Arrays.toString(dist));
    }
}
