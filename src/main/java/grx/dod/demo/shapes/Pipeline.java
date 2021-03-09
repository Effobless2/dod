package grx.dod.demo.shapes;

import java.util.List;

public interface Pipeline {
    List<Shape> output(List<Shape> input);
}
