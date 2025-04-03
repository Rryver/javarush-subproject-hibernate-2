package org.example.utils;

import org.example.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySqlSessionManager {
    private static MySqlSessionManager instance;

    private final SessionFactory sessionFactory;

    static {
        instance = new MySqlSessionManager();
    }

    private MySqlSessionManager() {
        sessionFactory = new Configuration()
//                .configure()
//                .addPackage("org.example.models") //TODO Не работает. Разобраться
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .buildSessionFactory();
    }

    public static MySqlSessionManager getInstance() {
        if (instance == null) {
            instance = new MySqlSessionManager();
        }

        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
