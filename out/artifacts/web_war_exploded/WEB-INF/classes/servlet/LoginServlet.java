package servlet;

import dto.CustomerAuthenticationDto;
import entity.Customer;
import service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Andrey on 21.01.2017.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("command")) {
            case "login":
                processLogin(req, resp);
                break;
            case "logout":
                processLogout(req, resp);
                break;
        }

    }

    private void processLogin(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Customer customer = CustomerService.getInstance().getByEmailAndPassword(req.getParameter("email"), req.getParameter("password"));
        if (customer != null) {
            CustomerAuthenticationDto customerAuthenticationDto = new CustomerAuthenticationDto();
            customerAuthenticationDto.setName(customer.getEmail());
            customerAuthenticationDto.setPassword(customer.getPassword());
            customerAuthenticationDto.setRole(customer.getRole());
            req.getSession().setAttribute("userData", customerAuthenticationDto);
            resp.sendRedirect("/andrey-shop");

        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }

    }

    private void processLogout(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/andrey-shop");
    }
}