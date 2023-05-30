package com.cinema.cinemafx.tp1;


import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadJsonFormateDataFromTxtFileAndBindWithObject {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {


        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/main/resources/jsonCinema.txt"));
        JSONObject jsonObject = (JSONObject) obj;

        String jsonObjectList = (String)jsonObject.get("cinemaList").toString();

        Gson gson = new Gson();
        Cinema[] empArray = gson.fromJson(jsonObjectList, Cinema[].class);

        for(Cinema obj2 : empArray) {

            System.out.println(obj2.getNom());

        }


    }

}

//BIEN FAIT !