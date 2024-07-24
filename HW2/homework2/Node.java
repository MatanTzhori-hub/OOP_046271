package homework2;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

enum Color{BLACK, WHITE};


/*
 * 
 */
public class Node<T, L> {
    
    /*
     * Abstruction Function:
     */

     /*
      * Representation Invariant:
      */

    private final L nodeLabel;
    private final T nodeType;
    private final Color nodeColor;

    private List<L> childrens;
    private List<L> parents;
    private Map<L, Edge<L>> outEdges;
    private Map<L, Edge<L>> inEdges;

    /* 
     * @effects asserts if the Representation Invarient breaks.
     */
    private void checkRep(){
        assert this.nodeLabel == null : "Node label cannot be null";
        assert this.nodeType == null : "Node type cannot be null";
        assert this.nodeColor == null : "Node Color cannot be null";
    }

    /*
     * 
     */
    public Node(L label, T type, Color color){
        this.nodeLabel = label;
        this.nodeType = type;
        this.nodeColor = color;

        this.childrens = new ArrayList<>();
        this.outEdges = new HashMap<>();
        this.parents = new ArrayList<>();
        this.inEdges = new HashMap<>();
        this.checkRep();
    }

    /*
     * @return this node's label
     */
    public L getLabel(){
        return this.nodeLabel;
    }

    /*
     * @return this node's type
     */
    public T getType(){
        return this.nodeType;
    }

    /*
     * @return this node's color
     */
    public Color getColor(){
        return this.nodeColor;
    }

    /*
     * @requires childNode != null
     * @modifies this and childNode
     * @effects adds the node childNode as a child of this and adds this as a parent of childNode
     */
    public void addChild(Node<T, L> childNode){
        
    }
}
