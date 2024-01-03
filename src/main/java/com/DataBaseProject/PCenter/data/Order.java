package com.DataBaseProject.PCenter.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-ddThh:mm:ssZ") // ISO8601
    private Instant dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Status status; // To raczej jako enum

    public enum Status { CREATED, DELIVERED }

    @JsonManagedReference
    @OneToMany(mappedBy = "pk.order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @Valid
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Transient
    public BigDecimal getTotalOrderPrice(){
        BigDecimal sum = new BigDecimal("0");
        List<OrderProduct> orderProducts = getOrderProducts();
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
