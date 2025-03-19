package com.newwave.bu3internecommerce.service;


import com.newwave.bu3internecommerce.dto.CartDTO;
import com.newwave.bu3internecommerce.entity.user.User;
import com.newwave.bu3internecommerce.exception.AppException;
import com.newwave.bu3internecommerce.exception.ErrorCode;
import com.newwave.bu3internecommerce.entity.cart.Cart;
import com.newwave.bu3internecommerce.entity.cart.CartItem;
import com.newwave.bu3internecommerce.entity.Laptop;
import com.newwave.bu3internecommerce.repository.CartItemRepository;
import com.newwave.bu3internecommerce.repository.CartRepository;
import com.newwave.bu3internecommerce.repository.LaptopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void addToCart(Long cartId, Long laptopId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_EXISTED));
        Laptop laptop = laptopRepository.findById(laptopId)
                .orElseThrow(() -> new AppException(ErrorCode.LAPTOP_NOT_EXISTED));

        cartItemRepository.findByCartAndLaptop(cart, laptop)
                .ifPresentOrElse(
                        cartItem -> {
                            int newQuantity = cartItem.getQuantity() + quantity;
                            if (newQuantity > laptop.getStock()) {
                                throw new RuntimeException("Not enough stock in inventory");
                            }
                            cartItem.setQuantity(newQuantity);
                            cartItemRepository.save(cartItem);
                        },
                        () -> {
                            if (quantity > laptop.getStock()) {
                                throw new RuntimeException("Not enough stock in inventory");
                            }
                            cartItemRepository.save(new CartItem(cart, laptop, quantity));
                        }
                );
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
        return CartDTO.mappingFromCart(cart);
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

    /**
     * Create Cart for new User, Set cartId is userId
     * @param newUser The new User
     * @return Result success/fail
     */
    public String createCart(User newUser) {
        Cart newCart = new Cart();
        newCart.setId(newUser.getId());
        cartRepository.save(newCart);
        return "new Shopping Cart for new User";
    }


}
