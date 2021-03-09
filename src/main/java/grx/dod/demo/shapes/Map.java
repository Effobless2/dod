package grx.dod.demo.shapes;

import java.util.List;
import java.util.stream.Collectors;

public class Map extends Shape {
    List<Shape> shapes;

    public Map(List<Shape> shapes) {
        super(
            "blank",
            GetPositions.getMinX(shapes),
            GetPositions.getMinY(shapes),
            GetPositions.getMaxX(shapes) - GetPositions.getMinX(shapes),
            GetPositions.getMaxY(shapes) - GetPositions.getMinY(shapes)
        );
        this.shapes = shapes;
    }

    @Override
    public double area() {
        return getHeight() * getWidth();
    }

    public double shapesArea() {
        double result = 0;
        for (Shape s : shapes) {
            result += s.area();
        }
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "shapes=" +
                shapes
                        .stream()
                        .map(x -> x.toString())
                        .collect(Collectors.joining()) +
                '}';
    }
}
