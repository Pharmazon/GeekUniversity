package ru.shcheglov.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alexey Shcheglov
 * @version dated 23.12.2018
 */

@WebServlet(urlPatterns = {"/session"})
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String sessionId = req.getSession().getId();
        resp.getWriter().println(sessionId);

        Cookie cookie = new Cookie("name", "value");
        resp.addCookie(cookie);

        Cookie[] cookies = req.getCookies();
        System.out.println("COOKIES:");
        for (Cookie cook : cookies) {
            resp.getWriter().println("Name=" + cook.getName() + "; Value=" + cook.getValue());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
