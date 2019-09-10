package ru.shcheglov.endpoint.soap;

import ru.shcheglov.dto.ResultDTO;
import ru.shcheglov.entity.Product;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 08.02.2019
 */

@WebService
public interface IProductEndpoint {

    @WebMethod
    Collection<Product> findAllProducts();

    @WebMethod
    Product getProductById(
            @WebParam(name = "productId", partName = "productId") String id
    );

    @WebMethod
    ResultDTO removeProductById(
            @WebParam(name = "productId", partName = "productId") String id
    );

    @WebMethod
    Collection<Product> findAllProductsByCategoryId(
            @WebParam(name = "productCategoryId", partName = "productCategoryId") final String categoryId
    );

    @WebMethod
    Collection<Product> findAllProductsByName(
            @WebParam(name = "productName", partName = "productName") final String productName
    );

    @WebMethod
    ResultDTO addProduct(
            @WebParam(name = "product", partName = "product") final Product product
    );

    @WebMethod
    ResultDTO addProductByName(
            @WebParam(name = "productName", partName = "productName") final String name
    );
}
