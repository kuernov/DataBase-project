package com.DataBaseProject.PCenter.service.order;

import com.DataBaseProject.PCenter.data.Order;
import com.DataBaseProject.PCenter.data.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OrderService {
    @NotNull Iterable<Order> getAllOrders();
    Order create(@NotNull(message = "The order cannot be null.") @Valid Order order, User user);
    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);
}
