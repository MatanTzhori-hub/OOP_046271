package homework1;

import java.awt.*;
import java.util.Random;

/**
 * A AngleChangingSector is sector of a Circle/Oval that spins using step()
 * method.
 * Thus, a typical AngleChangingSector consists of the following set of
 * properties: {location, color, shape, size, arc-angle}
 */
public class AngleChangingSector extends Shape implements Animatable {
    
    /**
     * Abstraction Function: The AngleChangingSector class represents a sector of a Circle/Oval that spins.
     *                       this.startAngle represent the angle the sector starts, and this.arcAngle represents
     *                       the amount of degrees the sector covers.
     *                       dimension.height is the height of the bounding Rectangle
     *                       and dimension.width is the width of the bounding Rectangle.
     * 
     * Representation Invariant: dimension != null, 0 < dimension.height, 0 < dimension.width
     */ 

    private static final int defaultSize = 10;

     Dimension dimension;

    private int startAngle;
    private int arcAngle;

    /**
     * @effects Ensures the Rep. Invariant is kept, asserts otherwise.
     */
    private void checkRep() {
        assert dimension != null: "Error: invalid dimension";
        assert 0 < this.dimension.getHeight(): "Error: invalid bounding rectangle height";
        assert 0 < this.dimension.getWidth(): "Error: invalid bounding rectangle width";
    }

    /**
	 * @effects Initializes this with a a given location, color, and angles.
	 */
    AngleChangingSector(Point location, Color color, int startAngle, int arcAngle){
        super(location, color);
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
        Dimension dimension = new Dimension(defaultSize, defaultSize);
        this.dimension = dimension;
        checkRep();
    }

    /**
	 * @effects Initializes this with a a given location, color, dimension and angles.
	 */
    AngleChangingSector(Point location, Color color, Dimension dimension, int startAngle, int arcAngle) throws ImpossibleSizeException{
        super(location, color);
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
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
     * @requires boud != null
     * @modifies this
     * @effects this.startingAngle grows by 1 degree.
     */
    public void step(Rectangle bound) {
    	checkRep();
        
        this.startAngle = this.startAngle + 1;

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
    	((Graphics2D) g).fillArc(x, y, w, h, this.startAngle, this.arcAngle);

        checkRep();
    }

}
