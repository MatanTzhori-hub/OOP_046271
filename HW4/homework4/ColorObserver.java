package homework4;

import java.awt.Color;


/**
 * A ColorObserver is an interface for objects that need to be notified when a color changes.
 */
interface ColorObserver {
    /**
     * Abstraction Function: 
     * The `ColorObserver` interface represents an entity that observes and reacts to changes in color. 
     * The `updateColor(Color color)` method is used to notify this entity with the new color value.
     * 
     * Representation Invariant: None
     */

    /**
     * @modiefies this
	 * @effects Changes this's color to the given color.
	 */
    public void updateColor(Color color);
}