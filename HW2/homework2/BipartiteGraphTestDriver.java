package homework2;

import java.util.*;

/**
 * This class implements a testing driver for BipartiteGraph. The driver
 * manages BipartiteGraphs whose nodes and edges are Strings.
 */
public class BipartiteGraphTestDriver {

    private Map<String, BipartiteGraph<String>> graphs;

    /**
     * @modifies this
     * @effects Constructs a new test driver.
     */
    public BipartiteGraphTestDriver () {
    	// TODO: Implement this constructor
       this.graphs = new HashMap<>();
    }

    
    /**
     * @requires graphName != null
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public void createGraph(String graphName) {
        // TODO: Implement this method
        BipartiteGraph<String> newGraph = new BipartiteGraph<>();
    	this.graphs.put(graphName, newGraph);
    }

    
    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a black node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addBlackNode(String graphName, String nodeName)
        throws BipartiteGraphException {
    	// TODO: Implement this method
        this.graphs.get(graphName).addNode(nodeName, nodeName, NodeColor.BLACK);
    }

    
    /**
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a white node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addWhiteNode(String graphName, String nodeName)
        throws BipartiteGraphException {
        // TODO: Implement this method
        this.graphs.get(graphName).addNode(nodeName, nodeName, NodeColor.WHITE);
    }

    
    /**
     * @requires createGraph(graphName)
     *           && ((addBlackNode(parentName) && addWhiteNode(childName))
     *              || (addWhiteNode(parentName) && addBlackNode(childName)))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel
     * @modifies graph named graphName
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the String
     * 			edgeLabel.
     */
    public void addEdge(String graphName,
    					String parentName, String childName, 
                        String edgeLabel)
                        throws BipartiteGraphException {
    	//TODO: Implement this method
    	this.graphs.get(graphName).addEdge(edgeLabel, parentName, childName);
    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes(String graphName)
        throws BipartiteGraphException {
    	//TODO: Implement this method
    	List<String> blackNodes = this.graphs.get(graphName).listColoredNodes(NodeColor.BLACK);
        Collections.sort(blackNodes);
        String s = String.join(" ", blackNodes);
        return s;
    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes(String graphName)
        throws BipartiteGraphException {
            //TODO: Implement this method
            List<String> whiteNodes = this.graphs.get(graphName).listColoredNodes(NodeColor.WHITE);
            Collections.sort(whiteNodes);
            String s = String.join(" ", whiteNodes);
            return s;
    }

    
    /**
     * @requires createGraph(graphName) && createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listChildren(String graphName, String parentName)
        throws BipartiteGraphException {
    	//TODO: Implement this method
    	List<String> children = this.graphs.get(graphName).getNodeChildren(parentName);
    	Collections.sort(children);
        return String.join(" ", children);
    }

    
    /**
     * @requires createGraph(graphName) && createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     */
    public String listParents(String graphName, String childName)
        throws BipartiteGraphException {
    	//TODO: Implement this method
    	List<String> parents = this.graphs.get(graphName).getNodeParents(childName);
    	Collections.sort(parents);
        return String.join(" ", parents);
    }

    
    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getChildByEdgeLabel(String graphName, String parentName,
    								   String edgeLabel)
                                       throws BipartiteGraphException {
    	//TODO: Implement this method
    	return this.graphs.get(graphName).getChildByEdgeLabel(parentName, edgeLabel);
    }

    
    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 string str
     * @return the name of the parent of childName that is connected by the 
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getParentByEdgeLabel(String graphName, String childName,
    									String edgeLabel)
                                        throws BipartiteGraphException {
         //TODO: Implement this method
         return this.graphs.get(graphName).getParentByEdgeLabel(childName, edgeLabel);
    }
}
