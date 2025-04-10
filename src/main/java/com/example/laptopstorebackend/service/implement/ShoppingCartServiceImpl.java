
package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.dto.ShoppingCartDTO;
import com.example.laptopstorebackend.dto.ShoppingCartItemDTO;
import com.example.laptopstorebackend.entity.ShoppingCart;
import com.example.laptopstorebackend.exception.ResourceNotFoundException;
import com.example.laptopstorebackend.repository.ShoppingCartRepository;
import com.example.laptopstorebackend.service.interfaces.IShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements IShoppingCartService {

    private final ShoppingCartItemServiceImpl shoppingCartItemServiceImpl;
    private final ShoppingCartRepository shoppingCartRepository;

    /**
     *
     * @return
     */
    public ShoppingCart createShoppingCart(Long userId) {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .customerId(userId)
                .totalPrice(null)
                .build();
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;

    }
    @Override
    public ShoppingCartDTO getAllItem(Long shoppingCartId) {
        return buildShoppingCartDTO(shoppingCartId);
    }

    /**
     *
     * @param shoppingCartId
     * @param productId
     * @param quantity
     * @return
     */
    public ShoppingCartDTO createShoppingCartItem(Long shoppingCartId, Long productId, Integer quantity) {
        shoppingCartItemServiceImpl.createShoppingCartItem(shoppingCartId, productId, quantity);
        return buildShoppingCartDTO(shoppingCartId);
    }

    /**
     * @param shoppingCartId
     * @param productId
     * @return
     */

    @Override
    public ShoppingCartDTO addItem(Long shoppingCartId, Long productId) {
        shoppingCartItemServiceImpl.addItem(shoppingCartId, productId);
        return buildShoppingCartDTO(shoppingCartId);
    }


    /**
     * @param shoppingCartId
     * @param productId
     * @return
     */
    @Override
    public ShoppingCartDTO reduceItem(Long shoppingCartId, Long productId) {
        shoppingCartItemServiceImpl.reduceItem(shoppingCartId, productId);
        return buildShoppingCartDTO(shoppingCartId);
    }

    /**
     * Helper method to build ShoppingCartDTO from shoppingCartId.
     */
    private ShoppingCartDTO buildShoppingCartDTO(Long shoppingCartId) {
        List<ShoppingCartItemDTO> itemDTOS = shoppingCartItemServiceImpl.findAll(shoppingCartId);

        BigDecimal totalPrice = itemDTOS.stream()
                .map(item -> item.getProductDTO()
                        .getSellingPrice()
                        .multiply(BigDecimal.valueOf(item.getProductQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        ShoppingCart shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping Cart not found"));
        shoppingCart.setTotalPrice(totalPrice);
        shoppingCartRepository.save(shoppingCart);
        return ShoppingCartDTO.builder()
                .items(itemDTOS)
                .totalPrice(totalPrice)
                .build();
    }

}


