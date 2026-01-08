package org.acme.error;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@Provider
public class ApiExceptionMapper  {

    @ServerExceptionMapper
    public Response toResponse(CodedException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(ApiException.builder()
                        .code(exception.getCode())
                        .message(exception.getMessage())
                        .build())
                .build();

    }
}
