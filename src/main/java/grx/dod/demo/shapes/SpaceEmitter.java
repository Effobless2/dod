package grx.dod.demo.shapes;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class SpaceEmitter implements Pipeline<Integer> {
    @Override
    public Integer output(List<Shape> input) {
        if (input.size() == 0)
            return 0;
        Optional<Integer> minX = Optional.ofNullable(null);
        Optional<Integer> minY = Optional.ofNullable(null);
        Optional<Integer> maxX = Optional.ofNullable(null);
        Optional<Integer> maxY = Optional.ofNullable(null);

        Iterator<Shape> it = input.iterator();
        while (it.hasNext()) {
            Shape s = it.next();
            if(!minX.isPresent() || minX.get() > s.X() ) {
                minX = Optional.of(s.X());
            }
            if(!minY.isPresent() || minY.get() > s.Y() ) {
                minY = Optional.of(s.Y());
            }
            if(!maxY.isPresent() || maxY.get() < s.Y() ) {
                maxY = Optional.of(s.Y());
            }
            if(!maxX.isPresent() || maxX.get() < s.X() ) {
                maxX = Optional.of(s.X());
            }
        }
        return (maxX.get() - minX.get()) * (maxY.get() - minY.get());
    }
}
