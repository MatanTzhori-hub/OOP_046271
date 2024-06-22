package homework0;

import java.util.HashSet;

/**
 * A coin collection contains coins. Each coin value can only appear in the
 * collection once (e.g only one coin with value 0.05)
 */
public class CoinCollection {
	private HashSet<Double> coinsSet;
	private double sumCoins;

	/**
	 * @effects Creates a new empty coin collection
	 */
	public CoinCollection() {
		this.coinsSet = new HashSet<>();
		sumCoins = 0;
	}

	/**
	 * @modifies this
	 * @effects Adds a coin to the collection
	 * @return true if the coin was successfully added to the collection; false
	 *         otherwise
	 */
	public boolean addCoin(Coin coin) {
		if (coin == null | this.coinsSet.contains(coin.getValue())){
			return false;
		  }
		  else{
			this.coinsSet.add(coin.getValue());
			sumCoins += coin.getValue();
			return true;
		  }
	}

	/**
	 * @return the current value of the collection
	 */
	public double getTotal() {
		return this.sumCoins;
	}

	/**
	 * @return the total value of coins in the collection
	 */
	public int getSize() {
		return this.coinsSet.size();
	}

	/**
	 * @modifies this
	 * @effects Empties the collection. After this method is called, both getSize()
	 *          and getTotal() will return 0.
	 */
	public void empty() {
		this.coinsSet.clear();
		this.sumCoins = 0;
	}
}
