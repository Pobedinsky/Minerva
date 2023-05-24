package com.example.minerva;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;



public class API_READER {

    private String OPT;
    private String ARGUMENT;

    private String API_OUTPUT;


    public API_READER(String OPT, String ARGUMENT) {
        this.OPT = OPT;
        this.ARGUMENT = (ARGUMENT);
    }

    public static String urlEncode(String text) {
        try {
            return URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }



    private void sendRequest() {
        String baseApiUrl = "https://newton.vercel.app/api/v2/";
        String encodedArgument = urlEncode(ARGUMENT);
        String url_send = baseApiUrl + OPT + "/" + encodedArgument;
        System.out.println(url_send);

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url_send).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line = reader.readLine();
                response.append(line);
                reader.close();

                com.fasterxml.jackson.databind.ObjectMapper objectMapper = new ObjectMapper();
                com.fasterxml.jackson.databind.JsonNode jsonNode = objectMapper.readTree(String.valueOf(response));
                String result = (OPT.equals("zeroes"))? jsonNode.get("result").toString():jsonNode.get("result").asText();
                API_OUTPUT = result;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getAPIOutput(){
        sendRequest();
        return API_OUTPUT;
    }
}
