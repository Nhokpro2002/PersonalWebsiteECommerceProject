package com.newwave.bu3internecommerce.service;

import com.newwave.bu3internecommerce.dto.LaptopDTO;
import com.newwave.bu3internecommerce.model.Cart;
import com.newwave.bu3internecommerce.model.Laptop;
import com.newwave.bu3internecommerce.repository.CartRepository;
import com.newwave.bu3internecommerce.repository.LaptopRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class CartService {

    @Autowired
    private LaptopRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    /**
     * Add product to Shopping Cart
     * @param cartId cart is retrieved by cartId
     * @param productId product be added to Shopping Cart
     * @return
     */
    public Cart addProductToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        Laptop product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cart.addProduct(product);
        return cartRepository.save(cart); // save Shopping Cart in database
    }

    /**
     * Get all products in Shopping Cart
     * @param cartId cart is retrieved by cartId
     * @return all products information in Shopping Cart
     */
    public List<LaptopDTO> getAll(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        // return all products in Shopping Cart
        return cart.getProducts().stream().map(LaptopDTO::new).collect(Collectors.toList());
    }

    /**
     * remote the product from Shopping Cart
     * @param cartId cart is retrieved by cartId
     * @param productId product is got by productId
     * @return Shopping Cart after remove product
     */
    public Cart removeProductInCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        Laptop product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cart.removeProduct(product);
        return cartRepository.save(cart);
    }

    /**
     * Get total price of Shopping Cart
     * @param cartId cart is retrieved by cartId
     * @return total price of all products in Shopping Cart
     */
    public double getTotalPrice(Long cartId) {
       List<LaptopDTO> productDTOList = getAll(cartId);
       double totalPrice = 0;
       for (LaptopDTO productDTO: productDTOList) {
           totalPrice += productDTO.getPrice();
       }
       return totalPrice;
    }


    /**
     * Delete all products in Shopping Cart
     * @param cartId cart is retrieved by cartId
     * @return empty Shopping Cart
     */
    public Cart removeAll(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        //productRepository.deleteAllById();
        productRepository.removeAllProductsFromCart(cartId);
        return cart;
    }
}

