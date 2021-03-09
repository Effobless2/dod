package grx.dod.demo.shapes;

import java.util.List;
import java.util.stream.Collectors;

public class Filter {

    public static List<Shape> filter(List<Shape> shapes, Class c) {
        return shapes
                .stream()
                .filter(x -> c.isInstance(x))
                .collect(Collectors.toList());
    }
}
