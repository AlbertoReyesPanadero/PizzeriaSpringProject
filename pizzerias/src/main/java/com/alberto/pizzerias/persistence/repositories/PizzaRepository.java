package com.alberto.pizzerias.persistence.repositories;

import com.alberto.pizzerias.persistence.entities.PizzaEntity;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<PizzaEntity, String> {



}
