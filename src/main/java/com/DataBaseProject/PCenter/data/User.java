package com.DataBaseProject.PCenter.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Collection<RoleEnum> roles;
    enum RoleEnum {ADMIN, USER}
    @OneToOne(mappedBy = "user")
    private ShoppingCart cart;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User(){
        this.cart = new ShoppingCart();
        this.orders = new ArrayList<>();
    }
}
