package pa8;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphMatrixWeightedTest{

    @Test 
    public void TestHasCycle() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(5);
        graph.addEdge(0, 0);  
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);  

        assertTrue(graph.hasCycle());
    }

    @Test
    public void testNoCycle() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3); 

        assertFalse(graph.hasCycle());
    }

    @Test
    public void testBFS() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);

        String result = graph.bfs(0);
        assertEquals("0 1 2 3", result); 
    }

    @Test
    public void testDFS() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);

        String result = graph.dfs(0);
        assertEquals("0 1 3 2", result); 
    }

    @Test
    public void testShortestPath() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        String path = graph.shortestPath(0, 3);
        assertEquals("0 1 2 3", path);  
    }

    @Test
    public void testShortestPathNoPath() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        String path = graph.shortestPath(0, 3);
        assertEquals("0", path); 
    }

    @Test
    public void testDisconnectedGraph() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        
        String path = graph.shortestPath(0, 4);
        assertEquals("0", path);  
    }

    @Test
    public void testDisconnectIsland() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);

        String path1 = graph.shortestPath(0, 2);
        assertEquals("0 1 2", path1);

        String path2 = graph.shortestPath(3, 5);
        assertEquals("0", path2);  
    }

    @Test
    public void testSingleNodeGraph() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(1);
        
        String path = graph.shortestPath(0, 0);
        assertEquals("0", path);  
    }


    @Test
    public void testCycleDetect() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);  

        assertTrue(graph.hasCycle());
    }

    @Test
    public void testSelfLoop() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(4);
        graph.addEdge(0, 0); 
        graph.addEdge(1, 2);
        
        String path = graph.shortestPath(0, 0);
        assertEquals("0", path); 
    }

    @Test
    public void testBFSDisconnectedGraph() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        
        String result = graph.bfs(0);
        assertEquals("0 1 2", result);  
        
        result = graph.bfs(3);
        assertEquals("3 4", result);  
    }

    
    @Test
    public void testWeightedEdges() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(4);
        graph.addWeightedEdge(0, 1, 5);
        graph.addWeightedEdge(1, 2, 3);
        graph.addWeightedEdge(2, 3, 1);

        String path = graph.shortestPath(0, 3);
        assertEquals("0 1 2 3", path);  
    }

    @Test
    public void testWeightedGraphNoPath() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(4);
        graph.addWeightedEdge(0, 1, 5);
        graph.addWeightedEdge(1, 2, 3);

        String path = graph.shortestPath(0, 3);
        assertEquals("0", path);  
    }

    @Test
    public void testCycleWithWeight() {
        GraphMatrixWeighted graph = new GraphMatrixWeighted(4);
        graph.addWeightedEdge(0, 1, 5);
        graph.addWeightedEdge(1, 2, 3);
        graph.addWeightedEdge(2, 3, 1);
        graph.addWeightedEdge(3, 0, 2);

        assertTrue(graph.hasCycle());
    }
}