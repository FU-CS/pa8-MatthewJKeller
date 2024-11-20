package pa8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphListTest {

    @Test
    public void TestHasCycle() {
        GraphList graph = new GraphList();
        graph.addEdge(0, 0);  
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);  

        assertTrue(graph.hasCycle());
    }

    @Test
    public void testNoCycle() {
        GraphList graph = new GraphList();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertFalse(graph.hasCycle());
    }

    @Test
    public void testBFS() {
        GraphList graph = new GraphList();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);

        String result = graph.bfs(0);
        assertEquals("0 1 2 3", result);
    }

    @Test
    public void testDFS() {
        GraphList graph = new GraphList();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);

        String result = graph.dfs(0);
        assertEquals("0 1 3 2", result);
    }

    @Test
    public void testShortestPath() {
        GraphList graph = new GraphList();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        String path = graph.shortestPath(0, 3);
        assertEquals("0 1 2 3", path);
    }

    @Test
    public void testShortestPathNoPath() {
        GraphList graph = new GraphList();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        String path = graph.shortestPath(0, 3);
        assertEquals("0", path);  
    }

    @Test
    public void testDisconnectedGraph() {
        GraphList graph = new GraphList();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        
        String path = graph.shortestPath(0, 4);
        assertEquals("0", path);  
    }

    @Test
    public void testDisconnectedIsland() {
        GraphList graph = new GraphList();
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
        GraphList graph = new GraphList();
        
        String path = graph.shortestPath(0, 0);
        assertEquals("0", path);  
    }

    @Test
    public void testCycleDetect() {
        GraphList graph = new GraphList();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);

        assertTrue(graph.hasCycle());
    }

    @Test
    public void testSelfLoop() {
        GraphList graph = new GraphList();
        graph.addEdge(0, 0);
        graph.addEdge(1, 2);
        
        String path = graph.shortestPath(0, 0);
        assertEquals("0", path);  
    }

    @Test
    public void testBFSDisconnect() {
        GraphList graph = new GraphList();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        
        String result = graph.bfs(0);
        assertEquals("0 1 2", result);
        
        result = graph.bfs(3);
        assertEquals("3 4", result);
    }
}
