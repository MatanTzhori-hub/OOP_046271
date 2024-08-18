package homework4;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A ColorGenerator is
 */
public class ColorGenerator {
    /**
     * Abstraction Function: 
     * 
     * Representation Invariant: 
     */

    private static final int DELAY = 40;

	private static ColorGenerator generator;
	private Color color;
    private ColoringStrategy strategy;
    private List<ColorObserver> observers;
	
	/**
	 * @effects 
	 */
    private ColorGenerator(ColoringStrategy strategy) {
        this.rerollColor();
        this.strategy = strategy;
        this.observers = new ArrayList<ColorObserver>();
    }

    /**
	 * @effects 
	 */
    private void rerollColor(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        this.color = new Color(r, g, b);
    }

    /**
	 * @effects 
	 */
    public static ColorGenerator getInstance(){
        if(generator == null){
            generator = new ColorGenerator(new ColorAscendStrategy(5,5));
        }

        return generator;
    }

    /**
	 * @effects 
	 */
    public void setStrategy(ColoringStrategy strategy){
        this.strategy = strategy;
    }

    /**
	 * @effects 
	 */
    public void addObserver(ColorObserver observer){
        this.observers.add(observer);
    }

    /**
	 * @effects 
	 */
    public void removeObserver(ColorObserver observer){
        this.observers.remove(observer);
    }

    /**
	 * @effects 
	 */
    public void updateColor(){
        this.rerollColor();
        this.strategy.reset();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = 0;
            public void run() {
                (observers.get(strategy.getNext())).updateColor(color);
                counter++;
                if (counter == observers.size()) {
                    this.cancel();
                }
            }
        }, 0, DELAY);
    }
}