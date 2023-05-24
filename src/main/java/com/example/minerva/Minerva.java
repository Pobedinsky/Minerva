package com.example.minerva;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


import java.io.IOException;

public class Minerva extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;


        FXMLLoader fxmlLoader = new FXMLLoader(Minerva.class.getResource("INTRO.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        stage.setTitle("MiNERVA");
        Image aux = new Image("https://media.discordapp.net/attachments/1103016266492551228/1105901074478534677/image.png?width=542&height=662");
        primaryStage.getIcons().add(aux);


        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    public static void newScene(String fxml, int height, int width) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(Minerva.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), height, width);
        Stage aux = new Stage();
        aux.setResizable(false);
        aux.getIcons().add(new Image("https://media.discordapp.net/attachments/1103016266492551228/1105901074478534677/image.png?width=542&height=662"));

        aux.setTitle("Help");
        aux.setScene(scene);
        aux.show();

    }

    public static void changeScene(String fxml, int height, int width) throws IOException {
        changeScene(fxml, height, width, true);
    }

    public static void changeScene(String fxml, int height, int width, boolean isResizable) throws IOException {
        primaryStage.setTitle("MiNERVA");
        FXMLLoader fxmlLoader = new FXMLLoader(Minerva.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load(), height, width);
        primaryStage.getIcons().add(new Image("https://media.discordapp.net/attachments/1103016266492551228/1105901074478534677/image.png?width=542&height=662"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(isResizable);
        primaryStage.show();
    }

    public static String FileToString() throws IOException {
        FileChooser fileChooser = new FileChooser();

        File initialDirectory = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(initialDirectory);

        fileChooser.setTitle("Select a file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));

        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        String fileContent =  new String(Files.readAllBytes(Paths.get(selectedFile.getPath())));
        return fileContent;
    }

    public static void StringToFile(String content) throws IOException {
        FileChooser fileChooser = new FileChooser();

        File initialDirectory = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(initialDirectory);

        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File selectedFile = fileChooser.showSaveDialog(primaryStage);

        BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile.getPath()));
        writer.write(content);
        writer.close();

    }
}