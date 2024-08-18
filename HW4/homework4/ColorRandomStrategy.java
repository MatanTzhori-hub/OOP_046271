package homework4;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A ColoringStrategy is
 */
public class ColorRandomStrategy implements ColoringStrategy{
    /**
     * Abstraction Function: 
     * 
     * Representation Invariant: 
     */

    private int cols;
    private int rows;
    private ArrayList<Integer> indices;
    private int current;


     /**
	 * @effects 
	 */
    public ColorRandomStrategy(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        indices = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            indices.add(i);
        }
        this.current = 0;
    };
     /**
	 * @effects 
	 */
    public int getNext(){
        int temp = indices.get(this.current);
        int max = rows*cols;

        this.current = (this.current + 1) % max;
        return temp;
    };

    /**
    * @effects 
    */
    public void reset(){
        this.current = 0;
        Collections.shuffle(this.indices);
    };
}
