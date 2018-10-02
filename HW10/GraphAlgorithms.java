import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.HashMap;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Edward Xia
 * @userid exia3
 * @GTID 903191169
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Inputs can not be null.");
        }
        List<Vertex<T>> list = new ArrayList<>();
        Queue<T> queue = new LinkedList<>();
        queue.add((T) start);
        list.add(start);
        while (!queue.isEmpty()) {
            Vertex<T> node = (Vertex<T>) queue.remove();
            ArrayList<T> edges = (ArrayList<T>) graph.getAdjList().get(node);
            if (edges == null) {
                throw new IllegalArgumentException("Start not found in graph");
            }
            for (Object i: edges) {
                Edge<T> edge = (Edge<T>) i;
                if (!list.contains(edge.getV())) {
                    list.add(edge.getV());
                    queue.add((T) edge.getV());
                }
            }
        }
        return list;
    }


    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Inputs can not be null.");
        }
        if (graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException("Start is not found in graph.");
        }
        List<Vertex<T>> visited = new ArrayList<>();
        dfs(start, graph, visited);
        return visited;
    }

    /**
     * Helper function for depthFirstSearch to give recursive properties
     * @param node a Vertex
     * @param graph the graph to search through
     * @param visited list of visited vertexes
     * @param <T> the generic typing of the data
     */
    private static <T> void dfs(Vertex<T> node, Graph<T> graph, List visited) {
        visited.add(node);
        ArrayList<T> edges = (ArrayList<T>) graph.getAdjList().get(node);
        for (Object i: edges) {
            Edge<T> edge = (Edge<T>) i;
            Vertex<T> v = edge.getV();
            if (!visited.contains(v)) {
                dfs(v, graph, visited);
            }
        }
    }


    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     *
     * You should implement the version of Dijkstra's where you terminate the
     * algorithm once either all vertices have been visited or the PQ becomes
     * empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                      Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Inputs can not be null.");
        }
        if (graph.getAdjList().get(start) == null) {
            throw new IllegalArgumentException("Start is not found in graph.");
        }
        Map<Vertex<T>, Integer> dist = new HashMap<>();
        Set<Vertex<T>> visited = new HashSet<>();
        PriorityQueue<Edge<T>> pQ = new PriorityQueue<>();
        pQ.add(new Edge<>(start, start, 0));
        for (Map.Entry i : graph.getAdjList().entrySet()) {
            Vertex<T> a = (Vertex<T>) i.getKey();
            dist.put(a, Integer.MAX_VALUE);
        }
        dist.put(start, 0);
        while (!pQ.isEmpty() && graph.getAdjList().size() != visited.size()) {
            Edge<T> curEdge = pQ.remove();
            Vertex<T> node = curEdge.getV();
            visited.add(node);
            ArrayList<T> edges = (ArrayList<T>) graph.getAdjList().get(node);
            for (Object i: edges) {
                Edge<T> edge = (Edge<T>) i;
                if (dist.get(edge.getV()) > (dist.get(node)
                        + edge.getWeight())) {
                    dist.put(edge.getV(), dist.get(node) + edge.getWeight());
                    pQ.add(edge);
                }
            }
        }
        return dist;
    }


    /**
     * Runs Prim's algorithm on the given graph and return the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * opposite edge to the set as well. This is for testing purposes.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface, as long as it's efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Inputs can not be null.");
        }
        Set<Edge<T>> set = new HashSet<>();
        Set<Vertex<T>> visited = new HashSet<>();
        PriorityQueue<Edge<T>> pQ = new PriorityQueue<>();
        pQ.add(new Edge<>(start, start, 0));
        ArrayList<T> edges = (ArrayList<T>) graph.getAdjList().get(start);
        if (edges == null) {
            throw new IllegalArgumentException("Start is not found in graph.");
        }
        for (Object i : edges) {
            Edge<T> edge = (Edge<T>) i;
            pQ.add(edge);
        }
        visited.add(start);
        while (!pQ.isEmpty() && graph.getAdjList().size() != visited.size()) {
            Edge<T> curEdge = pQ.remove();
            Vertex<T> node = curEdge.getV();
            if (!visited.contains(curEdge.getV())) {
                visited.add(node);
                set.add(curEdge);
                set.add(new Edge<>(curEdge.getV(), curEdge.getU(),
                        curEdge.getWeight()));
                ArrayList<T> edges2 = (ArrayList<T>) graph.getAdjList()
                        .get(node);
                for (Object i : edges2) {
                    Edge<T> edge = (Edge<T>) i;
                    if (!visited.contains(edge.getV())) {
                        pQ.add(edge);
                    }
                }
            }
        }
        if (graph.getAdjList().size() != visited.size()) {
            return null;
        }
        return set;
    }
}