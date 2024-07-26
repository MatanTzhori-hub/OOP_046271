package homework2;

//import static org.junit.Assert.*;
//import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * BipartiteGraphTest contains JUnit block-box unit tests for BipartiteGraph.
 */
public class BipartiteGraphTest {

	@Test
    public void testExample() throws BipartiteGraphException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        
        //create a graph
        driver.createGraph("graph1");
        
        //add a pair of nodes
        driver.addBlackNode("graph1", "n1");
        driver.addWhiteNode("graph1", "n2");
        
        //add an edge
        driver.addEdge("graph1", "n1", "n2", "edge");
        
        //check neighbors
        assertEquals("n1", driver.listBlackNodes("graph1"), "wrong black nodes");
        assertEquals("n2", driver.listWhiteNodes("graph1"), "wrong white nodes");
        assertEquals("n2", driver.listChildren ("graph1", "n1"), "wrong children");
        assertEquals("", driver.listChildren ("graph1", "n2"), "wrong children");
        assertEquals("", driver.listParents ("graph1", "n1"), "wrong parents");
        assertEquals("n1", driver.listParents ("graph1", "n2"), "wrong parents");
    }
    
	@Test
    public void testAddingNodes() throws BipartiteGraphException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        
        //create graph
        driver.createGraph("graph1");

        //add some nodes
        driver.addBlackNode("graph1", "a");
        driver.addWhiteNode("graph1", "b");
        driver.addBlackNode("graph1", "c");
        driver.addWhiteNode("graph1", "d");
        driver.addBlackNode("graph1", "e");
        driver.addWhiteNode("graph1", "f");
        driver.addBlackNode("graph1", "g");
        driver.addWhiteNode("graph1", "h");
        driver.addBlackNode("graph1", "i");
        driver.addWhiteNode("graph1", "j");
        
        //check color
        assertEquals("a c e g i", driver.listBlackNodes("graph1"), "wrong black nodes");
        assertEquals("b d f h j", driver.listWhiteNodes("graph1"), "wrong white nodes");
	}
	
	@Test
	public void testAddingSameNodes() throws BipartiteGraphException{
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        
        //create graph
        driver.createGraph("graph1");

        //add some nodes
        driver.addBlackNode("graph1", "n1");
        driver.addWhiteNode("graph1", "n2");

        //test adding the same nodes again
        BipartiteGraphException e1 = assertThrows(BipartiteGraphException.class, ()->{driver.addBlackNode("graph1", "n1");}, "no exception thrown");
        assertEquals("Node n1 already exists", e1.getMessage());
        BipartiteGraphException e2 = assertThrows(BipartiteGraphException.class, ()->{driver.addBlackNode("graph1", "n2");}, "no exception thrown");
        assertEquals("Node n2 already exists", e2.getMessage());
	}
	
	@Test
	public void testAddingEdges() throws BipartiteGraphException {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();

        //create graph
        String graph1 = "graph1";
        driver.createGraph(graph1);
        
        //add some nodes
        driver.addBlackNode(graph1, "n1");
        driver.addWhiteNode(graph1, "n2");
        driver.addBlackNode(graph1, "n3");
        driver.addWhiteNode(graph1, "n4");
        driver.addBlackNode(graph1, "n5");
        driver.addWhiteNode(graph1, "n6");
        
        //add some edges
        driver.addEdge(graph1, "n1", "n2", "edge1");
        driver.addEdge(graph1, "n1", "n4", "edge2");
        driver.addEdge(graph1, "n1", "n6", "edge3");
        driver.addEdge(graph1, "n3", "n6", "edge4");
        driver.addEdge(graph1, "n2", "n1", "edge5");
        driver.addEdge(graph1, "n4", "n3", "edge6");
        
        //check edges
        assertEquals("n2 n4 n6", driver.listChildren(graph1, "n1"));
        assertEquals("n1", driver.listChildren(graph1, "n2"));
        assertEquals("n6", driver.listChildren(graph1, "n3"));
        assertEquals("n3", driver.listChildren(graph1, "n4"));
        assertEquals("", driver.listChildren(graph1, "n5"));
        assertEquals("", driver.listChildren(graph1, "n6"));

        assertEquals("n2", driver.listParents(graph1, "n1"));
        assertEquals("n1", driver.listParents(graph1, "n2"));
        assertEquals("n4", driver.listParents(graph1, "n3"));
        assertEquals("n1", driver.listParents(graph1, "n4"));
        assertEquals("", driver.listParents(graph1, "n5"));
        assertEquals("n1 n3", driver.listParents(graph1, "n6"));
	}
	
	@Test
	public void testBadArguments() throws BipartiteGraphException{
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
		
        //create graph
        driver.createGraph("graph1");
        
        //add nodes and edges with bad arguments
        BipartiteGraphException e1 = assertThrows(BipartiteGraphException.class, ()->{driver.addBlackNode("graph1", null);}, "no exception thrown");
        assertEquals("Arguments must not be null", e1.getMessage());
        
        BipartiteGraphException e2 = assertThrows(BipartiteGraphException.class, ()->{driver.addWhiteNode("graph1", null);}, "no exception thrown");
        assertEquals("Arguments must not be null", e2.getMessage());
        
        BipartiteGraphException e3 = assertThrows(BipartiteGraphException.class, ()->{driver.addEdge("graph1", null, null, null);}, "no exception thrown");
        assertEquals("Arguments must not be null", e3.getMessage());
        
        BipartiteGraphException e4 = assertThrows(BipartiteGraphException.class, ()->{driver.addEdge("graph1", "a", null, null);}, "no exception thrown");
        assertEquals("Arguments must not be null", e4.getMessage());
        
        BipartiteGraphException e5 = assertThrows(BipartiteGraphException.class, ()->{driver.addEdge("graph1", "a", "b", null);}, "no exception thrown");
        assertEquals("Arguments must not be null", e5.getMessage());
        
        BipartiteGraphException e6 = assertThrows(BipartiteGraphException.class, ()->{driver.listChildren("graph1", null);}, "no exception thrown");
        assertEquals("Arguments must not be null", e6.getMessage());
        
        BipartiteGraphException e7 = assertThrows(BipartiteGraphException.class, ()->{driver.listParents("graph1", null);}, "no exception thrown");
        assertEquals("Arguments must not be null", e7.getMessage());
        
        BipartiteGraphException e8 = assertThrows(BipartiteGraphException.class, ()->{driver.getChildByEdgeLabel("graph1", null, null);}, "no exception thrown");
        assertEquals("Arguments must not be null", e8.getMessage());
        
        BipartiteGraphException e9 = assertThrows(BipartiteGraphException.class, ()->{driver.getChildByEdgeLabel("graph1", "a", null);}, "no exception thrown");
        assertEquals("Arguments must not be null", e9.getMessage());
        
        BipartiteGraphException e10 = assertThrows(BipartiteGraphException.class, ()->{driver.getParentByEdgeLabel("graph1", null, null);}, "no exception thrown");
        assertEquals("Arguments must not be null", e10.getMessage());
        
        BipartiteGraphException e11 = assertThrows(BipartiteGraphException.class, ()->{driver.getParentByEdgeLabel("graph1", "a", null);}, "no exception thrown");
        assertEquals("Arguments must not be null", e11.getMessage());
	}
	
	@Test
	public void testAddingProblematicEdges() throws BipartiteGraphException{
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
		
        //create graph
        driver.createGraph("graph1");
        
        //add edges of non existing nodes
        BipartiteGraphException e1 = assertThrows(BipartiteGraphException.class, ()->{driver.addEdge("graph1", "n1", "n2", "edge");}, "no exception thrown");
        assertEquals("Node n1 does not exist", e1.getMessage());
        
        driver.addBlackNode("graph1", "n1");
        
        BipartiteGraphException e2 = assertThrows(BipartiteGraphException.class, ()->{driver.addEdge("graph1", "n1", "n2", "edge");}, "no exception thrown");
        assertEquals("Node n2 does not exist", e2.getMessage());

        driver.addBlackNode("graph1", "n2");
        
        BipartiteGraphException e3 = assertThrows(BipartiteGraphException.class, ()->{driver.addEdge("graph1", "n1", "n2", "edge");}, "no exception thrown");
        assertEquals("Connected nodes most have different colors", e3.getMessage());
        
        BipartiteGraphException e4 = assertThrows(BipartiteGraphException.class, ()->{driver.addEdge("graph1", "n1", "n1", "edge");}, "no exception thrown");
        assertEquals("Self edges are not allowed", e4.getMessage());
        
        driver.addWhiteNode("graph1", "n3");
        driver.addWhiteNode("graph1", "n4");
        driver.addWhiteNode("graph1", "n5");
        driver.addEdge("graph1", "n1", "n3", "edge1");
        driver.addEdge("graph1", "n1", "n4", "edge2");
        
        BipartiteGraphException e5 = assertThrows(BipartiteGraphException.class, ()->{driver.addEdge("graph1", "n1", "n3", "edge3");}, "no exception thrown");
        assertEquals("Node n1 already has child n3", e5.getMessage());
        
        BipartiteGraphException e6 = assertThrows(BipartiteGraphException.class, ()->{driver.addEdge("graph1", "n2", "n3", "edge1");}, "no exception thrown");
        assertEquals("Node n3 already has in edge edge1", e6.getMessage());
        
        BipartiteGraphException e7 = assertThrows(BipartiteGraphException.class, ()->{driver.addEdge("graph1", "n1", "n5", "edge1");}, "no exception thrown");
        assertEquals("Node n1 already has out edge edge1", e7.getMessage());
	}
	
	@Test
	public void testUsingNonExistingNodes() throws BipartiteGraphException{
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        
        //create graph
        driver.createGraph("graph1");
        
        //adding some nodes
        driver.addBlackNode("graph1", "a");
        driver.addBlackNode("graph1", "b");
        driver.addWhiteNode("graph1", "c");
        driver.addWhiteNode("graph1", "d");
        
        BipartiteGraphException e1 = assertThrows(BipartiteGraphException.class, ()->{driver.listChildren("graph1", "n1");}, "no exception thrown");
        assertEquals("Node n1 does not exist", e1.getMessage());

        BipartiteGraphException e2 = assertThrows(BipartiteGraphException.class, ()->{driver.listParents("graph1", "n1");}, "no exception thrown");
        assertEquals("Node n1 does not exist", e2.getMessage());
        
        BipartiteGraphException e3 = assertThrows(BipartiteGraphException.class, ()->{driver.getChildByEdgeLabel("graph1", "n1", "edge");}, "no exception thrown");
        assertEquals("Node n1 does not exist", e3.getMessage());

        BipartiteGraphException e4 = assertThrows(BipartiteGraphException.class, ()->{driver.getChildByEdgeLabel("graph1", "a", "edge");}, "no exception thrown");
        assertEquals("Node a doesn't have an out edge edge", e4.getMessage());
        
        BipartiteGraphException e5 = assertThrows(BipartiteGraphException.class, ()->{driver.getParentByEdgeLabel("graph1", "n1", "edge");}, "no exception thrown");
        assertEquals("Node n1 does not exist", e5.getMessage());

        BipartiteGraphException e6 = assertThrows(BipartiteGraphException.class, ()->{driver.getParentByEdgeLabel("graph1", "a", "edge");}, "no exception thrown");
        assertEquals("Node a doesn't have an in edge edge", e6.getMessage());
	}
	
	@Test
	public void testLegalScenario() throws BipartiteGraphException{
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        
        //create graph
        driver.createGraph("graph1");
        
        //adding some nodes
        driver.addBlackNode("graph1", "a");
        driver.addBlackNode("graph1", "b");
        driver.addBlackNode("graph1", "c");
        driver.addBlackNode("graph1", "d");
        driver.addWhiteNode("graph1", "e");
        driver.addWhiteNode("graph1", "f");
        driver.addWhiteNode("graph1", "g");
        driver.addWhiteNode("graph1", "h");
        
        //add some edges
        driver.addEdge("graph1", "a", "e", "edge1");
        driver.addEdge("graph1", "a", "f", "edge2");
        driver.addEdge("graph1", "a", "g", "edge3");
        driver.addEdge("graph1", "a", "h", "edge4");
        driver.addEdge("graph1", "c", "e", "edge5");
        driver.addEdge("graph1", "c", "f", "edge6");
        driver.addEdge("graph1", "c", "g", "edge7");
        driver.addEdge("graph1", "c", "h", "edge8");
        driver.addEdge("graph1", "e", "b", "edge9");
        driver.addEdge("graph1", "e", "d", "edge10");
        driver.addEdge("graph1", "f", "b", "edge11");
        driver.addEdge("graph1", "f", "d", "edge12");
        
        //test get child by label
        assertEquals("e", driver.getChildByEdgeLabel("graph1", "a", "edge1"));
        assertEquals("f", driver.getChildByEdgeLabel("graph1", "a", "edge2"));
        assertEquals("g", driver.getChildByEdgeLabel("graph1", "a", "edge3"));
        assertEquals("h", driver.getChildByEdgeLabel("graph1", "a", "edge4"));
        assertEquals("c", driver.getParentByEdgeLabel("graph1", "e", "edge5"));
        assertEquals("c", driver.getParentByEdgeLabel("graph1", "f", "edge6"));
        assertEquals("c", driver.getParentByEdgeLabel("graph1", "g", "edge7"));
        assertEquals("c", driver.getParentByEdgeLabel("graph1", "h", "edge8"));

        assertEquals("e f g h", driver.listChildren("graph1", "a"));
        assertEquals("", driver.listParents("graph1", "a"));
        assertEquals("", driver.listChildren("graph1", "b"));
        assertEquals("e f", driver.listParents("graph1", "b"));
        assertEquals("e f g h", driver.listChildren("graph1", "c"));
        assertEquals("", driver.listParents("graph1", "c"));
        assertEquals("", driver.listChildren("graph1", "d"));
        assertEquals("e f", driver.listParents("graph1", "d"));
        assertEquals("b d", driver.listChildren("graph1", "e"));
        assertEquals("a c", driver.listParents("graph1", "e"));
        assertEquals("b d", driver.listChildren("graph1", "f"));
        assertEquals("a c", driver.listParents("graph1", "f"));
        assertEquals("", driver.listChildren("graph1", "g"));
        assertEquals("a c", driver.listParents("graph1", "g"));
        assertEquals("", driver.listChildren("graph1", "h"));
        assertEquals("a c", driver.listParents("graph1", "h"));
	}
	
	@Test
	public void testGetNodeType() throws BipartiteGraphException{
		BipartiteGraph<String> graph = new BipartiteGraph<>();
		
		graph.addNode("n1", "I am the data of the node", NodeColor.BLACK);

        BipartiteGraphException e1 = assertThrows(BipartiteGraphException.class, ()->{graph.getNodeData(null);}, "no exception thrown");
        assertEquals("Arguments must not be null", e1.getMessage());
        BipartiteGraphException e2 = assertThrows(BipartiteGraphException.class, ()->{graph.getNodeData("n2");}, "no exception thrown");
        assertEquals("Node n2 does not exist", e2.getMessage());
        assertEquals("I am the data of the node", graph.getNodeData("n1"));
	}
        
        
}
