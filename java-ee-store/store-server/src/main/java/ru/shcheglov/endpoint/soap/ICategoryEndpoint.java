package ru.shcheglov.endpoint.soap;

import ru.shcheglov.dto.ResultDTO;
import ru.shcheglov.entity.Category;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

@WebService
public interface ICategoryEndpoint {

    @WebMethod
    Collection<Category> findAllCategories();

    @WebMethod
    Category getCategoryById(
            @WebParam(name = "categoryId", partName = "categoryId") String id
    );

    @WebMethod
    ResultDTO removeCategoryById(
            @WebParam(name = "categoryId", partName = "categoryId") String id
    );

    @WebMethod
    ResultDTO addCategoryByName(
            @WebParam(name = "categoryName", partName = "categoryName") String name
    );

}
