package ru.shcheglov.endpoint.crud;

import org.jetbrains.annotations.Nullable;
import ru.shcheglov.dto.ProductDTO;
import ru.shcheglov.dto.ResultDTO;
import ru.shcheglov.dto.SuccessDTO;
import ru.shcheglov.entity.Product;
import ru.shcheglov.repository.ProductRepository;

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
 * @version dated 08.02.2019
 */

@WebService
@Path("/crud/ProductEndpointCrud")
@ApplicationScoped
public class ProductEndpointCrud {

    @Inject
    private ProductRepository productRepository;

    @WebMethod
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @WebMethod
    @GET
    @Path("/getAllByCategoryId")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductDTO> findAllByCategoryId(
            @QueryParam("categoryId")
            @WebParam(name = "categoryId", partName = "categoryId")
            @Nullable final String categoryId
    ) {
        return productRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @WebMethod
    @GET
    @Path("/getAllByName")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductDTO> findAllByName(
            @QueryParam("name")
            @WebParam(name = "name", partName = "name")
            @Nullable final String productName
    ) {
        final Collection<ProductDTO> result = new ArrayList<>();
        final Collection<Product> products = productRepository.findAllByName(productName);
        for (final Product product : products) {
            if (product == null) continue;
            result.add(new ProductDTO(product));
        }
        return result;
    }

    @WebMethod
    @GET
    @Path("/getOneById")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDTO getOneById(
            @QueryParam("id")
            @WebParam(name = "id", partName = "id")
            @Nullable final String id
    ) {
        final Product product = productRepository.findOneById(id);
        return new ProductDTO(product);
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
        productRepository.removeById(id);
        return new SuccessDTO();
    }

    @WebMethod
    @POST
    @Path("/createOne")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO addOne() {
        final Product product = new Product();
        productRepository.merge(product);
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
        final Product product = new Product(name);
        productRepository.merge(product);
        return new SuccessDTO();
    }

    @WebMethod
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultDTO merge(
            @Nullable final ProductDTO dto
    ) {
        productRepository.merge(dto);
        return new SuccessDTO();
    }

}
