package homework1;

import java.awt.*;
import java.util.Random;


/**
 * A LocationChaningShape is a Shape that can change its location using its step()
 * method. A LocationChaningShape has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChaningShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationChangingShape extends Shape implements Animatable {

    /**
     * Abstraction Function: The LocationChangingShape class represents a geometric shape that may move in the 2D plane.
     *                       this.xVelocity is the horizonal velocity, and this.yVelocity is the vertical velocity.
     * 
     * Representation Invariant: -5 <= this.xVelocity <= 5, this.xVelocity != 0
     *                           -5 <= this.yVelocity <= 5, this.yVelocity != 0
     */ 

    private static final int MAX_INIT_VELOCITY = 5;

    private int xVelocity;
    private int yVelocity;

    /**
     * @effects Ensures the Rep. Invariant is kept, asserts otherwise.
     */
    private void checkRep() {
        assert (-MAX_INIT_VELOCITY <= this.xVelocity && this.xVelocity <= MAX_INIT_VELOCITY && this.xVelocity != 0): "Error: invalid x velocity";
        assert (-MAX_INIT_VELOCITY <= this.yVelocity && this.yVelocity <= MAX_INIT_VELOCITY && this.yVelocity != 0): "Error: invalid y velocity";
    }
	
	/**
	 * @effects Initializes this with a a given location and color. Each
	 *          of the horizontal and vertical velocities of the new
	 *          object is set to a random integral value i such that
	 *          -5 <= i <= 5 and i != 0
	 */
	LocationChangingShape(Point location, Color color) {
        super(location, color);
        Random randomGenerator = new Random(); 
        xVelocity = randomGenerator.nextInt(2*MAX_INIT_VELOCITY) - MAX_INIT_VELOCITY;
        yVelocity = randomGenerator.nextInt(2*MAX_INIT_VELOCITY) - MAX_INIT_VELOCITY;
        
        if(xVelocity == 0)
            xVelocity = MAX_INIT_VELOCITY;
        if(yVelocity == 0)
            yVelocity = MAX_INIT_VELOCITY;

        checkRep();
    }


    /**
     * @return the horizontal velocity of this.
     */
    public int getVelocityX() {
        checkRep();
        return this.xVelocity;
    }


    /**
     * @return the vertical velocity of this.
     */
    public int getVelocityY() {
        checkRep();
        return this.yVelocity;
    }


    /**
     * @modifies this
     * @effects Sets the horizontal velocity of this to velocityX and the
     * 			vertical velocity of this to velocityY.
     */
    public void setVelocity(int velocityX, int velocityY) {
        checkRep();
    	this.xVelocity = velocityX;
        this.yVelocity = velocityY;
        checkRep();
    }


    /**
     * @modifies this
     * @effects Let p = location
     * 				v = (vx, vy) = velocity
     * 				r = the bounding rectangle of this
     *         	If (part of r is outside bound) or (r is within bound but
     *          adding v to p would bring part of r outside bound) {
     * 				If adding v to p would move r horizontally farther away
     * 				from the center of bound,
     * 					vx = -vx
     * 				If adding v to p would move r vertically farther away
     * 				from the center of bound,
     * 					vy = -vy
     *          }
     * 			p = p + v
     */
    public void step(Rectangle bound) {
    	checkRep();
        Rectangle shapeBoundry = getBounds();

        boolean xOutOfBounds        = shapeBoundry.getMinX() < bound.getMinX() || shapeBoundry.getMaxX() > bound.getMaxX();
        boolean yOutOfBounds        = shapeBoundry.getMinY() < bound.getMinY() || shapeBoundry.getMaxY() > bound.getMaxY();
        boolean xStepOutOfBounds    = shapeBoundry.getMinX() + xVelocity < bound.getMinX() ||
                                      shapeBoundry.getMaxX() + xVelocity > bound.getMaxX();
        boolean yStepOutOfBounds    = shapeBoundry.getMinY() + yVelocity < bound.getMinY() ||
                                      shapeBoundry.getMaxY() + yVelocity > bound.getMaxY();
        boolean xStepAwayFromCenter = shapeBoundry.getMaxX() + xVelocity > bound.getCenterX() ||
                                      shapeBoundry.getMinX() + xVelocity < bound.getCenterX();
        boolean yStepAwayFromCenter = shapeBoundry.getMaxY() + yVelocity > bound.getCenterY() ||
                                      shapeBoundry.getMinY() + yVelocity < bound.getCenterY();

        if(xOutOfBounds || xStepOutOfBounds || xStepAwayFromCenter){
            this.xVelocity = -xVelocity;
        }
        if(yOutOfBounds || yStepOutOfBounds || yStepAwayFromCenter){
            this.yVelocity = -yVelocity;
        }

        Point newLocation = this.getLocation();
        newLocation.setLocation(newLocation.x + xVelocity, newLocation.y + yVelocity);
        this.setLocation(newLocation);
        checkRep();
    }
}
