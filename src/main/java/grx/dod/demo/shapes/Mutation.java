package grx.dod.demo.shapes;
import java.util.List;
import java.util.stream.Collectors;

public class Mutation implements Pipeline<List<Shape>> {
    Class c;

    public Mutation(Class c) {
        this.c = c;
    }

    @Override
    public List<Shape> output(List<Shape> input) {
        return input.stream().map(x -> {
            if (c.isInstance(x)) {
                return x;
            } else {
                if (x instanceof Square) {
                    Square s = (Square) x;
                    return new Circle(
                        s.getColor(),
                        s.X(),
                        s.Y(),
                        s.getHeight() / 2
                    );
                } else {
                    Circle c = (Circle) x;
                    return new Square(
                            c.getColor(),
                            c.X(),
                            c.Y(),
                            c.ray * 2,
                            c.ray * 2
                    );
                }
            }
        })
        .collect(Collectors.toList());
    }
}
