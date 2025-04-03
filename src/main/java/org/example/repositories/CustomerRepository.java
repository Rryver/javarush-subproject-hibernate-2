package org.example.repositories;

import org.example.models.Address;
import org.example.models.City;
import org.example.models.Country;
import org.example.models.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerRepository extends AbstractHibernateRepository<Customer> {
    public CustomerRepository(Class<Customer> entityClazz, SessionFactory sessionFactory) {
        super(entityClazz, sessionFactory);
    }

    @Override
    public List<Customer> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Customer c join fetch c.address join fetch c.store", Customer.class).list();
        }
    }

    @Override
    public void create(Customer entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();

                Address address = entity.getAddress();
                City city = address.getCity();
                Country country = city.getCountry();
                if (country.getId() == null) {
                    session.persist(country);
                }
                if (city.getId() == null) {
                    session.persist(city);
                }
                if (address.getId() == null) {
                    session.persist(address);
                }

                session.persist(entity);

                transaction.commit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                transaction.rollback();
            }
        }
    }
}
