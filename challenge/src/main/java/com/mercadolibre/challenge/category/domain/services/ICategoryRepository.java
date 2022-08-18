package com.mercadolibre.challenge.category.domain.services;

import com.mercadolibre.challenge.category.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "category",collectionResourceRel = "category")
public interface ICategoryRepository extends JpaRepository<Category,Long> {

}
