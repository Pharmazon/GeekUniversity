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
import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 13.01.2019
 */

@WebServlet(urlPatterns = "/product-list")
public class ProductListServlet extends HttpServlet {

    @Inject
    private ProductRepository productRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Collection<Product> products = productRepository.findAll();
        req.setAttribute(FieldConst.PRODUCTS, products);
        req.getRequestDispatcher("WEB-INF/views/product-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    
}
