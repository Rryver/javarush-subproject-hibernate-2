package org.example.repositories;

import org.example.models.Actor;
import org.hibernate.SessionFactory;

public class ActorRepo extends AbstractHibernateRepository<Actor> {
    public ActorRepo(Class<Actor> entityClazz, SessionFactory sessionFactory) {
        super(entityClazz, sessionFactory);
    }
}
