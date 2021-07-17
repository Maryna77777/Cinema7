package com.example.dao;

import com.example.entity.Director;

import java.sql.SQLException;
import java.util.List;

public interface DirectorDAO {
    //create
    void create(Director filmDirector) throws SQLException;

    //read
    List<Director> getAll() throws SQLException;

    Director getById(Long id) throws SQLException;

    //update
    void update(Director director) throws SQLException;

    //delete
    void delete (Director director) throws SQLException;


}