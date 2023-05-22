package com.cinema;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

class Founder {
    String name;
    int flowerCount;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getFlowerCount() {
        return flowerCount;
    }
    public void setFlowerCount(int flowerCount) {
        this.flowerCount = flowerCount;
    }

}

public class JsonDataBindWithJavaObject {

    public static void main(String[] args) {
        String cinemaJson = "[{\"nom\":\"Cinema A\",\"emplacement\":\"Paris\",\"capaciteMaximale\":100,\"estOuvert\":true,\"nombreDeSalles\":2,\"chiffreAffaireAnnuel\":5000.0,\"prixMoyen\":8.0,\"numeroDeTelephone\":\"1234567890\"},{\"nom\":\"Cinema B\",\"emplacement\":\"Marseille\",\"capaciteMaximale\":150,\"estOuvert\":false,\"nombreDeSalles\":3,\"chiffreAffaireAnnuel\":7000.0,\"prixMoyen\":9.0,\"numeroDeTelephone\":\"0987654321\"}]";

        Gson gson = new Gson();
        Cinema[] cinemaArray = gson.fromJson(cinemaJson, Cinema[].class);

        List<Cinema> cinemaList = new ArrayList<>();
        for(Cinema cinema : cinemaArray) {
            cinemaList.add(cinema);
        }

        for(Cinema cinema : cinemaList) {
            System.out.println(cinema.getNom());
        }
    }

}


//BIEN FAIT !