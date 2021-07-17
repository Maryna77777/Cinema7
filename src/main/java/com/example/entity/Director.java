package com.example.entity;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name="director")
public class Director {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        //  @Column(name="DIRECTOR_ID")
        private long id;
        @Column(name = "FIRST_NAME_DIRECTOR")
        private String firstNameDirector;
        @Column(name = "LAST_NAME_DIRECTOR")
        private String lastNameDirector;
        @Column(name = "NATIONALITY_DIRECTOR")
        private String nationalityDirector;
        //   @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "filmDirector")
        //   private Set <Film> films;

        @ManyToMany(mappedBy = "filmDirectors")
        private Set<Film> films;

        public Director() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNationalityDirector() {
            return nationalityDirector;
        }

        public void setNationalityDirector(String nationalityDirector) {
            this.nationalityDirector = nationalityDirector;
        }

        public Set<Film> getFilms() {
            return films;
        }

        public void setFilms(Set<Film> films) {
            this.films = films;
        }

    public String getFirstNameDirector() {
        return firstNameDirector;
    }

    public void setFirstNameDirector(String firstNameDirector) {
        this.firstNameDirector = firstNameDirector;
    }

    public String getLastNameDirector() {
        return lastNameDirector;
    }

    public void setLastNameDirector(String lastNameDirector) {
        this.lastNameDirector = lastNameDirector;
    }

}
