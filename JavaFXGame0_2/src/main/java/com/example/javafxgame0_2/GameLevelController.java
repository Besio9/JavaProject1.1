package com.example.javafxgame0_2;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GameLevelController implements Initializable {

    //Movement
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    private int movementSpeed = 10;

    @FXML
    private Circle myCircle;
    @FXML
    private AnchorPane scene;
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            if(wPressed.get()&& myCircle.getLayoutY()>15){
                myCircle.setLayoutY(myCircle.getLayoutY() - movementSpeed);
            }
            if(sPressed.get()&& myCircle.getLayoutY()<580){
                myCircle.setLayoutY(myCircle.getLayoutY() + movementSpeed);
            }
            if(aPressed.get()&& myCircle.getLayoutX()>15){
                myCircle.setLayoutX(myCircle.getLayoutX() - movementSpeed);
            }
            if(dPressed.get()&& myCircle.getLayoutX()<630){
                myCircle.setLayoutX(myCircle.getLayoutX() + movementSpeed);
            }
        }
    };

    @FXML
    public ImageView Present;
    public ImageView enemy1;
    public ImageView enemy2;
    public ImageView enemy3;
    public ImageView enemy4;
    public ImageView enemy5;
    public ImageView enemy6;

    AnimationTimer collisionTimer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            checkCollisionGood(myCircle, Present);
            try {
                checkCollision(myCircle, enemy1);
                checkCollision(myCircle, enemy2);
                checkCollision(myCircle, enemy2);
                checkCollision(myCircle, enemy3);
                checkCollision(myCircle, enemy4);
                checkCollision(myCircle, enemy5);
                checkCollision(myCircle, enemy6);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        collisionTimer.start();
        movementSetup();

        keyPressed.addListener(((observableValue, aBoolean, t1 ) -> {
            if(!aBoolean){
                timer.start();
            } else{
                timer.stop();
            }
        }));

        //Movement for Knight enemy
        System.out.println("initialazing");
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(enemy1);
        translate.setDuration(Duration.millis(5000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(500);
        translate.setAutoReverse(true);
        translate.play();
        // Movement for sleigh
        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(enemy2);
        translate2.setDuration(Duration.millis(4000));
        translate2.setCycleCount(TranslateTransition.INDEFINITE);
        translate2.setByX(600);
        translate2.setAutoReverse(true);
        translate2.play();
        // Movement for snowflakes
        TranslateTransition translate3 = new TranslateTransition();
        translate3.setNode(enemy3);
        translate3.setDuration(Duration.millis(7000));
        translate3.setCycleCount(TranslateTransition.INDEFINITE);
        translate3.setByX(50);
        translate3.setByY(1200);
        translate3.play();
        TranslateTransition translate4 = new TranslateTransition();
        translate4.setNode(enemy4);
        translate4.setDuration(Duration.millis(2000));
        translate4.setCycleCount(TranslateTransition.INDEFINITE);
        translate4.setByX(50);
        translate4.setByY(1200);
        translate4.play();
        TranslateTransition translate5 = new TranslateTransition();
        translate5.setNode(enemy5);
        translate5.setDuration(Duration.millis(4000));
        translate5.setCycleCount(TranslateTransition.INDEFINITE);
        translate5.setByX(50);
        translate5.setByY(1200);
        translate5.play();
        TranslateTransition translate6 = new TranslateTransition();
        translate6.setNode(enemy6);
        translate6.setDuration(Duration.millis(3000));
        translate6.setCycleCount(TranslateTransition.INDEFINITE);
        translate6.setByX(50);
        translate6.setByY(900);
        translate6.play();

    }
    public int counter=0;
    public int HP=5;
    int a,b;
    @FXML
    private Label presentsCountText;
    public void checkCollisionGood(Circle shape1, ImageView goodShape){
        if (shape1.getBoundsInParent().intersects(goodShape.getBoundsInParent())){
            counter += 1;
            a = (int)(Math.random()*(570 - 20)) + 20;
            b = (int)(Math.random()*(610 - 30)) + 30;
            Present.setLayoutX(a-25);
            Present.setLayoutY(b-25);
            System.out.println(counter);
            presentsCountText.setText("Number of presents gathered: "+ counter);
        }
    }

    @FXML
    private Label livesCountText;
    public void checkCollision(Circle shape1, ImageView enemyShape) throws FileNotFoundException {
        if (shape1.getBoundsInParent().intersects(enemyShape.getBoundsInParent())){
            HP -= 1;
            livesCountText.setText("Number of lives: "+ HP);
            myCircle.setLayoutX((int)(Math.random()*(570 - 20)) + 20);
            myCircle.setLayoutY((int)(Math.random()*(610 - 30)) + 30);
            if (HP<1){
                PrintWriter outputStream = new PrintWriter(new FileOutputStream(new File("Data.txt"), true ));
                outputStream.append("score is :"+ counter+"\n");
                outputStream.flush();
                outputStream.close();
                HP = 5;
                counter = 0;
                myCircle.setLayoutX(1000);
                myCircle.setLayoutY(1000);
                Stage stage = (Stage) MenuButton.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setScene(new Scene(root));
            }
        }
    }

    public void movementSetup(){
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.W){
                wPressed.set(true);
            }
            if(e.getCode() == KeyCode.A){
                aPressed.set(true);
            }
            if(e.getCode() == KeyCode.S){
                sPressed.set(true);
            }
            if(e.getCode() == KeyCode.D){
                dPressed.set(true);
            }
        });

        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.W){
                wPressed.set(false);
            }
            if(e.getCode() == KeyCode.A){
                aPressed.set(false);
            }
            if(e.getCode() == KeyCode.S){
                sPressed.set(false);
            }
            if(e.getCode() == KeyCode.D){
                dPressed.set(false);
            }
        });
    }

    @FXML
    private Button MenuButton;
    @FXML
    public void switchToSceneMenu(MouseEvent event) throws IOException {
        myCircle.setLayoutX(1000);
        myCircle.setLayoutY(1000);
        Stage stage = (Stage) MenuButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage.setScene(new Scene(root));
    }
}
