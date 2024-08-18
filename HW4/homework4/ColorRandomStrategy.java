package homework4;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A ColorRandomStrategy is a ColoringStrategy that retrieves the indices of a row x cols grid in a random order.
 */
public class ColorRandomStrategy implements ColoringStrategy{
    /**
     * Abstraction Function:
     * The `ColorRandomStrategy` class represents a strategy for iterating through indices of a grid of size `rows x cols`
     * in a random order. The `indices` list contains all grid indices shuffled randomly. The `getNext()` method returns
     * the current index from this shuffled list and advances to the next index, wrapping around if necessary. The `reset()` 
     * method reshuffles the indices and resets the current index to 0.
     * 
     * Representation Invariant:
     * - `cols >= 0`: The number of columns in the grid must be non-negative.
     * - `rows >= 0`: The number of rows in the grid must be non-negative.
     * - `indices.size() == rows * cols`: The `indices` list must contain exactly `rows * cols` indices.
     * - `0 <= current < rows * cols`: The `current` index must be within the valid range of `[0, rows * cols - 1]`.
     */

    private int cols;
    private int rows;
    private ArrayList<Integer> indices;
    private int current;


    /**
	 * @effects creates a new ColorRandomStrategy with the specified number of rows and columns.
	 */
    public ColorRandomStrategy(int rows, int cols){
        if (rows <= 0 || cols <= 0){
            throw new IllegalArgumentException("Rows and Columns are non-negative.");
        }
        
        this.rows = rows;
        this.cols = cols;
        indices = new ArrayList<>();
        for (int i = 0; i < rows*cols; i++) {
            indices.add(i);
        }
        this.current = 0;
    };

    /**
     * @modifies this
	 * @returns the current random index and advances to the next index. All indices of the grid will be retrieved
     * each sequence. 
	 */
    public int getNext(){
        checkRep();

        int temp = indices.get(this.current);
        int max = rows*cols;

        this.current = (this.current + 1) % max;
        return temp;
    };

    /**
     * @modifies this
     * @effects resets the current index to 0 and reshuffles the indices.
     */
    public void reset(){
        checkRep();

        this.current = 0;
        Collections.shuffle(this.indices);

        checkRep();
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
