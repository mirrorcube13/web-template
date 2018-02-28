package servlet;

import dao.CustomerDao;
import dto.CustomerAuthenticationDto;
import dto.ItemDto;
import dto.ShoppingBagDto;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import service.CustomerService;
import service.ItemsService;
import service.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Andrey on 26.01.2017.
 */
@WebServlet("/shoppingBag")
public class ShoppingBagServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("list", ItemsService.getInstance().getItemsFromBag(ShoppingBagDto.get(session).getIds()));
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/shoppingBag.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
            System.out.println("StringBuffer=" + jb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CustomerAuthenticationDto userData =   (CustomerAuthenticationDto) req.getSession().getAttribute("userData");
        boolean flag = OrderService.getInstance().createOrder(ItemsService.getInstance().parseItems(jb.toString()),
                CustomerService.getInstance().getByEmailAndPassword(userData.getName(), userData.getPassword()).getId());
        req.getSession().removeAttribute("bag");
        resp.setContentType("text/xml");
        resp.getWriter().println(flag);
    }
}