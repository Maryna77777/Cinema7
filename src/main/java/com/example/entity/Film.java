package com.example.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name="film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "FILM_ID")
    private Long id;
    @NotEmpty(message = "Title should not be empty")
    @Size (min=2, max=50, message = "Title should be between 2 and 50 character")
    @Column(name = "TITLE")
    private String title;
    @Min(value = 1888, message = "Year should be greater 1888")
    @Column(name = "YEAR")
    private int year;
    @Column(name = "COUNTRY")
    @NotEmpty(message = "Country should not be empty")
    private String country;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "FILM_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACTOR_ID")
    )
    private Set<Actor> actors;

    //@ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER )
    // @JoinColumn(name = "FilmDirector_ID",unique=true)
    // private FilmDirector filmDirector;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_genre",
            joinColumns = @JoinColumn(name = "FILM_ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID")
    )
    private Set <Genre> genres;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_director",
            joinColumns = @JoinColumn(name = "FILM_ID"),
            inverseJoinColumns = @JoinColumn(name = "DIRECTOR_ID")
    )
    private Set <Director> filmDirectors;

    public Film() {
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public Set<Director> getFilmDirectors() {
        return filmDirectors;
    }

    public void setFilmDirectors(Set<Director> filmDirectors) {
        this.filmDirectors = filmDirectors;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    //  public FilmDirector getFilmDirector() {
    //       return filmDirector;
    //  }
    // public void setFilmDirector(FilmDirector filmDirector) {
    //    this.filmDirector = filmDirector;
    // }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", country='" + country + '\'' +
              //  ", actors=" + actors +
             //   ", genres=" + genres +
              //  ", filmDirectors=" + filmDirectors +
                '}';
    }

}