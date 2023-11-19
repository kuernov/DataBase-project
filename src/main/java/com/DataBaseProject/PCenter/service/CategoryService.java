package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.Category;
import com.DataBaseProject.PCenter.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
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
}
