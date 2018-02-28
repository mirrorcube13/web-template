package entity;


import java.time.LocalDateTime;
import java.util.HashMap;


/**
 * Created by Andrey on 02.01.2017.
 */

public class Order implements Entity {
    private int orderNumber;
    private int customerID;
    private LocalDateTime checkoutDate;
    private LocalDateTime closedDate;
    private OrderStatus orderStatus;
    private HashMap<Item, Integer> items = new HashMap<>();

    public Order(int orderNumber, int customerID, LocalDateTime checkoutDate, LocalDateTime closedDate, OrderStatus orderStatus) {
        this.orderNumber = orderNumber;
        this.customerID = customerID;
        this.checkoutDate = checkoutDate;
        this.closedDate = closedDate;
        this.orderStatus = orderStatus;
    }

    public Order(int orderNumber, int customerID, LocalDateTime checkoutDate, OrderStatus orderStatus) {
        this.orderNumber = orderNumber;
        this.customerID = customerID;
        this.checkoutDate = checkoutDate;
        this.orderStatus = orderStatus;
    }

    public Order(int customerID, LocalDateTime checkoutDate, OrderStatus orderStatus) {
        this.customerID = customerID;
        this.checkoutDate = checkoutDate;
        this.orderStatus = orderStatus;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + orderNumber;
        result = 31 * result + customerID;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return  false;
        }
        if(!(obj instanceof  Order)) {
            return false;
        }
        Order otherorder = (Order) obj;
        return orderNumber == otherorder.getId() && customerID == otherorder.getCustomerID();
    }

    @Override
    public String toString() {
        return getOrderNumber() + " " + getCustomerID() + " " + getCheckoutDate() + " " + getClosedDate()
                + " " + getOrderStatus() + getItems().toString();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDateTime checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDateTime getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(LocalDateTime closedDate) {
        this.closedDate = closedDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public int getId() {
        return orderNumber;
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<Item, Integer> items) {
        this.items = items;
    }

    public void addItem(Item item, int amount) {
        items.put(item, amount);
    }
}
