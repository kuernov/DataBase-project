package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.Cart;
import com.DataBaseProject.PCenter.data.Product;
import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.dto.cart.AddToCartDto;
import com.DataBaseProject.PCenter.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, User user){
        //Check if product id is valid
        Product product = productService.findById(addToCartDto.getProductId());
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cartRepository.save(cart);
    }


}
