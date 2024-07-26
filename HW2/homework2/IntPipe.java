package homework2;

import java.util.ArrayList;
import java.util.List;


/**
 * The IntPipe is a pipe that passes through integers.
 * Empty pipe always returns 0.
 */
public class IntPipe implements Simulatable<String> {
    
    /**
     * Abstraction Function:
     *  The IntPipe is being represented by two Lists, one for input integers and one for output integers.
     *  A simulation of IntPipe moves all integers from the input list to the output list.
     *  Empty pipe always returns 0.
     */

     /**
      * Representation Invariant: 
      *  pipeLabel != null
      */

	private String pipeLabel;
	private List<Integer> inputPipe;
	private List<Integer> outputPipe;
	
	/**
	 * @effects asserts if the Representation Invariant breaks.
	 */
	private void checkRep() {
		assert pipeLabel != null : "Pipe's label must not be null";
	}
	
	/**
	 * @requires label != null
     * @modifies this
     * @effects Creates a new IntPipe.
	 */
	public IntPipe(String label){
		this.pipeLabel = label;
		this.inputPipe = new ArrayList<>();
		this.outputPipe = new ArrayList<>();
		checkRep();
	}
	
	/**
	 * @modifies this
     * @effects adds the value inVal to this pipe input.
	 */
	public void insertInput(int inVal) {
		checkRep();
		
		this.inputPipe.addFirst(inVal);

		checkRep();
	}
	
	/**
	 * @modifies this
     * @effects adds the value inVal to this pipe output.
	 */
	public void insertOutput(int inVal) {
		checkRep();
		
		this.outputPipe.addLast(inVal);

		checkRep();
	}
	
	/**
	 * @modifies this
     * @effects returns the first output from this pipe, if empty returns 0;
	 */
	public int removeOutput() {
		checkRep();
		
		if (this.outputPipe.isEmpty()) {
			return 0;
		}
		return this.outputPipe.removeLast();
	}
	
	/**
	 * @effects returns a space separated String with the content of this pipe.
	 */
	public String getContent() {
		checkRep();
		
		List<Integer> combined = new ArrayList<>(this.inputPipe);
		combined.addAll(this.outputPipe);
		String s = "";
		for (int val : combined) {
			s = s.concat(Integer.toString(val));
			s = s.concat(" ");
		}
		if (s.length() > 0) {
			s = s.substring(0, s.length() - 1);
		}

		checkRep();
		
        return s;
	}
	
	/**
	 * @modifies this, graph
	 * @effects Simulates this pipe in the system modeled by graph.
	 */
	public void simulate(BipartiteGraph<String> graph){
		checkRep();
		
		while (this.inputPipe.size() > 0) {
			int temp = this.inputPipe.removeLast();
			this.outputPipe.addFirst(temp);
		}

		checkRep();
	}
}