package ru.shcheglov.servlet;

import ru.shcheglov.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alexey Shcheglov
 * @version dated 03.02.2019
 */

@WebServlet(urlPatterns = "/secured/list")
public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = Model.getInstance();
        req.setAttribute("userNames", model.list());
        req.getRequestDispatcher("/WEB-INF/views/secured/list.jsp").forward(req, resp);
    }

}
