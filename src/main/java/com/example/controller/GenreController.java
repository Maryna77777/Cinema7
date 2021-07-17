package com.example.controller;

import com.example.service.FilmService;
import com.example.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/genre")
public class GenreController {

    private GenreService genreService;

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping()
    public String Genre(Model model) throws SQLException {
        //Получаем все жанры и передадим в отображение в представление
        model.addAttribute("genre", genreService.getAll());

        return "genre/genres";
    }
}

