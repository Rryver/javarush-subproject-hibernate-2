package org.example.repositories;

import org.example.models.City;
import org.example.models.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import java.util.List;

public class CountryRepository extends AbstractHibernateRepository<Country> {
    public CountryRepository(Class<Country> entityClazz, SessionFactory sessionFactory) {
        super(entityClazz, sessionFactory);
    }

    @Override
    public List<Country> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Country", clazz)
                    .list();
        }
    }

    public Country findOne(String name) {
        try (Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            JpaCriteriaQuery<Country> query = builder.createQuery(clazz);
            JpaRoot<Country> root = query.from(clazz);

            if (name != null) {
                query.where(builder.equal(root.get("country"), name));
            }

            return session.createQuery(query).setMaxResults(1).uniqueResult();
        }
    }
}
