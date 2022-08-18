package com.mercadolibre.challenge.category.domain.services;

import com.mercadolibre.challenge.category.application.request.CategoryRequest;
import com.mercadolibre.challenge.category.application.response.CategoryResponse;
import com.mercadolibre.challenge.category.domain.models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    CategoryResponse createCategory(CategoryRequest request);

    Optional<CategoryResponse> updateCategory(Long id, CategoryRequest request);

    List<Category> getAllCategories();

    double getRateByCategoryId(Long id);

}
