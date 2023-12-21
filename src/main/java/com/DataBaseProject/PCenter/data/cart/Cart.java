package com.DataBaseProject.PCenter.data.cart;

import com.DataBaseProject.PCenter.data.OrderProduct;
import com.DataBaseProject.PCenter.data.OrderProductPK;
import com.DataBaseProject.PCenter.data.Product;
import com.DataBaseProject.PCenter.data.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "pk.user")
    @Valid
    private List<CartProduct> cartProducts = new ArrayList<>();

    public Cart(User user){
        this.user = user;
    }
    @Transient
    public BigDecimal getTotalCartPrice(){
        BigDecimal sum = new BigDecimal("0");
        List<CartProduct> cartProducts = getCartProducts();
        for (CartProduct cp : cartProducts){
           sum = sum.add(cp.getTotalPrice());
        }
        return sum;
    }
    @Transient //does not have to be stored in database (calculated data)
    public int getNumberOfProducts() {
        return this.cartProducts.size();
    }
}


