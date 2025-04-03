package org.example.repositories;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@Getter
@Setter
public class AbstractHibernateRepository<T> {
    protected final Class<T> clazz;
    protected final SessionFactory sessionFactory;

    public AbstractHibernateRepository(final Class<T> entityClazz, SessionFactory sessionFactory) {
        this.clazz = entityClazz;
        this.sessionFactory = sessionFactory;
    }

    public T findById(final long id) {
        try (Session session = sessionFactory.openSession()) {
            return (T) session.get(clazz, id);
        }
    }

    public List<T> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from " + clazz.getName(), clazz).list();
        }
    }

    public List<T> findAll(int offset, int limit) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from " + clazz.getName(), clazz)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .list();
        }
    }

    public void create(final T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        }
    }

    public T update(final T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            T merged = session.merge(entity);
            transaction.commit();

            return merged;
        }
    }

    public void delete(final T entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        }
    }

    public void deleteById(final long entityId) {
        final T entity = findById(entityId);
        delete(entity);
    }

    public void deleteById(final T entity) {
        delete(entity);
    }
}
