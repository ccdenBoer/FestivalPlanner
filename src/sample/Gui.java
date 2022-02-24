package sample;

import DataStructure.Data.Artist;
import DataStructure.Data.Band;
import DataStructure.PerformerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.*;

public class Gui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // The different scenes
        MainScene mainScene = new MainScene();
        Stage mainStage = new Stage();
        mainStage.setScene(mainScene.getScene());

        AddEditPerformerScene addPerformerScene = new AddEditPerformerScene();
        Stage addPerformerStage = new Stage();
        addPerformerStage.setScene(addPerformerScene.getScene());

        AddBandScene addBandScene = new AddBandScene();
        Stage addBandStage = new Stage();
        addBandStage.setScene(addBandScene.getScene());

        EditArtistScene editArtistScene = new EditArtistScene();
        Stage editArtistStage = new Stage();
        editArtistStage.setScene(editArtistScene.getScene());

        AddShowScene addShowScene = new AddShowScene(mainScene.performerController);
        Stage addPerformanceStage = new Stage();
        addPerformanceStage.setScene(addShowScene.getScene());
        EditBandScene editBandScene = new EditBandScene();
        Stage editBandStage = new Stage();
        editBandStage.setScene(editBandScene.getScene());

        mainScene.addShow.setOnAction(e -> {
            System.out.println("opening");
            addShowScene.setVariables(mainScene.performerList.getSelectionModel().getSelectedItem());
            addPerformanceStage.setResizable(false);
            addPerformanceStage.show();

        });
        mainScene.saveButton.setOnAction(e -> {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream("Data.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
                oos.writeObject(mainScene.performerController);
                fileOutputStream.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        mainScene.loadButton.setOnAction(e -> {
            try {
                FileInputStream fis = new FileInputStream("Data.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
               PerformerController newPerformerController = (PerformerController) ois.readObject();
               mainScene.performerController.loadFrom(newPerformerController);
                fis.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        addShowScene.getSave().setOnAction(e -> {
            addShowScene.saveShow();
            mainScene.updateShows();
        });
        mainScene.addLocation.setOnAction(e -> {
            Stage addLoactionStage = new Stage();
            addLoactionStage.setScene(new AddLocation(mainScene.performerController).getScene());
            addLoactionStage.show();
        });

        //Button eventhandling
        mainScene.editPerformer.setOnAction(E -> {
            if (!mainScene.performerList.getSelectionModel().isEmpty()) {

                for (Artist artist : mainScene.performerController.getArtists()){
                    editArtistScene.setOldArtist(mainScene.performerList.getSelectionModel().getSelectedItem());
                    if (artist.getPerformerName().equals(editArtistScene.getOldArtist())) {
                        editArtistScene.getArtistField().setText(mainScene.performerList.getSelectionModel().getSelectedItem());
                        editArtistStage.show();
                        break;
                    }
                }
                for (Band band : mainScene.performerController.getBands()) {
                    editBandScene.setOldArtist(mainScene.performerList.getSelectionModel().getSelectedItem());
                    if (band.getPerformerName().equals(editBandScene.getOldArtist())) {
                        editBandScene.getBandField().setText(mainScene.performerList.getSelectionModel().getSelectedItem());
                        editBandStage.show();
                    }
                }

            }
        });


        addBandScene.switchToArtistButton.setOnAction(E -> {
            addBandStage.close();
            addPerformerStage.show();
        });

        addBandScene.backButton.setOnAction(E -> {
            addBandStage.close();
        });
        addBandScene.addMemberButton.setOnAction(E -> {
            if (!addBandScene.addMemberField.getText().trim().equals("")){
                addBandScene.newBandMemberList.getItems().add(addBandScene.addMemberField.getText());
            }
            addBandScene.addMemberField.setText("");
        });

        addBandScene.addButton.setOnAction(E -> {
            if (!addBandScene.performerNameTextField.getText().isEmpty()) {
                mainScene.performerController.addBand(addBandScene.performerNameTextField.getText());
                mainScene.performerController.addBandMembers(addBandScene.newBandMemberList, addBandScene.performerNameTextField.getText());
                mainScene.performerController.updateList(mainScene.performerList);
                addBandScene.newBandMemberList.getItems().clear();
                addBandScene.performerNameTextField.deleteText(0, addBandScene.performerNameTextField.getText().length());
            }
        });

        mainScene.addPerformer.setOnAction(E -> {
            addPerformerStage.show();
        });

        primaryStage.setTitle("Festival planner agenda");
        primaryStage.setScene(mainScene.getScene());
        primaryStage.show();
    }
}
