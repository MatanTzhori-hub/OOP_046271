package homework1;

import java.awt.*;

/**
 * A LocationChangingNumberedOval is an Oval that have a unique number.
 * The Oval can change its location using its step() method.
 * A LocationChangingNumberedOval has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChangingNumberedOval consists of the following set of
 * properties: {location, color, dimension, size, velocity, number}
 */

public class LocationChangingNumberedOval extends LocationChangingOval {
    
    /**
     * Abstraction Function: The LocationChangingNumberedOval class represents a numbered Oval
     *                       that may move in the 2D plane.
     *                       this.ovalNumber is the serial number of this oval.
     *                       createdOvals are the total number of ovals that were created.
     * 
     * Representation Invariant: this.ovalNumber <= createdOvals
     */ 

    private static long createdOvals = 1;
    private long ovalNumber;

    private void checkRep() {
        assert ovalNumber <= createdOvals: "Error: invalid oval number";
    }

    /**
     * @requires location != null, color != null
	 * @effects Initializes this with a a given location and color.
	 *          bounding rectangle size is being set by default to (height, width) = (10, 10).
	 */
    public LocationChangingNumberedOval(Point location, Color color){
        super(location, color);
        this.ovalNumber = createdOvals++;
        checkRep();
    }

    /**
     * @requires location != null, color != null, dimension != null
	 * @effects Initializes this with a a given location, color and dimension.
     *          throws ImpossibleSizeException if got invalid dimension.
	 */
    public LocationChangingNumberedOval(Point location, Color color, Dimension dimension) throws ImpossibleSizeException{
        super(location, color, dimension);
        this.ovalNumber = createdOvals++;
        checkRep();
    }

    /**
     * @requires g != null
     * @modifies g
     * @effects Draws this onto g with this's number.
     */
    @Override
    public void draw(Graphics g){
        checkRep();

        int x = getLocation().x;
        int y = getLocation().y;
        int w = (int)getBounds().getWidth();
        int h = (int)getBounds().getHeight();

        super.draw(g);
        g.setColor(Color.WHITE);
        ((Graphics2D) g).drawString(Long.toString(this.ovalNumber), (int)(x + w/2.0) , (int)(y + h/2.0));

        checkRep();
    }

    /**
     * @requires g != null
     * @modifies g
     * @effects Draws this onto g with this's number.
     */
    public static void resetNumbering(){
        LocationChangingNumberedOval.createdOvals = 1;
    }

    /**
     * @effects Creates and returns a copy of this.
     */
	@Override
    public Object clone() {
    	checkRep();
    	LocationChangingNumberedOval ovalClone;
    	ovalClone = (LocationChangingNumberedOval)super.clone();
        ovalClone.ovalNumber = this.ovalNumber;
        
    	return ovalClone;
    }
}
