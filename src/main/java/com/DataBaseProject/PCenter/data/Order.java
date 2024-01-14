package com.DataBaseProject.PCenter.data;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat//(pattern = "yyyy-MM-dd'T'HH:mm:ssZ") // ISO8601
    private Instant dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    private Status status; // To raczej jako enum

    public enum Status { CREATED, DELIVERED }

    @JsonManagedReference
    @OneToMany(mappedBy = "pk.order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, orphanRemoval = true)
    private Set<OrderProduct> orderProducts;

    @Transient
    public BigDecimal getTotalOrderPrice(){
        BigDecimal sum = new BigDecimal("0");
        Set<OrderProduct> orderProducts = getOrderProducts();
        for (OrderProduct op : orderProducts){
            sum = sum.add(op.getTotalPrice());
        }
        return sum;
    }
    @Transient //does not have to be stored in database (calculated data)
    public int getNumberOfProducts() {
        return this.orderProducts.size();
    }

}
