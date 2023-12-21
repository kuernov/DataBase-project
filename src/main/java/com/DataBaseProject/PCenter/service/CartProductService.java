package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.data.cart.CartProduct;
import com.DataBaseProject.PCenter.dto.CartProductDto;
import com.DataBaseProject.PCenter.repository.CartProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartProductService {
    @Autowired
    private CartProductRepository cartProductRepository;
    @Transactional
    public CartProduct addCartProduct(Integer userId, CartProductDto cartProductDto) {
        User user = getUserById(userId);
        cartProductRepository.save();
        return cartProduct;
    }
    @Transactional
    public void deleteCartProduct(CartProduct cartProduct) {
        cartProductRepository.delete(cartProduct);
    }
}
