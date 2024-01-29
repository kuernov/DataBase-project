package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.Category;
import com.DataBaseProject.PCenter.data.Subcategory;
import com.DataBaseProject.PCenter.exception.ResourceNotFoundException;
import com.DataBaseProject.PCenter.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(Category category){
        categoryRepository.save(category);
    }
    public List<Category> listCategories(){
        return categoryRepository.findAll();
    }
    public void editCategory(int categoryId, Category updateCategory){
        Category category = categoryRepository.getById(categoryId);
        category.setName(updateCategory.getName());
        category.setDescription(updateCategory.getDescription());
        categoryRepository.save(category);
    }
    public boolean findById(int categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }
    public Optional<Category> getById(int categoryId){
        if (categoryRepository.findById(categoryId).isPresent()) {
            return categoryRepository.findById(categoryId);
        }
        return null;
    }

    public List<Subcategory> listSubcategories(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return category.getSubcategories();
    }
}
