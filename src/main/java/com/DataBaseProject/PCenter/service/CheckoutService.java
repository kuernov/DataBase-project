package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.*;
import com.DataBaseProject.PCenter.exception.InsufficientStockException;
import com.DataBaseProject.PCenter.repository.*;
import com.DataBaseProject.PCenter.service.order.OrderProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductService orderProductService;

    @Transactional
    public Order checkout(String userId) {
        User user = userRepository.findByEmail(userId).orElseThrow(() -> new UsernameNotFoundException(""));
        ShoppingCart cart = user.getCart();

        Order order = new Order();
        orderRepository.save(order);
        order.setUser(user);

        Set<OrderProduct> orderProducts = cart.getCartItems().stream()
                .map(cartItem -> {
                    Product product = cartItem.getProduct();
                    int quantity = cartItem.getQuantity();


                    if (product.getCurrentQuantity() < quantity) {
                        throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
                    }


                    product.setCurrentQuantity(product.getCurrentQuantity() - quantity);
                    productRepository.save(product);

                    return new OrderProduct(order, product,quantity);

                    //orderProductService.create(orderProduct);
                    //return orderProduct;
                })
                .collect(Collectors.toSet());
        order.setOrderProducts(orderProducts);
        order.setDateCreated(Instant.now());
        order.setStatus(Order.Status.CREATED);
        shoppingCartRepository.delete(cart);
        return orderRepository.save(order);

    }

}
