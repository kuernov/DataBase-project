package com.DataBaseProject.PCenter.repository;

import com.DataBaseProject.PCenter.data.OrderProduct;
import com.DataBaseProject.PCenter.data.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK> {
}
