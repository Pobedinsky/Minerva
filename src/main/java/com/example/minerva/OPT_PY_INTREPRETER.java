package com.example.minerva;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OPT_PY_INTREPRETER {

    protected static int height = 800;
    protected static int width = 600;

    @FXML
    private TextArea codeTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private void returnToMenu() throws IOException {
        Minerva.changeScene("MENU.fxml", MenuController.height, MenuController.width);
    }


    @FXML
    private void uploadCode() throws IOException {
        String python_code = Minerva.FileToString();
        codeTextArea.setText(python_code);
    }

    @FXML
    private void downloadCode() throws IOException {
        Minerva.StringToFile(codeTextArea.getText());
    }


    @FXML
    private void executeCode() {
        String pythonCode = codeTextArea.getText();
        try {
            Process process = Runtime.getRuntime().exec("python -");
            process.getOutputStream().write(pythonCode.getBytes());
            process.getOutputStream().close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            outputTextArea.setText(output.toString());
        }
        catch(IOException e){
            outputTextArea.setText("Ocorreu algum erro, por favor reinice o programa");

        }
    }

}
