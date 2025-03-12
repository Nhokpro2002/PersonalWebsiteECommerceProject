package com.newwave.bu3internecommerce.service;


import com.newwave.bu3internecommerce.dto.response.CartDTO;
import com.newwave.bu3internecommerce.dto.response.CartItemDTO;
import com.newwave.bu3internecommerce.exception.AppException;
import com.newwave.bu3internecommerce.exception.ErrorCode;
import com.newwave.bu3internecommerce.model.shoppingcart.Cart;
import com.newwave.bu3internecommerce.model.shoppingcart.CartItem;
import com.newwave.bu3internecommerce.model.Laptop;
import com.newwave.bu3internecommerce.repository.CartItemRepository;
import com.newwave.bu3internecommerce.repository.CartRepository;
import com.newwave.bu3internecommerce.repository.LaptopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;

    private final CartRepository cartRepository;

    private final LaptopRepository laptopRepository;

    public CartService(CartItemRepository cartItemRepository,
                       CartRepository cartRepository,
                       LaptopRepository laptopRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.laptopRepository = laptopRepository;
    }


    /**
     * Add number laptop to Shopping Cart
     * @param cartId The cartId of Shopping Cart
     * @param laptopId The laptopId of Laptop
     * @param quantity The laptop quantity be added
     */
    // FIXME: Shopping Cart can save laptop quantities more than stock in inventory
    // FIXME: Example: in inventory have 10 laptop, Shopping Cart add 7, after add 5, so is 12 but Cart can save them
    public void addToCart(Long cartId, Long laptopId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_EXISTED));
        Laptop laptop = laptopRepository.findById(laptopId)
                .orElseThrow(() -> new AppException(ErrorCode.LAPTOP_NOT_EXISTED));

        // check laptop is existing in Shopping Cart
        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndLaptop(cart, laptop);

        CartItem cartItem;
        if (quantity <= laptop.getStock()) {
            if (existingCartItem.isPresent()) {
                // if laptop is existing, update quantity in Shopping Cart
                cartItem = existingCartItem.get();
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                // if not, create new
                cartItem = new CartItem(cart, laptop, quantity);
            }
            cartItemRepository.save(cartItem);
        } else {
            throw new RuntimeException("laptop quantity in inventory not enough");
        }


    }

    /**
     * Remove number laptop to Shopping Cart
     * @param cartId The cartId of Shopping Cart
     * @param laptopId The laptopId of Laptop
     * @param quantity The laptop quantity be removed
     */
    public void removeFromCart(Long cartId, Long laptopId, int quantity) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_EXISTED));
        Laptop laptop = laptopRepository.findById(laptopId)
                .orElseThrow(() -> new AppException(ErrorCode.LAPTOP_NOT_EXISTED));
        CartItem cartItem = cartItemRepository.findByCartAndLaptop(cart, laptop)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_EXISTED));

        if (cartItem.getQuantity() <= quantity) {
            cartItemRepository.delete(cartItem); // Remove item from cart if quantity reaches 0
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - quantity);
            cartItemRepository.save(cartItem); // Update the cart item with new quantity
        }

    }

    /**
     * Get CartDTO contain Cart details
     * @param cartId The cartId of Shopping Cart
     * @return information of Shopping Cart
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

        // Calculate total price using Stream
        double totalPrice = cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getPrice() * cartItem.getQuantity())
                .sum();

        return new CartDTO(cartId, cartItems, totalPrice);
    }


    /**
     * Get total price Shopping Cart
     * @param cartId The cartId of Shopping Cart
     * @return total price of Shopping Cart
     */
    @Transactional
    public double getCartTotalPrice(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return cart.getCartItems().stream()
                .mapToDouble(item -> item.getLaptop().getSellingPrice() * item.getQuantity())
                .sum();
    }

}
