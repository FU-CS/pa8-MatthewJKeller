package pa8;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphListTest {
    @Test
    public void TestHasCycle() {
        GraphListWeighted graph = new GraphListWeighted();
        graph.addWeightedEdge(0, 0, 1);  
        graph.addWeightedEdge(0, 3, 1);
        graph.addWeightedEdge(3, 4, 1);
        graph.addWeightedEdge(4, 0, 1);  

        assertTrue(graph.hasCycle());
    }

    @Test
    public void testNoCycle() {
        GraphListWeighted graph = new GraphListWeighted();
        graph.addWeightedEdge(0, 1, 1);
        graph.addWeightedEdge(1, 2, 1);
        graph.addWeightedEdge(2, 3, 1);

        assertFalse(graph.hasCycle());
    }

    @Test
    public void testBFS() {
        GraphListWeighted graph = new GraphListWeighted();
        graph.addWeightedEdge(0, 1, 2);
        graph.addWeightedEdge(0, 2, 3);
        graph.addWeightedEdge(1, 3, 1);

        String result = graph.bfs(0);
        assertEquals("0 1 2 3", result);
    }

    @Test
    public void testDFS() {
        GraphListWeighted graph = new GraphListWeighted();
        graph.addWeightedEdge(0, 1, 2);
        graph.addWeightedEdge(0, 2, 3);
        graph.addWeightedEdge(1, 3, 1);

        String result = graph.dfs(0);
        assertEquals("0 1 3 2", result); 
    }

    @Test
    public void testShortestPath() {
        GraphListWeighted graph = new GraphListWeighted();
        graph.addWeightedEdge(0, 1, 1);
        graph.addWeightedEdge(1, 2, 1);
        graph.addWeightedEdge(2, 3, 1);

        String path = graph.shortestPath(0, 3);
        assertEquals("0 1 2 3", path);  
    }

    @Test
    public void testShortestPathNoPath() {
        GraphListWeighted graph = new GraphListWeighted();
        graph.addWeightedEdge(0, 1, 2);
        graph.addWeightedEdge(1, 2, 3);

        String path = graph.shortestPath(0, 3);
        assertEquals("0", path); 
    }

    @Test
    public void testDisconnectedGraph() {
        GraphListWeighted graph = new GraphListWeighted();
        graph.addWeightedEdge(0, 1, 2);
        graph.addWeightedEdge(1, 2, 3);
        
        String path = graph.shortestPath(0, 4);
        assertEquals("0", path);  
    }

    @Test
    public void testDisconnectedIsland() {
        GraphListWeighted graph = new GraphListWeighted();
        graph.addWeightedEdge(0, 1, 2);
        graph.addWeightedEdge(1, 2, 3);
        graph.addWeightedEdge(3, 4, 4);

        String path1 = graph.shortestPath(0, 2);
        assertEquals("0 1 2", path1); 

        String path2 = graph.shortestPath(3, 5);
        assertEquals("3", path2); 
    }

    @Test
    public void testSingleNode() {
        GraphListWeighted graph = new GraphListWeighted();
        
        String path = graph.shortestPath(0, 0);
        assertEquals("0", path);  
    }

    @Test
    public void testCycleDetect() {
        GraphListWeighted graph = new GraphListWeighted();
        graph.addWeightedEdge(0, 1, 1);
        graph.addWeightedEdge(1, 2, 1);
        graph.addWeightedEdge(2, 3, 1);
        graph.addWeightedEdge(3, 0, 1);  

        assertTrue(graph.hasCycle());
    }

    @Test
    public void testSelfLoop() {
        GraphListWeighted graph = new GraphListWeighted();
        graph.addWeightedEdge(0, 0, 1);  
        graph.addWeightedEdge(1, 2, 3);
        
        String path = graph.shortestPath(0, 0);
        assertEquals("0", path);  
    }

    @Test
    public void testBFSDisconnect() {
        GraphListWeighted graph = new GraphListWeighted();
        graph.addWeightedEdge(0, 1, 1);
        graph.addWeightedEdge(1, 2, 2);
        graph.addWeightedEdge(3, 4, 3);
        
        String result = graph.bfs(0);
        assertEquals("0 1 2", result); 
        
        result = graph.bfs(3);
        assertEquals("3 4", result);
    }


}
