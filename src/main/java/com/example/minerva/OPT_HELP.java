package com.example.minerva;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class OPT_HELP {

    protected static int height = 700;
    protected static int width = 550;


    @FXML
    private VBox aux;



    @FXML
    public void returnBack() throws IOException {
        ((Stage)aux.getScene().getWindow()).close();


    }
}
