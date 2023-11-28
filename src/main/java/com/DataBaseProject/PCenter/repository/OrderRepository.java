package com.DataBaseProject.PCenter.repository;

import com.DataBaseProject.PCenter.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
