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

        shapes.add(new Circle("red", 1,2,4));
        shapes.add(new Circle("green", 18,11,2));
        shapes.add(new Circle("blue", 5,4,5));
        shapes.add(new Square("black", 1,2,4, 15));
        shapes.add(new Square("purple", 148,123,8, 10));

        System.out.println("===== QUEUING =====");
        System.out.println(new AreaEmitter().output(new TransformEmitter(new Transform(Square.class)).output(shapes)));
        System.out.println(new SpaceEmitter().output(new TransformEmitter(new Transform(Square.class)).output(shapes)));
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
