package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product extends PanacheEntity {

    @jakarta.persistence.Column(length = 60)
    public String description;
    @jakarta.persistence.Column(length = 10000)
    public String details;

    @PrePersist
    public void prePersist() {
        if (description != null)
            description = description.trim();
        if (details != null)
            details = details.trim();
    }

}
