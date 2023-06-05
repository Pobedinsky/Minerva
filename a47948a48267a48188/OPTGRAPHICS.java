package com.example.minerva;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.io.IOException;


public class OPTGRAPHICS {

    protected static int height = 800;
    protected static int width = 600;


    @FXML
    private TextField functionInput;

    @FXML
    private LineChart<Number, Number> lineChart;

    private XYChart.Series<Number, Number> series;

    private String out ="";

    @FXML
    private void returnToMenu() throws IOException {
        Minerva.changeScene("MENU.fxml", MenuController.height, MenuController.width);
    }

    @FXML
    private void upload() throws IOException {
        String content = Minerva.FileToString();
        functionInput.setText(content);
    }

    @FXML
    private void download() throws IOException {
        Minerva.StringToFile(out);
    }

    @FXML
    private void plotFunction() throws IOException {

        if (series != null) {
            lineChart.getData().remove(series);
        }
        out = "";

        // Get the function expression from user input
        String functionExpression = functionInput.getText();
        out+=("FUNCTION: " + functionExpression + "\n");

        // Create the function using the expression
        Expression expression;
        try {
            expression = new ExpressionBuilder(functionExpression)
                    .variable("x")
                    .build();
        } catch (IllegalArgumentException e) {
            Minerva.newScene("NO_FUNCTION.fxml", NOFUCNTION.height, NOFUCNTION.width);
            return;
        }

        // Create the data series for the function
        series = new XYChart.Series<>();

        // Determine the range of x-values based on the line chart's x-axis
        Axis<Number> xAxis = lineChart.getXAxis();
        double minX = xAxis.getValueForDisplay(xAxis.getBoundsInLocal().getMinX()).doubleValue();
        double maxX = xAxis.getValueForDisplay(xAxis.getBoundsInLocal().getMaxX()).doubleValue();
        double step = (maxX - minX) / 1000;

        out+=("minX: " + minX + " maxX: " + maxX + " step: " + step + "\n");

        // Add data points to the series
        for (double x = minX; x <= maxX; x += step) {
            expression.setVariable("x", x);
            double y = expression.evaluate();
            if(!Double.isNaN(y)){
                series.getData().add(new XYChart.Data<>(x, y));
                out+=("x: "+x+ ", y: "+y);

            }

        }

        // Add the series to the line chart
        lineChart.getData().add(series);
    }
}
