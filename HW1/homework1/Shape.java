package homework1;

import java.awt.*;


/**
 * A Shape is an abstraction of a shape object. A typical Shape consists of
 * a set of properties: {location, color, shape, size}.
 * Shapes are mutable and cloneable.
 */
public abstract class Shape implements Cloneable {

	private Point location;
	private Color color;

    /**
     * Abstraction Function: The Shape class represents a geometric shape that this.location point is the top left corner
     *                       of it's bounding rectangle on the 2D plane, and have the color this.color.
     * 
     * Representation Invariant: location != null, color != null
     */
	
	/**
	 * @effects Initializes this with a a given location and color.
	 */
    public Shape(Point location, Color color) {
    	setLocation(location);
    	setColor(color);
    }


    /**
     * @return the top left corner of the bounding rectangle of this.
     */
    public Point getLocation() {
        checkRep();
    	return (Point)location.clone();
    }


    /**
     * @modifies this
     * @effects Moves this to the given location, i.e. this.getLocation()
     * 			returns location after call has completed.
     */
    public void setLocation(Point location) {
    	this.location = (Point)location.clone();
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
    public abstract void setSize(Dimension dimension)
    	throws ImpossibleSizeException;

    
    /**
     * @return the bounding rectangle of this.
     */
    public abstract Rectangle getBounds();
  

    /**
     * @return true if the given point lies inside the bounding rectangle of
     * 		   this and false otherwise.
     */
    public boolean contains(Point point) {
    	return getBounds().contains(point);
    }
        

    /**
     * @return color of this.
     */
    public Color getColor() {
    	return color;
    }


    /**
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
    	this.color = color;
    }


    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public abstract void draw(Graphics g);


    /**
     * @effects Creates and returns a copy of this.
     */
    public Object clone() {
        checkRep();
    	return Shape((Point)this.location.clone(), color);
    }

    /**
     * @effects Ensures the Rep. Invariant is kept, asserts otherwise.
     */
    private void checkRep() {
        assert color != null: "Error: color must not be null";
        assert location != null: "Error: location must not be null";
    }
}