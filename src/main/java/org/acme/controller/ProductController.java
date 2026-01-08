package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.model.Product;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import org.acme.service.ProductService;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject ProductService productService;

    @POST
    @Transactional
    public Response create(Product product) {
        var insertedProduct = productService.createProduct(product);
        return Response.status(Response.Status.CREATED).entity(insertedProduct).build();
    }

    @GET
    @Path("/{id}")
    public Product get(@PathParam("id") Long id) {
        Product product = Product.findById(id);
        if (product == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return product;
    }
}
