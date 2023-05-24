module com.example.minerva {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.net.http;
    requires json.simple;
    requires unirest.java;
    requires com.fasterxml.jackson.databind;
    requires org.jfree.jfreechart;
    requires commons.math3;
    requires javaluator;
    requires java.scripting;
    requires exp4j;

    opens com.example.minerva to javafx.fxml;
    exports com.example.minerva;
}