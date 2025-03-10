package com.newwave.bu3internecommerce.service;


import com.newwave.bu3internecommerce.dto.CartDTO;
import com.newwave.bu3internecommerce.dto.CartItemDTO;
import com.newwave.bu3internecommerce.model.Cart;
import com.newwave.bu3internecommerce.model.CartItem;
import com.newwave.bu3internecommerce.model.Laptop;
import com.newwave.bu3internecommerce.repository.CartItemRepository;
import com.newwave.bu3internecommerce.repository.CartRepository;
import com.newwave.bu3internecommerce.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private LaptopRepository laptopRepository;


    /**
     * Add number laptop to Shopping Cart
     * @param cartId The Shopping Cart
     * @param laptopId The Shopping Cart
     * @param quantity The laptop quantity be added
     */
    public void addToCart(Long cartId, Long laptopId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        Laptop laptop = laptopRepository.findById(laptopId)
                .orElseThrow(() -> new RuntimeException("Laptop not found"));

        // check laptop is existing in Shopping Cart
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndLaptop(cart, laptop);

        if (existingCartItem.isPresent()) {
            // if laptop is existing, update quantity in Shopping Cart
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        } else {
            // if not, create new
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setLaptop(laptop);
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
    }

    /**
     *
     * @param cartId
     * @param laptopId
     * @param quantity
     */
    public void removeFromCart(Long cartId, Long laptopId, int quantity) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        Laptop laptop = laptopRepository.findById(laptopId)
                .orElseThrow(() -> new RuntimeException("Laptop not found"));


        CartItem cartItem = cartItemRepository.findByCartAndLaptop(cart, laptop)
                .orElseThrow(() -> new RuntimeException("Laptop not found in cart"));

        if (cartItem.getQuantity() <= quantity) {
            cartItemRepository.delete(cartItem); // Remove item from cart if quantity reaches 0
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity() - quantity);
            cartItemRepository.save(cartItem); // Update the cart item with new quantity
        }
    }


    /**
     *
     * @param cartId
     * @return
     */
    @Transactional
    public CartDTO getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItemDTO> cartItems = cart.getCartItems().stream()
                .map(item -> new CartItemDTO(
                        item.getLaptop().getId(),
                        item.getQuantity(),
                        item.getLaptop().getSellingPrice()
                ))
                .collect(Collectors.toList());

        double totalPrice = 0.0;
        for(CartItemDTO cartItemDTO: cartItems) {
            totalPrice += cartItemDTO.getPrice() * cartItemDTO.getQuantity();
        }

        return new CartDTO(cartId, cartItems, totalPrice);
    }

    /**
     *
     * @param cartId
     * @return
     */
    @Transactional
    public double getCartTotalPrice(Long cartId) {
        CartDTO cartDTO = getCartById(cartId);
        double totalPrice = 0;
        for(CartItemDTO cartItemDTO: cartDTO.getCartItems()) {
            totalPrice += cartItemDTO.getPrice() * cartItemDTO.getQuantity();
        }
        return totalPrice;
    }

}
