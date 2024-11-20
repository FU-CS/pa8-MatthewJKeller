package pa8;

import java.util.*;

public class GraphListWeighted implements Graph {

    private Map<Integer, ArrayList<Pair<Integer, Integer>>> map = new HashMap<>();

    public void addEdge(int v, int w) {
        addWeightedEdge(v, w, 1); 
    }

    public void addWeightedEdge(int v, int w, int weight) {
        map.putIfAbsent(v, new ArrayList<>());
        map.get(v).add(new Pair<>(w, weight));
    }

    public String bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        String concat = "";  
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int x = queue.poll();
            concat += " " + x;  

            ArrayList<Pair<Integer, Integer>> neighbors = map.get(x);
            if (neighbors != null) {
                for (Pair<Integer, Integer> neighbor : neighbors) {
                    if (!visited.contains(neighbor.getKey())) {
                        queue.add(neighbor.getKey());
                        visited.add(neighbor.getKey());
                    }
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

        ArrayList<Pair<Integer, Integer>> neighbors = map.get(node);
        if (neighbors != null) {
            for (Pair<Integer, Integer> edge : neighbors) {
                if (!visited.contains(edge.getKey())) {
                    concat = dfsHelper(edge.getKey(), visited, concat);
                }
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

        ArrayList<Pair<Integer, Integer>> neighbors = map.get(node);
        if (neighbors != null) {
            for (Pair<Integer, Integer> edge : neighbors) {
                if (curr.contains(edge.getKey())) {
                    return true;
                }

                if (!visited.contains(edge.getKey())) {
                    if (dfsCycleHelper(edge.getKey(), visited, curr)) {
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
    
            ArrayList<Pair<Integer,Integer>> neighbors = map.get(x);
            if (neighbors != null) {
                for (Pair<Integer,Integer> neighbor : neighbors) {
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
