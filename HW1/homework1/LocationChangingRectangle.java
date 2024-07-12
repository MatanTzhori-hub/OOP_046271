package homework1;

import java.awt.*;

/**
 * A LocationChangingRectangle is a Rectangle that can change its location using its step()
 * method. A LocationChangingRectangle has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChangingRectangle consists of the following set of
 * properties: {location, color, dimension, size, velocity}
 */

public class LocationChangingRectangle extends LocationChangingShape {
    
    /**
     * Abstraction Function: The LocationChangingRectangle class represents a Rectangle that may move in the 2D plane.
     *                       dimension.height is the height of the Rectangle and dimension.width is the width of the Rectangle.
     * 
     * Representation Invariant: dimension != null, 0 < dimension.height, 0 < dimension.width
     */ 
    private static final int defaultSize = 10;

     Dimension dimension;

     /**
     * @effects Ensures the Rep. Invariant is kept, asserts otherwise.
     */
    private void checkRep() {
        assert dimension == null: "Error: invalid dimension";
        assert 0 < this.dimension.getHeight(): "Error: invalid height";
        assert 0 < this.dimension.getWidth(): "Error: invalid width";
    }

    /**
     * @requires location != null, color != null
	 * @effects Initializes this with a a given location and color.
	 *          rectangle size is being set by default to (height, width) = (10, 10).
	 */
    public LocationChangingRectangle(Point location, Color color){
        super(location, color);
        Dimension dimension = new Dimension(defaultSize, defaultSize);
        this.dimension = dimension;
        checkRep();
    }

    /**
     * @requires location != null, color != null, dimension != null
	 * @effects Initializes this with a a given location, color and dimension.
     *          throws ImpossibleSizeException if got invalid dimension.
	 */
    public LocationChangingRectangle(Point location, Color color, Dimension dimension) throws ImpossibleSizeException{
        super(location, color);
        this.setSize(dimension);
        checkRep();
    }

    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     * 			dimension.
     * 			If this cannot be resized to the specified dimension =>
     * 			this is not modified, throws ImpossibleSizeException
     * 			(the exception suggests an alternative dimension that is
     * 			 supported by this).
     */
    @Override
    public void setSize(Dimension dimension) throws ImpossibleSizeException{
        checkRep();
        if(dimension == null || dimension.getHeight() < 0 || dimension.getWidth() < 0){
            throw new ImpossibleSizeException();
        }
        this.dimension = (Dimension)dimension.clone();
        checkRep();
    }

    
    /**
     * @return the bounding rectangle of this.
     */
    @Override
    public Rectangle getBounds(){
        checkRep();
        return new Rectangle(this.getLocation(), dimension);
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
        int h = (int)getBounds().getWidth();
        int w = (int)getBounds().getHeight();

        ((Graphics2D) g).setColor(getColor());
    	((Graphics2D) g).fillRect(x, y, w, h);

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
