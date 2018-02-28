package dao;

import connection.DbConnection;
import dao.sqlBuilder.InsertBuilder;
import dao.sqlBuilder.SelectBuilder;
import dao.sqlBuilder.SqlBuilder;
import entity.Entity;
import entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.List;

/**
 * Created by Andrey on 07.01.2017.
 */
public abstract class GenericDao<T extends Entity> {

    public abstract List<T> getAll();

    public abstract T getById(int id);

    public abstract boolean insert(T entity);

    public abstract void update(T entity);

    public abstract void delete(T entity);

    protected ResultSet getAllResults(String tableName) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DbConnection.getConnection();
            statement = connection.createStatement();
            SqlBuilder sqlBuilder = new SelectBuilder().select("*").from(tableName);
            return statement.executeQuery(sqlBuilder.build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected ResultSet getResultsById(String tableName, int id) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DbConnection.getConnection();
            statement = connection.createStatement();
            SqlBuilder sqlBuilder = new SelectBuilder().select("*").from(tableName).where("id=" + id);
            return statement.executeQuery(sqlBuilder.build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
