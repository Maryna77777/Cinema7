package com.example.service;

import com.example.config.SessionUtil;
import com.example.dao.DirectorDAO;
import com.example.entity.Director;
import com.example.entity.Film;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Service
public class DirectorService extends SessionUtil implements DirectorDAO {

    public void create(Director director) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.persist(director);

        closeTransactionSession();
    }

    public List<Director> getAll() throws SQLException {

        openTransactionSession();

        Session session = getSession();

        //Запрос с помощью Criteria JPA
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Director> cr = cb.createQuery(Director.class);
        Root<Director> root =cr.from(Director.class);
        cr.select(root);
       // cr.select(root).where(cb.);

        Query<Director> query = session.createQuery(cr);
        List<Director> directorList= query.getResultList();

        // запрос с помощью HQL
        //Query query = session.createQuery("From Director");

        // запрос с помощью SQL
        // String sql = "SELECT * FROM DIRECTOR";
        // Query query = session.createNativeQuery(sql).addEntity(Director.class);

        //List <Director> DirectorList = query.list();

        closeTransactionSession();

        return directorList;
    }

    public Director getById(Long id) throws SQLException {

        openTransactionSession();

        String sql = "SELECT * FROM DIRECTOR WHERE ID = :id";

        Session session = getSession();

        Query query = session.createNativeQuery(sql).addEntity(Director.class);
        query.setParameter("id", id);

        Director director = (Director) query.getSingleResult();

        closeTransactionSession();

        return director;
    }

    public void update(Director filmDirector) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.update(filmDirector);

        closeTransactionSession();
    }

    public void delete (Director director) throws SQLException {

        openTransactionSession();

        Session session = getSession();
        session.delete (director);

        closeTransactionSession();


        //запрос соответсвия режиссерев и фильмов
        //SELECT f.TITLE, d.LAST_NAME_DIRECTOR FROM pet1_schema.FILM f join pet1_schema.FILM_DIRECTOR fd on f.id=fd.FILM_ID join pet1_schema.DIRECTOR d on fd.DIRECTOR_ID=d.id;
    }

}
