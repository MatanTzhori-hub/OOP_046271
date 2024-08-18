package homework4;


/**
 * A ColoringStrategy is
 */
public class ColorColAscendStrategy implements ColoringStrategy{
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
    public ColorColAscendStrategy(int rows, int cols){
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

        if (this.current + rows >= max){
            this.current = this.current + 1;
        }
        this.current = (this.current + rows) % max;
        return temp;
    };

    /**
    * @effects 
    */
    public void reset(){
        this.current = 0;
    };
}
