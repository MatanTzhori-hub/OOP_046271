package homework2;

public class BipartiteGraphException extends Exception {
    
    /**
	 * @effects Initializes this.
	 */
    BipartiteGraphException(){
        super();
    };

    /**
	 * @effects Initializes this.
	 */
    BipartiteGraphException(String message){
        super(message);
    };

    /**
	 * @effects Initializes this.
	 */
    BipartiteGraphException(Throwable casue){
        super(casue);
    };

    /**
	 * @effects Initializes this.
	 */
    BipartiteGraphException(String message, Throwable casue){
        super(message, casue);
    };
}
