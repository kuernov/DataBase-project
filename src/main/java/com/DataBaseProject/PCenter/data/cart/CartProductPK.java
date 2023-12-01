package com.DataBaseProject.PCenter.data.cart;

import com.DataBaseProject.PCenter.data.Order;
import com.DataBaseProject.PCenter.data.Product;
import com.DataBaseProject.PCenter.data.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class CartProductPK implements Serializable {
    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;
}
