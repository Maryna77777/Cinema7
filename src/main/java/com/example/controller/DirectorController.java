package com.example.controller;

import com.example.entity.Director;
import com.example.entity.Film;
import com.example.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/director")
public class DirectorController {
    private DirectorService directorService;
    @Autowired
    public void setDirectorService (DirectorService directorService){
        this.directorService=directorService;
    }
    @GetMapping("")
    public String allFilms(Model model) throws SQLException {
        //Получаем всех режиссеров и передадим отображение в представление
        model.addAttribute("director", directorService.getAll());

        return "director/directors";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) throws SQLException {
        //Получим одного режиссера по id из directorService и передадим на отображение в представление
        model.addAttribute("director", directorService.getById(id));
        return "director/show_id";
    }

    @GetMapping("/new_director")
    //возвращает html форму для создания режиссера
    public String newDirector(@ModelAttribute("director") Director director) {
        return "director/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("director") Director director) throws SQLException {
        //из post запроса добавляет режиссера в базу данных
        directorService.create(director);
        return "redirect:/director";
    }

    @GetMapping("/{id}/edit")
    //страница для редактирования
    public String edit(Model model, @PathVariable("id") long id) throws SQLException {
        model.addAttribute("director", directorService.getById(id));
        return "director/edit";
    }

    @PatchMapping("/{id}")
    //принимает patch запрос
    public String update(@ModelAttribute("director") Director director, @PathVariable("id") long id) throws SQLException {
        directorService.update(director);
        return "redirect:/director";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id, @ModelAttribute("director") Director director) throws SQLException {
        directorService.delete(director);
        return "redirect:/director";
    }
}




