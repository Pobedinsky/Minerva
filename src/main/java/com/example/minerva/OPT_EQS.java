package com.example.minerva;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.math3.analysis.function.Min;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OPT_EQS {

    protected static int height = 700;
    protected static int width = 800;

    @FXML
    private TextArea out;

    @FXML
    private TextField in;


    private boolean isLog = false;


    private String inputText = "";

    private String PREVIOUS_ANSWER = "";

    private ArrayList<String> PREVIOUS_INPUT = new ArrayList<>();

    private TextArea output;

    private int MODE = 0;



    public boolean validInput(String in){
        try{
            Integer.parseInt(in);
            return true;
        }
        catch (NumberFormatException el){
        }

        if(in.equals("abs") |in.equals("i") |in.equals("Ans") |in.equals("log(") |in.equals("ln(") |in.equals("log") |in.equals("ln")| in.equals("x")| in.equals("y")| in.equals("z")| in.equals("a")| in.equals("b")){
            return true;
        }
        return false;
    }


    public boolean isMulti(String el){
        return el.equals("cos") | el.equals("arccos") | el.equals("sin") | el.equals("arcsin") | el.equals("tan") | el.equals("arctan") | el.equals("ln") | el.equals("log");
    }


    @FXML

    public void askForHelp() throws IOException {
        Minerva.newScene("OPT_HELP.fxml", OPT_HELP.height, OPT_HELP.width);


    }

    @FXML

    public void clearinput(){
        inputText="";
        PREVIOUS_INPUT.clear();
        out.setText("");
        in.setText("");
    }


    @FXML
    public void upload() throws IOException {
        inputText = Minerva.FileToString();
        in.setText(inputText);

    }
    @FXML
    public void download() throws IOException {
        Minerva.StringToFile("FUNCTION: "+in.getText()+"\n\nRESULT: "+out.getText()+"\n");

    }





    @FXML
    public void appendText(ActionEvent e) {

        Button button = (Button) e.getSource();
        String buttonText = button.getText();

        boolean isTrigo = isMulti(buttonText);
        boolean isprev = false;

        if(buttonText.equals("\uD835\uDFB9")){
            buttonText = "pi";
        }
        if(buttonText.equals("√")){
            buttonText = "sqrt(";
        }


        if(buttonText.equals("Ans")){
            buttonText = PREVIOUS_ANSWER;
            isprev = true;
            System.out.println("I entered at Prev");
        }
        if(inputText.equals("") && !(validInput(buttonText)) && !(isTrigo) && !isprev){
            inputText+=PREVIOUS_ANSWER;
            in.appendText(PREVIOUS_ANSWER);
            PREVIOUS_INPUT.add(buttonText);
            System.out.println("Im here");
        }

        inputText = (isTrigo)?inputText+buttonText+"(":inputText+buttonText;
        String prev_input = (isTrigo)?buttonText+"(":buttonText;

        if(isTrigo){

            char[] arr = prev_input.toCharArray();

            for(int i = 0; i < arr.length; i++){
                PREVIOUS_INPUT.add(String.valueOf(arr[i]));

            }
            in.appendText(prev_input);
        }
        else{
            in.appendText(prev_input);
            PREVIOUS_INPUT.add(prev_input);

            System.out.println(PREVIOUS_INPUT);

        }

    }

    @FXML
    public void clear(ActionEvent e){
        int length = inputText.length();
        if(length>0) {
            int index = in.getText().length()-1;
            int index_input = PREVIOUS_INPUT.size()-1;
            int size_of_deleted_input = PREVIOUS_INPUT.get(index_input).length();
            in.deleteText(index+1-size_of_deleted_input,index+1);
            PREVIOUS_INPUT.remove(index_input);
            inputText=inputText.substring(0, inputText.length()-1);
            System.out.println(inputText);



        }

    }

    @FXML
    public void returnToMenu() throws IOException {
        Minerva.changeScene("MENU.fxml", MenuController.height, MenuController.width);
    }
    @FXML

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void calculate() throws ScriptException {
        if(inputText.isEmpty()){

            PREVIOUS_ANSWER= PREVIOUS_ANSWER.replace("sin", "Math.sin");
            PREVIOUS_ANSWER= PREVIOUS_ANSWER.replace("cos", "Math.cos");
            PREVIOUS_ANSWER= PREVIOUS_ANSWER.replace("tan", "Math.tan");
            PREVIOUS_ANSWER= PREVIOUS_ANSWER.replace("log", "Math.log");
            PREVIOUS_ANSWER = PREVIOUS_ANSWER.replace("arcsin", "Math.asin");
            PREVIOUS_ANSWER = PREVIOUS_ANSWER.replace("arctan", "Math.atan");
            PREVIOUS_ANSWER = PREVIOUS_ANSWER.replace("arccos", "Math.acos");
            PREVIOUS_ANSWER = PREVIOUS_ANSWER.replace("pi", "(Math.PI)");

            System.out.println("MODIFIED PREV ANSWER: "+ PREVIOUS_ANSWER);


            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine nashorn = manager.getEngineByName("JavaScript");
            Double eval = (Double) nashorn.eval(PREVIOUS_ANSWER);

            out.appendText("val : "+eval+"\n");
            PREVIOUS_ANSWER = ""+eval;


        }
        else{
            System.out.println(inputText);
            API_READER apiReader = new API_READER("simplify", inputText);
            String OUTPUT_FROM_API = apiReader.getAPIOutput();


            out.appendText("\nval : "+OUTPUT_FROM_API+"\n");
            PREVIOUS_ANSWER = OUTPUT_FROM_API;
            inputText = "";
            out.widthProperty().asString();

        }
    }


    @FXML

    public void findZeroes(ActionEvent e){
        System.out.println(inputText);
        API_READER apiReader = new API_READER("zeroes", inputText);
        String OUTPUT_FROM_API = apiReader.getAPIOutput();

        if(OUTPUT_FROM_API == null){
            out.appendText("\nZeros = Not Found\n");
            return;

        }

        System.out.println(OUTPUT_FROM_API);

        String regex ="-?\\d+(\\.\\d+)?";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(OUTPUT_FROM_API);

        List<Double> numbers = new ArrayList<>();

        while (matcher.find()) {
            String numberStr = matcher.group();
            double number = Double.parseDouble(numberStr);
            numbers.add(number);
        }

        String to_out = "{  ";

        for (double  number : numbers) {
            to_out += number+"  ";
        }
        to_out += "}";

        out.appendText("\nZeros = "+ to_out+"\n");
        PREVIOUS_ANSWER = to_out;
    }


    @FXML

    public void derive(ActionEvent e){
        API_READER apiReader = new API_READER("derive", inputText);
        String OUTPUT_FROM_API = apiReader.getAPIOutput();

        System.out.println(OUTPUT_FROM_API);
        out.appendText("\nDerivada = "+ OUTPUT_FROM_API+"\n");

    }

    @FXML

    public void integrar(ActionEvent e){
        API_READER apiReader = new API_READER("integrate", inputText);
        String OUTPUT_FROM_API = apiReader.getAPIOutput();

        System.out.println(OUTPUT_FROM_API);
        out.appendText("\nIntegral = "+ OUTPUT_FROM_API+"\n");

    }
}
