package org.example.repositories;

import org.example.models.Address;
import org.example.models.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import java.util.List;

public class AddressRepository extends AbstractHibernateRepository<Address> {
    public AddressRepository(Class<Address> entityClazz, SessionFactory sessionFactory) {
        super(entityClazz, sessionFactory);
    }

    @Override
    public List<Address> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Address a join fetch a.city c join fetch c.country", clazz)
                    .list();
        }
    }

    public Address findOne(Short cityId, String address, String address2, String district, String postalCode, String phone) {
        try (Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            JpaCriteriaQuery<Address> query = builder.createQuery(Address.class);
            JpaRoot<Address> root = query.from(Address.class);

            if (cityId != null) {
                query.where(builder.equal(root.get("city_id"), cityId));
            }
            if (address != null) {
                query.where(builder.equal(root.get("address"), address));
            }
            if (address2 != null) {
                query.where(builder.equal(root.get("address2"), address2));
            }
            if (district != null) {
                query.where(builder.equal(root.get("district"), district));
            }
            if (postalCode != null) {
                query.where(builder.equal(root.get("postalCode"), postalCode));
            }
            if (phone != null) {
                query.where(builder.equal(root.get("phone"), phone));
            }

            return session.createQuery(query).setMaxResults(1).uniqueResult();
        }
    }
}
