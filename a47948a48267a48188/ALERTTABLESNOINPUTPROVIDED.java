package com.example.minerva;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ALERTTABLESNOINPUTPROVIDED {

    protected static int height = 450;
    protected static int width = 300;


    @FXML
    private VBox aux;



    @FXML
    public void returnBack() throws IOException {
        ((Stage)aux.getScene().getWindow()).close();

    }
}
