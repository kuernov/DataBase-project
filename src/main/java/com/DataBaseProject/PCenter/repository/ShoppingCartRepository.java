package com.DataBaseProject.PCenter.repository;

import com.DataBaseProject.PCenter.data.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}
