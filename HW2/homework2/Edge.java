package homework2;


/**
 * The Edge class is an abstraction of a directed edge in a certain graph.
 * An Edge typicaly consists a label, and a child and parent labels.
 * The labels of the edge and the nodes are of class L.
 * An Edge is imuteable.
 */
public class Edge<L> {
    
    /**
     * Abstraction Function:
     *  this.edgeLabel represents the edge's label, this.edgeParent represents
     *  the edge's source node and edgeChild represents the edge's destination node.
     */

     /**
      * Representation Invariant: 
      *  this.edgeLabel  != null && this.edgeParent != null && this.edgeChild  != null
      */

    private L edgeLabel;
    private L edgeParent;
    private L edgeChild;

    /**
     * @effects asserts if the Representation Invarient breaks.
     */
    private void checkRep(){
        assert this.edgeLabel != null : "Edge label cannot be null";
        assert this.edgeChild != null : "Edge child cannot be null";
        assert this.edgeParent != null : "Edge parent cannot be null";
    }

    /**
     * @requires edgeLabel != null && edgeParent != null && edgeChild != null
     * @modifies this
     * @effects Creates a new Edge.
     */
    public Edge(L edgeLabel, L edgeParent, L edgeChild) {
        this.edgeLabel = edgeLabel;
        this.edgeChild = edgeChild;
        this.edgeParent = edgeParent;
        this.checkRep();
    }

    /**
     * @return this edge's label.
     */
    public L getLabel(){
        return this.edgeLabel;
    }
    
    /**
     * @return this edge's parent.
     */
    public L getParent(){
        return this.edgeParent;
    }
    
    /**
     * @return this edge's child.
     */
    public L getChild(){
        return this.edgeChild;
    }
}
