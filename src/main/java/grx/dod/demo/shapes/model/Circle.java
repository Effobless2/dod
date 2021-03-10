package grx.dod.demo.shapes.model;

public class Circle extends Shape {
    int ray;

    public Circle(String color, int x, int y, int ray) {
        super(color, x, y, ray * 2, ray * 2);
        this.ray = ray;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "ray=" + ray +
                '}';
    }
}
