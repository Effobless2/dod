package grx.dod.demo.shapes.parallel;

import grx.dod.demo.shapes.model.Shape;
import grx.dod.demo.shapes.model.ShapeCalculator;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleAreaTask implements Callable<Double> {
    Shape shape;

    public SingleAreaTask(Shape shape) {
        this.shape = shape;
    }

    @Override
    public Double call() throws Exception {
        return ShapeCalculator.area(shape);
    }
}
