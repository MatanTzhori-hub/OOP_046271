import homework0.Coin;
import homework0.CoinCollection;

public class CoinMain {
    public static void main(String[] args) {
        Coin coin001 = new Coin(0.01);
        Coin coin005 = new Coin(0.05);
        Coin coin01 = new Coin(0.1);
        Coin coin05 = new Coin(0.5);
        Coin coin1 = new Coin(1);

        Coin coin4 = new Coin(0.1);
        try {
            Coin coin7 = new Coin(0.73);
        } catch (Exception e) {
            System.out.println("Dissallowed coin value, got error: " + e.getMessage()); // Error message for dissallowed coin value
        }

        CoinCollection coinCollection1 = new CoinCollection();

        System.out.println(coinCollection1.addCoin(coin001)); // true
        System.out.println(coinCollection1.getTotal()); // 0.01
        System.out.println(coinCollection1.getSize()); // 1
        
        System.out.println(coinCollection1.addCoin(coin01)); // true
        System.out.println(coinCollection1.getTotal()); // 0.11
        System.out.println(coinCollection1.getSize()); // 2
        
        System.out.println(coinCollection1.addCoin(coin01)); // false
        System.out.println(coinCollection1.getTotal()); // 0.11
        System.out.println(coinCollection1.getSize()); // 2
        
        System.out.println(coinCollection1.addCoin(coin4)); // false
        System.out.println(coinCollection1.getTotal()); // 0.11
        System.out.println(coinCollection1.getSize()); // 2

        coinCollection1.empty();
        System.out.println(coinCollection1.getTotal()); // 0
        System.out.println(coinCollection1.getSize()); // 0
        
        System.out.println(coinCollection1.addCoin(coin05)); // true
        System.out.println(coinCollection1.getTotal()); // 0.5
        System.out.println(coinCollection1.getSize()); // 1
        
        System.out.println(coinCollection1.addCoin(coin1)); // true
        System.out.println(coinCollection1.getTotal()); // 1.5
        System.out.println(coinCollection1.getSize()); // 2
        
        System.out.println(coinCollection1.addCoin(coin005)); // true
        System.out.println(coinCollection1.getTotal()); // 1.55
        System.out.println(coinCollection1.getSize()); // 3
    }
    
}
