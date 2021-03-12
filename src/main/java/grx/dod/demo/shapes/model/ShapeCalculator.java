package grx.dod.demo.shapes.model;

import grx.dod.demo.shapes.model.Shape;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ShapeCalculator {
    public static double area(Shape s) {
        return s.getHeight() * s.getWidth();
    }

    public static double area(List<Shape> input) {
        if (input.size() == 0)
            return 0;
        Optional<Double> minX = Optional.empty();
        Optional<Double> minY = Optional.empty();
        Optional<Double> maxX = Optional.empty();
        Optional<Double> maxY = Optional.empty();

        Iterator<Shape> it = input.iterator();
        while (it.hasNext()) {
            Shape s = it.next();
            if(!minX.isPresent() || minX.get() > s.X() ) {
                minX = Optional.of(s.X());
            }
            if(!minY.isPresent() || minY.get() > s.Y() ) {
                minY = Optional.of(s.Y());
            }
            if(!maxY.isPresent() || maxY.get() < s.Y() ) {
                maxY = Optional.of(s.Y());
            }
            if(!maxX.isPresent() || maxX.get() < s.X() ) {
                maxX = Optional.of(s.X());
            }
        }
        return (maxX.get() - minX.get()) * (maxY.get() - minY.get());
    }
}
