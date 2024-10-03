package com.alberto.users.persistence.repositories;

import com.alberto.users.persistence.entities.RoleEntity;
import com.alberto.users.persistence.entities.RoleEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    Optional<RoleEntity> findRoleEntityByRoleEnum(RoleEnum role);
}
