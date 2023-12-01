package com.DataBaseProject.PCenter.service.cart;

import com.DataBaseProject.PCenter.data.cart.CartProduct;
import com.DataBaseProject.PCenter.repository.CartProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartProductServiceImpl {
    private CartProductRepository cartProductRepository;

    public CartProduct create(CartProduct cartProduct){
        return this.cartProductRepository.save(cartProduct);
    }
}
