package homework4;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;

/**
 * A Panel is a graphical component of type JPanel. 
 * It implements the ColorObserver interface to respond to color updates.
 */
public class Panel extends JPanel implements ColorObserver {
    /**
     * Abstraction Function:
     * The `Panel` class represents a graphical user interface component of type JPanel.
     * Implements ColorObserver to respond to color updates.
     * 
     * Representation Invariant: None
     */


    /**
	 * @effects Creates a new Panel object.
	 */
    public Panel(){
        updateColor(Color.WHITE);
    }

    /**
     * @modifies this
	 * @effects Updates the panel's background color and sets the border black.
     *          Throws a NullPointerException if color is null.
	 */
    public void updateColor(Color color){
        if (color == null){
            throw new NullPointerException("Given color must not be null");
        }

        this.paintImmediately(this.getVisibleRect());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(color);
    }
}
