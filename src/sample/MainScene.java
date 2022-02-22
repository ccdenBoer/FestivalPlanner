package sample;

import DataStructure.Data.Location;
import DataStructure.Data.Performer;
import DataStructure.Data.Show;
import DataStructure.PerformerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class MainScene extends StandardScene {
    ListView<String> performerList = new ListView<>();
    PerformerController performerController = new PerformerController();
    //stage title and main borderpane
    BorderPane agendaBorderPane = new BorderPane();
    GridPane buttons = new GridPane();
    //Vbox components
    VBox performerVBox = new VBox();
    Label performerLabel = new Label("Performers:");
    Button addPerformer = new Button("Add performer");
    Button removePerformer = new Button("Remove performer");
    Button editPerformer = new Button("Edit performer");
    Button addShow = new Button("Add show");
    Button addLocation = new Button("Add Location");
    Button removeLocation = new Button("Remove Location");
    Button editLocation = new Button("Edit Location");
    Agenda agenda = new Agenda();
    String selectedPerformer;
    TableView showsTable = new TableView<>();


    public MainScene() {

        performerList = new ListView();
        performerList.setOnMousePressed(e -> {
            selectedPerformer = performerList.getSelectionModel().getSelectedItem();
            //performerController.editPerformer(e.g);

        });
        //List components
        performerList.setOrientation(Orientation.VERTICAL);


        TextField textField = new TextField();

        //schedule stuff
//        Agenda agenda = new Agenda();
//        agenda.setHeight(400);
//        agenda.setWidth(3000);
//        agenda.drawAgendaBase();
//        agenda.addShow();
//        agenda.addShow();
//        agenda.addShow();
//        agenda.drawShows();
//
//        agenda.setOnMousePressed(e -> agenda.mousePressed(e));
//        agenda.setOnMouseReleased(e -> agenda.mouseReleased(e));
//        agenda.setOnMouseDragged(e -> agenda.moveOnMouse(e.getX(), e.getY()));
//
//        ScrollPane agendaScroll = new ScrollPane();
//        agendaScroll.setContent(agenda);
//        agendaScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//        agendaScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//        agendaScroll.fitToWidthProperty().setValue(false);
//        agendaScroll.fitToHeightProperty().setValue(false);
//        agendaScroll.setMaxHeight(600);
//        agendaScroll.setMaxWidth(800);

        TableColumn showName = new TableColumn("Show");
        showName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn performer = new TableColumn("Performer");
        performer.setCellValueFactory(new PropertyValueFactory<>("performer.getName()"));
        TableColumn beginTime = new TableColumn("Begin time");
        beginTime.setCellValueFactory(new PropertyValueFactory<>("beginTime"));
        TableColumn endTime = new TableColumn("End time");
        endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
//        ArrayList<Show> shows = performerController.getShows();

        //shows.add(new Show("yee", null, new Performer("eey"), LocalTime.NOON, LocalTime.now()));
        showsTable.getColumns().addAll(showName, performer, beginTime, endTime);

        updateShows();


        buttons.addColumn(0, addPerformer, editPerformer, removePerformer);
        buttons.addColumn(1, addShow);
        buttons.addColumn(2, addLocation, editLocation, removeLocation);
        performerVBox.getChildren().addAll(performerLabel, performerList, buttons);
        agendaBorderPane.setRight(performerVBox);

        removePerformer.setOnAction(E -> {
            System.out.println(performerList.getSelectionModel().getSelectedItem());
            performerController.removePerformer(performerList.getSelectionModel().getSelectedItem() + "");
            performerController.updateList(performerList);
        });


//        agendaBorderPane.setLeft(agendaScroll);
        agendaBorderPane.setLeft(showsTable);
        this.scene = new Scene(agendaBorderPane);

    }

    public void updateShows() {
        ArrayList<Show> shows = performerController.getShows();
        int i = 0;
        for (Show show : shows) {
            showsTable.getItems().add(i, show);
            i++;
        }
    }
}
