package com.cinema.cinemafx.JDBC.service;

import com.cinema.cinemafx.JDBC.dao.CinemaDao;
import com.cinema.cinemafx.JDBC.dao.impl.CinemaDaoImp;
import com.cinema.cinemafx.JDBC.entities.Cinema;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CinemaService {
    private static CinemaDao cinemaDao = new CinemaDaoImp();

    public List<Cinema> findAll() {
        return cinemaDao.findAll();
    }

    public static void save(Cinema cinema) {
        cinemaDao.insert(cinema);
    }

    public static void update(Cinema cinema) {
        cinemaDao.update(cinema);
    }

    public static void remove(Cinema cinema) {
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
        cinemaInfo.put("1", new Object[]{"ID", "NOM", "EMPLACEMENT", "CAPACITEMAXIMALE", "ESTOUVERT", "NOMBREDESALLES", "CHIFFREAFFAIREANNUEL", "PRIXMOYEN", "NUMERODETELEPHONE"});

        int rowNum = 2;
        for (Cinema cinema : cinemas) {
            cinemaInfo.put(String.valueOf(rowNum), new Object[]{
                    String.valueOf(cinema.getId()),
                    cinema.getNom(),
                    cinema.getEmplacement(),
                    String.valueOf(cinema.getCapaciteMaximale()),
                    String.valueOf(cinema.isEstOuvert()),
                    String.valueOf(cinema.getNombreDeSalles()),
                    String.valueOf(cinema.getChiffreAffaireAnnuel()),
                    String.valueOf(cinema.getPrixMoyen()),
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
            FileOutputStream out = new FileOutputStream("src/main/resources/ExpFromBddCinemaData.xlsx");

            workbook.write(out);
            out.close();
            System.out.println("Données exportées avec succès vers le fichier ExpFromBddCinemaData.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void exportToTxt() {
        List<Cinema> cinemas = cinemaDao.findAll();

        try {
            // Vérifier si le répertoire existe, sinon le créer
            String directoryPath = "src/main/resources/";
            Path directory = Paths.get(directoryPath);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            // Création du fichier de sortie
            String filePath = directoryPath + "ExpFromBddToTxt";
            File outputFile = new File(filePath);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                for (Cinema cinema : cinemas) {
                    writer.write("ID: " + cinema.getId());
                    writer.newLine();
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
            }

            System.out.println("Data exported successfully to ExpFromBddToTxt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ////////////////

    public void exportToJson() {
        List<Cinema> cinemas = cinemaDao.findAll();

        try {
            // Vérifier si le répertoire existe, sinon le créer
            String directoryPath = "src/main/resources/";
            Path directory = Paths.get(directoryPath);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            // Création du fichier de sortie
            String filePath = directoryPath + "ExpFromBddToJson.json";
            File outputFile = new File(filePath);

            // Conversion des données en JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(cinemas);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(jsonData);
            }

            System.out.println("Data exported successfully to ExpFromBddToJson.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////
    public void importFromTxt(String filePath) {
        List<Cinema> cinemas = cinemaDao.findAll();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ID:")) {
                    int id = Integer.parseInt(line.substring(4).trim());
                    String nom = reader.readLine().substring(5).trim();
                    String emplacement = reader.readLine().substring(12).trim();
                    int capaciteMaximale = Integer.parseInt(reader.readLine().substring(17).trim());
                    boolean estOuvert = Boolean.parseBoolean(reader.readLine().substring(10).trim());
                    int nombreDeSalles = Integer.parseInt(reader.readLine().substring(16).trim());
                    double chiffreAffaireAnnuel = Double.parseDouble(reader.readLine().substring(21).trim());
                    double prixMoyen = Double.parseDouble(reader.readLine().substring(11).trim());
                    String numeroDeTelephone = reader.readLine().substring(18).trim();

                    // Check if the cinema already exists in the database
                    Cinema existingCinema = findCinemaById(cinemas, id);
                    if (existingCinema != null) {
                        // The cinema exists, update the data
                        existingCinema.setNom(nom);
                        existingCinema.setEmplacement(emplacement);
                        existingCinema.setCapaciteMaximale(capaciteMaximale);
                        existingCinema.setEstOuvert(estOuvert);
                        existingCinema.setNombreDeSalles(nombreDeSalles);
                        existingCinema.setChiffreAffaireAnnuel(chiffreAffaireAnnuel);
                        existingCinema.setPrixMoyen(prixMoyen);
                        existingCinema.setNumeroDeTelephone(numeroDeTelephone);
                        update(existingCinema);
                    } else {
                        // The cinema doesn't exist, create a new one
                        Cinema cinema = new Cinema();
                        cinema.setId(id);
                        cinema.setNom(nom);
                        cinema.setEmplacement(emplacement);
                        cinema.setCapaciteMaximale(capaciteMaximale);
                        cinema.setEstOuvert(estOuvert);
                        cinema.setNombreDeSalles(nombreDeSalles);
                        cinema.setChiffreAffaireAnnuel(chiffreAffaireAnnuel);
                        cinema.setPrixMoyen(prixMoyen);
                        cinema.setNumeroDeTelephone(numeroDeTelephone);
                        save(cinema);
                    }
                }
            }

            System.out.println("Data imported successfully from the TXT file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Cinema findCinemaById(List<Cinema> cinemas, int id) {
        for (Cinema cinema : cinemas) {
            if (cinema.getId() == id) {
                return cinema;
            }
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////

    public void importFromExcel(String filePath) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            List<Cinema> cinemas = cinemaDao.findAll();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell cell = row.getCell(0);

                if (row.getRowNum() == 0) {
                    // Skip the header row
                    continue;
                }

                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    int id = (int) cell.getNumericCellValue();
                    Cinema existingCinema = findCinemaById(cinemas, id);

                    if (existingCinema != null) {
                        // Update existing cinema
                        existingCinema.setNom(getStringCellValue(row.getCell(1)));
                        existingCinema.setEmplacement(getStringCellValue(row.getCell(2)));
                        existingCinema.setCapaciteMaximale(Integer.parseInt(getStringCellValue(row.getCell(3))));
                        existingCinema.setEstOuvert(Boolean.parseBoolean(getStringCellValue(row.getCell(4))));
                        existingCinema.setNombreDeSalles(Integer.parseInt(getStringCellValue(row.getCell(5))));
                        existingCinema.setChiffreAffaireAnnuel(Double.parseDouble(getStringCellValue(row.getCell(6))));
                        existingCinema.setPrixMoyen(Double.parseDouble(getStringCellValue(row.getCell(7))));
                        existingCinema.setNumeroDeTelephone(getStringCellValue(row.getCell(8)));

                        update(existingCinema);
                    } else {
                        // Insert new cinema
                        Cinema cinema = new Cinema();
                        cinema.setId(id);
                        cinema.setNom(getStringCellValue(row.getCell(1)));
                        cinema.setEmplacement(getStringCellValue(row.getCell(2)));
                        cinema.setCapaciteMaximale(Integer.parseInt(getStringCellValue(row.getCell(3))));
                        cinema.setEstOuvert(Boolean.parseBoolean(getStringCellValue(row.getCell(4))));
                        cinema.setNombreDeSalles(Integer.parseInt(getStringCellValue(row.getCell(5))));
                        cinema.setChiffreAffaireAnnuel(Double.parseDouble(getStringCellValue(row.getCell(6))));
                        cinema.setPrixMoyen(Double.parseDouble(getStringCellValue(row.getCell(7))));
                        cinema.setNumeroDeTelephone(getStringCellValue(row.getCell(8)));
                        save(cinema);
                    }
                }
            }

            workbook.close();
            file.close();

            System.out.println("Données importées avec succès depuis le fichier Excel");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getStringCellValue(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        }
        return "";
    }

    private int getNumericCellValue(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        return 0;
    }

    private boolean getBooleanCellValue(Cell cell) {
        if (cell != null && cell.getCellType() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        }
        return false;
    }

    //////////////////////////////////////////////////

}