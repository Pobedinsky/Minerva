package com.example.minerva;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class INTRO_CONTROLLER implements Initializable {
    protected static int height = 600;
    protected static int width = 600;

    @FXML
    public VBox main;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        main.setPrefWidth(800); // Set the preferred width
        main.setPrefHeight(600); // Set the preferred height



        // Create a fade-in transition
        FadeTransition fadeTransitionIn = new FadeTransition(Duration.seconds(3), main);
        fadeTransitionIn.setFromValue(0.0);
        fadeTransitionIn.setToValue(1.0);


        // Play the fade-in transition
        fadeTransitionIn.play();

        closeViewAfterDelay(3);


    }

    private void closeViewAfterDelay(int seconds) {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(seconds), event -> {
            try {
                Minerva.changeScene("MENU.fxml", MenuController.height, MenuController.width);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }


}
