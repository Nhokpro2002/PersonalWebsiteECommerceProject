package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.dto.ProductDTO;
import com.example.laptopstorebackend.dto.ShoppingCartItemDTO;
import com.example.laptopstorebackend.entity.Product;
import com.example.laptopstorebackend.entity.ShoppingCartItem;
import com.example.laptopstorebackend.exception.ArgumentException;
import com.example.laptopstorebackend.exception.ResourceNotFoundException;
import com.example.laptopstorebackend.mapper.ProductConverter;
import com.example.laptopstorebackend.repository.ProductRepository;
import com.example.laptopstorebackend.repository.ShoppingCartItemRepository;
import com.example.laptopstorebackend.service.interfaces.IShoppingCartItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShoppingCartItemServiceImpl implements IShoppingCartItemService {

    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final ProductServiceImpl productServiceImpl;
    private final ProductRepository productRepository;

    /**
     * Retrieve all shopping cart items for a given shopping cart ID.
     *
     * @param shoppingCartId ID of the shopping cart to retrieve items from.
     * @return List of shopping cart items as DTOs.
     */
    @Override
    public List<ShoppingCartItemDTO> findAll(Long shoppingCartId) {
        List<ShoppingCartItem> listItem = shoppingCartItemRepository.findByShoppingCartId(shoppingCartId);
        return listItem.stream()
                .map((shoppingCartItem -> ShoppingCartItemDTO
                        .builder()
                        .productDTO(productServiceImpl.findById(shoppingCartItem.getProductId()))
                        .productQuantity(shoppingCartItem.getProductQuantity()).build()))
                .collect(Collectors.toList());
    }

    /**
     * Add a product to the shopping cart.
     *
     * @param shoppingCartId ID of the shopping cart where the product will be added.
     * @param productId      ID of the product to be added.
     * @return ShoppingCartItemDTO containing product details and updated quantity.
     */
    @Override
    public ShoppingCartItemDTO createShoppingCartItem(Long shoppingCartId, Long productId, Integer quantity) {
        Optional<ShoppingCartItem> shoppingCartItem = shoppingCartItemRepository
                .findByShoppingCartIdAndProductId(shoppingCartId, productId);

        if (shoppingCartItem.isPresent()) {
            ShoppingCartItem item = shoppingCartItem.get();
            if (item.getProductQuantity() + quantity <= findById(item.getProductId()).getStock()) {
                item.setProductQuantity(item.getProductQuantity() + quantity);
                shoppingCartItemRepository.save(item);
                ProductDTO productDTO = ProductConverter.convertFromEntity(findById(item.getProductId()));

                return buildShoppingCartItemDTO(productDTO, quantity);

            } else {
                throw new ArgumentException("You can only buy a maximum of products: "
                        + findById(item.getProductId()).getStock().toString());
                }
        } else {
            ShoppingCartItem item = ShoppingCartItem.builder()
                    .shoppingCartId(shoppingCartId)
                    .productId(productId)
                    .productQuantity(quantity)
                    .build();
            shoppingCartItemRepository.save(item);
            ProductDTO productDTO = ProductConverter.convertFromEntity(findById(item.getProductId()));

            return buildShoppingCartItemDTO(productDTO, quantity);
        }
    }

    /**
     * Reduce the quantity of a product in the shopping cart or remove it if the quantity reaches zero.
     *
     * @param shoppingCartId ID of the shopping cart where the product quantity needs to be reduced.
     * @param productId      ID of the product whose quantity needs to be reduced.
     * @return ShoppingCartItemDTO containing updated product details and quantity.
     */
    @Override
    public ShoppingCartItemDTO reduceItem(Long shoppingCartId, Long productId) {
        Optional<ShoppingCartItem> shoppingCartItem = shoppingCartItemRepository
                .findByShoppingCartIdAndProductId(shoppingCartId, productId);

        if (shoppingCartItem.isPresent()) {
            ShoppingCartItem item = shoppingCartItem.get();
            if (item.getProductQuantity() >= 1) {
                item.setProductQuantity(item.getProductQuantity() - 1);
                shoppingCartItemRepository.save(item);
            } else {
                shoppingCartItemRepository.delete(item);
            }

            ProductDTO productDTO = ProductConverter.convertFromEntity(findById(item.getProductId()));
            Integer quantity = item.getProductQuantity();

            return buildShoppingCartItemDTO(productDTO, quantity);
        }
        throw new ResourceNotFoundException("Product not found in Shopping Cart " + productId);
    }

    /**
     * @param shoppingCartId ID of the shopping cart.
     * @param productId      ID of the product.
     * @return ShoppingCartItemDTO
     */
    @Override
    public ShoppingCartItemDTO addItem(Long shoppingCartId, Long productId) {
        Optional<ShoppingCartItem> shoppingCartItem = shoppingCartItemRepository
                .findByShoppingCartIdAndProductId(shoppingCartId, productId);

        if (shoppingCartItem.isPresent()) {
            ShoppingCartItem item = shoppingCartItem.get();
            if (item.getProductQuantity() + 1 <= findById(productId).getStock()) {
                item.setProductQuantity(item.getProductQuantity() + 1);
                shoppingCartItemRepository.save(item);
            } else {
                throw new ArgumentException("You can only buy a maximum of products: "
                        + findById(productId).getStock().toString());
            }
            ProductDTO productDTO = ProductConverter.convertFromEntity(findById(item.getProductId()));
            Integer quantity = item.getProductQuantity();

            return buildShoppingCartItemDTO(productDTO, quantity);
        }

        throw new ResourceNotFoundException("Product not exist in Shopping Cart");
    }

    /**
     * @param shoppingCartId ID of the shopping cart.
     * @param productId      ID of the product.
     * @return ShoppingCartItemDTO
     */
    @Override
    public ShoppingCartItemDTO deleteItem(Long shoppingCartId, Long productId) {
        ShoppingCartItem item = shoppingCartItemRepository
                .deleteByShoppingCartIdAndProductId(shoppingCartId, productId);

        ProductDTO productDTO = ProductConverter.convertFromEntity(findById(item.getProductId()));
        Integer quantity = item.getProductQuantity();

        return buildShoppingCartItemDTO(productDTO, quantity);
    }

    /**
     * Find a product by its ID.
     * @param productId ID of the product to be retrieved.
     * @return Product entity if found, otherwise throws an exception.
     */
    private Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in inventory: " + productId));
    }

    /**
     * Helper function to build ShoppingCartItemDTO
     * @param productDTO The productDTO: name, sellingPrice, ...
     * @param quantity The product quantity
     * @return ShoppingCartItemDTO
     */
    private ShoppingCartItemDTO buildShoppingCartItemDTO(ProductDTO productDTO, Integer quantity) {
        return ShoppingCartItemDTO.builder()
                .productDTO(productDTO)
                .productQuantity(quantity)
                .build();
    }
}







