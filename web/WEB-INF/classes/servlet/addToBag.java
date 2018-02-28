package servlet;

import dto.ShoppingBagDto;
import entity.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Andrey on 26.01.2017.
 */
@WebServlet(urlPatterns = "/addToBag")
public class addToBag extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingBagDto shoppingBag = ShoppingBagDto.get(session);
        String id = request.getParameter("itemId");
        if (!id.isEmpty()) {
            shoppingBag.addItem(Integer.parseInt(id.trim()));
        }
        request.getRequestDispatcher("/andrey-shop").forward(request, response);
    }

}

