package homework2;

import java.util.List;


/*
 * The PlusFilter is a filter that sums all it's input pipes and pass the sum to it's output pipe.
 * Requires an output pipe.
 */
public class PlusFilter implements Simulatable<String> {
    
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
     * @effects Creates a new PlusFilter.
	 */
	public PlusFilter(String label){
		this.filterLabel = label;
		checkRep();
	}
	
	/**
	 * @modifies this, graph
	 * @effects Simulates this filter in the system modeled by graph.
	 * 			The PlusFilter takes all inputs from all in coming pipes, and push their sum to the out going pipe.
	 */
	public void simulate(BipartiteGraph<String> graph){
		checkRep();
		
		List<String> inputsLabels = graph.getNodeParents(this.filterLabel);
		List<String> outputLabels = graph.getNodeChildren(this.filterLabel);
		int sum = 0;
		
		for (String label : inputsLabels) {
			IntPipe inPipe = (IntPipe)graph.getNodeData(label);
			sum += inPipe.removeOutput();
		}
		
		if(outputLabels.isEmpty()) {
			throw new BipartiteGraphException("No output pipe for Plus-Filter " + filterLabel);
		}
		else if(outputLabels.size() > 1) {
			throw new BipartiteGraphException("Too many output pipes for Plus-Filter " + filterLabel);
		}
		
		IntPipe outPipe = (IntPipe)graph.getNodeData(outputLabels.getFirst());
		outPipe.insertInput(sum);
		
		checkRep();
	}
}