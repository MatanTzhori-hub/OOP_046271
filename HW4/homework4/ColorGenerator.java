package homework4;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A ColorGenerator is a singleton class responsible for generating random colors. It utilizes a ColoringStrategy to dictate
 * the sequence in which ColorObserver instances are updated with these colors.
 * The class maintains a set of observers and schedules periodic updates, using the specified strategy to cycle through them.
 */
public class ColorGenerator {
    /**
     * Abstraction Function:
     * The `ColorGenerator` class represents a singleton service responsible for generating random colors
     * and notifying `ColorObserver` instances about color changes. The class uses a `ColoringStrategy` strategy to
     * determine the order in which observers are updated. observers is a list of observers it maintains and updates
     * them periodically with new colors.
     * 
     * Representation Invariant:
     * - strategy != null : `strategy` is a valid instance of `ColoringStrategy`.
     * - observer != null : `observers` is a non-null list of `ColorObserver` instances, which may be empty or contain observers.
     * - Singleton property: only one instance of `ColorGenerator` exists.
     */

    private static final int DELAY = 40;

	private static ColorGenerator generator;
    private ColoringStrategy strategy;
    private List<ColorObserver> observers;
	
	/**
	 * @effects private constructor to enforce singleton pattern. Initializes the ColorGenerator single object.
	 */
    private ColorGenerator(ColoringStrategy strategy) {
        this.strategy = strategy;
        this.observers = new ArrayList<ColorObserver>();
    }

    /**
     * @modifies this
	 * @effects returns an instance of ColorGenerator.
     * If ColorGenerator was not initialized, initializes a new instance with default ColoringStrategy.
	 */
    public static ColorGenerator getInstance(){
        if(generator == null){
            generator = new ColorGenerator(new ColorAscendStrategy(5,5));
        }

        return generator;
    }

    /**
	 * @returns a new random color. 
	 */
    private static Color rerollColor(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

    /**
     * @modifies this
	 * @effects sets a new ColoringStrategy for the generator.
	 */
    public void setStrategy(ColoringStrategy strategy){
        checkRep();

        if(strategy == null){
            throw new NullPointerException();
        }

        this.strategy = strategy;

        checkRep();
    }

    /**
     * @modifies this
	 * @effects Inserts observer to this's observers set. If already included, does nothing.
	 */
    public void addObserver(ColorObserver observer){
        checkRep();

        if(observer == null){
            throw new NullPointerException();
        }

        if(!this.observers.contains(observer)){
            this.observers.add(observer);
        }

        checkRep();
    }

    /**
     * @modifies this
	 * @effects Removes observer from this's observers set. If not included, does nothing.
	 */
    public void removeObserver(ColorObserver observer){
        checkRep();

        if(this.observers.contains(observer)){
            this.observers.remove(observer);
        }

        checkRep();
    }

    /**
     * @modifies this
	 * @effects Generate new color and notifies observers in a periodic manner based on the current strategy to update.
	 */
    public void updateColor(){
        checkRep();

        Color color = rerollColor();
        this.strategy.reset();

        if (observers.size() > 0){
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

        checkRep();
    }

    /**
     * Checks the representation invariant.
     */
    private void checkRep() {
        assert (strategy != null) : "strategy must not be null";
        assert (observers != null) : "observers must not be null";
        assert (!observers.contains(null)) : "null observer is not allowed";
        assert (generator != null) : "generator must not be null";
        assert (generator == this) : "there may only be 1 instance of ColorGenerator";
    }
}