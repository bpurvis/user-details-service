package org.bp.security.spring.repository;

import org.bp.security.spring.model.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

}
