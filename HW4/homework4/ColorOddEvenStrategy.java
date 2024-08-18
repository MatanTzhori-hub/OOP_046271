package homework4;


/**
 * A ColoringStrategy is
 */
public class ColorOddEvenStrategy implements ColoringStrategy{
    /**
     * Abstraction Function: 
     * 
     * Representation Invariant: 
     */

    private int cols;
    private int rows;
    private int current;


     /**
	 * @effects 
	 */
    public ColorOddEvenStrategy(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.current = 0;
    };
     /**
	 * @effects 
	 */
    public int getNext(){
        int temp = this.current;
        int max = rows*cols;

        this.current = (this.current + 2) % max;
        return temp;
    };

    /**
    * @effects 
    */
    public void reset(){
        this.current = 0;
    };
}
