package ru.shcheglov.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Alexey Shcheglov
 * @version dated 23.12.2018
 */

@WebFilter(urlPatterns = {"/*"})
public class UTFFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        res.setContentType("text/html;charset=UTF-8");
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}
