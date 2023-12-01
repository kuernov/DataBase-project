package com.DataBaseProject.PCenter.controller;

import com.DataBaseProject.PCenter.data.Order;
import com.DataBaseProject.PCenter.data.OrderProduct;
import com.DataBaseProject.PCenter.data.OrderStatus;
import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.dto.OrderProductDto;
import com.DataBaseProject.PCenter.exception.ResourceNotFoundException;
import com.DataBaseProject.PCenter.service.order.OrderProductService;
import com.DataBaseProject.PCenter.service.order.OrderService;
import com.DataBaseProject.PCenter.service.ProductService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    ProductService productService;
    OrderService orderService;
    OrderProductService orderProductService;

    @GetMapping("/list")
    public @NotNull Iterable<Order> list(){
        return this.orderService.getAllOrders();
    }

    @PostMapping("/create")
    public ResponseEntity<Order> create(@RequestBody OrderForm form, User user){
        List<OrderProductDto> formDtos = form.getProductOrders();
        validateProductExistence(formDtos);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        this.orderService.create(order, user);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos){
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

    private void validateProductExistence(List<OrderProductDto> orderProducts){
        List<OrderProductDto> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(productService.getProductById(op
                        .getProduct().getId())))
                .toList();
        if(!list.isEmpty()){
            throw new ResourceNotFoundException("Product not found");
        }
    }
    @Setter
    @Getter
    public static class OrderForm {
        private List<OrderProductDto> productOrders;
    }
}
