package grx.dod.demo.shapes.model;

public class Circle extends Shape {
    public Double ray;

    public Circle(String color, Double x, Double y, Double ray) {
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
