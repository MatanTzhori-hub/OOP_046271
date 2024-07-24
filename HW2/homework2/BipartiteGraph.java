package homework2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * A BipartiteGraph is a simple directed graph containing Black and White nodes, with no self edges.
 * BipartiteGraph's nodes of the same color may not be connected.
 * Each node and each edge in the graph is labeled with type L. In respect to L, all in edges of a node
 * are unique, all out edges of a node are unique, and all nodes are unique. 
 */
public class BipartiteGraph<L> {
    
    /*
     * Abstruction Function:
     *  graphNodes is a map of label-node pairs.
     *  All nodes that are apart of the graph are mapped in graphNodes.
     *  Each node consists of its in-edges, out-edges, label, type and color.
     */

     /*
      * Representation Invariant: 
      */

    private HashMap<L, Node<L>> graphNodes;

    /**
     * @effects asserts if the Representation Invarient breaks.
     */
    private void checkRep(){
        return;
    }

    /**
     * @effects Creates a new Bipartite Graph.
     */
    public BipartiteGraph(){
        this.graphNodes = new HashMap<>();
        checkRep();
    }

    /**
     * @modifies this
     * @effects Adds a new node to the graph.
     *  throws BipartiteGraphException in the following cases:
     *  - one of the arguments is null 
     *  - node already exists
     */
    public void addNode(L nodeLabel, Object nodeType, NodeColor nodeColor)
        throws BipartiteGraphException{

        if (nodeLabel == null || nodeType == null || nodeColor == null){
            throw new BipartiteGraphException("Arguments must not be null");
        }
        if (graphNodes.containsKey(nodeLabel)){
            throw new BipartiteGraphException("Node "+ nodeLabel + " already exists");
        }

        Node<L> newNode = new Node<>(nodeLabel, nodeType, nodeColor);
        this.graphNodes.put(nodeLabel, newNode);
    }
    
    /**
     * @modifies this
     * @effects Adds a new edge to the graph.
     *  throws BipartiteGraphException in the following cases:
     */
    public void addEdge(L edgeLabel, L parentLabel, L childLabel)
        throws BipartiteGraphException{

        checkRep();

        Node<L> childNode = graphNodes.get(childLabel);
        Node<L> parentNode = graphNodes.get(parentLabel);

        if (edgeLabel == null || parentLabel == null || childLabel == null){
            throw new BipartiteGraphException("Arguments must not be null");
        }
        if (parentNode == null){
            throw new BipartiteGraphException("Node " + parentLabel + " does not exist");
        }
        if (childNode == null){
            throw new BipartiteGraphException("Node " + childLabel + " does not exist");
        }
        if (parentNode.hasChild(childLabel)){
            throw new BipartiteGraphException("Node " + parentLabel + " already has child " + childLabel);
        }
        if (childNode.hasParent(parentLabel)){
            throw new BipartiteGraphException("Node " + childLabel + " already has parent " + parentLabel);
        }
        if (parentNode.hasOutEdge(edgeLabel)){
            throw new BipartiteGraphException("Node " + parentLabel + " already has out edge " + edgeLabel);
        }
        if (childNode.hasInEdge(edgeLabel)){
            throw new BipartiteGraphException("Node " + parentLabel + " already has in edge " + edgeLabel);
        }
        if (parentNode.getColor() == childNode.getColor()){
            throw new BipartiteGraphException("Connected nodes most have different colors");
        }

        parentNode.addChild(childNode, edgeLabel);

        checkRep();
    }

    /**
     * @effects returns a list containing labels of all nodes with color 'color' in the graph.
     *          If there are no such nodes in the graph, returns an empty list.
     *          throws BipartiteGraphException if color == null
     */
    public List<L> listColoredNodes(NodeColor color)
        throws BipartiteGraphException{

        if (color == null){
            throw new BipartiteGraphException("Arguments must not be null");
        }

        checkRep();

        List<L> nodesList = new ArrayList<>();
        for (Node<L> node : this.graphNodes.values()){
            if (node.getColor() == color){
                nodesList.add(node.getLabel());
            }
        }

        return nodesList;
    }

    /**
     * @effects return a list containing the labels of all children of the node with label nodeLabel.
     *          throws BipartiteGraphException if nodeLabel == null or no such node exists
     */
    public List<L> getNodeChildren(L nodeLabel)
        throws BipartiteGraphException{
        
        if (nodeLabel == null){
            throw new BipartiteGraphException("Arguments must not be null");
        }

        checkRep();
        Node<L> node = graphNodes.get(nodeLabel);
        if (node == null){
            throw new BipartiteGraphException("Node " + nodeLabel + " does not exist");
        }

        return node.getChildren();
    }

    /**
     * @effects return a list containing the labels of all children of the node with label nodeLabel.
     *          throws BipartiteGraphException if nodeLabel == null or no such node exists
     */
    public List<L> getNodeParents(L nodeLabel)
        throws BipartiteGraphException{
        
        if (nodeLabel == null){
            throw new BipartiteGraphException("Arguments must not be null");
        }

        checkRep();
        Node<L> node = graphNodes.get(nodeLabel);
        if (node == null){
            throw new BipartiteGraphException("Node " + nodeLabel + " does not exist");
        }

        return node.getParents();
    }

    /**
     * @effects return the child of the node lebeled nodeLabel with edge labeled edgeLabel.
     *          throws BipartiteGraphException if nodeLabel == null, edgeLabel == null,
     *          a node labeled nodeLabel does not exists it has no edge labeled edgeLabel.
     */
    public L getChildByEdgeLabel(L nodeLabel, L edgeLabel)
        throws BipartiteGraphException{

        if (nodeLabel == null || edgeLabel == null){
            throw new BipartiteGraphException("Arguments must not be null");
        }

        checkRep();

        Node<L> parentNode = graphNodes.get(nodeLabel);
        if (parentNode == null){
            throw new BipartiteGraphException("Node " + nodeLabel + " does not exist");
        }
        if (parentNode.hasOutEdge(edgeLabel) == false){
            throw new BipartiteGraphException("Node " + nodeLabel + " doesn't have an out edge " + edgeLabel);
        }

        return parentNode.getChildByEdgeLabel(edgeLabel);
    }

    /**
     * @effects return the parent of the node lebeled nodeLabel with edge labeled edgeLabel.
     *          throws BipartiteGraphException if nodeLabel == null, edgeLabel == null,
     *          a node labeled nodeLabel does not exists it has no edge labeled edgeLabel.
     */
    public L getParentByEdgeLabel(L nodeLabel, L edgeLabel)
        throws BipartiteGraphException{

        if (nodeLabel == null || edgeLabel == null){
            throw new BipartiteGraphException("Arguments must not be null");
        }

        checkRep();

        Node<L> childNode = graphNodes.get(nodeLabel);
        if (childNode == null){
            throw new BipartiteGraphException("Node " + nodeLabel + " does not exist");
        }
        if (childNode.hasInEdge(edgeLabel) == false){
            throw new BipartiteGraphException("Node " + nodeLabel + " doesn't have an in edge " + edgeLabel);
        }

        return childNode.getParentByEdgeLabel(edgeLabel);
    }
}
