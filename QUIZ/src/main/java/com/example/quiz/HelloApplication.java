package com.example.quiz;

import Domain.Intrebari;
import Repository.IntrebareDBRepository;
import Repository.TwiceException;
import Service.IntrebareService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;

public class HelloApplication extends Application {
    TextField textField = new TextField();
    TextField RaTextField = new TextField();

    TextField RbTextField = new TextField();

    TextField RcTextField = new TextField();

    TextField RCorectTextField = new TextField();

    TextField PunctajTextField = new TextField();

//    IntrebareDBRepository repo = new IntrebareDBRepository();
//
//    IntrebareService intrebareService = new IntrebareService(repo);
//    intreba.add(new Intrebari(1000,"Din ce fel de nuca se pregateste Martipanul?","Alune","Migdale","Caju","Migdale",5));
    @Override
    public void start(Stage stage) throws IOException {
        IntrebareDBRepository repo = new IntrebareDBRepository();

        IntrebareService intrebareService = new IntrebareService(repo);
        repo.add(new Intrebari(1000,"Din ce fel de nuca se pregateste martipanul?","Alune","Migdale","Caju","Migdale",5));

        VBox MainVerticalBox = new VBox();
        MainVerticalBox.setPadding(new Insets(10));
        Scene scene = new Scene(MainVerticalBox, 320, 240);
        ObservableList<Intrebari> questions = FXCollections.observableArrayList(intrebareService.getAll());
        ListView<Intrebari> listView = new ListView<Intrebari>(questions);
        MainVerticalBox.getChildren().add(listView);

        GridPane gridPane = new GridPane();

        Label textLabel = new Label();
        textLabel.setText("Text: ");
        textLabel.setPadding(new Insets(10,0,10,0));

        Label RaLabel = new Label();
        RaLabel.setText("RaspunsA: ");
        RaLabel.setPadding(new Insets(10,0,10,0));

        Label RbLabel = new Label();
        RbLabel.setText("RaspunsB: ");
        RbLabel.setPadding(new Insets(10,0,10,0));

        Label RcLabel = new Label();
        RcLabel.setText("RaspunsC: ");
        RcLabel.setPadding(new Insets(10,0,10,0));

        Label RCorectLabel = new Label();
        RCorectLabel.setText("RaspunsCorect: ");
        RCorectLabel.setPadding(new Insets(10,0,10,0));

        Label punctajLabel = new Label();
        punctajLabel.setText("Punctaj: ");
        punctajLabel.setPadding(new Insets(10,0,10,0));



        gridPane.add(textLabel,0,0);
        gridPane.add(textField,1,0);

        gridPane.add(RaLabel,0,1);
        gridPane.add(RaTextField,1,1);

        gridPane.add(RbLabel,0,2);
        gridPane.add(RbTextField,1,2);

        gridPane.add(RcLabel,0,3);
        gridPane.add(RcTextField,1,3);

        gridPane.add(RCorectLabel,0,4);
        gridPane.add(RCorectTextField,1,4);


        gridPane.add(punctajLabel,0,5);
        gridPane.add(PunctajTextField,1,5);

        MainVerticalBox.getChildren().add(gridPane);

        HBox buttonsHorizontalBox = new HBox();

        MainVerticalBox.getChildren().add(buttonsHorizontalBox);
        Button addButton = new Button("Adauga Intrebare");
        MainVerticalBox.getChildren().add(addButton);

        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    Collection<Intrebari> sir = intrebareService.getAll();
                    int id = 1000+sir.size();
                    String text = textField.getText();
                    String Ra = RaTextField.getText();
                    String Rb = RbTextField.getText();
                    String Rc = RcTextField.getText();
                    String RCorect = RCorectTextField.getText();
                    int punctaj= Integer.parseInt(PunctajTextField.getText());
                    intrebareService.add(id,text,Ra,Rb,Rc,RCorect,punctaj);
                } catch (TwiceException e) {
                    Alert errorPopUp = new Alert(Alert.AlertType.ERROR);
                    errorPopUp.setTitle("Error");
                    errorPopUp.setContentText(e.getMessage());
                    errorPopUp.show();
                }
                questions.setAll(intrebareService.getAll());
            }
        });

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}