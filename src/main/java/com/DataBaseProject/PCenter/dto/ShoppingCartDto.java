package com.DataBaseProject.PCenter.dto;

import com.DataBaseProject.PCenter.data.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {
    private Long id;

    private User user;

    private BigDecimal totalPrice;

    private Set<CartItemDto> cartItems;
}
