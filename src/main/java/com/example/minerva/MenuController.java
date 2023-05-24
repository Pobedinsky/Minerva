package com.example.minerva;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MenuController {

    protected static int height = 1000;
    protected static int width = 500;



    @FXML
    public void GO_TO_OPT_CALCULADORA() throws IOException {
        Minerva.changeScene("OPT_CALCULADORA.fxml", OPT_CALCULADORA.height, OPT_CALCULADORA.width);
    }

    @FXML
    public void GO_TO_OPT_GRAPHICS() throws IOException {
        Minerva.changeScene("OPT_GRAPH.fxml", OPTGRAPHICS.height, OPTGRAPHICS.width);
    }

    @FXML
    public void GO_TO_PYINTREPRETER() throws IOException {
        Minerva.changeScene("OPT_PY_INTREPRETER.fxml", OPT_PY_INTREPRETER.height, OPT_PY_INTREPRETER.width);
    }

    @FXML
    public void GO_TO_EXIT() throws IOException {
        Minerva.changeScene("EXIT.fxml", EXIT.height, EXIT.width);
    }

    @FXML
    public void GO_TO_OPT_EQUATIONS() throws IOException {
        Minerva.changeScene("OPT_EQ.fxml", OPT_EQS.height, OPT_EQS.width);
    }

    @FXML
    public void GO_TO_OPT_TABELAS() throws IOException {
        Minerva.changeScene("OPT_TABLES.fxml", OPT_TABLES.height, OPT_TABLES.width);
    }



}