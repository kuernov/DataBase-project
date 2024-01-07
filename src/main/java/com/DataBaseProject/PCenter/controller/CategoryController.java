package com.DataBaseProject.PCenter.controller;

import com.DataBaseProject.PCenter.common.ApiResponse;
import com.DataBaseProject.PCenter.data.Category;
import com.DataBaseProject.PCenter.service.CategoryService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/create")
    //@RolesAllowed("ADMIN")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "a new category created"), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public List<Category> listCategories(){
        return categoryService.listCategories();
    }
    @GetMapping("/{id}")
    public Optional<Category> findById(@PathVariable int id){
        return categoryService.getById(id);
    }
    @PostMapping("/update/{categoryId}")
    //@RolesAllowed("ADMIN")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable ("categoryId") int categoryId, @RequestBody Category category){
        if(!categoryService.findById(categoryId))
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category not found"), HttpStatus.NOT_FOUND);
        categoryService.editCategory(categoryId,category);
        return new ResponseEntity<>(new ApiResponse(true, "category has been updated"), HttpStatus.OK);
    }

    // może zagnieżdżone kategorie?


}
