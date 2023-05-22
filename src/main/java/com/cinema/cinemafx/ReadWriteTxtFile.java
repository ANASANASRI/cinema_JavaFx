package com.cinema;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadWriteTxtFile {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/CinemaInputData.txt"));

        ArrayList<Cinema> list = new ArrayList<Cinema>();
        Cinema c = null;
        String readLine = br.readLine();

        while(readLine != null){

            String [] cinema  = readLine.split("\\|");


            c = new Cinema();
            c.setNom(cinema[0].trim());
            c.setEmplacement(cinema[1].trim());
            c.setCapaciteMaximale(Integer.parseInt(cinema[2].trim()));
            c.setEstOuvert(Boolean.parseBoolean(cinema[3].trim()));
            c.setNombreDeSalles(Integer.parseInt(cinema[4].trim()));
            c.setChiffreAffaireAnnuel(Double.parseDouble(cinema[5].trim()));
            c.setPrixMoyen(Double.parseDouble(cinema[6].trim()));
            c.setNumeroDeTelephone(cinema[7].trim());
            list.add(c);
            readLine = br.readLine();
        }


        try( FileOutputStream fout = new FileOutputStream("src/main/resources/CinemaOutputData.txt"))
        {

            for(Cinema cinm : list){
                fout.write(cinm.toString().getBytes());
                fout.write('\n');

                System.out.println("Cinema :"+cinm.toString());
            }
        }
        catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

    }

}


//BIEN FAIT !
