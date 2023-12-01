package com.DataBaseProject.PCenter.service.cart;

import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.data.cart.Cart;
import com.DataBaseProject.PCenter.repository.CartRepository;

public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;

    @Override
    public Cart create(Cart cart, User user){
        cart.setUser(user);
        return this.cartRepository.save(cart);
    }

    @Override
    public void update(Cart cart){this.cartRepository.save(cart);}
}
