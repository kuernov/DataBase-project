package com.DataBaseProject.PCenter.repository;

import com.DataBaseProject.PCenter.data.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    Subcategory findByName(String name);
}
