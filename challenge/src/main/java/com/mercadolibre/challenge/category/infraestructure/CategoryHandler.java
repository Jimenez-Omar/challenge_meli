package com.mercadolibre.challenge.category.infraestructure;

import com.mercadolibre.challenge.category.application.request.CategoryRequest;
import com.mercadolibre.challenge.category.application.response.CategoryResponse;
import com.mercadolibre.challenge.category.domain.models.Category;
import com.mercadolibre.challenge.category.domain.services.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryHandler {

    private CategoryService categoryService;

    public CategoryHandler(CategoryService service){
        this.categoryService = service;
    }

    @Operation(summary = "Creación de nuevo target")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Categoria creada exitosamente",response = CategoryResponse.class)})
    @PostMapping("/new")
    private ResponseEntity<CategoryResponse> newCategory(@RequestBody  CategoryRequest request){

        CategoryResponse newCategory = categoryService.createCategory(request);

        try {
            return ResponseEntity.created(new URI("category/new/"+newCategory.getTarget())).body(newCategory);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "actualizción de valores de target")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Categoria 'NOMBRE-CATEGORIA' actualizada correctamente!!",response = CategoryResponse.class)})
    @PostMapping("/update/{cat_id}")
    private ResponseEntity<Optional<CategoryResponse>> updateCategory(@PathVariable long cat_id, @RequestBody  CategoryRequest request){

        Optional<CategoryResponse> response = categoryService.updateCategory(cat_id,request);

        try {
            return ResponseEntity.created(new URI("category/update/"+request.getName())).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "listar targets disponibles")
    @ApiResponses({
            @ApiResponse(code = 200,message="Lista de Categorias",response = Category.class)})
    @GetMapping("/list")
    private ResponseEntity<List<Category>> listCategory(){

        List<Category> list = categoryService.getAllCategories();

        try {
            return ResponseEntity.created(new URI("category/list/all")).body(list);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


}
