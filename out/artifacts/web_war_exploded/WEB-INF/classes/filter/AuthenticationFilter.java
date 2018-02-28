package filter;

import dto.CustomerAuthenticationDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Andrey on 21.01.2017.
 */
@WebFilter(value = "/*")
public class AuthenticationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        Object userData = request.getSession().getAttribute("userData");
        if(request.getRequestURI().contains("/shoppingBag")) {
            if (userData == null) {
                servletRequest.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                        .forward(servletRequest, servletResponse);
            }
        } else {
            if (request.getRequestURI().contains("/dashboard")) {
                if (userData == null) {
                    servletRequest.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                            .forward(servletRequest, servletResponse);
                } else if (((CustomerAuthenticationDto) userData).getRole().equals("user")) {
                    ((HttpServletResponse) servletResponse).sendRedirect("/andrey-shop");
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }



    @Override
    public void destroy() {

    }
}
