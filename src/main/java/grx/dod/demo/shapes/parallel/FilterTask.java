package grx.dod.demo.shapes.parallel;

import grx.dod.demo.shapes.model.Filter;
import grx.dod.demo.shapes.model.Shape;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class FilterTask implements Callable<List<Shape>> {
    Function<Shape, Boolean> exp;
    List<Shape> shapes;

    public FilterTask(Function<Shape, Boolean> exp, List<Shape> shapes) {
        this.exp = exp;
        this.shapes = shapes;
    }

    @Override
    public List<Shape> call() throws Exception {
        return Filter.filter(shapes, exp);
    }
}
