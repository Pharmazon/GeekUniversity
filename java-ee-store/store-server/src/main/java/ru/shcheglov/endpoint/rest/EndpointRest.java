package ru.shcheglov.endpoint.rest;

import ru.shcheglov.dto.ResultDTO;
import ru.shcheglov.dto.SuccessDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Alexey Shcheglov
 * @version dated 09.02.2019
 */

@Path("/rest")
public class EndpointRest {

    @GET
    @Path("/hello")
    public String hello() {
        return "HELLO!";
    }

    @GET
    @Path("/pingJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO pingJSON() {
        return new SuccessDTO();
    }

    @GET
    @Path("/pingXML")
    @Produces(MediaType.APPLICATION_XML)
    public ResultDTO pingXml() {
        return new SuccessDTO();
    }

}
