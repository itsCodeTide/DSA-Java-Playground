// File: Graph.java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

/**
 * Represents a Graph data structure using an Adjacency List.
 * This implementation supports both undirected and directed graphs (by choice).
 * For simplicity, vertices are represented by integers.
 */
public class Graph {
    // Adjacency List: Maps a vertex to a list of its adjacent vertices.
    private Map<Integer, List<Integer>> adjList;
    private boolean isDirected; // Flag to indicate if the graph is directed or undirected.

    /**
     * Constructor for Graph.
     * @param isDirected true for a directed graph, false for an undirected graph.
     */
    public Graph(boolean isDirected) {
        this.adjList = new HashMap<>();
        this.isDirected = isDirected;
        System.out.println("Graph created. Directed: " + isDirected);
    }

    // --- 1. Add Vertex ---

    /**
     * Adds a new vertex to the graph.
     * If the vertex already exists, no action is taken.
     * Time Complexity: O(1) on average (HashMap operation).
     * @param vertex The vertex to add.
     */
    public void addVertex(int vertex) {
        if (!adjList.containsKey(vertex)) {
            adjList.put(vertex, new ArrayList<>());
            System.out.println("Added vertex: " + vertex);
        } else {
            System.out.println("Vertex " + vertex + " already exists.");
        }
    }

    // --- 2. Remove Vertex ---

    /**
     * Removes a vertex and all its incident edges from the graph.
     * Time Complexity: O(V + E) in worst case (iterating through all lists to remove references).
     * @param vertex The vertex to remove.
     */
    public void removeVertex(int vertex) {
        if (!adjList.containsKey(vertex)) {
            System.out.println("Vertex " + vertex + " not found. Cannot remove.");
            return;
        }

        // Remove all edges pointing TO this vertex from other vertices.
        for (List<Integer> neighbors : adjList.values()) {
            neighbors.remove(Integer.valueOf(vertex)); // Remove all occurrences of 'vertex' from neighbor lists.
        }

        // Remove the vertex itself from the adjacency list.
        adjList.remove(vertex);
        System.out.println("Removed vertex: " + vertex + " and all its edges.");
    }

    // --- 3. Add Edge ---

    /**
     * Adds an edge between two vertices.
     * Vertices are automatically added if they don't exist.
     * Time Complexity: O(1) on average (HashMap and ArrayList add).
     * @param u The first vertex.
     * @param v The second vertex.
     * @param weight (Optional) The weight of the edge. For unweighted, can be ignored or default to 1.
     */
    public void addEdge(int u, int v) {
        // Ensure both vertices exist in the graph.
        addVertex(u);
        addVertex(v);

        // Add edge from u to v.
        adjList.get(u).add(v);

        // If undirected, also add edge from v to u.
        if (!isDirected) {
            adjList.get(v).add(u);
        }
        System.out.println("Added edge: " + u + " -> " + v + (isDirected ? "" : " (undirected)"));
    }

    // --- 4. Remove Edge ---

    /**
     * Removes an edge between two vertices.
     * Time Complexity: O(degree(u)) in worst case (list removal).
     * @param u The first vertex.
     * @param v The second vertex.
     */
    public void removeEdge(int u, int v) {
        if (!adjList.containsKey(u) || !adjList.containsKey(v)) {
            System.out.println("Cannot remove edge: One or both vertices not found.");
            return;
        }

        // Remove edge from u to v.
        adjList.get(u).remove(Integer.valueOf(v));

        // If undirected, also remove edge from v to u.
        if (!isDirected) {
            adjList.get(v).remove(Integer.valueOf(u));
        }
        System.out.println("Removed edge: " + u + " -> " + v + (isDirected ? "" : " (undirected)"));
    }

    // --- 5. Check Adjacency ---

    /**
     * Checks if an edge exists between two vertices.
     * Time Complexity: O(degree(u)) in worst case (list contains check).
     * @param u The first vertex.
     * @param v The second vertex.
     * @return true if an edge exists, false otherwise.
     */
    public boolean isAdjacent(int u, int v) {
        if (!adjList.containsKey(u) || !adjList.containsKey(v)) {
            return false; // Vertices don't exist.
        }
        return adjList.get(u).contains(v);
    }

    // --- 6. Graph Traversal: Breadth-First Search (BFS) ---

    /**
     * Performs a Breadth-First Search (BFS) starting from a given vertex.
     * Visits vertices layer by layer.
     * Time Complexity: O(V + E).
     * @param startVertex The vertex to start the BFS from.
     */
    public void bfs(int startVertex) {
        if (!adjList.containsKey(startVertex)) {
            System.out.println("BFS: Start vertex " + startVertex + " not found.");
            return;
        }

        System.out.print("\nBFS Traversal (starting from " + startVertex + "): ");
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>(); // Keep track of visited vertices to avoid cycles.

        queue.offer(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            // Get neighbors of the current vertex
            List<Integer> neighbors = adjList.get(currentVertex);
            if (neighbors != null) { // Check if neighbors list exists (e.g., for isolated vertex)
                for (int neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    // --- 7. Graph Traversal: Depth-First Search (DFS) ---

    /**
     * Performs a Depth-First Search (DFS) starting from a given vertex.
     * Explores as far as possible along each branch before backtracking.
     * Delegates to a recursive helper method.
     * Time Complexity: O(V + E).
     * @param startVertex The vertex to start the DFS from.
     */
    public void dfs(int startVertex) {
        if (!adjList.containsKey(startVertex)) {
            System.out.println("DFS: Start vertex " + startVertex + " not found.");
            return;
        }

        System.out.print("\nDFS Traversal (starting from " + startVertex + "): ");
        Set<Integer> visited = new HashSet<>(); // Keep track of visited vertices.
        dfsRecursive(startVertex, visited);
        System.out.println();
    }

    /**
     * Helper method for recursive DFS.
     * @param currentVertex The current vertex being visited.
     * @param visited A set of already visited vertices.
     */
    private void dfsRecursive(int currentVertex, Set<Integer> visited) {
        visited.add(currentVertex);
        System.out.print(currentVertex + " ");

        List<Integer> neighbors = adjList.get(currentVertex);
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    dfsRecursive(neighbor, visited); // Recursively call for unvisited neighbors.
                }
            }
        }
    }

    /**
     * Utility method to print the adjacency list representation of the graph.
     */
    public void printGraph() {
        System.out.println("\n--- Graph Adjacency List ---");
        if (adjList.isEmpty()) {
            System.out.println("(Graph is empty)");
            return;
        }
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
            System.out.println("Vertex " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("--------------------------");
    }

    /**
     * Main method to demonstrate basic Graph operations.
     */
    public static void main(String[] args) {
        System.out.println("--- Demonstrating Undirected Graph ---");
        Graph undirectedGraph = new Graph(false); // Create an undirected graph

        // Add Vertices
        undirectedGraph.addVertex(0);
        undirectedGraph.addVertex(1);
        undirectedGraph.addVertex(2);
        undirectedGraph.addVertex(3);
        undirectedGraph.addVertex(4);
        undirectedGraph.addVertex(5); // An isolated vertex initially

        // Add Edges
        undirectedGraph.addEdge(0, 1);
        undirectedGraph.addEdge(0, 2);
        undirectedGraph.addEdge(1, 3);
        undirectedGraph.addEdge(2, 3);
        undirectedGraph.addEdge(3, 4);

        undirectedGraph.printGraph();

        // Check Adjacency
        System.out.println("\n--- Checking Adjacency ---");
        System.out.println("Is 0 adjacent to 1? " + undirectedGraph.isAdjacent(0, 1)); // true
        System.out.println("Is 0 adjacent to 3? " + undirectedGraph.isAdjacent(0, 3)); // false
        System.out.println("Is 4 adjacent to 5? " + undirectedGraph.isAdjacent(4, 5)); // false (5 is isolated)

        // Traversal
        System.out.println("\n--- Graph Traversal ---");
        undirectedGraph.bfs(0); // BFS from vertex 0
        undirectedGraph.dfs(0); // DFS from vertex 0
        undirectedGraph.bfs(5); // BFS from isolated vertex 5

        // Remove Edge
        System.out.println("\n--- Removing Edge ---");
        undirectedGraph.removeEdge(0, 1);
        undirectedGraph.printGraph();
        System.out.println("Is 0 adjacent to 1? " + undirectedGraph.isAdjacent(0, 1)); // false

        // Remove Vertex
        System.out.println("\n--- Removing Vertex ---");
        undirectedGraph.removeVertex(3); // Remove vertex 3 and its connections
        undirectedGraph.printGraph();
        System.out.println("Is 1 adjacent to 3? " + undirectedGraph.isAdjacent(1, 3)); // false (3 is gone)
        System.out.println("Is 2 adjacent to 3? " + undirectedGraph.isAdjacent(2, 3)); // false

        System.out.println("\n--- Demonstrating Directed Graph ---");
        Graph directedGraph = new Graph(true); // Create a directed graph

        directedGraph.addEdge(0, 1); // 0 -> 1
        directedGraph.addEdge(0, 2); // 0 -> 2
        directedGraph.addEdge(1, 3); // 1 -> 3
        directedGraph.addEdge(2, 3); // 2 -> 3
        directedGraph.addEdge(3, 0); // 3 -> 0 (creates a cycle)

        directedGraph.printGraph();

        System.out.println("Is 0 adjacent to 1? " + directedGraph.isAdjacent(0, 1)); // true
        System.out.println("Is 1 adjacent to 0? " + directedGraph.isAdjacent(1, 0)); // false (directed)

        directedGraph.bfs(0); // BFS from 0
        directedGraph.dfs(0); // DFS from 0
    }
}


