package com.DataBaseProject.PCenter.controller;

import com.DataBaseProject.PCenter.service.ProductService;
import com.DataBaseProject.PCenter.service.cart.CartProductServiceImpl;
import com.DataBaseProject.PCenter.service.cart.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    ProductService productService;
    CartService cartService;
    CartProductServiceImpl cartProductService;


}
