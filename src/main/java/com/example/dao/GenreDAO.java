package com.example.dao;

import com.example.entity.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDAO {

    //create
    void create(Genre genre) throws SQLException;

    //read
    List<Genre> getAll() throws SQLException;

    Genre getById(Long id) throws SQLException;

    //update
    void update(Genre genre) throws SQLException;

    //delete
    void delete (Genre genre) throws SQLException;


}


