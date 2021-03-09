package grx.dod.demo.shapes;

public class Square extends Shape {

    public Square(String color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
    }

    @Override
    public double area() {
        return getWidth() * getHeight();
    }

    @Override
    public String toString() {
        return "Square{" +
                "width=" + getWidth() +
                ", height=" + getHeight() +
                '}';
    }
}
