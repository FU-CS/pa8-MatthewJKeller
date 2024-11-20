package pa8;
import java.util.*;

public class GraphList implements Graph {
    
    private Map<Integer, ArrayList<Integer>> map = new HashMap<>();

    public void addEdge(int v, int w) {
        map.putIfAbsent(v, new ArrayList<>());
        map.get(v).add(w);
    }

    public void addWeightedEdge(int v, int w, int weight) {
        this.addEdge(v, w);
    }

    public String bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        String concat = "";

        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            concat = concat + " " + x;

            ArrayList<Integer> neighbors = map.get(x);
            if (neighbors != null) {
                int i = 0;
                while (i < neighbors.size()) {
                    if (!visited.contains(neighbors.get(i))) {
                        queue.add(neighbors.get(i));
                        visited.add(neighbors.get(i));
                    }
                    i = i + 1;
                }
            }
        }
        return concat.trim(); 
    }

    public String dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        String concat = "";
        return dfsHelper(start, visited, concat);
    }

    private String dfsHelper(int node, Set<Integer> visited, String concat) {
        visited.add(node);
        concat += " " + node;

        ArrayList<Integer> neighbors = map.get(node);
        if (neighbors != null) {
            int i = 0;
            while (i < neighbors.size()) {
                if (!visited.contains(neighbors.get(i))) {
                    concat = dfsHelper(neighbors.get(i), visited, concat);
                }
                i = i + 1;
            }
        }

        return concat.trim(); 
    }

    public boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> curr = new HashSet<>();

        for (int node : map.keySet()) {
            if (!visited.contains(node)) {
                if (dfsCycleHelper(node, visited, curr)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsCycleHelper(int node, Set<Integer> visited, Set<Integer> curr) {
        curr.add(node);

        ArrayList<Integer> neighbors = map.get(node);
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (curr.contains(neighbor)) {
                    return true;
                }

                if (!visited.contains(neighbor)) {
                    if (dfsCycleHelper(neighbor, visited, curr)) {
                        return true;
                    }
                }
            }
        }

        curr.remove(node);
        visited.add(node);
        return false;
    }

    public String shortestPath(int v, int w) {
        if (v == w) {
            return String.valueOf(v);  
        }
    
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();
        String concat = "";
    
        queue.add(v);
        visited.add(v);
        parent.put(v, null);  
    
        while (!queue.isEmpty()) {
            int x = queue.poll();
            
            if (x == w) {
                
                Integer current = w;
                while (current != null) {
                    concat = current + " " + concat;  
                    current = parent.get(current);
                }
                return concat.trim();
            }
    
            ArrayList<Integer> neighbors = map.get(x);
            if (neighbors != null) {
                for (int neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                        parent.put(neighbor, x);
                    }
                }
            }
        }
    
        return "0";
    }
    
}
