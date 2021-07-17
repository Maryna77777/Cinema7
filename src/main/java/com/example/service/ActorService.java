package com.example.service;

import com.example.config.SessionUtil;
import com.example.dao.ActorDAO;
import com.example.entity.Actor;
import com.example.entity.Film;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Service
public class ActorService extends SessionUtil implements ActorDAO {

        public void create (Actor actor) throws SQLException {
            openTransactionSession();
            Session session = getSession();
            session.persist(actor);
            closeTransactionSession();
        }

        public List<Actor> getAll() throws SQLException {
            openTransactionSession();
            Session session = getSession();
            //устаревший Сriteria Hibernate
            // Criteria criteria = session.createCriteria(Actor.class);
          //  List<Actor> actorList = criteria.list();
            //Запрос с помощью Criteria JPA
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery <Actor> cr = cb.createQuery(Actor.class);
            Root<Actor> root =cr.from(Actor.class);
            cr.select(root);
            Query<Actor> query = session.createQuery(cr);
            List<Actor> actorList= query.getResultList();
            // запрос с помощью HQL
           //  Query query = session.createQuery("FROM Actor");
            // запрос с помощью SQL
            //  Query query = session.createQuery("FROM Actor");
            // String sql = "SELECT * FROM ACTOR";
           // Query query = session.createNativeQuery(sql).addEntity(Actor.class);
          //  List<Actor> actorList = query.list();
            closeTransactionSession();
            return actorList;
        }

        public Actor getById(Long id) throws SQLException {
            openTransactionSession();
            Session session = getSession();
            Query query = session.createQuery("FROM Actor WHERE id=:id");
            //   String sql = "SELECT * FROM ACTOR WHERE ID = :id";
            //Query query = session.createNativeQuery(sql).addEntity(Actor.class);
            query.setParameter("id", id);
            Actor actor = (Actor) query.getSingleResult();
            closeTransactionSession();
            System.out.println(actor.toString());
            return actor;
        }

        public void update(Actor actor) throws SQLException {

            openTransactionSession();

            Session session = getSession();
            session.update(actor);

            closeTransactionSession();
        }

        public void delete (Actor actor) throws SQLException {

            openTransactionSession();

            Session session = getSession();
            session.delete(actor);

            closeTransactionSession();

        }

    public List<Actor> getActorTitle (String title) //throws SQLException
     {

        openTransactionSession();

        //String sql = "Select a.First_Name, a.Last_name from ACTOR a join FILM_ACTOR fa on a.id=fa.ACTOR_ID join FILM f on f.id=fa.FILM_ID  Where f.title =: title";
        //" SELECT a.firstName, a.lastName FROM Actor as a INNER JOIN  FETCH a.film as f WHERE a.id=f.id AND f.title =: title"
        Session session = getSession();

         String hql = "Select a from Actor a  join a.films f with f.title =:title";
         Query query = session.createQuery(hql);

        //Query query = session.createNativeQuery(sql).addEntity("a", Actor.class)
        //.addEntity("f", Film.class).addJoin("fa", "a.films").addJoin("f","fa.actors");

        query.setParameter("title", title);
        List<Actor> actorList = query.list();

        closeTransactionSession();

        for (Actor actor : actorList) {
            System.out.println(actor.toString());

        }
         return actorList;

    }
    public Actor getByLastName(String lastName) throws SQLException {

        openTransactionSession();

       // String sql = "SELECT * FROM ACTOR a WHERE a.LAST_NAME = :lastName";

        Session session = getSession();
        Query query = session.createQuery("FROM Actor as a WHERE a.lastName =:lastName");

        // Query query = session.createNativeQuery(sql).addEntity(Actor.class);
        query.setParameter("lastName", lastName);

        Actor actor = (Actor) query.getSingleResult();

        closeTransactionSession();

        return actor;
    }


}


//Select a.First_Name, a.last_name, f.TITLE
 //       from ACTOR a join pet1_schema.FILM_ACTOR fa on a.id=fa.ACTOR_ID
   //     join pet1_schema.FILM f on f.id=fa.FILM_ID  Where f.id=5