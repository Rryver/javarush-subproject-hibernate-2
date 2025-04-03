package org.example.repositories;

import org.example.models.City;
import org.example.models.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import java.util.List;

public class FilmRepository extends AbstractHibernateRepository<Film> {
    public FilmRepository(Class<Film> entityClazz, SessionFactory sessionFactory) {
        super(entityClazz, sessionFactory);
    }

    @Override
    public List<Film> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Film f " +
                            "join fetch f.actors " +
                            "join fetch f.categories " +
                            "join fetch f.language " +
                            "join fetch f.originalLanguage " +
                            "join fetch f.text", clazz)
                    .list();
        }
    }
}
