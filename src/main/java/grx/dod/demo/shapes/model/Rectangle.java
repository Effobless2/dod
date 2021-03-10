package grx.dod.demo.shapes.model;

public class Rectangle extends Shape {

    public Rectangle(String color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
    }

    @Override
    public String toString() {
        return "Square{" +
                "width=" + getWidth() +
                ", height=" + getHeight() +
                '}';
    }
}
