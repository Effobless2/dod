package grx.dod.demo.shapes.queuing;

import grx.dod.demo.shapes.model.Shape;
import grx.dod.demo.shapes.model.ShapeCalculator;

import java.util.Iterator;
import java.util.List;

public class AreaEmitter implements Pipeline<Double> {
    @Override
    public Double output(List<Shape> input) {
        Iterator it = input.iterator();
        double result = 0;
        while (it.hasNext()) {
            Shape s = (Shape) it.next();
            result += ShapeCalculator.area(s);
        }
        return result;
    }
}
