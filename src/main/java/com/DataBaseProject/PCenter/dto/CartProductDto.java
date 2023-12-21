package com.DataBaseProject.PCenter.dto;

import com.DataBaseProject.PCenter.data.Product;
import com.DataBaseProject.PCenter.data.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartProductDto {
    private Product product;
    private Integer quantity;
}
