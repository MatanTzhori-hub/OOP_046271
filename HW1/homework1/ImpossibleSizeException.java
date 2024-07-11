package homework1;


/**
 * ImpossibleSizeException is an exception thrown when Shape's size
 * is changed to and illegal value.
 */
public class ImpossibleSizeException extends Exception {
    


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
}
