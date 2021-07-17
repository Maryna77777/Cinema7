package com.example.dao;

import com.example.entity.Actor;

import java.sql.SQLException;
import java.util.List;

public interface ActorDAO {
    //create
    void create(Actor actor) throws SQLException;

    //read
    List<Actor> getAll() throws SQLException;

    Actor getById(Long id) throws SQLException;

    //update
    void update(Actor actor) throws SQLException;

    //delete
    void delete (Actor actor) throws SQLException;

}

