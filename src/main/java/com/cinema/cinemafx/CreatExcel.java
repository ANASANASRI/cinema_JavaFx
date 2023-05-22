package com.cinema;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CreatExcel {

    public static void main(String[] args) throws Exception {

        //Création d'un objet de type fichier Excel
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Création d'un objet de type feuille Excel
        XSSFSheet spreadsheet = workbook.createSheet(" Cinema Info ");

        //Création d'un objet row (ligne)
        XSSFRow row;

        //Les données à inserer;
        Map<String, Object[]> cinemaInfo = new TreeMap<String, Object[]>();

        cinemaInfo.put("1", new Object[]{"NOM", "EMPLACEMENT", "CAPACITEMAXIMALE", "ESTOUVERT", "NOMBREDESALLES", "CHIFFREAFFAIREANNUEL", "PRIXMOYEN", "NUMERODETELEPHONE"});
        cinemaInfo.put("2", new Object[]{"Le Colisée", "Casablanca", 150.0, true, 5.0, 5000000.0, 50.0, "212-522223344"});
        cinemaInfo.put("3", new Object[]{"Palais des Congrès", "Marrakech", 200.0, false, 7.0, 7500000.0, 35.0, "212-524445566"});
        cinemaInfo.put("4", new Object[]{"Imax Tanger", "Tanger", 100.0, true, 4.0, 3000000.0, 30.0, "212-539998877"});
        cinemaInfo.put("5", new Object[]{"Mega CGR", "Rabat", 180.0, true, 6.0, 6000000.0, 33.0, "212-537778899"});
        cinemaInfo.put("6", new Object[]{"Cinéma Rialto", "Agadir", 80.0, true, 3.0, 2000000.0, 25.0, "212-528889900"});

        // print the map
        for (Map.Entry<String, Object[]> entry : cinemaInfo.entrySet()) {
            System.out.println("Cinema ID: " + entry.getKey());
            Object[] values = entry.getValue();
            System.out.println("  NOM: " + values[0]);
            System.out.println("  EMPLACEMENT: " + values[1]);
            System.out.println("  CAPACITEMAXIMALE: " + values[2]);
            System.out.println("  ESTOUVERT: " + values[3]);
            System.out.println("  NOMBREDESALLES: " + values[4]);
            System.out.println("  CHIFFREAFFAIREANNUEL: " + values[5]);
            System.out.println("  PRIXMOYEN: " + values[6]);
            System.out.println("  NUMERODETELEPHONE: " + values[7]);
        }

        //parcourir les données pour les écrire dans le fichier Excel
        Set<String> keyid = cinemaInfo.keySet();
        int rowid = 0;

        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = cinemaInfo.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                }
            }

            //Ecrire les données dans un FileOutputStream
            FileOutputStream out = new FileOutputStream(new File("src/main/resources/CreateSpreadsheetAndWriteToFile.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Travail bien fait!!!");
        }
    }
}


//BIEN FAIT !
