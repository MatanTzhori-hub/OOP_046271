package homework4;


/**
 * A ColorOddEvenStrategy is a ColoringStrategy that retrieves the indices of a row x cols grid in an ascending order with
 * 2 transitions - odd indices and then even indices.
 */
public class ColorOddEvenStrategy implements ColoringStrategy{
    /**
     * Abstraction Function:
     * The `ColorOddEvenStrategy` class represents a strategy for generating grid indices by alternating between even 
     * and odd indices. The `getNext()` method returns the current index and advances by 2, toggling between even and 
     * odd sequences. The `reset()` method sets the index back to 0, starting the sequence over.
     * 
     * Representation Invariant:
     * - `cols >= 0`: The number of columns in the grid must be non-negative.
     * - `rows >= 0`: The number of rows in the grid must be non-negative.
     * - `0 <= current < rows * cols`: The current index must be within the valid range of `[0, rows * cols - 1]`.
     * - `even` toggles between `true` and `false`, ensuring the strategy alternates correctly between even and odd indices.
     */

    private boolean even;
    private int cols;
    private int rows;
    private int current;


    /**
	 * @effects creates a new ColorOddEvenStrategy with the specified number of rows and columns.
	 */
    public ColorOddEvenStrategy(int rows, int cols){
        if (rows <= 0 || cols <= 0){
            throw new IllegalArgumentException("Rows and Columns are non-negative.");
        }
        
        this.rows = rows;
        this.cols = cols;
        this.even = true;
        this.current = 0;
    };

    /**
     * @modifies this
	 * @returns the current index, then advances it, alternating between even and odd sequences. 
     * Wraps around to the start of the sequence if necessary. 
	 */
    public int getNext(){
        checkRep();

        int temp = this.current;
        int max = rows*cols;

        if (this.current + 2 >= max){
            if (even){
                this.current = 1;
            }
            else{
                this.current = 0;
            }
            even = !even;
            return temp;
        }
        this.current = (this.current + 2) % max;
        return temp;
    };

    /**
     * @modifies this
     * @effects resets the current index to 0, starting the sequence over.
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
