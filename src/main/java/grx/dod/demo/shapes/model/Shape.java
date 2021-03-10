package grx.dod.demo.shapes.model;

public abstract class Shape {
    private int x;
    private int y;
    private int width;
    private int height;
    private String color;

    public Shape(String color, int x, int y, int width, int height) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public int X(){
        return x;
    }

    public int Y() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
