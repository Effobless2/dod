package grx.dod.demo.shapes.queuing;

import grx.dod.demo.shapes.model.Shape;

import java.util.List;

public interface  Pipeline<T> {
    T output(List<Shape> input);
}
