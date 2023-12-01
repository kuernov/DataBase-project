package com.DataBaseProject.PCenter.service.order;

import com.DataBaseProject.PCenter.data.Order;
import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    @Override
    public Iterable<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }
    @Override
    public Order create(Order order, User user){
        order.setDateCreated(LocalDate.now());
        order.setUser(user);
        return this.orderRepository.save(order);
    }
    @Override
    public void update(Order order){
        this.orderRepository.save(order);
    }
}
