package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.*;
import com.DataBaseProject.PCenter.exception.InsufficientStockException;
import com.DataBaseProject.PCenter.repository.OrderRepository;
import com.DataBaseProject.PCenter.repository.ProductRepository;
import com.DataBaseProject.PCenter.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void checkout(String userId) {
        User user = userRepository.findByEmail(userId).orElseThrow(() -> new UsernameNotFoundException(""));
        ShoppingCart cart = user.getCart();

        Order order = new Order();
//// byc moze trzeba zapisac order przed tworzeniem orderProducts zeby dalo sie
//        // ustawic refernecje
//        List<OrderProduct> orderProducts = cart.getCartItems().stream()
//                .map(cartItem -> new OrderProduct())
//                .toList();
//        // dodac dekrementowanie currentQty i wyjątek jeśli <0
//        order.setOrderProducts(orderProducts);



        order.setUser(user);

        List<OrderProduct> orderProducts = cart.getCartItems().stream()
                .map(cartItem -> {
                    Product product = cartItem.getProduct();
                    int quantity = cartItem.getQuantity();


                    if (product.getCurrentQuantity() < quantity) {
                        throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
                    }


                    product.setCurrentQuantity(product.getCurrentQuantity() - quantity);
                    productRepository.save(product);

                    // Utwórz obiekt OrderProduct
                    OrderProduct orderProduct = new OrderProduct(order ,product, quantity);
                    return orderProduct;
                })
                .toList();
        orderRepository.save(order);

    }

}
