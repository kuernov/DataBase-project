package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.ShoppingCart;
import com.DataBaseProject.PCenter.dto.ProductDto;

public interface ShoppingCartService {
    ShoppingCart addItemToCart(ProductDto productDto, int quantity, String email);
    ShoppingCart updateCart(ProductDto productDto, int quantity, String email);
    ShoppingCart removeItemFromCart(ProductDto productDto, String email);




}
