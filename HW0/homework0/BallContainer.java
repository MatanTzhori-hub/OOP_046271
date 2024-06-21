package homework0;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a container that can be used to contain Balls. A given Ball may
 * only appear in a BallContainer once.
 */
public class BallContainer {
    private List<Ball> ballsList;

    /**
     * @effects Creates a new BallContainer.
     */
    public BallContainer() {
      this.ballsList = new ArrayList<>();
    }


    /**
     * @modifies this
     * @effects Adds ball to the container.
     * @return true if ball was successfully added to the container,
     * 		   i.e. ball is not already in the container; false otherwise.
     */
    public boolean add(Ball ball) {
      if (ball == null | this.ballsList.contains(ball)){
        return false;
      }
      else{
        this.ballsList.add(ball);
        return true;
      }
    }


    /**
     * @modifies this
     * @effects Removes ball from the container.
     * @return true if ball was successfully removed from the container,
     * 		   i.e. ball is actually in the container; false otherwise.
     */
    public boolean remove(Ball ball) {
      if (ballsList.contains(ball)){
        this.ballsList.remove(ball);
        return true;
      }
      else{
        return true;
      }
    }


    /**
     * @return the volume of the contents of the container, i.e. the
     * 		   total volume of all Balls in the container.
     */
    public double getVolume() {
      double sumVolume = 0;

      for(Ball ball : this.ballsList){
        sumVolume += ball.getVolume();
      }
      return sumVolume;
    }


    /**
     * @return the number of Balls in the container.
     */
    public int size() {
      return this.ballsList.size();
    }


    /**
     * @modifies this
     * @effects Empties the container, i.e. removes all its contents.
     */
    public void clear() {
      this.ballsList.clear();
    }


    /**
     * @return true if this container contains ball; false, otherwise.
     */
    public boolean contains(Ball ball) {
      return this.ballsList.contains(ball);
    }

}
