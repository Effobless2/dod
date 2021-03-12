package grx.dod.demo.shapes;

import grx.dod.demo.shapes.model.*;
import grx.dod.demo.shapes.parallel.AreaTask;
import grx.dod.demo.shapes.parallel.FilterTask;
import grx.dod.demo.shapes.parallel.SpaceTask;
import grx.dod.demo.shapes.queuing.AreaEmitter;
import grx.dod.demo.shapes.queuing.SpaceEmitter;
import grx.dod.demo.shapes.queuing.TransformEmitter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle("red", 1d,2d,4d));
        shapes.add(new Circle("green", 18d,11d,2d));
        shapes.add(new Circle("blue", 5d,4d,5d));
        shapes.add(new Rectangle("black", 1d,2d,4d, 15d));
        shapes.add(new Rectangle("purple", 148d,123d,8d, 10d));

        System.out.println("===== QUEUING =====");
        System.out.println(new AreaEmitter().output(new TransformEmitter(new Transform(Rectangle.class)).output(shapes)));
        System.out.println(new SpaceEmitter().output(new TransformEmitter(new Transform(Rectangle.class)).output(shapes)));
        System.out.println(Filter.filter(shapes, Circle.class::isInstance));
        System.out.println("==================");

        System.out.println("===== THREADING =====");
        try {
            System.out.println(new AreaTask(shapes).call());
            System.out.println(new SpaceTask(shapes).call());
            System.out.println(new FilterTask(Circle.class::isInstance, shapes).call());
        } catch (Exception e) {

        }
    }
}
