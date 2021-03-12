package grx.dod.demo.shapes.model;

public abstract class Shape {
    private Double x;
    private Double y;
    private Double width;
    private Double height;
    private String color;

    public Shape(String color, Double x, Double y, Double width, Double height) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public Double X(){
        return x;
    }

    public Double Y() {
        return y;
    }

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
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
