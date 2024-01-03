package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.Order;
import com.DataBaseProject.PCenter.data.OrderProduct;
import com.DataBaseProject.PCenter.data.ShoppingCart;
import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.repository.OrderRepository;
import com.DataBaseProject.PCenter.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public void checkout(String userId) {
        User user = userRepository.findByEmail(userId);
        ShoppingCart cart = user.getCart();

        Order order = new Order();
// byc moze trzeba zapisac order przed tworzeniem orderProducts zeby dalo sie
        // ustawic refernecje
        List<OrderProduct> orderProducts = cart.getCartItems().stream()
                .map(cartItem -> new OrderProduct())
                .toList();
        // dodac dekrementowanie currentQty i wyjątek jeśli <0
        order.setOrderProducts(orderProducts);

        orderRepository.save(order);

    }

}
