package servlet;

import entity.Item;
import service.CustomerService;
import service.ItemsService;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Andrey on 19.01.2017.
 */
@WebServlet(urlPatterns = "/dashboard")
public class DashBoardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("customerList", CustomerService.getInstance().getAllCustomers());
        req.setAttribute("orderList", OrderService.getInstance().getAllOrders());
        req.setAttribute("itemList", ItemsService.getInstance().getAllItems());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/dashBoard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Item item = new Item(req.getParameter("itemName"), req.getParameter("itemDescription"),
                Double.parseDouble(req.getParameter("itemPrice")), Integer.parseInt(req.getParameter("itemAmount")),
                req.getParameter("itemImage"), req.getParameter("type"));
        if (item.getName().isEmpty() || item.getDescription().isEmpty() || item.getImage().isEmpty()) {
            resp.sendRedirect("/dashboard");
        } else {
            ItemsService.getInstance().insertItem(item);
            resp.sendRedirect("/dashboard");
        }
    }

}