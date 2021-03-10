package grx.dod.demo.shapes.model;

import grx.dod.demo.shapes.model.Shape;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Filter {

    public static List<Shape> filter(List<Shape> shapes, Function<Shape, Boolean> exp) {
        return shapes
                .stream()
                .filter(exp::apply)
                .collect(Collectors.toList());
    }
}
