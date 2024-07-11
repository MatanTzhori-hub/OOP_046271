package homework1;

import java.awt.Dimension;

/**
 * ImpossibleSizeException is an exception thrown when Shape's size
 * is changed to and illegal value.
 */
public class ImpossibleSizeException extends Exception {
    
    private static final int defaultSize = 10;

    ImpossibleSizeException(){
        super();
    };

    ImpossibleSizeException(String message){
        super(message);
    };

    ImpossibleSizeException(Throwable casue){
        super(casue);
    };

    ImpossibleSizeException(String message, Throwable casue){
        super(message, casue);
    };

    Dimension suggestSize(){
        return new Dimension(defaultSize, defaultSize);
    }
}
