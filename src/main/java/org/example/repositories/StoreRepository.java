package org.example.repositories;

import org.example.models.Customer;
import org.example.models.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StoreRepository extends AbstractHibernateRepository<Store> {
    public StoreRepository(Class<Store> entityClazz, SessionFactory sessionFactory) {
        super(entityClazz, sessionFactory);
    }

    @Override
    public List<Store> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Store s join fetch s.address join fetch s.managerStaff", clazz).list();
        }
    }

    public Store findFirst() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Store s join fetch s.address join fetch s.managerStaff", clazz)
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }
}
