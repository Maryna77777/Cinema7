package com.example.dao;

import com.example.entity.Film;

import java.sql.SQLException;
import java.util.List;

public interface FilmDAO {
    //create
    void create(Film film) throws SQLException;
    //read
    List<Film> getAll () throws SQLException;
    Film getById (long id) throws SQLException;
    void delete(Film film) throws SQLException;
    void update(Film film) throws SQLException;
    Film findByTitle (String title);
    List<Film> getFilmGenre(String category);
    List<Film> getFilmDirector(String lastNameDirector);
    List<Film> getFilmCountry(String country);
    List<Film> getFilmActor(String lastName);
    List<Film> getFilmYear(int year);

  //  boolean checkTitle(String title);
}


