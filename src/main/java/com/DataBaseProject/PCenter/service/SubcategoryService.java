package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.Category;
import com.DataBaseProject.PCenter.data.Subcategory;
import com.DataBaseProject.PCenter.exception.ResourceNotFoundException;
import com.DataBaseProject.PCenter.repository.CategoryRepository;
import com.DataBaseProject.PCenter.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    public void createSubcategory(Subcategory subcategory){
        subcategoryRepository.save(subcategory);
        Category category = categoryRepository.findById(subcategory.getCategory().getId()).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        List<Subcategory> subcategories = category.getSubcategories();
        subcategories.add(subcategory);
        category.setSubcategories(subcategories);
        categoryRepository.save(category);
    }
    public List<Subcategory> listCategories(){
        return subcategoryRepository.findAll();
    }
    public void editSubcategory(int subcategoryId, Subcategory updateSubcategory){
        Subcategory subcategory = subcategoryRepository.getById(subcategoryId);
        subcategory.setName(updateSubcategory.getName());
        subcategoryRepository.save(subcategory);
    }
    public boolean findById(int subcategoryId) {
        return subcategoryRepository.findById(subcategoryId).isPresent();
    }
    public Optional<Subcategory> getById(int subcategoryId){
        if (subcategoryRepository.findById(subcategoryId).isPresent()) {
            return subcategoryRepository.findById(subcategoryId);
        }
        return null;
    }
}
