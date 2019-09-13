package ru.shcheglov.endpoint.crud;

import org.jetbrains.annotations.Nullable;
import ru.shcheglov.dto.CategoryDTO;
import ru.shcheglov.dto.ResultDTO;
import ru.shcheglov.dto.SuccessDTO;
import ru.shcheglov.entity.Category;
import ru.shcheglov.repository.CategoryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

@WebService
@Path("/crud/CategoryEndpointCrud")
@ApplicationScoped
public class CategoryEndpointCrud {

    @Inject
    private CategoryRepository categoryRepository;

    @WebMethod
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CategoryDTO> findAll() {
        final Collection<CategoryDTO> result = new ArrayList<>();
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    @WebMethod
    @GET
    @Path("/getOneById")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryDTO getOneById(
            @QueryParam("id")
            @WebParam(name = "id", partName = "id")
            @Nullable final String id
    ) {
        final Category category = categoryRepository.findOneById(id);
        return new CategoryDTO(category);
    }

    @WebMethod
    @GET
    @Path("/removeOneById")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO removeById(
            @QueryParam("id")
            @WebParam(name = "id", partName = "id")
            @Nullable final String id
    ) {
        categoryRepository.removeById(id);
        return new SuccessDTO();
    }

    @WebMethod
    @POST
    @Path("/createOneByName")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO addOneByName(
            @QueryParam("name")
            @WebParam(name = "name", partName = "name")
            @Nullable final String name
    ) {
        final Category category = new Category(name);
        categoryRepository.merge(category);
        return new SuccessDTO();
    }

    @WebMethod
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultDTO merge(
            @Nullable final CategoryDTO dto
    ) {
        categoryRepository.merge(dto);
        return new SuccessDTO();
    }
}
