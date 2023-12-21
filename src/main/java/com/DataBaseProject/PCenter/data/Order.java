package com.DataBaseProject.PCenter.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String status;

    @JsonManagedReference
    @OneToMany(mappedBy = "pk.order")
    @Valid
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Transient
    public BigDecimal getTotalOrderPrice(){
        BigDecimal sum = new BigDecimal("0");
        List<OrderProduct> orderProducts = getOrderProducts();
        for (OrderProduct op : orderProducts){
            sum=op.getTotalPrice();
        }
        return sum;
    }
    @Transient //does not have to be stored in database (calculated data)
    public int getNumberOfProducts() {
        return this.orderProducts.size();
    }

}
