package com.alberto.pizzerias.persistence.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "pizzas")
public class PizzaEntity {

    @Id
    String id;

    String nombre;
    String descripcion;

    @DBRef
    float precio;
}
