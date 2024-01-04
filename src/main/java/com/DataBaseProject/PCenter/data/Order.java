package com.DataBaseProject.PCenter.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
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
