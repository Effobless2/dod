package grx.dod.demo.shapes.jfx;

import grx.dod.demo.shapes.model.Circle;
import grx.dod.demo.shapes.model.Rectangle;
import grx.dod.demo.shapes.model.Shape;
import grx.dod.demo.shapes.model.Transform;
import grx.dod.demo.shapes.parallel.AreaTask;
import grx.dod.demo.shapes.parallel.SpaceTask;
import grx.dod.demo.shapes.queuing.AreaEmitter;
import grx.dod.demo.shapes.queuing.SpaceEmitter;
import grx.dod.demo.shapes.queuing.TransformEmitter;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    ComboBox<String> comboProcess;
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
        comboProcess = (ComboBox<String>) root.lookup("#comboProcess");
        shapeAreaLabel = (Label) root.lookup("#shapeAreaLabel");
        totalAreaLabel = (Label) root.lookup("#totalAreaLabel");
        textFieldRay.textProperty().addListener(getListener(textFieldRay));
        textFieldPosX.textProperty().addListener(getListener(textFieldPosX));
        textFieldPosY.textProperty().addListener(getListener(textFieldPosY));
        textFieldWR.textProperty().addListener(getListener(textFieldWR));
        textFieldHR.textProperty().addListener(getListener(textFieldHR));
        comboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            comboBox.setValue(t1);
            btnDraw.setDisable(disableButton());
        });

        textFieldHR.setVisible(false);
        textFieldWR.setVisible(false);
        btnDraw.setDisable(true);

        btnDraw.setOnAction(actionEvent -> {
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
        });

        comboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            if(t1.equals("Cercle")){
                textFieldHR.setVisible(false);
                textFieldWR.setVisible(false);
                textFieldRay.setVisible(true);
            }else {
                textFieldRay.setVisible(false);
                textFieldHR.setVisible(true);
                textFieldWR.setVisible(true);
            }
        });
    }

    private void updateLabels() {
        double totalArea = 0d;
        double shapeArea = 0d;
        if (comboProcess.getValue().equals("asynchrone")) {
            try {
                System.out.println("Parallele");
                shapeArea = new AreaTask(shapes).call();
                totalArea = new SpaceTask(shapes).call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Queueing");
            shapeArea = new AreaEmitter().output(new TransformEmitter(new Transform(Rectangle.class)).output(shapes));
            totalArea = new SpaceEmitter().output(new TransformEmitter(new Transform(Rectangle.class)).output(shapes));
        }
        totalAreaLabel.setText(totalArea + "px");
        shapeAreaLabel.setText(shapeArea + "px");
    }

    private boolean disableButton() {
        return textFieldPosX.getText().equals("") ||
            textFieldPosY.getText().equals("") ||
            (
                    comboBox.getValue().equals("Cercle") && textFieldRay.getText().equals("")
            ) ||
            (
                    comboBox.getValue().equals("Rectangle") &&
                            (
                                    textFieldHR.getText().equals("") ||
                                            textFieldWR.getText().equals("")
                            )
            );
    }

    private ChangeListener<String> getListener(TextField textField){
        return (observableValue, s, t1) -> {
            if (!t1.matches("\\d*")) {
                textField.setText(t1.replaceAll("[^\\d]", ""));
            }
            btnDraw.setDisable(disableButton());
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
