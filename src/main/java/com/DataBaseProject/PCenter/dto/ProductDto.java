package com.DataBaseProject.PCenter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private @NotNull String name;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer categoryId;
}
