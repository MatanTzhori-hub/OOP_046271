package homework0;

import java.util.ArrayList;
import java.util.List;

/**
 * A wallet can contain a number of coins. There could be several coins of the same value, 
 * but the same coin cannot appear in the wallet twice.
 */
public class Wallet {
    private List<Coin> coinsList;

    /**
     * @effects Creates a new empty wallet
     */
    public Wallet() {
    	this.coinsList = new ArrayList<>();
    }


    /**
     * @modifies this
     * @effects Adds a coin to the wallet
     * @return true if the coin was successfully added to the wallet;
     * 		   false otherwise
     */
    public boolean addCoin(Coin coin) {
        if (coin == null | this.coinsList.contains(coin)){
            return false;
          }
          else{
            this.coinsList.add(coin);
            return true;
          }
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
        if (sum <= 0){
            throw new IllegalArgumentException("Sum value not allowed.");
        }
        else if (this.getTotal() < sum) {
            return 0;
        }

        double paid = 0;

        while (sum > 0){
            Coin curCoin = this.coinsList.removeFirst();
            paid += curCoin.getValue();
            sum -= curCoin.getValue();
        }

        return paid;
    }


    /**
     * @return the total value of coins in this wallet
     */
    public double getTotal() {
        double sumAmount = 0;
        for(Coin coin : this.coinsList){
            sumAmount += coin.getValue();
        }
        return sumAmount;
    }


    /**
     * @return the number of coins in this wallet
     */
    public int getSize() {
        return this.coinsList.size();
    }


    /**
     * @modifies this
     * @effects Empties the wallet. After this method is called, both
	 * 			getSize() and getTotal() will return 0.
     */
    public void empty() {
        this.coinsList.clear();
    }


    /**
     * @return true if this wallet contains a coin with value;
     *  	   false otherwise
     */
    public boolean containsCoin(double value) {
        for(Coin coin : this.coinsList){
            if (coin.getValue() == value){
                return true;
            }
        }
        return false;
    }
	
	
	/**
     * @return true if value == the amount of money is this wallet;
     *  	   false otherwise
     */
    public boolean containsAmount(double value) {
        return (value == this.getTotal());
    }
	
}
