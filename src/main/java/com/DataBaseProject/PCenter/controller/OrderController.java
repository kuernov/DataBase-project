package com.DataBaseProject.PCenter.controller;

import com.DataBaseProject.PCenter.data.Order;
import com.DataBaseProject.PCenter.data.OrderProduct;
import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.dto.OrderProductDto;
import com.DataBaseProject.PCenter.exception.ResourceNotFoundException;
import com.DataBaseProject.PCenter.service.ProductService;
import com.DataBaseProject.PCenter.service.order.OrderProductService;
import com.DataBaseProject.PCenter.service.order.OrderService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;


@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderProductService orderProductService;

    @GetMapping("/list")
    public @NotNull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }

    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody OrderForm form, User user) {
        List<OrderProductDto> formDtos = form.getProductOrders();
        validateProductExistence(formDtos);
        Order order = new Order();
        order.setStatus(Order.Status.CREATED);
        this.orderService.create(order, user);

        Set<OrderProduct> orderProducts = new HashSet<>();
        for (OrderProductDto dto : formDtos) {
            orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProductById(dto
                    .getProduct().
                    getId()), dto.getQuantity())));
        }

        order.setOrderProducts(orderProducts);
        this.orderService.update(order);
        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();

        // Utworzenie nagłówków z informacją o lokalizacji nowo utworzonego zasobu.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        // Zwrócenie odpowiedzi HTTP 201 CREATED wraz z nowo utworzonym zamówieniem.
        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);

    }

    private void validateProductExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(productService.getProductById(op
                        .getProduct().getId())))
                .toList();
        if (!list.isEmpty()) {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    @Setter
    @Getter
    public static class OrderForm {
        private List<OrderProductDto> productOrders;
    }
}
