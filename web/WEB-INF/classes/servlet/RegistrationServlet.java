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
import java.io.PrintWriter;

/**
 * Created by Andrey on 20.01.2017.
 */
@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        if (req.getParameter("firstName").isEmpty() || req.getParameter("lastName").isEmpty()
                || req.getParameter("email").isEmpty() || req.getParameter("password").isEmpty()
                || req.getParameter("phoneNumber").isEmpty() ||  req.getParameter("address").isEmpty()) {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
        } else {
            CustomerService.getInstance().insertCustomer(new Customer(req.getParameter("firstName"), req.getParameter("lastName"),
                    req.getParameter("email"), req.getParameter("password"), req.getParameter("phoneNumber"),
                    req.getParameter("address")));
            CustomerAuthenticationDto customerAuthenticationDto = new CustomerAuthenticationDto();
            customerAuthenticationDto.setName(req.getParameter("email"));
            customerAuthenticationDto.setPassword(req.getParameter("password"));
            customerAuthenticationDto.setRole("user");
            req.getSession().setAttribute("userData", customerAuthenticationDto);
            resp.sendRedirect("/andrey-shop");
        }
    }
}
