package grx.dod.demo.shapes.parallel;

import grx.dod.demo.shapes.model.Shape;
import grx.dod.demo.shapes.model.Transform;

import java.util.concurrent.Callable;

public class TransformTask implements Callable<Shape> {
    Shape shape;
    Transform transform;

    public TransformTask(Shape shape, Transform transform) {
        this.shape = shape;
        this.transform = transform;
    }

    @Override
    public Shape call() throws Exception {
        return transform.transform(shape);
    }
}
