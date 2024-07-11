package homework1;

import java.awt.Dimension;

/**
 * ImpossibleSizeException is an exception thrown when Shape's size
 * is changed to and illegal value.
 */
public class ImpossibleSizeException extends Exception {
    
    private static final int defaultSize = 10;

    /**
	 * @effects Initializes this.
	 */
    ImpossibleSizeException(){
        super();
    };

    /**
	 * @effects Initializes this.
	 */
    ImpossibleSizeException(String message){
        super(message);
    };

    /**
	 * @effects Initializes this.
	 */
    ImpossibleSizeException(Throwable casue){
        super(casue);
    };

    /**
	 * @effects Initializes this.
	 */
    ImpossibleSizeException(String message, Throwable casue){
        super(message, casue);
    };

    /**
     * @return A suggested default dimensions that are valid.
     */
    Dimension suggestSize(){
        return new Dimension(defaultSize, defaultSize);
    }
}
