package grx.dod.demo.shapes;

import java.util.Iterator;
import java.util.List;

public class GetPositions {
    public static int getMaxX(List<Shape> shapes) {
        Iterator<Shape> it = shapes.iterator();
        int result = 0;
        while(it.hasNext()) {
            Shape s = it.next();
            if (s.X() > result) {
                result = s.X();
            }
        }
        return result;
    }

    public static int getMaxY(List<Shape> shapes) {
        Iterator<Shape> it = shapes.iterator();
        int result = 0;
        while(it.hasNext()) {
            Shape s = it.next();
            if (s.Y() > result) {
                result = s.Y();
            }
        }
        return result;
    }

    public static int getMinY(List<Shape> shapes) {
        int result = 0;
        Iterator<Shape> it = shapes.iterator();
        while(it.hasNext()) {
            Shape s = it.next();
            if (s.Y() < result) {
                result = s.Y();
            }
        }
        return result;
    }

    public static int getMinX(List<Shape> shapes) {
        int result = 0;
        Iterator<Shape> it = shapes.iterator();
        while(it.hasNext()) {
            Shape s = it.next();
            if (s.X() < result) {
                result = s.X();
            }
        }
        return result;
    }
}
