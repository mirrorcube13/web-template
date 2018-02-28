import dao.*;
import dao.sqlBuilder.DeleteBuilder;
import dao.sqlBuilder.SqlBuilder;
import entity.*;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Andrey on 10.01.2017.
 */
public class ShopTest {
    @Test
    public void TestGetAllCustomers() throws SQLException, ClassNotFoundException {
        GenericDao customerDao = new CustomerDao();
        List result = customerDao.getAll();
        result.forEach(x -> System.out.println(x.toString()));
    }

    @Test
    public void TestGetCustomerById() throws SQLException, ClassNotFoundException {
        GenericDao customerDao = new CustomerDao();
        Entity entity = customerDao.getById(2);
        System.out.println(((Customer) entity).toString());
    }

    @Test
    public void TestInsertCustomer() throws SQLException, ClassNotFoundException {
        GenericDao customerDao = new CustomerDao();
        Customer customer = new Customer("Алексей", "Варивода", "lexa123@mail.ru" ,"12345" ,"666-66-66" ,
                "Г. Минск ул. Киселева 22 кв. 12");
        customerDao.insert(customer);
    }

    @Test
    public void TestUpdateCustomer() throws SQLException, ClassNotFoundException {
        GenericDao customerDao = new CustomerDao();
        Customer customer = (Customer) customerDao.getById(9);
        customer.setPhoneNumber("123-45-67");
        customerDao.update(customer);
    }

    @Test
    public void TestDeleteCustomer() throws SQLException, ClassNotFoundException {
        GenericDao customerDao = new CustomerDao();
        Customer customer = (Customer) customerDao.getById(7);
        customerDao.delete(customer);
    }

    @Test
    public void TestGetAllOrders() throws SQLException, ClassNotFoundException {
        GenericDao orderDao = new OrderDao();
        List result = orderDao.getAll();
        result.forEach(x -> System.out.println(x.toString()));
    }

    @Test
    public void TestGetOrderById() throws SQLException, ClassNotFoundException {
        GenericDao orderDao = new OrderDao();
        Entity entity = orderDao.getById(2);
        System.out.println(((Order) entity).toString());
    }

    @Test
    public void TestInsertOrder() throws SQLException, ClassNotFoundException {
        OrderDao orderDao = new OrderDao();
        Order order = (Order) orderDao.getById(4);
        GenericDao itemDao = new ItemDao();
        Entity entity = itemDao.getById(8);
        order.getItems().put((Item)entity, 2);
        orderDao.insert(order);
    }

    @Test
    public void TestUpdateOrder() throws SQLException, ClassNotFoundException {
        GenericDao orderDao = new OrderDao();
        Order order = (Order) orderDao.getById(6);
        order.setClosedDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.FINISHED);
        orderDao.update(order);
    }

    @Test
    public void TestDeleteOrder() throws SQLException, ClassNotFoundException {
        GenericDao orderDao = new OrderDao();
        Order order = (Order) orderDao.getById(2);
        orderDao.delete(order);
    }




    @Test
    public void TestGetOrdersWithItem() throws SQLException, ClassNotFoundException {
        OrderDao orderDao = new OrderDao();
        GenericDao itemDao = new ItemDao();
        List list = orderDao.getOrdersWithItem((Item)itemDao.getById(2));
        list.forEach(x -> System.out.println(x.toString()));
    }

    @Test
    public void TestDeleteOrdersBefore() throws SQLException, ClassNotFoundException {
        OrderDao orderDao = new OrderDao();
        orderDao.deleteOrdersBefore(LocalDate.of(2017, 1, 7));
    }

    @Test
    public void TestGetAllItems() throws SQLException, ClassNotFoundException {
        GenericDao itemDao = new ItemDao();
        List result = itemDao.getAll();
        result.forEach(x -> System.out.println(x.toString()));
    }

    @Test
    public void TestGetItemById() throws SQLException, ClassNotFoundException {
        GenericDao itemDao = new ItemDao();
        Entity entity = itemDao.getById(4);
        System.out.println(((Item) entity).toString());
    }

    @Test
    public void TestInsertItem() throws SQLException, ClassNotFoundException {
        GenericDao itemDao = new ItemDao();
        Item item = (Item) itemDao.getById(1);
        itemDao.insert(item);
    }

    @Test
    public void TestUpdateItem() throws SQLException, ClassNotFoundException {
        GenericDao itemDao = new ItemDao();
        Item item = (Item) itemDao.getById(14);
        item.setPrice(1000);
        item.setRemainingAmount(5);
        itemDao.update(item);
    }

    @Test
    public void TestDeleteItem() throws SQLException, ClassNotFoundException {
        GenericDao itemDao = new ItemDao();
        Item item = (Item) itemDao.getById(14);
        itemDao.delete(item);
    }
}
