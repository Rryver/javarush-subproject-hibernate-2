package org.example.repositories;

import org.example.models.Address;
import org.example.models.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import java.util.List;

public class CityRepository extends AbstractHibernateRepository<City> {
    public CityRepository(Class<City> entityClazz, SessionFactory sessionFactory) {
        super(entityClazz, sessionFactory);
    }

    @Override
    public List<City> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from City c join fetch c.addresses join fetch c.country", clazz)
                    .list();
        }
    }

    public City findOne(Short countryId, String name) {
        try (Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            JpaCriteriaQuery<City> query = builder.createQuery(clazz);
            JpaRoot<City> root = query.from(clazz);

            if (countryId != null) {
                query.where(builder.equal(root.get("country_id"), countryId));
            }
            if (name != null) {
                query.where(builder.equal(root.get("city"), name));
            }

            return session.createQuery(query).setMaxResults(1).uniqueResult();
        }
    }
}
