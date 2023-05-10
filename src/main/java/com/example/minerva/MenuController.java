package com.example.minerva;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MenuController implements Initializable {
    @FXML
    private GridPane menu;

    @FXML
    private ImageView[] OPTS;

    private String choice= "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<ImageView> nodes = menu.getChildren().stream()
                .filter(child -> child instanceof ImageView && ((ImageView) child).getId() != null && ((ImageView) child).getId().startsWith("OPT_"))
                .map(child -> (ImageView) child)
                .collect(Collectors.toList());

        OPTS = new ImageView[nodes.size()];
        int i = 0;
        for (Node node : nodes) {
            OPTS[i++] = (ImageView) node;
            (OPTS[i-1]).setOnMouseClicked(e -> onclick(((ImageView)e.getSource()).getId()));

        }
    }


    @FXML
    protected void onclick(String el) {

        System.out.println(el);
        choice = "";


    }
}