package grx.dod.demo.shapes.queuing;

import grx.dod.demo.shapes.model.Shape;
import grx.dod.demo.shapes.model.ShapeCalculator;

import java.util.List;

public class SpaceEmitter implements Pipeline<Double> {
    @Override
    public Double output(List<Shape> input) {
        return ShapeCalculator.area(input);
    }
}
