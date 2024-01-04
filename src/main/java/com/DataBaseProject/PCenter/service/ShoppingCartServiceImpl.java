package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.CartItem;
import com.DataBaseProject.PCenter.data.Product;
import com.DataBaseProject.PCenter.data.ShoppingCart;
import com.DataBaseProject.PCenter.data.User;
import com.DataBaseProject.PCenter.dto.CartItemDto;
import com.DataBaseProject.PCenter.dto.ProductDto;
import com.DataBaseProject.PCenter.dto.ShoppingCartDto;
import com.DataBaseProject.PCenter.repository.CartItemRepository;
import com.DataBaseProject.PCenter.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService{
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;

    @Override
    @Transactional
    public ShoppingCart addItemToCart(ProductDto productDto, int quantity, String email){
        User user = userService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
        ShoppingCart shoppingCart = user.getCart();
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
        }
        Set<CartItem> cartItemSet = shoppingCart.getCartItems();
        CartItem cartItem = find(cartItemSet, productDto.getId());
        Product product = transfer(productDto);
        if (cartItemSet == null) {
            cartItemSet = new HashSet<>();
        }
        if (cartItem==null){
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(shoppingCart);
            cartItem.setQuantity(quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
        }
        cartItemRepository.save(cartItem);
        shoppingCart.setCartItems(cartItemSet);
        shoppingCart.setTotalPrice(calculateTotalSum(shoppingCart.getCartItems()));
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart updateCart(ProductDto productDto, int quantity, String email) {
        User user = userService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));;
        ShoppingCart shoppingCart = user.getCart();
        Set<CartItem> cartItemSet = shoppingCart.getCartItems();
        CartItem cartItem = find(cartItemSet,  productDto.getId());
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        shoppingCart.setCartItems(cartItemSet);
        shoppingCart.setTotalPrice(calculateTotalSum(cartItemSet));

        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart removeItemFromCart(ProductDto productDto, String email){
        User user = userService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));;
        ShoppingCart shoppingCart = user.getCart();
        Set<CartItem> cartItemSet = shoppingCart.getCartItems();
        CartItem cartItem = find(cartItemSet, productDto.getId());
        cartItemSet.remove(cartItem);
        cartItemRepository.delete(cartItem);
        shoppingCart.setCartItems(cartItemSet);
        shoppingCart.setTotalPrice(calculateTotalSum(cartItemSet));
        return shoppingCartRepository.save(shoppingCart);
    }


    private CartItemDto findInDto(ShoppingCartDto shoppingCart, long productId) {
        if (shoppingCart == null) {
            return null;
        }
        CartItemDto cartItem = null;
        for (CartItemDto item : shoppingCart.getCartItems()) {
            if (item.getProduct().getId() == productId) {
                cartItem = item;
            }
        }
        return cartItem;
    }

    private BigDecimal calculateTotalSum(Set<CartItem> cartItems) {
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for (CartItem item : cartItems)
            totalPrice = totalPrice.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        return totalPrice;
    }

    private BigDecimal calculateTotalSumDto(Set<CartItemDto> cartItems) {
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for (CartItemDto item : cartItems)
            totalPrice = totalPrice.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        return totalPrice;
    }


    private CartItem find(Set<CartItem> cartItems, Integer productId) {
        if (cartItems == null){
            return null;
        }
        CartItem cartItem= null;
        for (CartItem item : cartItems){
            if (item.getProduct().getId().equals(productId)) {
                cartItem = item;
            }
        }
        return cartItem;
    }
    private Product transfer(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setCurrentQuantity(productDto.getCurrentQuantity());
        return product;
    }
}
