package org.acme;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        Map<String, String> responseObj = new HashMap<>();

        for (ConstraintViolation<?> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();

            // Extract the last part of the property path (the field name)
            String field = propertyPath.substring(propertyPath.lastIndexOf('.') + 1);

            if ("description".equals(field)) {
                responseObj.put("code", "INVALID_DESCRIPTION");
            } else {
                responseObj.put("code", "VALIDATION_ERROR");
            }
            responseObj.put("message", message);
            // Returning after the first violation for simplicity as per requirement
            return Response.status(Response.Status.BAD_REQUEST).entity(responseObj).build();
        }

        // Fallback if no violations found (should not happen with
        // ConstraintViolationException)
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
