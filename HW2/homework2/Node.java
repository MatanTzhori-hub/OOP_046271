package homework2;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

enum NodeColor{BLACK, WHITE};


/**
 * The Node class is an abstraction of a node object in a certain graph.
 * A node typically consists of a set of properties: {label, type, color}.
 * A node can have edges going in and out of it to different nodes.
 * Nodes are mutable.
 */
public class Node<L> {
    
    /**
     * Abstraction Function:
     *  The Node class represents a node in a graph. nodeLabel represents it's label,
     *  nodeType represents it's type and nodeColor represents it's color.
     *  The outEdges and inEdges contains the edges that and going out and coming in to this node.
     *  The children and parents contains the child and parent nodes labels of this node.
     */

     /**
      * Representation Invariant: 
      *  nodeLabel != null, nodeType != null, nodeColor != null,
      *  children and outEdges are with the same size and contains the same children
      *  parents and inEdges are with the same size and contains the same parents
      */

    private final L nodeLabel;
    private final NodeColor nodeColor;
    private Object nodeType;

    private List<L> childrens;
    private List<L> parents;
    private Map<L, Edge<L>> outEdges;
    private Map<L, Edge<L>> inEdges;

    /**
     * @effects asserts if the Representation Invariant breaks.
     */
    private void checkRep(){
        assert this.nodeLabel != null : "Node label cannot be null";
        assert this.nodeType != null : "Node type cannot be null";
        assert this.nodeColor != null : "Node Color cannot be null";
        assert this.childrens.size() == this.outEdges.size() : "Children list and out edges most be the same size";
        assert this.parents.size() == this.inEdges.size() : "Parents list and in edges most be the same size";
        assert this.checkConsistency() : "Inconsistency in childrens or parents containers";
    }

    /**
     * @return true is all edges child in outEdges are also in children
     *         and all edges parent in inEdges are also in parents, false o.w.
     */
    private boolean checkConsistency(){
        for (Edge<L> edge : this.outEdges.values()){
            if (this.childrens.contains(edge.getChild()) == false)
                return false;
        }
        
        for (Edge<L> edge : this.inEdges.values()){
            if (this.parents.contains(edge.getParent()) == false)
                return false;
        }

        return true;
    }

    /**
     * @requires label != null, type != null, color != null
     * @modifies this
     * @effects Creates a new node.
     */
    public Node(L label, Object type, NodeColor color){
        this.nodeLabel = label;
        this.nodeType = type;
        this.nodeColor = color;

        this.childrens = new ArrayList<>();
        this.outEdges = new HashMap<>();
        this.parents = new ArrayList<>();
        this.inEdges = new HashMap<>();
        this.checkRep();
    }

    /**
     * @return this node's label
     */
    public L getLabel(){
        return this.nodeLabel;
    }

    /**
     * @return this node's type
     */
    public Object getType(){
        return this.nodeType;
    }

    /**
     * @return this node's color
     */
    public NodeColor getColor(){
        return this.nodeColor;
    }

    /**
     * @requires childNode != null, edgeLabel != null
     * @modifies this and childNode
     * @effects adds the node childNode as a child of this and adds this as a parent of childNode
     */
    public void addChild(Node<L> childNode, L edgeLabel){
        this.checkRep();
        childNode.checkRep();
        
        Edge<L> childEdge = new Edge<L>(edgeLabel, this.nodeLabel, childNode.nodeLabel);
        this.childrens.add(childNode.nodeLabel);
        this.outEdges.put(edgeLabel, childEdge);

        childNode.parents.add(this.nodeLabel);
        childNode.inEdges.put(edgeLabel, childEdge);

        this.checkRep();
        childNode.checkRep();
    }

    /**
     * @return a list containing all node's childrens labels
     */
    public List<L> getChildren() {
        return new ArrayList<>(this.childrens);
    }

    /**
     * @return a list containing all node's parents labels
     */
    public List<L> getParents() {
        return new ArrayList<>(this.parents);
    }

    /**
     * @requires child != null
     * @return true if this has a child node with label child, else false
     */
    public boolean hasChild(L child){
        return this.childrens.contains(child);
    }

    /**
     * @requires parent != null
     * @return true if this has a parent node with label parent, else false
     */
    public boolean hasParent(L parent){
        return this.parents.contains(parent);
    }

    /**
     * @requires edge != null
     * @return true if this has an in edge with label edge, else false
     */
    public boolean hasInEdge(L edge){
        return this.inEdges.containsKey(edge);
    }

    /**
     * @requires edge != null
     * @return true if this has an out edge with label edge, else false
     */
    public boolean hasOutEdge(L edge){
        return this.outEdges.containsKey(edge);
    }

    /**
     * @requires edge != null
     * @return true if this has an out edge with label edge, else false
     */
    public L getChildByEdgeLabel(L edge){
        return this.outEdges.get(edge).getChild();
    }

    /**
     * @requires edge != null
     * @return true if this has an out edge with label edge, else false
     */
    public L getParentByEdgeLabel(L edge){
        return this.inEdges.get(edge).getParent();
    }
}
