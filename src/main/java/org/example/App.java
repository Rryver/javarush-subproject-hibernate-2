package org.example;

import org.example.models.*;
import org.example.repositories.*;
import org.example.utils.MySqlSessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
//        try (Session session = MySqlSessionManager.getInstance().getSessionFactory().openSession()) {
//            List<Rental> results = session.createQuery("from Rental r join fetch r.inventory join fetch r.customer", Rental.class)
//                    .setMaxResults(2)
//                    .list();
//            List<Customer> results = session.createQuery("from Customer c", Customer.class).list();

//            for (Rental result : results) {
//                System.out.println(result);
//            }
//        }

        task6_CreateNewCustomer();
    }

    public static void task6_CreateNewCustomer() {
        SessionFactory factory = MySqlSessionManager.getInstance().getSessionFactory();

        Customer customer = new Customer();
        Store store = (new StoreRepository(Store.class, factory)).findFirst();
        customer.setStore(store);

        customer.setFirstName("Pablo");
        customer.setLastName("Greenland");
        customer.setEmail("PabloGrenland@example.com");

        String countryName = "Algeria";
        Country country = (new CountryRepository(Country.class, factory)).findOne(countryName);
        if (country == null) {
            country = new Country();
            country.setCountry(countryName);
        }

        String cityName = "City name";
        City city = (new CityRepository(City.class, factory)).findOne(null, cityName);
        if (city == null) {
            city = new City();
            city.setCity(cityName);
            city.setCountry(country);
        }

        String addressName = "address test";
        String district = "district test";
        String postalCode = "123456";
        String phone = "9812313133";
        Address address = (new AddressRepository(Address.class, factory)).findOne(city.getId(), addressName, null, district, postalCode, phone);

        customer.setAddress(address);
        customer.setActive(true);

        (new CustomerRepository(Customer.class, factory)).create(customer);
    }

    public static void task7_CustomerReturnFilm(Short customerId, Short filmId) {
        SessionFactory factory = MySqlSessionManager.getInstance().getSessionFactory();

        Customer customer = (new CustomerRepository(Customer.class, factory)).findById(customerId);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }

        Film film = (new FilmRepository(Film.class, factory)).findById(filmId);
        if (film == null) {
            throw new RuntimeException("Film not found");
        }
    }

    public static void task8_CustomerReturnFilm() {

    }

    public static void task9_CustomerReturnFilm() {

    }

    public static void task10_CustomerReturnFilm() {

    }
}
