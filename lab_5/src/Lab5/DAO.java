package Lab5;

import Lab3.RegionalDep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    default Connection getConnection() throws SQLException {
        Connection result = DriverManager.getConnection("jdbc:Political.db");
        return result;
    }

    RegionalDep create(T entity);

    boolean update(T entity);

    boolean delete(int id);

    List<T> find(T entity);

    List<T> findAll();
}

