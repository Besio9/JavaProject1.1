package com.example.javafxgame0_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private Stage stage;

    @FXML
    private Button PlayButton;
    @FXML
    public void switchToGame(MouseEvent event) throws IOException {
        Stage stage = (Stage) PlayButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("GameLevel.fxml"));
        stage.setScene(new Scene(root));

    }
    @FXML
    private Button SubmitButton;
    @FXML
    public void switchToSubmitScene(MouseEvent event) throws IOException {
        Stage stage = (Stage) SubmitButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Submit.fxml"));
        stage.setScene(new Scene(root));
    }
    @FXML
    private Button listButton;
    @FXML
    public void switchToList(MouseEvent event) throws IOException {
        Stage stage = (Stage) listButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ScoreMenu.fxml"));
        stage.setScene(new Scene(root));
    }

    @FXML
    private AnchorPane scenePane;

    public void exit(ActionEvent event){
        stage =(Stage) scenePane.getScene().getWindow();
        System.out.println("Exitted");
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}