package pa8;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphMatrixTest {

    @Test 
    public void TestHasCycle() {
        GraphMatrix graph = new GraphMatrix(5);
        graph.addEdge(0, 0);  
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);  

        assertTrue(graph.hasCycle());
    }

    @Test
    public void testNoCycle() {
        GraphMatrix graph = new GraphMatrix(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertFalse(graph.hasCycle());
    }

    @Test
    public void testBFS() {
        GraphMatrix graph = new GraphMatrix(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);

        String result = graph.bfs(0);
        assertEquals("0 1 2 3", result);
    }

    @Test
    public void testDFS() {
        GraphMatrix graph = new GraphMatrix(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);

        String result = graph.dfs(0);
        assertEquals("0 1 3 2", result);
    }

    @Test
    public void testShortestPath() {
        GraphMatrix graph = new GraphMatrix(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        String path = graph.shortestPath(0, 3);
        assertEquals("0 1 2 3", path);
    }

    @Test
    public void testShortestPathNoPath() {
        GraphMatrix graph = new GraphMatrix(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        String path = graph.shortestPath(0, 3);
        assertEquals("0", path);  
    }

    @Test
    public void testDisconnectedGraph() {
        GraphMatrix graph = new GraphMatrix(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        
        String path = graph.shortestPath(0, 4);
        assertEquals("0", path);  
    }

    @Test
    public void testDisconnectedIsland() {
        GraphMatrix graph = new GraphMatrix(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);

        String path1 = graph.shortestPath(0, 2);
        assertEquals("0 1 2", path1);

        String path2 = graph.shortestPath(3, 5);
        assertEquals("0", path2);
    }

    @Test
    public void testSingleNode() {
        GraphMatrix graph = new GraphMatrix(1);
        
        String path = graph.shortestPath(0, 0);
        assertEquals("0", path);  
    }


    @Test
    public void testCycleDetect() {
        GraphMatrix graph = new GraphMatrix(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);

        assertTrue(graph.hasCycle());
    }

    @Test
    public void testSelfLoop() {
        GraphMatrix graph = new GraphMatrix(4);
        graph.addEdge(0, 0);
        graph.addEdge(1, 2);
        
        String path = graph.shortestPath(0, 0);
        assertEquals("0", path);  
    }

    @Test
    public void testBFSDisconnect() {
        GraphMatrix graph = new GraphMatrix(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        
        String result = graph.bfs(0);
        assertEquals("0 1 2", result);
        
        result = graph.bfs(3);
        assertEquals("3 4", result);
    }
}
