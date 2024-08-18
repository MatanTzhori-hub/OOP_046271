package homework4;


/**
 * A ColorAscendStrategy is a ColoringStrategy that retrieves the indices of a row x cols grid in an ascending order.
 * The indices are row ordered.
 */
public class ColorAscendStrategy implements ColoringStrategy{
    /**
     * Abstraction Function:
     * The `ColorAscendStrategy` class represents a strategy for iterating through indices of a grid of size `rows x cols`
     * in ascending order. The `getNext()` method returns the current index and then advances to the next index in a
     * cyclic manner, wrapping around to the beginning after reaching the maximum index. The `reset()` method returns the
     * index to 0, restarting the sequence.
     * 
     * Representation Invariant:
     * - `cols >= 0`: The number of columns in the grid must be non-negative.
     * - `rows >= 0`: The number of rows in the grid must be non-negative.
     * - `0 <= current < rows * cols`: The current index must be within the valid range of `[0, rows * cols - 1]`.
     */

    private int cols;
    private int rows;
    private int current;


    /**
	 * @effects Creates a new ColorAscendStrategy with the specified number of rows and columns.
	 */
    public ColorAscendStrategy(int rows, int cols){
        if (rows <= 0 || cols <= 0){
            throw new IllegalArgumentException("Rows and Columns are non-negative.");
        }

        this.rows = rows;
        this.cols = cols;
        this.current = 0;
    };

    /**
     * @modifies this
	 * @returns the current index, and then increments the index.
	 */
    public int getNext(){
        checkRep();

        int temp = this.current;
        int max = rows*cols;

        this.current = (this.current + 1) % max;
        return temp;
    };

    /**
     * @modifies this
     * @effects resets the index to 0.
     */
    public void reset(){
        checkRep();

        this.current = 0;
    };

    /**
     * @effects asserts if the Representation Invariant breaks.
     */
    private void checkRep(){
        assert (rows >= 0): "Rows are non-negative.";
        assert (cols >= 0): "Columns are non-negative.";
        assert (0 <= current && current < rows * cols): "Index out of range.";
    }
}
