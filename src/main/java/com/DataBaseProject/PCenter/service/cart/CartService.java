package com.DataBaseProject.PCenter.service.cart;

import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.data.cart.Cart;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CartService {
    Cart create(@NotNull(message = "The order cannot be null.") Cart cart, User user);
    void update(@NotNull(message = "The order cannot be null.") Cart cart);
}
