package com.DataBaseProject.PCenter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private @NotNull String name;
    private @NotNull BigDecimal price;
    private @NotNull String description;
    private @NotNull Integer categoryId;

    public ProductDto(String name, BigDecimal price, String description, Integer categoryId){
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }
}
