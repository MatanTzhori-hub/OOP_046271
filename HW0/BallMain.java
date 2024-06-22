import homework0.Ball;
import homework0.BallContainer_a;

public class BallMain {
    public static void main(String[] args) {
        /**
         *  In this test program, each comment states the anticipated return value.
         */

        Ball ball1 = new Ball(1);
        Ball ball2 = new Ball(2);
        Ball ball3 = new Ball(3);
        Ball ball4 = new Ball(4);
        Ball ball5 = new Ball(5);

        BallContainer_a ballCont = new BallContainer_a();
        System.out.println(ballCont.add(ball1)); // true
        System.out.println(ballCont.add(ball2)); // true
        System.out.println(ballCont.add(ball3)); // true
        System.out.println(ballCont.add(ball4)); // true
        System.out.println(ballCont.add(ball5)); // true

        System.out.println(ballCont.getVolume()); // 15.0

        Ball ball6 = ball1;
        Ball ball7 = new Ball(1);

        System.out.println(ballCont.remove(ball7));  // false

        System.out.println(ballCont.add(ball6));  // false
        System.out.println(ballCont.add(ball7));  // true
        System.out.println(ballCont.contains(ball6));  // true
        System.out.println(ballCont.contains(ball7));  // true

        ball1.setVolume(10);
        System.out.println(ballCont.size()); // 6
        System.out.println(ballCont.getVolume()); // 25.0
        
        System.out.println(ballCont.remove(ball7)); // true
        System.out.println(ballCont.remove(ball3)); // true
        System.out.println(ballCont.remove(ball5)); // true

        System.out.println(ballCont.size()); // 3
        System.out.println(ballCont.getVolume()); // 16.0

        ballCont.clear();
        System.out.println(ballCont.size()); // 0
        System.out.println(ballCont.getVolume()); // 0.0
    }
}
