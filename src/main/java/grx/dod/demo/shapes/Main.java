package grx.dod.demo.shapes;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle("red", 1,2,4));
        shapes.add(new Circle("green", 18,11,2));
        shapes.add(new Circle("blue", -5,4,5));
        shapes.add(new Square("black", 1,2,4, 15));
        shapes.add(new Square("purple", -5,8,8, 10));
        Map map = new Map(shapes);

        System.out.println(new Mutation(Square.class).output(shapes));
        System.out.println(Filter.filter(shapes, Circle.class));
        System.out.println(map.area());
        System.out.println(map.shapesArea());
    }
}
