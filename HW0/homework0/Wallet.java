package homework0;

/**
 * A wallet can contain a number of coins. There could be several coins of the same value, 
 * but the same coin cannot appear in the wallet twice.
 */
public class Wallet {
	
    /**
     * @effects Creates a new empty wallet
     */
    public Wallet() {
    	//TODO
    }


    /**
     * @modifies this
     * @effects Adds a coin to the wallet
     * @return true if the coin was successfully added to the wallet;
     * 		   false otherwise
     */
    public boolean addCoin(Coin coin) {
    	//TODO
    }

    /**
	 * @requires sum > 0
     * @modifies this
     * @effects tries to match at least sum with coins in the wallet. 
	 *			If payment is possible, removes the paid coins from the wallet;
	 *          else changes nothing
     * @return the amount actually paid, 0 if amount could not be obtained
     */
    public double pay(double sum) {
    	//TODO
    }


    /**
     * @return the total value of coins in this wallet
     */
    public double getTotal() {
    	//TODO
    }


    /**
     * @return the number of coins in this wallet
     */
    public int getSize() {
    	//TODO
    }


    /**
     * @modifies this
     * @effects Empties the wallet. After this method is called, both
	 * 			getSize() and getTotal() will return 0.
     */
    public void empty() {
    	//TODO
    }


    /**
     * @return true if this wallet contains a coin with value;
     *  	   false otherwise
     */
    public boolean containsCoin(double value) {
    	//TODO
    }
	
	
	/**
     * @return true if value == the amount of money is this wallet;
     *  	   false otherwise
     */
    public boolean containsAmount(double value) {
    	//TODO
    }
	
}
