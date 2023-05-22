package com.cinema.JDBC.service;

import com.cinema.JDBC.dao.CinemaDao;
import com.cinema.JDBC.dao.impl.CinemaDaoImp;
import com.cinema.JDBC.entities.Cinema;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CinemaService {
    private CinemaDao cinemaDao = new CinemaDaoImp();

    public List<Cinema> findAll() {
        return cinemaDao.findAll();
    }

    public void save(Cinema cinema) {
        cinemaDao.insert(cinema);
    }

    public void update(Cinema cinema) {
        cinemaDao.update(cinema);
    }

    public void remove(Cinema cinema) {
        cinemaDao.deleteById(cinema.getId());
    }

    public void exporterVersExcel() {
        List<Cinema> cinemas = findAll();

        // Création d'un objet de type fichier Excel
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Création d'un objet de type feuille Excel
        XSSFSheet spreadsheet = workbook.createSheet("Cinema Info");

        // Les données à insérer
        Map<String, Object[]> cinemaInfo = new TreeMap<String, Object[]>();
        cinemaInfo.put("1", new Object[]{"NOM", "EMPLACEMENT", "CAPACITEMAXIMALE", "ESTOUVERT", "NOMBREDESALLES", "CHIFFREAFFAIREANNUEL", "PRIXMOYEN", "NUMERODETELEPHONE"});

        int rowNum = 2;
        for (Cinema cinema : cinemas) {
            cinemaInfo.put(String.valueOf(rowNum), new Object[]{
                    cinema.getNom(),
                    cinema.getEmplacement(),
                    cinema.getCapaciteMaximale(),
                    cinema.isEstOuvert(),
                    cinema.getNombreDeSalles(),
                    cinema.getChiffreAffaireAnnuel(),
                    cinema.getPrixMoyen(),
                    cinema.getNumeroDeTelephone()
            });
            rowNum++;
        }

        // Parcourir les données pour les écrire dans le fichier Excel
        Set<String> keyid = cinemaInfo.keySet();
        int rowid = 0;

        for (String key : keyid) {
            XSSFRow row = spreadsheet.createRow(rowid++);
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
        }

        try {
            // Écrire les données dans un FileOutputStream
            FileOutputStream out = new FileOutputStream(new File("src/main/resources/ExpFromBddCinemaData.xlsx"));

            workbook.write(out);
            out.close();
            System.out.println("Données exportées avec succès vers le fichier ExpFromBddCinemaData.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportToTxt() {
        List<Cinema> cinemas = cinemaDao.findAll();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/ExpFromBddToTxt"))) {
            for (Cinema cinema : cinemas) {
                writer.write("NOM: " + cinema.getNom());
                writer.newLine();
                writer.write("EMPLACEMENT: " + cinema.getEmplacement());
                writer.newLine();
                writer.write("CAPACITEMAXIMALE: " + cinema.getCapaciteMaximale());
                writer.newLine();
                writer.write("ESTOUVERT: " + cinema.isEstOuvert());
                writer.newLine();
                writer.write("NOMBREDESALLES: " + cinema.getNombreDeSalles());
                writer.newLine();
                writer.write("CHIFFREAFFAIREANNUEL: " + cinema.getChiffreAffaireAnnuel());
                writer.newLine();
                writer.write("PRIXMOYEN: " + cinema.getPrixMoyen());
                writer.newLine();
                writer.write("NUMERODETELEPHONE: " + cinema.getNumeroDeTelephone());
                writer.newLine();
                writer.newLine();
            }

            System.out.println("Data exported successfully to ExpFromBddToTxt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}