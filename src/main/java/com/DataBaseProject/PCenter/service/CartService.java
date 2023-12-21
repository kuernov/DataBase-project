package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.OrderProduct;
import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.data.cart.Cart;
import com.DataBaseProject.PCenter.data.cart.CartProduct;
import com.DataBaseProject.PCenter.dto.CartProductDto;
import com.DataBaseProject.PCenter.dto.OrderProductDto;
import com.DataBaseProject.PCenter.exception.ResourceNotFoundException;
import com.DataBaseProject.PCenter.repository.CartRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartProductService cartProductService;
    @Autowired
    private ProductService productService;
    public Cart getCart(@RequestBody CartForm form, User user){
        List<CartProductDto> formDtos = form.getProductsInCart();
        List<CartProduct> cartProducts = new ArrayList<>();
        for(CartProductDto dto : formDtos){
            
            cartProducts.add(cartProductService.addCartProduct(new CartProduct(productService.getProductById(dto.getProduct().getId()), user, dto.getQuantity())));
        }
        Cart cart = getCartById(user.getId());
        cart.setCartProducts(cartProducts);
        return cart;
    }

    private Cart getCartById(Integer id) {
        return cartRepository.findByUserId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart no found with user id: " + id));
    }

    @Setter
    @Getter
    public static class CartForm {
        private List<CartProductDto> productsInCart;
    }
}
