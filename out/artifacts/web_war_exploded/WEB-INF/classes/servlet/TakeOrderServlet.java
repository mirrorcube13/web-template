package servlet;

import entity.Item;
import service.CustomerService;
import service.ItemsService;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Andrey on 30.01.2017.
 */
@WebServlet(urlPatterns = "/takeOrder")
public class TakeOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer orderId = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                orderId.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int id = Integer.parseInt(orderId.toString());
        OrderService.getInstance().updateOrder(id);
    }
}