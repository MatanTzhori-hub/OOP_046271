package homework1;

import java.awt.*;

/**
 * A LocationChangingRoundedRectangle is a Rectangle with rounded corners that can change its location using its step()
 * method. A LocationChangingRoundedRectangle has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChangingRoundedRectangle consists of the following set of
 * properties: {location, color, dimension, size, velocity}
 */

public class LocationChangingRoundedRectangle extends LocationChangingRectangle {
    
    private static int ROUND_FACTOR = 6;

    /**
     * @effects Ensures the Rep. Invariant is kept, asserts otherwise.
     */
    private void checkRep() {
        assert 1 < ROUND_FACTOR: "Error: invalid dimension";
    }

    /**
     * @requires location != null, color != null
	 * @effects Initializes this with a a given location and color.
	 *          rectangle size is being set by default to (height, width) = (10, 10).
	 */
    public LocationChangingRoundedRectangle(Point location, Color color){
        super(location, color);
        checkRep();
    }

    /**
     * @requires location != null, color != null, dimension != null
	 * @effects Initializes this with a a given location, color and dimension.
     *          throws ImpossibleSizeException if got invalid dimension.
	 */
    public LocationChangingRoundedRectangle(Point location, Color color, Dimension dimension) throws ImpossibleSizeException{
        super(location, color, dimension);
        checkRep();
    }

    /**
     * @requires g != null
     * @modifies g
     * @effects Draws this onto g.
     */
    @Override
    public void draw(Graphics g){
        checkRep();

        int x = getLocation().x;
        int y = getLocation().y;
        int w = (int)getBounds().getWidth();
        int h = (int)getBounds().getHeight();

        ((Graphics2D) g).setColor(getColor());
    	((Graphics2D) g).fillRoundRect(x, y, w, h, w/ROUND_FACTOR , h/ROUND_FACTOR);

        checkRep();
    }

    /**
     * @effects Creates and returns a copy of this.
     */
	@Override
    public Object clone() {
        checkRep();

    	LocationChangingRectangle rectClone;
    	rectClone = (LocationChangingRectangle)super.clone();
    	rectClone.dimension = (Dimension)dimension.clone();

    	return rectClone;
    }

}
