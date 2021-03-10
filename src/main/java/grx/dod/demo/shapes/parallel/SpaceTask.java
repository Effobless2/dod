package grx.dod.demo.shapes.parallel;

import grx.dod.demo.shapes.model.Shape;
import grx.dod.demo.shapes.model.ShapeCalculator;

import java.util.List;
import java.util.concurrent.Callable;

public class SpaceTask implements Callable<Double> {
    List<Shape> shapes;

    public SpaceTask(List<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public Double call() throws Exception {
        return ShapeCalculator.area(shapes);
    }
}
