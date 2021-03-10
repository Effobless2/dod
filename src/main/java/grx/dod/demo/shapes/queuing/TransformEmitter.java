package grx.dod.demo.shapes.queuing;

import grx.dod.demo.shapes.model.Shape;
import grx.dod.demo.shapes.model.Transform;

import java.util.List;
import java.util.stream.Collectors;

public class TransformEmitter implements Pipeline<List<Shape>> {
    Transform transform;

    public TransformEmitter(Transform transform) {
        this.transform = transform;
    }

    @Override
    public List<Shape> output(List<Shape> input) {
        return input.stream().map(transform::transform)
        .collect(Collectors.toList());
    }
}
