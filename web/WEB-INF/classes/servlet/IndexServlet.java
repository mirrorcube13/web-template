package servlet;

import dao.CustomerDao;
import dao.GenericDao;
import dao.ItemDao;
import dao.OrderDao;
import dto.PageDetailDto;
import entity.Customer;
import entity.Item;
import entity.Order;
import service.ItemsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrey on 11.01.2017.
 */
@WebServlet(urlPatterns = "/andrey-shop")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("PageDetails", new PageDetailDto());
        req.setAttribute("list", ItemsService.getInstance().getAllItems());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PageDetailDto pageDetailDto = (PageDetailDto) req.getSession().getAttribute("PageDetails");
        List<Item> itemList = new LinkedList<>();

        if(req.getParameter("sort") != null) {
            String sort = req.getParameter("sort");
            if(pageDetailDto.getSort().equals(sort)) {
                pageDetailDto.setSort(sort + " desc");
            } else {
                pageDetailDto.setSort(sort);
            }
            req.getSession().setAttribute("PageDetails", pageDetailDto);
            req.setAttribute("list", ItemsService.getInstance().searchItems(pageDetailDto.getLike(),
                    pageDetailDto.getSort(), pageDetailDto.getProductType()));
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/items.jsp").forward(req, resp);

        } else if(req.getParameter("productType") != null) {
            String productType = req.getParameter("productType");
                pageDetailDto.setProductType(productType);
                pageDetailDto.setLike("");
            req.getSession().setAttribute("PageDetails", pageDetailDto);
            req.setAttribute("list", ItemsService.getInstance().searchItems(pageDetailDto.getLike(),
                    pageDetailDto.getSort(), pageDetailDto.getProductType()));
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/items.jsp").forward(req, resp);

        } else if(req.getParameter("like") != null) {
                pageDetailDto.setLike(req.getParameter("like"));
                pageDetailDto.setProductType(null);

            req.getSession().setAttribute("PageDetails", pageDetailDto);
            req.setAttribute("list", ItemsService.getInstance().searchItems(pageDetailDto.getLike(),
                    pageDetailDto.getSort(), pageDetailDto.getProductType()));
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);

        }


    }

}
