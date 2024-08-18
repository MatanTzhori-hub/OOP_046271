package homework4;


/**
 * A ColoringStrategy is an interface for defining strategies to order indices.
 * Implements 2 methods: getNext(), reset(), that dictates the order of indices.
 */
public interface ColoringStrategy {
    /**
     * Abstraction Function: None
     * 
     * Representation Invariant: None
     */

     /**
	 * @returns the next index of the strategy.
	 */
    public int getNext();

    /**
    * @effects reset's the strategy to it's initial state.
    */
   public void reset();
}
