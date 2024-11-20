package pa8;
import java.util.*;

public class GraphMatrix implements Graph{

    private int [][] matrix;

    public GraphMatrix(int n_nodes){
        this.matrix = new int[n_nodes][n_nodes];
    }

    public void addEdge(int v, int w){
        this.matrix[v][w] = 1;
    }

    public void addWeightedEdge(int v, int w, int weight){
        this.addEdge(v, w);
    }




    public String bfs(int start) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<>(); 
        String concat = ""; 
        queue.add(start);
        visited.add(start); 

        while (!queue.isEmpty()) {
            int x = queue.poll();
            concat = concat + " " + x;

            int[] neighbors = this.matrix[x];
            for (int i = 0; i < neighbors.length; i++) {
                if (neighbors[i] != 0 && !visited.contains(i)) {
                    queue.add(i);
                    visited.add(i);
                }
            }
        }
        return concat.trim();
    }


    public String dfs(int start) {
        boolean[] visited = new boolean[matrix.length];  
        String concat = "";
        return dfsHelper(start, visited, concat);  
    }
    
    private String dfsHelper(int node, boolean[] visited, String concat) {
        visited[node] = true; 
        concat += " " + node; 
    
        int[] neighbors = this.matrix[node];
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] != 0 && !visited[i]) {  
                concat = dfsHelper(i, visited, concat);
            }
        }
        
        return concat.trim();  
    }
    
    public boolean hasCycle() {
        int[] visited = new int[this.matrix.length];
        Arrays.fill(visited, 0);
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 0) {
                if (dfs2(i, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs2(int start, int[] visited) {
        visited[start] = 1;
        
        int[] neighbors = this.matrix[start];
        for (int i = 0; i < neighbors.length; i++) {
            if (neighbors[i] != 0) {
                if (visited[i] == 1) {
                    return true;
                }
                if (visited[i] == 0) {
                    if (dfs2(i, visited)) {
                        return true;
                    }
                }
            }
        }
        
        visited[start] = 2;
        return false;
    }
    
   
    public String shortestPath(int source, int destination) {
        double[] cost = new double[matrix.length];
        boolean[] visited = new boolean[matrix.length];
        int[] previous = new int[matrix.length];
    
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        Arrays.fill(previous, -1);
        cost[source] = 0; 
        visited[source] = true;
    
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
    
        while (!q.isEmpty()) {
            int curr = q.poll();
    
            if (curr == destination) {
                break;
            }
    
            int[] currRow = this.matrix[curr];
    
            for (int i = 0; i < currRow.length; i++) {
                if (currRow[i] != 0 && !visited[i]) {  
                    double cost_edge = currRow[i];
                    double new_cost = cost[curr] + cost_edge;
    
                    if (new_cost < cost[i]) {
                        cost[i] = new_cost;
                        previous[i] = curr;
                        q.add(i);  
                    }
                }
            }
            visited[curr] = true;
        }
    
        String concat = "";
        int node = destination;
    
        if (previous[node] == -1) {
            return "0";
        }
    
        while (node != -1) {
            concat = " " + node + concat;  
            node = previous[node];  
        }
    
        return concat.trim();
    }
   
}    