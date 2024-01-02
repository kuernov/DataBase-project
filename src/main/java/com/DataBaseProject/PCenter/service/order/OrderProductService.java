package com.DataBaseProject.PCenter.service.order;

import com.DataBaseProject.PCenter.data.OrderProduct;
import com.DataBaseProject.PCenter.data.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OrderProductService {
    OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct);
}
