package grx.dod.demo.shapes.model;

public class Transform {
    Class c;

    public Transform(Class c) {
        this.c = c;
    }

    public Shape transform(Shape shape) {
        if (c.isInstance(shape)) {
            return shape;
        } else {
            if (shape instanceof Rectangle) {
                Rectangle s = (Rectangle) shape;
                return new Circle(
                        s.getColor(),
                        s.X(),
                        s.Y(),
                        s.getHeight() / 2
                );
            } else {
                Circle c = (Circle) shape;
                return new Rectangle(
                        c.getColor(),
                        c.X(),
                        c.Y(),
                        c.ray * 2,
                        c.ray * 2
                );
            }
        }
    }
}
