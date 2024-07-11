package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;


/**
 * A LocationChangingRectangle is a Rectangle that can change its location using its step()
 * method. A LocationChangingRectangle has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChangingRectangle consists of the following set of
 * properties: {location, color, height, weight, size, velocity}
 */
public class LocationChangingRectangle extends LocationChangingShape implements Cloneable {
    
    /**
     * Abstraction Function: The LocationChangingRectangle class represents a Rectangle that may move in the 2D plane.
     *                       dimension.height is the height of the Rectangle and dimension.weight is the weight of the Rectangle.
     * 
     * Representation Invariant: 0 < dimension.height, 0 < dimension.weight
     */ 

     Dimension dimension;

     /**
     * @effects Ensures the Rep. Invariant is kept, asserts otherwise.
     */
    private void checkRep() {
        assert 0 < this.height: "Error: invalid height";
        assert 0 < this.weight: "Error: invalid weight";
    }

    /**
	 * @effects Initializes this with a a given location and color. Each
	 *          dimension is being set by default to 1x1.
	 */
    public LocationChangingRectangle(Point location, Color color){
        super(location, color);
        this.dimension = Dimension(1, 1);
    }

    /**
	 * @effects Initializes this with a a given location, color, height and weight.
	 */
    public LocationChangingRectangle(Point location, Color color, int height, int weight){
        super(location, color);
        this.dimension = Dimension(height, weight);
    }

    public static void main(String[] args){
        Point p = Point(2,2);
        Color c = Color.red;
        LocationChangingRectangle rect1 = LocationChangingRectangle(p, c);

        System.out.println(rect1.getVelocityX());
    }
}
