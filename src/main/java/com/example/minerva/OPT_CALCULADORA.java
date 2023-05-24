package com.example.minerva;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import net.objecthunter.exp4j.*;
import net.objecthunter.exp4j.function.Functions;

import javax.script.*;




public class OPT_CALCULADORA {

    protected static int height = 700;
    protected static int width = 800;

    @FXML
    private TextArea out;


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

        if(in.equals("abs") |in.equals("i") |in.equals("Ans") |in.equals("log(") |in.equals("ln(") |in.equals("log") |in.equals("ln")){
            return true;
        }
        return false;
    }


    public boolean isMulti(String el){
        return el.equals("cos") | el.equals("arccos") | el.equals("sin") | el.equals("arcsin") | el.equals("tan") | el.equals("arctan") | el.equals("ln") | el.equals("log") | el.equals("pi");
    }


    @FXML

    public void clearinput(){
        inputText="";
        PREVIOUS_INPUT.clear();
        out.setText("");
    }





    @FXML
    public void appendText(ActionEvent e) {

        Button button = (Button) e.getSource();
        String buttonText = button.getText();

        boolean isTrigo = isMulti(buttonText);
        boolean isprev = false;

        if(buttonText.equals("\uD835\uDFB9")){
            buttonText = "3.14159265359";
        }
        if(buttonText.equals("e")){
            buttonText = "2.71828182846";
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
            out.appendText(PREVIOUS_ANSWER);
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
            out.appendText(prev_input);
        }
        else{
            out.appendText(prev_input);
            PREVIOUS_INPUT.add(prev_input);

            System.out.println(PREVIOUS_INPUT);

        }

    }

    @FXML
    public void clear(ActionEvent e){
        int length = inputText.length();
        if(length>0) {
            int index = out.getText().length()-1;
            int index_input = PREVIOUS_INPUT.size()-1;
            int size_of_deleted_input = PREVIOUS_INPUT.get(index_input).length();
            out.deleteText(index+1-size_of_deleted_input,index+1);
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

            System.out.println("MODIFIED PREV ANSWER: "+ PREVIOUS_ANSWER);


            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine nashorn = manager.getEngineByName("JavaScript");
            try {
                Double eval = (double) nashorn.eval(PREVIOUS_ANSWER);
                out.appendText("val : " + eval + "\n");
                PREVIOUS_ANSWER = "" + eval;
            }
            catch (ClassCastException e){
                Integer eval = (int) nashorn.eval(PREVIOUS_ANSWER);
                out.appendText("val : " + eval + "\n");
                PREVIOUS_ANSWER = "" + eval;
            }


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




}
