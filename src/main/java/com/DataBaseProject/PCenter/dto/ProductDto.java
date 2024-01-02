package com.DataBaseProject.PCenter.dto;

import com.DataBaseProject.PCenter.data.Category;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private @NotNull String name;
    private @NotNull BigDecimal price;
    private @NotNull String description;
    private @NotNull Category category;
    private int currentQuantity;

}
