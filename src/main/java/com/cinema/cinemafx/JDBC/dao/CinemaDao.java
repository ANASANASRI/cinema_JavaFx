package com.cinema.cinemafx.JDBC.dao;

import com.cinema.cinemafx.JDBC.entities.Cinema;

import java.util.List;

public interface CinemaDao {
    void insert(Cinema cinema);

    void update(Cinema cinema);

    void deleteById(Integer id);

    Cinema findById(Integer id);

    List<Cinema> findAll();
}