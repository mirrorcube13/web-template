package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Andrey on 26.01.2017.
 */
@WebFilter(urlPatterns = { "/*" },
        initParams = {
                @WebInitParam(name = "language", value = "ru_RU") })
public class LanguageFilter implements Filter {

private String langusge;

    @Override
    public void init(FilterConfig config) throws ServletException {
        langusge = config.getInitParameter("language");
        if (langusge == null) {
            langusge = "ru_RU";
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter("language") == null) {
            ((HttpServletRequest)servletRequest).getSession().setAttribute("language", langusge);
        } else {
            langusge = servletRequest.getParameter("language");
            ((HttpServletRequest)servletRequest).getSession().setAttribute("language", langusge);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}