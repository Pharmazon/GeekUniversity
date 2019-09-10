package ru.shcheglov.endpoint.rest;

import ru.shcheglov.dto.CategoryDTO;
import ru.shcheglov.dto.ResultDTO;
import ru.shcheglov.dto.SuccessDTO;
import ru.shcheglov.entity.Category;
import ru.shcheglov.service.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

@Path("/CategoryEndpointCrud")
public class CategoryEndpoint {

    @Inject
    private CategoryService categoryService;

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CategoryDTO> findAll() {
        return categoryService.findAll()
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/getOneById")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryDTO getOneById(
            @QueryParam("id") final String id
    ) {
        final Category category = categoryService.findOneById(id);
        return new CategoryDTO(category);
    }

    @GET
    @Path("/removeOneById")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO removeById(
            @QueryParam("id") final String id
    ) {
        categoryService.removeById(id);
        return new SuccessDTO();
    }

    @POST
    @Path("/createOne")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO addOne(
            @QueryParam("name") final String name
    ) {
        categoryService.addOne(name);
        return new SuccessDTO();
    }

}
