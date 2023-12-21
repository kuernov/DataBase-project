package com.DataBaseProject.PCenter.data.cart;

import com.DataBaseProject.PCenter.data.OrderProductPK;
import com.DataBaseProject.PCenter.data.Product;
import com.DataBaseProject.PCenter.data.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class CartProduct {
    @EmbeddedId
    private CartProductPK pk;

    @Column(nullable = false)
    private Integer quantity;

    public CartProduct(Product product, User user, Integer quantity){
        pk = new CartProductPK();
        pk.setProduct(product);
        pk.setUser(user);
        this.quantity = quantity;
    }
    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }
    @Transient
    public BigDecimal getTotalPrice() {
        return getProduct().getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }
}
