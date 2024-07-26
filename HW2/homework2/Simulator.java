package homework2;

import java.util.List;


/*
 * A Simulator is a simulation of a Pipe-Filter system that allow work objects to move through them.
 * The system consist of 3 object types:
 * 1. Work objects - an object that moves around the system, and get manipulated by it.
 * 2. Pipes - moves object around the system.
 * 3. Filters - process the work objects that arrive to thme, and move them forward.
 */
public class Simulator<L> {
	
	/*
	 * Abstraction Function: 
	 *  The system is being represented by a BipartiteGraph. Each black node represents a pipe and
	 *  each white node represents a filter. The BipartiteGraph is two sided, meaning pipes cannot
	 *  be connected by an edge to each other, and same for filters. 
	 */
	
	/*
	 * Representation Invariant:
	 * systemGraph is a valid Bipartite Graph. 
	 */
	
	private BipartiteGraph<L> systemGraph;
	
	/**
	 * @effects Creates a new Simulator
	 */
	public Simulator(){
		this.systemGraph = new BipartiteGraph<L>();
	}
	
	/**
	 * @modifies this
     * @effects Adds a new pipe to the system.
     *  throws BipartiteGraphException in the following cases:
     *  - one of the arguments is null 
     *  - filter or pipe with the same label already exists
	 */
	public void addPipe(L pipeLabel, Simulatable<L> pipe)
			throws BipartiteGraphException {
		this.systemGraph.addNode(pipeLabel, pipe, NodeColor.BLACK);
	}
	
	/**
	 * @modifies this
     * @effects Adds a new filter to the system.
     *  throws BipartiteGraphException in the following cases:
     *  - one of the arguments is null 
     *  - filter or pipe with the same label already exists
	 */
	public void addFilter(L filterLabel, Simulatable<L> filter)
			throws BipartiteGraphException {
		this.systemGraph.addNode(filterLabel, filter, NodeColor.WHITE);
	}
	
	/**
	 * 
	 */
	public void addEdge(L parentName, L childName, L edgeLabel)
			throws BipartiteGraphException{
		this.systemGraph.addEdge(parentName, childName, edgeLabel);
	}
	
	/**
     * @modifies This.
     * @effects Simulates a single step of the system, for all pipes and filters.
     */
	public void simulate()
			throws BipartiteGraphException {
		
		checkRep();
		
		List<L> pipeNodes = this.systemGraph.listColoredNodes(NodeColor.BLACK);
		List<L> filterNodes = this.systemGraph.listColoredNodes(NodeColor.WHITE);
		
		for (L pipeLabel : pipeNodes) {
			Simulatable<L> pipe = (Simulatable<L>)this.systemGraph.getNodeData(pipeLabel);
			pipe.simulate(systemGraph);
		}
		
		for (L filterLabel : filterNodes) {
			Simulatable<L> filter = (Simulatable<L>)this.systemGraph.getNodeData(filterLabel);
			filter.simulate(systemGraph);
		}
		
		checkRep();
	}
	
	/**
	 * @effects asserts if the Representation Invarient breaks.
	 */
	public void checkRep() {
		assert this.systemGraph != null : "";
	}
}
    