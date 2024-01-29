package com.DataBaseProject.PCenter.repository;

import com.DataBaseProject.PCenter.data.Category;
import com.DataBaseProject.PCenter.data.Product;
import com.DataBaseProject.PCenter.data.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')" +
            "Or p.description LIKE CONCAT('%', :query, '%')")
    List<Product> searchProducts(String query);
    List<Product> findByCategory(Category category);
    List<Product> findBySubcategory(Subcategory Subcategory);
}
