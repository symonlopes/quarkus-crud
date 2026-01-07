package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends PanacheEntity {

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 5, max = 60, message = "Description must be between 5 and 60 characters")
    public String description;

    @Size(min = 3, max = 10000, message = "Details must be between 3 and 10000 characters")
    public String details;

    @PrePersist
    public void prePersist() {
        if (description != null)
            description = description.trim();
        if (details != null)
            details = details.trim();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
