package com.DataBaseProject.PCenter.repository;

import com.DataBaseProject.PCenter.data.cart.CartProduct;
import com.DataBaseProject.PCenter.data.cart.CartProductPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, CartProductPK> {
}
