package ru.shcheglov.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Alexey Shcheglov
 * @version dated 03.02.2019
 */

@WebServlet(urlPatterns = "/secured")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        session.setAttribute("username", req.getRemoteUser());
        req.getRequestDispatcher("/WEB-INF/views/secured/index.jsp").forward(req, resp);
    }

}
