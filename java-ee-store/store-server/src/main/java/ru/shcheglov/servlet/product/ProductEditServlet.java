package ru.shcheglov.servlet.product;

import ru.shcheglov.constant.FieldConst;
import ru.shcheglov.entity.Product;
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

@WebServlet(urlPatterns = "/product-edit")
public class ProductEditServlet extends HttpServlet {

    @Inject
    private ProductRepository productRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String productId = req.getParameter(FieldConst.ID);
        final Product product = productRepository.findOneById(productId);
        if (product == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        req.setAttribute(FieldConst.PRODUCT, product);
        req.getRequestDispatcher("WEB-INF/views/product-edit.jsp").forward(req, resp);
    }

}
