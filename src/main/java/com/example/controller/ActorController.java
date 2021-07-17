package com.example.controller;

import com.example.entity.Actor;
import com.example.entity.Film;
import com.example.service.ActorService;
import com.example.service.FilmService;
import com.example.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
@Controller
@RequestMapping("/actor")
public class ActorController {
    private ActorService actorService;

    @Autowired
    public void setActorService(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping()
    public String allActor(Model model) throws SQLException {
        //Получаем всех актеров и передадим отображение в представление
        model.addAttribute("actor", actorService.getAll());

        return "actor/actors";

    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) throws SQLException {
        //Получим одного актера по id из actorService и передадим на отображение в представление
        model.addAttribute("actor", actorService.getById(id));
        return "actor/show_id";
    }

   @GetMapping("/newactor")
    //возвращает html форму для создания актера
    public String newActor(@ModelAttribute("actor") Actor actor) {
        return "actor/new";
    }

   @PostMapping()
    public String create(@ModelAttribute("actor") Actor actor) throws SQLException {
        //из post запроса добавляет фильм в базу данных
        actorService.create(actor);
        return "redirect:/actor";
    }
      @GetMapping("/{id}/edit")
    //страница для редактирования
    public String edit(Model model, @PathVariable("id") long id) throws SQLException {
        model.addAttribute("actor", actorService.getById(id));
        return "actor/edit";
    }
    @PatchMapping("/{id}")
    //принимает patch запрос
    public String update(@ModelAttribute("actor") Actor actor, @PathVariable("id") long id) throws SQLException {
        actorService.update(actor);
        return "redirect:/actor";
    }





    @GetMapping("name/{lastName}")
    public String getByLastName(@PathVariable("lastName") String lastName, Model model) throws SQLException {
        //Получим одного актера по lastName из actorService и передадим на отображение в представление
        model.addAttribute("actor", actorService.getByLastName(lastName));
        return "actor/show_lastName";
    }

   @GetMapping("/film/{title}")
    public String showActorsTitle(@PathVariable("title") String title, Model model) throws SQLException {
        //Получим список актеров по названию фильма из actorService и передадим отображение в представление
        model.addAttribute("actor", actorService.getActorTitle(title));
        return "actor/show_actors_title";
    }

}


