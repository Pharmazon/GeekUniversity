package ru.shcheglov.endpoint.soap;

import ru.shcheglov.dto.ResultDTO;
import ru.shcheglov.dto.SuccessDTO;
import ru.shcheglov.entity.Category;
import ru.shcheglov.service.CategoryService;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

@WebService
public class CategoryEndpoint implements ICategoryEndpoint {

    @Inject
    private CategoryService categoryService;

    @Override
    @WebMethod
    public Collection<Category> findAllCategories() {
        return categoryService.findAll();
    }

    @Override
    @WebMethod
    public Category getCategoryById(
            @WebParam(name = "categoryId", partName = "categoryId") final String id
    ) {
        return categoryService.findOneById(id);
    }

    @Override
    @WebMethod
    public ResultDTO removeCategoryById(
            @WebParam(name = "categoryId", partName = "categoryId") final String categoryId
    ) {
        categoryService.removeById(categoryId);
        return new SuccessDTO();
    }

    @Override
    @WebMethod
    public ResultDTO addCategoryByName(
            @WebParam(name = "categoryName", partName = "categoryName") final String name
    ) {
        categoryService.addOne(name);
        return new SuccessDTO();
    }

}
