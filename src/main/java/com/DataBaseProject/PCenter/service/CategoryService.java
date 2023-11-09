package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.Category;
import com.DataBaseProject.PCenter.data.Product;
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
    public void editCategory(int categoryId, Category updateCategory) throws Exception{
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent()){
            throw new Exception("Product is not present");
        }
        Category category =optionalCategory.get();
        category.setName(updateCategory.getName());
        category.setDescription(updateCategory.getDescription());
        categoryRepository.save(category);
    }
}
