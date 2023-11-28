package com.DataBaseProject.PCenter.dto.cart;

import com.DataBaseProject.PCenter.data.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private Product product;

}
