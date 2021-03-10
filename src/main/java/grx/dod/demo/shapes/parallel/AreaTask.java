package grx.dod.demo.shapes.parallel;

import grx.dod.demo.shapes.model.Shape;
import grx.dod.demo.shapes.model.Rectangle;
import grx.dod.demo.shapes.model.Transform;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AreaTask implements Callable<Double> {
    List<Shape> shapes;

    public AreaTask(List<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public Double call() throws Exception {
        ExecutorService processeur = Executors
            .newFixedThreadPool(8);


        List<Future<Shape>> tasks = new ArrayList<>();
        for (Shape shape : shapes) {
            tasks.add(
                processeur.submit(
                    new TransformTask(
                        shape,
                        new Transform(Rectangle.class)
                    )
                )
            );
        }

        List<Future<Double>> areaTasks = new ArrayList<>();
        for (Future<Shape> task : tasks) {
            // Evaluation de la tâche
            areaTasks.add(
                processeur.submit(
                    new SingleAreaTask(
                        task.get()
                    )
                )
            );
        }

        double result  = 0d;
        for (Future<Double> task : areaTasks) {
            result += task.get();
        }
        processeur.shutdown();

        return result;
    }
}
