package service;

import dao.ItemDao;
import dao.OrderDao;
import entity.Item;
import entity.Order;
import entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Andrey on 19.01.2017.
 */
public class OrderService {
    private static OrderService INSTANCE = null;

    private OrderService() {}

    public static OrderService getInstance() {
        if (INSTANCE == null) {
            synchronized (OrderService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new OrderService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Order> getAllOrders() {
        return new OrderDao().getAll();
    }


    public boolean createOrder(HashMap<Item, Integer> itemMap, int customerId) {
        OrderDao orderDao = new OrderDao();
        Order order = new Order(customerId, LocalDateTime.now(), OrderStatus.IN_PROCESS);
        order.setItems(itemMap);
        return orderDao.insert(order);
    }

    public void updateOrder(int orderId) {
        OrderDao orderDao = new OrderDao();
        Order order = orderDao.getById(orderId);
        orderDao.update(order);
    }
}
