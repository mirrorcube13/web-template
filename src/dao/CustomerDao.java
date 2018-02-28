package dao;

import connection.DbConnection;
import dao.sqlBuilder.*;
import entity.Customer;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrey on 07.01.2017.
 */
public class CustomerDao extends GenericDao<Customer> {

    private static final String TABLE_NAME = "customers";

    @Override
    public List<Customer> getAll() {
        List<Customer> resultList = new LinkedList<>();
        ResultSet resultSet = getAllResults(TABLE_NAME);
        try {
            while (resultSet != null && resultSet.next()) {
                resultList.add(new Customer(resultSet.getInt("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password")
                        , resultSet.getString("phoneNumber"), resultSet.getString("addres")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Customer getById(int id) {
        Customer result = null;
        ResultSet resultSet = getResultsById(TABLE_NAME, id);
        try {
            if (resultSet != null && resultSet.next()) {
                result = new Customer(resultSet.getInt("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password")
                        , resultSet.getString("phoneNumber"), resultSet.getString("addres"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Customer customer) {
        Connection connection = DbConnection.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            SqlBuilder sqlBuilder = new InsertBuilder().insertInto("customers",
                    "first_name, last_name, email, password, phoneNumber, addres").values(
                    "\"" + customer.getFirstName() + "\", \"" + customer.getLastName() + "\", \"" + customer.getEmail()
                            + "\", \"" + customer.getPassword() + "\", \"" + customer.getPhoneNumber() + "\", \""
                            + customer.getAddres() +"\"");
            statement.executeUpdate(sqlBuilder.build());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void update(Customer customer) {
        Connection connection = DbConnection.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            SqlBuilder sqlBuilder = new UpdateBuilder().update("customers")
                    .set("PhoneNumber=\"" + customer.getPhoneNumber() + "\", addres=\"" + customer.getAddres() + "\"")
                    .where("id=" + customer.getId());
            statement.executeUpdate(sqlBuilder.build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        Connection connection = DbConnection.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            SqlBuilder sqlBuilder = new DeleteBuilder().delete("customers").where("id=" + customer.getId());
            statement.executeUpdate(sqlBuilder.build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Customer getByEmailAndPassword(String email, String password) {
        Customer result = null;
        Connection connection = DbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            SqlBuilder builder = new SelectBuilder().select("*").from("customers").where("email=\"" + email +"\" AND" +
                    " password=\"" + password +"\"");
            ResultSet resultSet = statement.executeQuery(builder.build());
            if (resultSet != null && resultSet.next()) {
                result = new Customer(resultSet.getInt("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("email"),
                        resultSet.getString("password"), resultSet.getString("phoneNumber"),
                        resultSet.getString("addres"), resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
