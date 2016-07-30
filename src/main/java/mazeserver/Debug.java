package mazeserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FinnBear on 7/28/16.
 */
public class Debug {
    public static void runTests() {
        System.out.println("Running Tests...");
        testCircleCircleCollission();
    }

    public static void testCircleCircleCollission()
    {
        Circle circle1 = new Circle("c1", new Vector2(0, 0), 3, 0.5f, 0.9f, 0);
        Circle circle2 = new Circle("c2", new Vector2(1,1), 3, 0.5f, 0.9f, 0);
        List<Circle> objectList = new ArrayList<Circle>(2);
        objectList.add(0, circle1);
        objectList.add(1, circle2);
        float c1x = circle1.getPosition().getX();
        float c1y = circle1.getPosition().getY();
        float c2x = circle2.getPosition().getX();
        float c2y = circle2.getPosition().getY();
        System.out.println(circle1.toString());
        System.out.println(circle2.toString());
        objectList.get(0).update(objectList);
        objectList.get(1).update(objectList);
        System.out.println(circle1.toString());
        System.out.println(circle2.toString());
        objectList.get(0).update(objectList);
        objectList.get(1).update(objectList);
        System.out.println(circle1.toString());
        System.out.println(circle2.toString());
        objectList.get(0).update(objectList);
        objectList.get(1).update(objectList);
        System.out.println(circle1.toString());
        System.out.println(circle2.toString());
        objectList.get(0).update(objectList);
        objectList.get(1).update(objectList);
        System.out.println(circle1.toString());
        System.out.println(circle2.toString());
        objectList.get(0).update(objectList);
        objectList.get(1).update(objectList);
        System.out.println(circle1.toString());
        System.out.println(circle2.toString());
        objectList.get(0).update(objectList);
        objectList.get(1).update(objectList);
        float c1x2 = circle1.getPosition().getX();
        float c1y2 = circle1.getPosition().getY();
        float c2x2 = circle2.getPosition().getX();
        float c2y2 = circle2.getPosition().getY();
        System.out.println("Circle 1 moved from x=" + Math.round(c1x) + " y=" + Math.round(c1y) + " to x=" + Math.round(c1x2) + " y=" + Math.round(c1x2));
        System.out.println("Circle 2 moved from x=" + Math.round(c2x) + " y=" + Math.round(c2y) + " to x=" + Math.round(c2x2) + " y=" + Math.round(c2x2));
    }
}
