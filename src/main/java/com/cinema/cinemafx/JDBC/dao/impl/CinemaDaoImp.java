package com.cinema.JDBC.dao.impl;

import com.cinema.JDBC.dao.CinemaDao;
import com.cinema.JDBC.entities.Cinema;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CinemaDaoImp implements CinemaDao {

    private final Connection conn = DB.getConnection();

    @Override
    public void insert(Cinema cinema) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("INSERT INTO cinema (nom,emplacement,capaciteMaximale,estOuvert,nombreDeSalles,numeroDeTelephone,chiffreAffaireAnnuel,prixMoyen)  VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, cinema.getNom());
            ps.setString(2, cinema.getEmplacement());
            ps.setInt(3, cinema.getCapaciteMaximale());
            ps.setBoolean(4, cinema.isEstOuvert());
            ps.setInt(5, cinema.getNombreDeSalles());
            ps.setString(6, cinema.getNumeroDeTelephone());
            ps.setDouble(7, cinema.getChiffreAffaireAnnuel());
            ps.setDouble(8, cinema.getPrixMoyen());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);

                    cinema.setId(id);
                }

                DB.closeResultSet(rs);
            } else {
                System.out.println("Aucune ligne renvoyée");
            }
        } catch (SQLException e) {
            System.err.println("problème d'insertion d'un cinema"+" --> "+e);;
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Cinema cinema) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE cinema SET nom = ?, emplacement = ?, capaciteMaximale = ?, estOuvert = ?, nombreDeSalles = ?, numeroDeTelephone = ?, chiffreAffaireAnnuel = ?, prixMoyen = ? WHERE id = ?");

            ps.setString(1, cinema.getNom());
            ps.setString(2, cinema.getEmplacement());
            ps.setInt(3, cinema.getCapaciteMaximale());
            ps.setBoolean(4, cinema.isEstOuvert());
            ps.setInt(5, cinema.getNombreDeSalles());
            ps.setString(6, cinema.getNumeroDeTelephone());
            ps.setDouble(7, cinema.getChiffreAffaireAnnuel());
            ps.setDouble(8, cinema.getPrixMoyen());
            ps.setInt(9, cinema.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("problème de mise à jour d'un cinema"+" --> "+e);;
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("DELETE FROM cinema WHERE id = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Problème lors de la suppression d'un cinéma"+" --> "+e);
        } finally {
            DB.closeStatement(ps);
        }

    }

    @Override
    public Cinema findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM cinema WHERE id = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Cinema cinema = new Cinema();

                cinema.setId(rs.getInt("id"));
                cinema.setNom(rs.getString("nom"));
                cinema.setEmplacement(rs.getString("emplacement"));
                cinema.setCapaciteMaximale(rs.getInt("capaciteMaximale"));
                cinema.setEstOuvert(rs.getBoolean("estOuvert"));
                cinema.setNombreDeSalles(rs.getInt("nombreDeSalles"));
                cinema.setNumeroDeTelephone(rs.getString("numeroDeTelephone"));
                cinema.setChiffreAffaireAnnuel(rs.getDouble("chiffreAffaireAnnuel"));
                cinema.setPrixMoyen(rs.getDouble("prixMoyen"));

                return cinema;
            }

            return null;
        } catch (SQLException e) {
            System.err.println("problème de requête pour trouver un cinéma");
            return null;
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }


    @Override
    public List<Cinema> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM cinema");
            rs = ps.executeQuery();

            List<Cinema> cinemaList = new ArrayList<>();

            while (rs.next()) {
                Cinema cinema = new Cinema();

                cinema.setId(rs.getInt("id"));
                cinema.setNom(rs.getString("nom"));
                cinema.setEmplacement(rs.getString("emplacement"));
                cinema.setCapaciteMaximale(rs.getInt("capaciteMaximale"));
                cinema.setEstOuvert(rs.getBoolean("estOuvert"));
                cinema.setNombreDeSalles(rs.getInt("nombreDeSalles"));
                cinema.setNumeroDeTelephone(rs.getString("numeroDeTelephone"));
                cinema.setChiffreAffaireAnnuel(rs.getDouble("chiffreAffaireAnnuel"));
                cinema.setPrixMoyen(rs.getDouble("prixMoyen"));

                cinemaList.add(cinema);
            }

            return cinemaList;
        } catch (SQLException e) {
            System.err.println("problème de requête pour sélectionner tous les cinemas"+" --> "+e);
            return null;
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

}