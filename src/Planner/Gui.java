package Planner;

import DataStructure.PerformerController;
import Simulation.SimulatorScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application implements GuiCallback {
    public Stage popUpStage;
    PerformerController performerController = new PerformerController();
    SimulatorScene simulatorScene;
    MainScene mainScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage simulatorStage = new Stage();
        this.popUpStage = new Stage();

        //make the simulator scene on startup to generate the map image
        this.simulatorScene = new SimulatorScene(this.performerController);
        simulatorStage.setScene(this.simulatorScene.getScene());

        // Make the mainScene
        this.mainScene = new MainScene(this.performerController, this, this.simulatorScene);
        primaryStage.setScene(this.mainScene.getScene());

        primaryStage.setScene(this.mainScene.getScene());
        primaryStage.setTitle("Festival planner agenda");
        primaryStage.show();
    }

    @Override
    public void setStage(Scene scene) {
        this.popUpStage.setScene(scene);
        this.popUpStage.setTitle("");
        this.popUpStage.show();
    }

    @Override
    public void setStage(Scene scene, String name) {
        this.popUpStage.setScene(scene);
        this.popUpStage.setTitle(name);
        this.popUpStage.show();
    }

    @Override
    public void closeStage() {
        this.popUpStage.close();
    }

    @Override
    public void updateLists() {
        this.mainScene.updateShows();
        this.mainScene.updatePerformerList();
    }

}
