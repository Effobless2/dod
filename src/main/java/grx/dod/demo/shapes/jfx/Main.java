package grx.dod.demo.shapes.jfx;

import grx.dod.demo.shapes.model.Circle;
import grx.dod.demo.shapes.model.Rectangle;
import grx.dod.demo.shapes.model.Shape;
import grx.dod.demo.shapes.parallel.AreaTask;
import grx.dod.demo.shapes.parallel.SpaceTask;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Main extends Application {

    Stage primaryStage;
    Button btnDraw;
    TextField textFieldRay;
    TextField textFieldPosX;
    TextField textFieldPosY;
    TextField textFieldWR;
    TextField textFieldHR;
    ComboBox<String> comboBox;
    ComboBox<String> comboBoxColor;
    List<Shape> shapes = new ArrayList<>();
    GraphicsContext gc;
    Label shapeAreaLabel;
    Label totalAreaLabel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/layout/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.show();

        Canvas canvas = (Canvas) root.lookup("#canvasShapes");
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.save();

        btnDraw = (Button) root.lookup("#btnDraw");
        textFieldRay = (TextField) root.lookup("#ray");
        textFieldPosX = (TextField) root.lookup("#posX");
        textFieldPosY = (TextField) root.lookup("#posY");
        textFieldWR = (TextField) root.lookup("#widthRec");
        textFieldHR = (TextField) root.lookup("#heightRec");

        comboBox = (ComboBox<String>) root.lookup("#cb");
        comboBoxColor = (ComboBox<String>) root.lookup("#color");
        shapeAreaLabel = (Label) root.lookup("#shapeAreaLabel");
        totalAreaLabel = (Label) root.lookup("#totalAreaLabel");
        textFieldRay.textProperty().addListener(getListener(textFieldRay));
        textFieldPosX.textProperty().addListener(getListener(textFieldPosX));
        textFieldPosY.textProperty().addListener(getListener(textFieldPosY));
        textFieldWR.textProperty().addListener(getListener(textFieldWR));
        textFieldHR.textProperty().addListener(getListener(textFieldHR));

        textFieldHR.setVisible(false);
        textFieldWR.setVisible(false);

        btnDraw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String color = comboBoxColor.getValue();
                Double posX = Double.valueOf(textFieldPosX.getText());
                Double posY = Double.valueOf(textFieldPosY.getText());
                Shape shape;

                if(comboBox.getValue().equals("Cercle")){
                    Double ray = Double.valueOf(textFieldRay.getText());
                    shape = new Circle(color, posX, posY, ray);
                }else{
                    Double widthRec = Double.valueOf(textFieldWR.getText());
                    Double heightRec = Double.valueOf(textFieldHR.getText());
                    shape = new Rectangle(color, posX, posY, widthRec, heightRec);
                }
                shapes.add(shape);
                drawShapes(shape);
                updateLabels();
            }
        });

        comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                System.out.println("TOTO = " + t1);
                if(t1.equals("Cercle")){
                    textFieldHR.setVisible(false);
                    textFieldWR.setVisible(false);
                    textFieldRay.setVisible(true);
                }else {
                    textFieldRay.setVisible(false);
                    textFieldHR.setVisible(true);
                    textFieldWR.setVisible(true);
                }
            }
        });

    }

    private void updateLabels() {
        double totalArea = 0d;
        double shapeArea = 0d;
        try {
            shapeArea = new AreaTask(shapes).call();
            totalArea = new SpaceTask(shapes).call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        totalAreaLabel.setText(totalArea + "px");
        shapeAreaLabel.setText(shapeArea + "px");
    }

    private ChangeListener<String> getListener(TextField textField){
        return new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*")) {
                    textField.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        };
    }

    private void drawShapes(Shape shape) {
        if (comboBoxColor.getValue().equals("Rouge")){
            gc.setFill(Color.RED);
        }else if(comboBoxColor.getValue().equals("Bleu")){
            gc.setFill(Color.BLUE);
        }else if(comboBoxColor.getValue().equals("Vert")){
            gc.setFill(Color.GREEN);
        }else{
            gc.setFill(Color.YELLOW);
        }


        if(Circle.class.isInstance(shape)){
            gc.fillOval(shape.X(), shape.Y(), ((Circle) shape).ray*2, ((Circle) shape).ray*2);
        }else {
            gc.fillRect(shape.X(), shape.Y(), shape.getWidth(), shape.getHeight());
        }

/*
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
*/

    }

    public static void main(String[] args) {
        launch(args);
    }
}
