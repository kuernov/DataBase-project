package com.DataBaseProject.PCenter.dto;

import com.DataBaseProject.PCenter.data.Subcategory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Integer id;
    private @NotNull String name;
    private @NotNull BigDecimal price;
    private @NotNull String description;
    private @NotNull String imageURL;
    private @NotNull CategoryDto category;
    private @NotNull Subcategory subcategory;
    private int currentQuantity;

}
