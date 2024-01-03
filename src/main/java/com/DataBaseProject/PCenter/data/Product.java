package com.DataBaseProject.PCenter.data;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // Indeksy w bazie danych
    private String name;
    private BigDecimal price;
    private String description;
    private String imageURL;
    private int currentQuantity; // decrement przy tworzeniu ordera, add to cart rzuca wyjątek jak za mało
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;


}
