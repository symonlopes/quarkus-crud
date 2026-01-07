package org.acme.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/hello")
public class SampleController {

    private static final Logger LOG = Logger.getLogger(SampleController.class);

    @GET // Define que este método responde a requisições HTTP GET
    @Produces(MediaType.TEXT_PLAIN) // Define o Content-Type da resposta (text/plain)
    public String hello() {
        LOG.info("Executando endpoint de exemplo.");
        return "Hello World, Quarkus!";
    }
}