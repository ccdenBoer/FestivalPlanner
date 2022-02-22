package sample;

//import javafx.*;
//import javafx.application.Application;

import DataStructure.PerformerController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

//import javax.swing.*;

public class AddLocation extends StandardScene {
    private TextField loactionNameField;


    public AddLocation(PerformerController controller) {
        GridPane pane = new GridPane();

        this.loactionNameField = new TextField();
        Button saveButton = new Button("Add Location");
        pane.add(loactionNameField, 0, 0);
        pane.add(saveButton, 0, 1);

        saveButton.setOnAction(e -> {
            controller.addLocation(loactionNameField.getText());
        });


        scene = new Scene(pane);
    }

}

