package grx.dod.demo.shapes;

import java.util.List;

public interface  Pipeline<T> {
    T output(List<Shape> input);
}
