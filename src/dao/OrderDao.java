package dao;

import connection.DbConnection;
import dao.sqlBuilder.*;
import entity.Customer;
import entity.Item;
import entity.Order;
import entity.OrderStatus;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrey on 10.01.2017.
 */
public class OrderDao extends GenericDao<Order> {

    private static final String TABLE_NAME = "orders";
    private static DateTimeFormatter DATE_TME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<Order> getAll() {
        List<Order> resultList = new LinkedList<>();
        Connection connection = DbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT orders.id, orders.customer_id, orders.checkout_date, " +
                    "orders.closed_date, orders.order_status, shopping_bag.product_id, products.name, products.description, " +
                    "products.price, products.remaining_product, products.image, products.product_type, shopping_bag.amount " +
                    "FROM shopping_bag JOIN orders ON shopping_bag.order_id=orders.id " +
                    "JOIN products ON shopping_bag.product_id=products.id;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet != null && resultSet.next()) {
                Order order = new Order(resultSet.getInt("id"), resultSet.getInt("customer_id"),
                        resultSet.getTimestamp("checkout_date").toLocalDateTime(),
                        resultSet.getObject("closed_date") != null ? resultSet.getTimestamp("closed_date").toLocalDateTime() : null,
                        OrderStatus.valueOf(resultSet.getString("order_status")));
                String description = new String(resultSet.getBlob("description").getBytes(1l,
                        (int) resultSet.getBlob("description").length()), "UTF-8");
                Item item = new Item(resultSet.getInt("product_id"), resultSet.getString("name"),
                        description, resultSet.getDouble("price"), resultSet.getInt("remaining_product"),
                        resultSet.getString("image") != null ? resultSet.getString("image") : null,
                        resultSet.getString("product_type"));
                if (resultList.contains(order)) {
                    resultList.get(resultList.indexOf(order)).getItems().put(item, resultSet.getInt("amount"));
                } else {
                    order.getItems().put(item, resultSet.getInt("amount"));
                    resultList.add(order);
                }
            }
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Order getById(int id) {
        Order result = null;
        Connection connection = DbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT orders.id, orders.customer_id, orders.checkout_date, " +
                    "orders.closed_date, orders.order_status, shopping_bag.product_id, products.name, products.description, " +
                    "products.price, products.remaining_product, products.image, products.product_type, shopping_bag.amount " +
                    "FROM shopping_bag JOIN orders ON shopping_bag.order_id=orders.id " +
                    "JOIN products ON shopping_bag.product_id=products.id WHERE orders.id=" + id + ";";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet != null && resultSet.next()) {
                result = new Order(resultSet.getInt("id"), resultSet.getInt("customer_id"),
                        resultSet.getTimestamp("checkout_date").toLocalDateTime(),
                        resultSet.getObject("closed_date") != null ? resultSet.getTimestamp("closed_date").toLocalDateTime() : null,
                        OrderStatus.valueOf(resultSet.getString("order_status")));
            }
            do {
                String description = new String(resultSet.getBlob("description").getBytes(1l,
                        (int) resultSet.getBlob("description").length()), "UTF-8");
                Item item = new Item(resultSet.getInt("product_id"), resultSet.getString("name"),
                        description, resultSet.getDouble("price"), resultSet.getInt("remaining_product"),
                        resultSet.getString("image") != null ? resultSet.getString("image") : null,
                        resultSet.getString("product_type"));
                result.getItems().put(item, resultSet.getInt("amount"));
            } while (resultSet.next());
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(Order order) {
        Connection connection = DbConnection.getConnection();
        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            SqlBuilder sqlBuilder1 = new InsertBuilder().insertInto("orders",
                    "customer_id, checkout_date, order_status")
                    .values(order.getCustomerID() + ", \"" + LocalDateTime.now().format(DATE_TME_FORMATTER) + "\", \""
                            + OrderStatus.IN_PROCESS + "\"");
            statement.executeUpdate(sqlBuilder1.build());
            connection.commit();

            order.setOrderNumber(getIdOfLastOrder());

            for (Item item : order.getItems().keySet()) {
                        SqlBuilder sqlBuilder2 = new UpdateBuilder().update("products")
                                .set("remaining_product=" + (item.getRemainingAmount() - order.getItems().get(item)))
                                .where("id=" + item.getId());
                statement.executeUpdate(sqlBuilder2.build());


                SqlBuilder sqlBuilder3 = new InsertBuilder().insertInto("shopping_bag", "order_id, product_id, amount")
                        .values(order.getId() + ", " + item.getId() + ", " + order.getItems().get(item));
                statement.executeUpdate(sqlBuilder3.build());

                connection.commit();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                return false;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return  false;
        }
        return  true;
    }

    @Override
    public void update(Order order) {
        Connection connection = DbConnection.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            SqlBuilder sqlBuilder = new UpdateBuilder().update("orders")
                    .set("closed_date=\"" + LocalDateTime.now().format(DATE_TME_FORMATTER).toString() + "\"," +
                            " order_status=\"" + OrderStatus.FINISHED + "\"")
                    .where("id=" + order.getId());
            statement.executeUpdate(sqlBuilder.build());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Order order) {
        Connection connection = DbConnection.getConnection();
        Statement statement = null;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            SqlBuilder sqlBuilder2 = new DeleteBuilder().delete("shopping_bag").where("order_id=" + order.getId());
            statement.executeUpdate(sqlBuilder2.build());
            SqlBuilder sqlBuilder = new DeleteBuilder().delete("orders").where("id=" + order.getId());
            statement.executeUpdate(sqlBuilder.build());
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<Order> getAll(Customer customer) {
        List<Order> resultList = new LinkedList<>();
        Connection connection = DbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT orders.id, orders.customer_id, orders.checkout_date, " +
                    "orders.closed_date, orders.order_status, shopping_bag.product_id, products.name, products.description, " +
                    "products.price, products.remaining_product, products.image, products.product_type, shopping_bag.amount " +
                    "FROM shopping_bag JOIN orders ON shopping_bag.order_id=orders.id " +
                    "JOIN products ON shopping_bag.product_id=products.id WHERE orders.customer_id=" + customer.getId() +";";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet != null && resultSet.next()) {
                Order order = new Order(resultSet.getInt("id"), resultSet.getInt("customer_id"),
                        resultSet.getTimestamp("checkout_date").toLocalDateTime(),
                        resultSet.getObject("closed_date") != null ? resultSet.getTimestamp("closed_date").toLocalDateTime() : null,
                        OrderStatus.valueOf(resultSet.getString("order_status")));
                String description = new String(resultSet.getBlob("description").getBytes(1l,
                        (int) resultSet.getBlob("description").length()), "UTF-8");
                Item item = new Item(resultSet.getInt("product_id"), resultSet.getString("name"),
                        description, resultSet.getDouble("price"), resultSet.getInt("remaining_product"),
                        resultSet.getString("image") != null ? resultSet.getString("image") : null,
                        resultSet.getString("product_type"));
                if (resultList.contains(order)) {
                    resultList.get(resultList.indexOf(order)).getItems().put(item, resultSet.getInt("amount"));
                } else {
                    order.getItems().put(item, resultSet.getInt("amount"));
                    resultList.add(order);
                }
            }
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public List<Order> getOrdersWithItem(Item item) {
        List<Order> resultList = new LinkedList<>();
        Connection connection = DbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            SqlBuilder builder = new SelectBuilder().select("orders.id, orders.customer_id, orders.checkout_date," +
                    "orders.closed_date, orders.order_status").from("orders").join("shopping_bag")
                    .on("orders.id=shopping_bag.order_id").where("shopping_bag.product_id=" + item.getId());
            ResultSet resultSet = statement.executeQuery(builder.build());
            while (resultSet != null && resultSet.next()) {
                resultList.add(new Order(resultSet.getInt("id"), resultSet.getInt("customer_id"),
                        resultSet.getTimestamp("checkout_date").toLocalDateTime(),
                        resultSet.getObject("closed_date") != null ? resultSet.getTimestamp("closed_date").toLocalDateTime() : null,
                        (resultSet.getObject("order_status") instanceof OrderStatus)
                                ? (OrderStatus) resultSet.getObject("order_status") : null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public void deleteOrdersBefore(LocalDate localDate) {
        List<Order> resultList = new LinkedList<>();
        Connection connection = DbConnection.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            SqlBuilder sqlBuilder = new SelectBuilder().select("*").from("orders").where("checkout_date<\""
                    + localDate.toString() + " 00:00:00\"");
            ResultSet resultSet = statement.executeQuery(sqlBuilder.build());
            while (resultSet != null && resultSet.next()) {
                resultList.add(new Order(resultSet.getInt("id"), resultSet.getInt("customer_id"),
                        resultSet.getTimestamp("checkout_date").toLocalDateTime(),
                        resultSet.getObject("closed_date") != null ? resultSet.getTimestamp("closed_date").toLocalDateTime() : null,
                        (resultSet.getObject("order_status") instanceof OrderStatus)
                                ? (OrderStatus) resultSet.getObject("order_status") : null));
            }
            for (Order order : resultList) {
                sqlBuilder = new DeleteBuilder().delete("shopping_bag").where("order_id=" + order.getId());
                statement.executeUpdate(sqlBuilder.build());
                delete(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdOfLastOrder() {
        Connection connection = DbConnection.getConnection();
        int id = 0;
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT id FROM orders ORDER BY id DESC LIMIT 1";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet != null && resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


}