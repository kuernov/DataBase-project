package com.DataBaseProject.PCenter.service.cart;

import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.data.cart.Cart;
import jakarta.validation.constraints.NotNull;

public interface CartService {
    Cart create(@NotNull(message = "The order cannot be null.") Cart cart, User user);
    void update(@NotNull(message = "The order cannot be null.") Cart cart);
}
