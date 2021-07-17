package com.example.service;

import com.example.config.SessionUtil;
import com.example.dao.FilmDAO;
import com.example.entity.Actor;
import com.example.entity.Film;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Service
public class FilmService extends SessionUtil implements FilmDAO {

    public void create(Film film) throws SQLException {
        //open session with a transaction
        openTransactionSession();
        Session session = getSession();
        session.persist(film);
       // session.flush();
        //close session with a transaction
        closeTransactionSession();
    }

    public List<Film> getAll() throws SQLException{
        openTransactionSession();
        Session session = getSession();
        //Запрос с помощью Criteria JPA
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Film> criteriaQuery = cb.createQuery(Film.class);
        Root<Film> root = criteriaQuery.from(Film.class);
        criteriaQuery.select(root);
        Query<Film> query = session.createQuery(criteriaQuery);
        List<Film> filmList = query.getResultList();
        // запрос с помощью HQL
        //Query query = session.createQuery("FROM Film");
        // запрос с помощью SQL
        //  String sql = "SELECT * FROM FILM";
        // Query query = session.createNativeQuery(sql).addEntity(Film.class);
        //List<Film> filmList = query.list();
        closeTransactionSession();
        return filmList;
    }

    public void update(Film film) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.update(film);
        closeTransactionSession();
    }

    @Override
    public Film findByTitle(String title) {
        openTransactionSession();
        Session session = getSession();
        Query query = session.createQuery("FROM Film WHERE title=:title");
        query.setParameter("title", title);
        Film filmTitle = (Film) query.getSingleResult();
        closeTransactionSession();
        System.out.println(filmTitle.toString());
        return filmTitle;
    }

    public Film getById(long id) throws SQLException {
        openTransactionSession();
        Session session = getSession();
      //   String sql = "SELECT * FROM FILM WHERE ID = :id";
      //   Query query = session.createNativeQuery(sql).addEntity(Film.class);
        Query query = session.createQuery("FROM Film WHERE id=:id");
        query.setParameter("id", id);
        Film film = (Film) query.getSingleResult();
        closeTransactionSession();
     //    System.out.println(film.toString());
        return film;
    }

    public void delete(Film film) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.delete(film);
        closeTransactionSession();
    }

    public List<Film> getFilmGenre (String category) {
        openTransactionSession();
        Session session = getSession();
        String hql = "Select f FROM Film f join f.genres g where g.category=: category";
        Query query = session.createQuery(hql);
        query.setParameter("category", category);
        List<Film> filmGenreList = query.list();
        closeTransactionSession();
        for (Film film : filmGenreList) {
            System.out.println(film.toString());
        }
        return filmGenreList;
    }

    public List<Film> getFilmActor(String lastName) {
        openTransactionSession();
        Session session = getSession();
        String hql = "Select f FROM Film f join f.actors a where a.lastName=: lastName";
        Query query = session.createQuery(hql);
        query.setParameter("lastName", lastName);
        List<Film> filmActorList = query.list();
        closeTransactionSession();
        for (Film film : filmActorList) {
            System.out.println(film.toString());
        }
        return filmActorList;
    }

    @Override
    public List<Film> getFilmYear(int year) {
        openTransactionSession();
        Session session = getSession();
        String hql = "FROM Film Where year=:year";
        Query query = session.createQuery(hql);
        query.setParameter("year", year);
        List<Film> filmYearList = query.list();
        closeTransactionSession();
        for (Film film :filmYearList) {
            System.out.println(film.toString());
        }
        return filmYearList;
    }

    public List<Film> getFilmDirector(String lastNameDirector) {
        openTransactionSession();
        Session session = getSession();
        String hql = "Select f FROM Film f join f.filmDirectors fd where fd.lastNameDirector =: lastNameDirector ";
        Query query = session.createQuery(hql);
        query.setParameter("lastNameDirector", lastNameDirector);
        List<Film> filmDirectorList = query.list();
        closeTransactionSession();
        for (Film film : filmDirectorList) {
            System.out.println(film.toString());
        }
        return filmDirectorList;
    }

    public List<Film> getFilmCountry(String country) {
        openTransactionSession();
        Session session = getSession();
        String hql = "FROM Film Where country=:country ";
        Query query = session.createQuery(hql);
        query.setParameter("country", country);
        List<Film> filmCountryList = query.list();
        closeTransactionSession();
        for (Film film :filmCountryList) {
            System.out.println(film.toString());
        }
        return filmCountryList;
    }

//    public List<Film> getFilmDirectorActor(String lastNameDirector, String lastName) {
//        openTransactionSession();
//        Session session = getSession();
//        String hql = "Select f FROM Film f join f.filmDirectors fd join f.actors a where fd.lastNameDirector =: lastNameDirector and a.lastName=: lastName";
//        Query query = session.createQuery(hql);
//        query.setParameter("lastNameDirector", lastNameDirector);
//        query.setParameter("lastName", lastName);
//        List<Film> filmList = query.list();
//        closeTransactionSession();
//        for (Film film : filmList) {
//            System.out.println(film.toString());
//        }
//        return filmList;
//    }

}
