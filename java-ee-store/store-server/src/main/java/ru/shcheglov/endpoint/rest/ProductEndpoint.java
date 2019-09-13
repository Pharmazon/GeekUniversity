package ru.shcheglov.endpoint.rest;

import ru.shcheglov.dto.ProductDTO;
import ru.shcheglov.dto.ResultDTO;
import ru.shcheglov.dto.SuccessDTO;
import ru.shcheglov.entity.Product;
import ru.shcheglov.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Alexey Shcheglov
 * @version dated 08.02.2019
 */

@Path("/ProductEndpointCrud")
public class ProductEndpoint {

    @Inject
    private ProductService productService;

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductDTO> findAll() {
        return productService.findAll()
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/getAllByCategoryId")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductDTO> findAllByCategoryId(
            @QueryParam("id") final String categoryId
    ) {
        return productService.findAllByCategoryId(categoryId)
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/getAllByName")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductDTO> findAllByName(
            @QueryParam("name") final String productName
    ) {
        return productService.findAllByName(productName)
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/getOneById")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO getOneById(
            @QueryParam("id") final String id
    ) {
        final Product product = productService.findOneById(id);
        return new ProductDTO(product);
    }

    @POST
    @Path("/createOne")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO merge(
            @QueryParam("product") final Product entity
    ) {
        final Product product = productService.merge(entity);
        return new ProductDTO(product);
    }

    @GET
    @Path("/removeById")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO removeById(
            @QueryParam("id") String id
    ) {
        productService.removeById(id);
        return new SuccessDTO();
    }

    @POST
    @Path("/addOne")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO addOne(
            @QueryParam("product") final Product product
    ) {
        productService.addOne(product);
        return new SuccessDTO();
    }

    @POST
    @Path("/addOneByName")
    @Produces(MediaType.APPLICATION_JSON)
    public void addOneByName(
            @QueryParam("name") final String name
    ) {
        productService.addOneByName(name);
    }

}
