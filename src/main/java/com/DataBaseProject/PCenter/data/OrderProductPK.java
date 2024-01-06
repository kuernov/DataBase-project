package com.DataBaseProject.PCenter.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class OrderProductPK implements Serializable {
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
