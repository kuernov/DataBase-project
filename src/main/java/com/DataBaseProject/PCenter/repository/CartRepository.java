package com.DataBaseProject.PCenter.repository;

import com.DataBaseProject.PCenter.data.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
