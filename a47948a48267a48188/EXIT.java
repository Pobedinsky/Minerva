package com.example.minerva;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EXIT implements Initializable {

    protected static int height = 600;
    protected static int width = 650;

    @FXML
    private VBox aux;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Create a fade-in transition
        FadeTransition fadeTransitionIn = new FadeTransition(Duration.seconds(2), aux);
        fadeTransitionIn.setFromValue(0.0);
        fadeTransitionIn.setToValue(1.0);


        // Play the fade-in transition
        fadeTransitionIn.play();



        closeViewAfterDelay(3);


    }

    private void closeViewAfterDelay(int seconds) {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(seconds), event -> {
            Platform.exit();
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

}
