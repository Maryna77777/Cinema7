package com.example.service;

import com.example.config.SessionUtil;
import com.example.dao.GenreDAO;
import com.example.entity.Film;
import com.example.entity.Genre;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Service
public class GenreService extends SessionUtil implements GenreDAO {

        public void create(Genre genre) throws SQLException {

            openTransactionSession();

            Session session = getSession();
            session.persist (genre);

            closeTransactionSession();
        }

        public List<Genre> getAll() throws SQLException {

            openTransactionSession();

            Session session = getSession();

            //Запрос с помощью Criteria JPA
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Genre> cr = cb.createQuery(Genre.class);
            Root<Genre> root =cr.from(Genre.class);
            cr.select(root);
            Query<Genre> query = session.createQuery(cr);
            List<Genre> genreList= query.getResultList();

            // запрос с помощью HQL
            //Query query = session.createQuery("FROM Genre");

            // запрос с помощью SQL
            // String sql = "SELECT * FROM GENRE";
            //Query query = session.createNativeQuery(sql).addEntity(Genre.class);

            //List<Genre> genreList = query.list();

            closeTransactionSession();

            return genreList;
        }

        public Genre getById(Long id) throws SQLException {

            openTransactionSession();

            String sql = "SELECT * FROM GENRE WHERE ID = :id";

            Session session = getSession();
            Query query = session.createNativeQuery(sql).addEntity(Genre.class);
            query.setParameter("id", id);

            Genre genre = (Genre) query.getSingleResult();

            closeTransactionSession();

            return genre;
        }

        public void update(Genre genre) throws SQLException {

            openTransactionSession();

            Session session = getSession();
            session.update(genre);

            closeTransactionSession();
        }

        public void delete (Genre genre) throws SQLException {

            openTransactionSession();

            Session session = getSession();
            session.delete (genre);

            closeTransactionSession();


        }

    }

//SQL запрос соответсвия категорий и фильмов
// SELECT g.CATEGORY, f.TITLE FROM GENRE g join FILM_GENRE fg on g.id=fg.GENRE_ID join FILM f on fg.FILM_ID=f.id;