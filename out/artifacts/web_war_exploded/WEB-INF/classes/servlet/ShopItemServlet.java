package servlet;

import service.ItemsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 18.01.2017.
 */
@WebServlet(urlPatterns = "/shop-item")
public class ShopItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        req.setAttribute("item", ItemsService.getInstance().getItemById(Integer.parseInt(req.getParameter("id"))));
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/shopItem.jsp").forward(req, resp);

    }
}
