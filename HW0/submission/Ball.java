package homework0;

/**
 * This is a simple object that has a volume.
 */
public class Ball {
    private double volume;

    /**
     * @requires volume > 0
     * @modifies this
     * @effects Creates and initializes new Ball object with the specified
     *  		volume.
     */
    public Ball(double volume) {
      this.volume = volume;
    }


	/**
	 * @requires volume > 0
	 * @modifies this
	 * @effects Sets the volume of the Ball.
	 */
	public void setVolume(double volume) {
      this.volume = volume;
	}


    /**
     * @return the volume of the Ball.
     */
    public double getVolume() {
      return this.volume;
    }
}