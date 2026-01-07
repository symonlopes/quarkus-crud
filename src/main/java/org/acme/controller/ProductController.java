package org.acme.controller;

import jakarta.transaction.Transactional;
import org.acme.model.Product;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    private static final org.jboss.logging.Logger LOG = org.jboss.logging.Logger.getLogger(ProductController.class);

    @POST
    @Transactional
    public Response create(@Valid Product product) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Creating product: " + product);
        }
        if (product == null || product.id != null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        product.persist();
        return Response.status(Response.Status.CREATED).entity(product).build();
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
