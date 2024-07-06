package homework0;

/**
	A Coin can have a value of 0.01, 0.05, 0.1, 0.25, 0.5, 1
 */
public class Coin {
	private double value;
    private final static double[] allowedValues = { 0.01, 0.05, 0.1, 0.25, 0.5, 1};
	
    /**
     * @requires value in {0.01, 0.05, 0.1, 0.25, 0.5, 1}
     * @effects Creates and initializes new Coin with the given value
     * 
     */
    public Coin(double value) {
        for (Double allowedValue : allowedValues){
            if (value == allowedValue){
                this.value = value;
                return;
            }
        }
        throw new IllegalArgumentException("Coin value of " + value + " not allowed.");
    }


    /**
     * @return the value of the Coin
     */
    public double getValue() {
        return this.value;
    }
    
    //
}