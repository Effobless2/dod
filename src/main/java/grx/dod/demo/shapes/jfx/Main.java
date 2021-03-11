package grx.dod.demo.shapes.jfx;

import grx.dod.demo.shapes.model.Shape;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    Button btnDraw;
    TextField textFieldColor;
    TextField textFieldRay;
    TextField textFieldPosX;
    TextField textFieldPosY;
    TextField textFieldWR;
    TextField textFieldHR;
    ComboBox<String> comboBox;
    List<Shape> shapes = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/layout/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.show();

        btnDraw = (Button) root.lookup("#btnDraw");
        textFieldColor = (TextField) root.lookup("#color");
        textFieldRay = (TextField) root.lookup("#ray");
        textFieldPosX = (TextField) root.lookup("#posX");
        textFieldPosY = (TextField) root.lookup("#posY");
        textFieldWR = (TextField) root.lookup("#widthRec");
        textFieldHR = (TextField) root.lookup("#heightRec");
        comboBox = (ComboBox<String>) root.lookup("#cb");

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
                String color = textFieldColor.getText();
                Double posX = Double.valueOf(textFieldPosX.getText());
                Double posY = Double.valueOf(textFieldPosY.getText());

                System.out.println("TA MAMAN EN STRING! " + posX.toString() + " " + posY.toString());
                if(comboBox.getValue().equals("Cercle")){
                    Double ray = Double.valueOf(textFieldRay.getText());
                }else{
                    Double widthRec = Double.valueOf(textFieldWR.getText());
                    Double heightRec = Double.valueOf(textFieldHR.getText());
                }
                //TODO: CONTINUER BUTTON ACTION
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

    public static void main(String[] args) {
        launch(args);
    }
}
