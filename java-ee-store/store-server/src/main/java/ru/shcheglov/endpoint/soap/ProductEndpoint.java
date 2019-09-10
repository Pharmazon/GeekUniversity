package ru.shcheglov.endpoint.soap;

import ru.shcheglov.dto.ResultDTO;
import ru.shcheglov.dto.SuccessDTO;
import ru.shcheglov.entity.Product;
import ru.shcheglov.service.ProductService;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 08.02.2019
 */

@WebService
public class ProductEndpoint implements IProductEndpoint {

    @Inject
    private ProductService productService;

    @Override
    @WebMethod
    public Collection<Product> findAllProducts() {
        return productService.findAll();
    }

    @Override
    @WebMethod
    public Collection<Product> findAllProductsByCategoryId(
            @WebParam(name = "productCategoryId", partName = "productCategoryId") final String categoryId
    ) {
        return productService.findAllByCategoryId(categoryId);
    }

    @Override
    @WebMethod
    public Collection<Product> findAllProductsByName(
            @WebParam(name = "productName", partName = "productName") final String productName
    ) {
        return productService.findAllByName(productName);
    }

    @Override
    @WebMethod
    public Product getProductById(
            @WebParam(name = "productId", partName = "productId") final String id
    ) {
        return productService.findOneById(id);
    }

    @Override
    @WebMethod
    public ResultDTO removeProductById(
            @WebParam(name = "productId", partName = "productId") final String id
    ) {
        productService.removeById(id);
        return new SuccessDTO();
    }

    @Override
    @WebMethod
    public ResultDTO addProduct(
            @WebParam(name = "product", partName = "product") final Product product
    ) {
        productService.addOne(product);
        return new SuccessDTO();
    }

    @Override
    @WebMethod
    public ResultDTO addProductByName(
            @WebParam(name = "productName", partName = "productName") final String name
    ) {
        productService.addOneByName(name);
        return new SuccessDTO();
    }

}
