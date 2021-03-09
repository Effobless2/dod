package grx.dod.demo.shapes;

import java.util.Iterator;
import java.util.List;

public class AreaEmitter implements Pipeline<Double> {
    @Override
    public Double output(List<Shape> input) {
        Iterator it = input.iterator();
        double result = 0;
        while (it.hasNext()) {
            Shape s = (Shape) it.next();
            result += s.area();
        }
        return result;
    }
}
