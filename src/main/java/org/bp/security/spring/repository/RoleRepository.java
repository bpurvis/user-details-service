package org.bp.security.spring.repository;

import org.bp.security.spring.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
