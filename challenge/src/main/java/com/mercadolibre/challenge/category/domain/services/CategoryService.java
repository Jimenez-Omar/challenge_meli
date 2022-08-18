package com.mercadolibre.challenge.category.domain.services;

import com.mercadolibre.challenge.category.application.request.CategoryRequest;
import com.mercadolibre.challenge.category.application.response.CategoryResponse;
import com.mercadolibre.challenge.category.domain.models.Category;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements  ICategoryService{

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {

        Category newCategory = new Category();
        newCategory.setName(request.getName());
        newCategory.setRate(request.getRate());
        newCategory.setMax(request.getMax());

        categoryRepository.saveAndFlush(newCategory);

        CategoryResponse response= new CategoryResponse();
        response.setTarget(newCategory.getName());
        response.setStatus("Categoria creada exitosamente");

        return response;
    }

    @Override
    public Optional<CategoryResponse> updateCategory(Long id, CategoryRequest request) {
        Optional<Category> response = categoryRepository.findById(id);

        if (response.isPresent()){
            return response.flatMap(cat->{
                cat.setName(request.getName());
                cat.setMax(request.getMax());
                cat.setRate(request.getRate());

                categoryRepository.saveAndFlush(cat);

                CategoryResponse succcessResponse = new CategoryResponse();
                succcessResponse.setTarget(request.getName());
                succcessResponse.setStatus("Categoria "+request.getName() + " actualizada correctamente!!");

                return Optional.of(succcessResponse);
            });
        }else{
            CategoryResponse errorResponse = new CategoryResponse();
            errorResponse.setTarget(request.getName());
            errorResponse.setStatus("NO se ha encontrado la categoria solicitada");
            return Optional.of(errorResponse);
        }

    }
    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public double getRateByCategoryId(Long id) {
        Optional<Category> categoryResponse =  categoryRepository.findById(id);
        Category category = categoryResponse.get();
        return  category.getRate();
    }
}
