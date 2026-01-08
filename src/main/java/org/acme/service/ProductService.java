package org.acme.service;

import jakarta.enterprise.context.RequestScoped;
import org.acme.error.CodedException;
import org.acme.model.Product;

@RequestScoped
public class ProductService {

    private static final org.jboss.logging.Logger LOG = org.jboss.logging.Logger.getLogger(ProductService.class);

    public Product createProduct(Product product) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Creating a new product: " + product);
        }
        validateForInsert(product);
        product.persist();
        return product;
    }

    private void validateForInsert(Product product) {
        if (product == null) {
            throw new CodedException("INVALID_DATA", "Product data is required");
        }
        if (product.description == null || product.description.trim().isEmpty()) {
            throw new CodedException("INVALID_DESCRIPTION", "Description is required");
        }
        if (product.description.length() < 5 || product.description.length() > 60) {
            throw new CodedException("INVALID_DESCRIPTION", "Description length must be between 5 and 60 characters");
        }
        if (product.details != null && !product.details.trim().isEmpty()) {
            if (product.details.length() < 3 || product.details.length() > 10000) {
                throw new CodedException("INVALID_DETAILS", "Details length must be between 3 and 10000 characters");
            }
        }
    }

}
