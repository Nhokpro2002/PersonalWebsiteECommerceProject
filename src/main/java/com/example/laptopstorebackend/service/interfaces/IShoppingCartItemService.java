package com.example.laptopstorebackend.service.interfaces;

import com.example.laptopstorebackend.dto.ShoppingCartItemDTO;
import java.util.List;

public interface IShoppingCartItemService {

    /**
     * Find all items in the shopping cart.
     * @param shoppingCartId ID of the shopping cart.
     * @return List of items in the shopping cart.
     */
    List<ShoppingCartItemDTO> findAll(Long shoppingCartId);

    /**
     * Add an item to the shopping cart.
     * @param shoppingCartId ID of the shopping cart.
     * @param productId ID of the product.
     * @return Added shopping cart item.
     */
    ShoppingCartItemDTO createShoppingCartItem(Long shoppingCartId, Long productId, Integer quantity);

    /**
     * Reduce an item in the shopping cart.
     * @param shoppingCartId ID of the shopping cart.
     * @param productId ID of the product.
     * @return Shopping cart item with reduced quantity.
     */
    ShoppingCartItemDTO reduceItem(Long shoppingCartId, Long productId);

    /**
     * Add an item in the shopping cart.
     * @param shoppingCartId ID of the shopping cart.
     * @param productId ID of the product.
     * @return Shopping cart item with added quantity.
     */
    ShoppingCartItemDTO addItem(Long shoppingCartId, Long productId);

    /**
     * Delete an item from the shopping cart.
     * @param shoppingCartId ID of the shopping cart.
     * @param productId ID of the product.
     * @return Deleted shopping cart item.
     */
    ShoppingCartItemDTO deleteItem(Long shoppingCartId, Long productId);

    void deleteAllItem(Long shoppingCartId);
}
