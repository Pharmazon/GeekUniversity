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

@WebServlet(urlPatterns = "/product-save")
public class ProductSaveServlet extends HttpServlet {

    @Inject
    private ProductRepository productRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String id = req.getParameter(FieldConst.ID);
        if (id == null || id.isEmpty()) {
            sendRedirectProductList(resp);
            return;
        }
        final Product product = productRepository.findOneById(id);
        if (product == null) {
            sendRedirectProductList(resp);
            return;
        }
        product.setName(req.getParameter(FieldConst.NAME));
        productRepository.merge(product);
        sendRedirectProductList(resp);
    }

    private void sendRedirectProductList(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("product-list");
    }

}
