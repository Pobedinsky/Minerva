package com.example.minerva;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.apache.commons.math3.analysis.function.Min;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OPT_TABLES implements Initializable {

    protected static int height = 700;
    protected static int width = 393;

    @FXML
    private TextField func, min, max;
    @FXML
    private Button exe, clean, menu;
    @FXML
    private TableView<User> tabela;
    @FXML
    private TableColumn<User, Integer> valx;
    @FXML
    private TableColumn<User, Double> valy;



    public void returnToMenu() throws IOException {
        Minerva.changeScene("MENU.fxml", MenuController.height, MenuController.width);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valx.setCellValueFactory(new PropertyValueFactory<>("valx"));
        valy.setCellValueFactory(new PropertyValueFactory<>("valy"));

        exe.setOnAction(event -> {
            String funcao = func.getText();
            String minimo = min.getText();
            String maximo = max.getText();
            int minValue = 0,maxValue =0;

            try {
                minValue = Integer.parseInt(minimo);
                maxValue = Integer.parseInt(maximo);
            }catch (NumberFormatException e){
                try {
                    Minerva.newScene("ALERT_TABLES_NO_INPUT_PROVIDED.fxml", ALERTTABLESNOINPUTPROVIDED.height, ALERTTABLESNOINPUTPROVIDED.width );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            ObservableList<User> updatedList = null;
            try {
                updatedList = parseFuncao(funcao, minValue, maxValue);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            tabela.setItems(updatedList);
        });

        clean.setOnAction(event -> {
            tabela.getItems().clear();
        });
    }

    private ObservableList<User> parseFuncao(String funcao, int min, int max) throws IOException {
        ObservableList<User> parsedList = FXCollections.observableArrayList();
        for (int i = min; i <= max; i++) {
            String replacedFunc = funcao.replace("x", String.valueOf(i));
            int valX = i;
            double valY = evaluateExpression(replacedFunc);
            parsedList.add(new User(valX, valY));
        }
        return parsedList;
    }

    private double evaluateExpression(String expression) throws IOException {
        try {
            Expression exp = new ExpressionBuilder(expression)
                    .build();
            double result = exp.evaluate();
            return  result;
        } catch (IllegalArgumentException e) {
            Minerva.newScene("NO_FUNCTION.fxml", NOFUCNTION.height, NOFUCNTION.width);
            return 0;
        }
    }
}






















