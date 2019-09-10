package ru.shcheglov.servlet.product;

import ru.shcheglov.constant.FieldConst;
import ru.shcheglov.repository.ProductRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alexey Shcheglov
 * @version dated 13.01.2019
 */

@WebServlet(urlPatterns = "/product-remove")
public class ProductRemoveServlet extends HttpServlet {

    @Inject
    private ProductRepository productRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String productId = req.getParameter(FieldConst.ID);
        productRepository.removeById(productId);
        resp.sendRedirect("product-list");
    }

}
