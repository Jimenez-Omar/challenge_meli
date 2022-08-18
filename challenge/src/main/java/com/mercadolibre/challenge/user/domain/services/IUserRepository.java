package com.mercadolibre.challenge.user.domain.services;

import com.mercadolibre.challenge.user.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user",collectionResourceRel = "user")
public interface IUserRepository extends JpaRepository<User, Long> {
}
