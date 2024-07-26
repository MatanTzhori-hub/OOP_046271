package homework2;

import java.util.List;

/*
 * The GCDFilter is a filter that calculates the GCD of it's 'a' and 'b' input pipes, and pass the GCD to the 'gcd' output pipe.
 * Requires 'a' and 'b' input and output pipes, and a 'gcd' output pipe.
 */
public class GCDFilter {
    
    /*
     * Abstraction Function:
     *  filterLabel represents the filter's label in the system graph
     */

     /*
      * Representation Invariant: 
      *  filterLabel != null
      */
	
	private String filterLabel;
	
	/**
	 * @effects asserts if the Representation Invariant breaks.
	 */
	private void checkRep() {
		assert filterLabel != null : "Pipe's label must not be null";
	}

	/**
	 * @requires label != null
     * @modifies this
     * @effects Creates a new GCDFilter.
	 */
	public GCDFilter(String label){
		this.filterLabel = label;
		checkRep();
	}
	
	/**
	 * @modifies this, graph
	 * @effects Simulates this filter in the system modeled by graph.
	 * 			The GCDFilter calculate the GCD of the input pipes 'a' and 'b'.
	 * 			If b == 0 then the GCD of 'a' and 'b' goes to output 'gcd'
	 * 			If |b| < |a| then the input 'a' goes to output 'b' and input 'b' goes to output 'a'
	 * 			If |a| >= |b| then 'b' goes to output 'a' and a%b goes to output 'b'
	 */
	public void simulate(BipartiteGraph<String> graph) throws BipartiteGraphException{
		checkRep();
		
		IntPipe a_outPipe = (IntPipe) graph.getNodeData(graph.getChildByEdgeLabel(filterLabel, "a"));
		IntPipe b_outPipe = (IntPipe) graph.getNodeData(graph.getChildByEdgeLabel(filterLabel, "b"));
		IntPipe gcd_outPipe = (IntPipe) graph.getNodeData(graph.getChildByEdgeLabel(filterLabel, "gcd"));

		IntPipe a_inPipe = (IntPipe) graph.getNodeData(graph.getParentByEdgeLabel(filterLabel, "a"));
		IntPipe b_inPipe = (IntPipe) graph.getNodeData(graph.getParentByEdgeLabel(filterLabel, "b"));

		int a = a_inPipe.removeOutput();
		int b = b_inPipe.removeOutput();
		
		if(b == 0) {
			gcd_outPipe.insertInput(a);
		}
		else if(Math.abs(a) < Math.abs(b)) {
			a_outPipe.insertOutput(b);
			b_outPipe.insertOutput(a);
		}
		else {
			a_outPipe.insertOutput(b);
			b_outPipe.insertOutput(a%b);
		}

		checkRep();
	}
}