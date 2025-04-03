package org.example;

import org.example.models.*;
import org.example.utils.MySqlSessionManager;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        try (Session session = MySqlSessionManager.getInstance().getSessionFactory().openSession()) {
            List<Language> results = session.createQuery("from Language", Language.class).list();
//            List<Customer> results = session.createQuery("from Customer c", Customer.class).list();

            for (Language result : results) {
                System.out.println(result);
            }
        }


//        Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://MySQL-8.0/subproject_hibernate_2",
//                "root",
//                ""
//        );
//
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select rating, special_features from film");
//
//        resultSet.next();
//
//        ResultSetMetaData metaData = resultSet.getMetaData();
//        System.out.println(resultSet.getString("rating"));
//        System.out.println(resultSet.getString("special_features"));
    }
}
