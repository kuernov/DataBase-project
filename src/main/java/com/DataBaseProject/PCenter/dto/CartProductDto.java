package com.DataBaseProject.PCenter.dto;

import com.DataBaseProject.PCenter.data.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartProductDto {
    private Product product;
    private Integer quantity;
}
