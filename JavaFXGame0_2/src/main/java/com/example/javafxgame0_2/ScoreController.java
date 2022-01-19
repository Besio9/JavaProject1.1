package com.example.javafxgame0_2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ScoreController implements Initializable{

    @FXML
    private Button MenuButton;

    public ScoreController() throws FileNotFoundException {
    }

    @FXML
    public void switchToSceneMenu(MouseEvent event) throws IOException {
        Stage stage = (Stage) MenuButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage.setScene(new Scene(root));

    }
    @FXML
    private ListView myListView;

    //TODO
    File file = new File("Data.txt");
    Scanner scan = new Scanner(file);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        while(scan.hasNextLine()){
            myListView.getItems().addAll(scan.nextLine());
        }
    }
}
