package com.DataBaseProject.PCenter.controller;


import com.DataBaseProject.PCenter.data.ShoppingCart;
import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.dto.ShoppingCartDto;
import com.DataBaseProject.PCenter.dto.ProductDto;
import com.DataBaseProject.PCenter.service.ProductService;
import com.DataBaseProject.PCenter.service.ShoppingCartService;
import com.DataBaseProject.PCenter.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/cart")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ShoppingCart> getShoppingCart(Model model, Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String username = principal.getName();
        User user = userService.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        ShoppingCart shoppingCart = user.getCart();


        if (shoppingCart != null) {
            return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add-to-cart")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ShoppingCart> addItemToCart(@RequestParam("id") Integer id, @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity, Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String email= principal.getName();
        ProductDto productDto = productService.getProductDto(productService.getProductById(id));
        if (productDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ShoppingCart shoppingCart = shoppingCartService.addItemToCart(productDto, quantity, email);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ShoppingCart> updateCart(@RequestParam("id") Integer id,
                                                          @RequestParam("quantity") int quantity,
                                                          Principal principal) {
            if (principal == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            String email = principal.getName();
            ProductDto productDto = productService.getProductDto(productService.getProductById(id));

            if (productDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            ShoppingCart shoppingCart = shoppingCartService.updateCart(productDto, quantity, email);
            return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ShoppingCart> deleteItem(@PathVariable("id") Integer id,
                                                          Principal principal) {

            if (principal == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            String email = principal.getName();
            ProductDto productDto = productService.getProductDto(productService.getProductById(id));
            if (productDto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            ShoppingCart shoppingCart = shoppingCartService.removeItemFromCart(productDto, email);
            return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }
}
