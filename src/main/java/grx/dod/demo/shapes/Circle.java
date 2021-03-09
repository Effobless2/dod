package grx.dod.demo.shapes;

public class Circle extends Shape {
    int ray;

    public Circle(String color, int x, int y, int ray) {
        super(color, x, y, ray * 2, ray * 2);
        this.ray = ray;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(this.ray, 2);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "ray=" + ray +
                '}';
    }
}
