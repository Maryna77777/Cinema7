package com.example.controller;

import com.example.entity.Film;
import com.example.service.FilmService;
import com.example.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/film")
public class FilmController {

    private FilmService filmService;

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping()
    public String allFilms(Model model) throws SQLException   {
        //Получаем все фильмы и передадим отображение в представление
        model.addAttribute("film", filmService.getAll());

        return "film/films";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model)  throws SQLException  {
        //Получим один фильм по id из filmService и передадим на отображение в представление
        model.addAttribute("film", filmService.getById(id));
        return "film/show_id";
    }

    @GetMapping("/newfilm")
    //возвращает html форму для создания фильма
    public String newFilm(@ModelAttribute("film") Film film) {
        return "film/new";
    }

    //из post запроса добавляет фильм в базу данных
    @PostMapping()
    public String create(@ModelAttribute("film") @Valid Film film,
                         BindingResult bindingResult) throws SQLException {
       if (bindingResult.hasErrors())
           return "film/new";
        filmService.create(film);
        return "redirect:/film";
    }
    @GetMapping("/{id}/edit")
    //страница для редактирования
    public String edit(Model model, @PathVariable("id") long id) throws SQLException {
        model.addAttribute("film", filmService.getById(id));
        return "film/edit";
    }

    @PatchMapping("/{id}")
    //принимает patch запрос
    public String update(@ModelAttribute("film") @Valid Film film, BindingResult bindingResult, @PathVariable("id") long id) throws SQLException {
        if (bindingResult.hasErrors())
            return "film/edit";
        filmService.update(film);
        return "redirect:/film";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id, @ModelAttribute("film") Film film) throws SQLException {
        filmService.delete(film);
        return "redirect:/film";
    }

//    @GetMapping("/{title}")
//    public String showFilmTitle (@PathVariable("title") String title, Model model)  throws SQLException  {
//        //Получим один фильм по title из filmService и передадим на отображение в представление
//        model.addAttribute("film", filmService.findByTitle("title"));
//        return "film/show_title";
 //   }

}
