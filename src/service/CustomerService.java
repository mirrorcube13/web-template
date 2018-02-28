package service;

import dao.CustomerDao;
import dao.OrderDao;
import entity.Customer;

import java.util.List;

/**
 * Created by Andrey on 19.01.2017.
 */
public class CustomerService {
    private static CustomerService INSTANCE = null;

    private CustomerService() {}

    public static CustomerService getInstance() {
        if (INSTANCE == null) {
            synchronized (CustomerService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CustomerService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new CustomerDao().getAll();
        for(Customer customer : customerList) {
            customer.setOrderList(new OrderDao().getAll(customer));
        }
        return customerList;
    }

    public void insertCustomer(Customer customer) {
        CustomerDao customerDao = new CustomerDao();
        customerDao.insert(customer);
    }

    public Customer getByEmailAndPassword(String email, String password) {
        return new CustomerDao().getByEmailAndPassword(email, password);

    }
}
