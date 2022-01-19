package com.example.javafxgame0_2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class SubmitController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private Button MenuButton;
    @FXML
    public void switchToSceneMenu(MouseEvent event) throws IOException {
        Stage stage = (Stage) MenuButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage.setScene(new Scene(root));
    }
    @FXML
    TextField myTextField;
    @FXML
    Button submitButton;
    @FXML
    String name;

    public void submit(MouseEvent event) throws FileNotFoundException {
        name = myTextField.getText();
        PrintWriter outputStream = new PrintWriter(new FileOutputStream(new File("Data.txt"), true ));
        outputStream.append(name+"'s ");
        outputStream.flush();
        outputStream.close();
        System.out.println(name);
    }

}
