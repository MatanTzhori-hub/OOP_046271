package homework2;

/**
 * BipartiteGraphException is an exception thrown in the implementation of BipartiteGraph
 */
public class BipartiteGraphException extends RuntimeException {

    /**
	 * @effects Initializes this.
	 */
    BipartiteGraphException(String message){
        super(message);
    };
}
