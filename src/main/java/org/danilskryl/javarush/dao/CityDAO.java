package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.entities.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CityDAO {
    private final SessionFactory sessionFactory;

    public CityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<City> getAll() {
        Query<City> query = sessionFactory
                .getCurrentSession()
                .createQuery("FROM City", City.class);
        return query.getResultList();
    }

    public List<City> getItems(int offset, int limit) {
        Query<City> query = sessionFactory
                .getCurrentSession()
                .createQuery("FROM City", City.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    public int getTotalCount() {
        Query<Long> query = sessionFactory
                .getCurrentSession()
                .createQuery("SELECT COUNT(c) FROM City c", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    public City getById(Integer id) {
        Query<City> query = sessionFactory
                .getCurrentSession()
                .createQuery("SELECT c FROM City c JOIN FETCH c.country WHERE c.id = :ID", City.class);
        query.setParameter("ID", id);
        return query.getSingleResult();

    }
}
