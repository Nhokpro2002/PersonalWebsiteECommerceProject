package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.dto.ShoppingCartItemDTO;
import com.example.laptopstorebackend.dto.YourOrderItemDTO;
import com.example.laptopstorebackend.entity.Product;
import com.example.laptopstorebackend.entity.YourOrderItem;
import com.example.laptopstorebackend.exception.ResourceNotFoundException;
import com.example.laptopstorebackend.repository.YourOrderItemRepository;
import com.example.laptopstorebackend.service.interfaces.IYourOrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YourOrderItemServiceImpl implements IYourOrderItemService {
    private final YourOrderItemRepository yourOrderItemRepository;
    private final ProductServiceImpl productServiceImpl;
    private final ShoppingCartItemServiceImpl shoppingCartItemServiceImpl;

    /**
     * Get list item dto in your order
     * @param yourOrderId
     * @return
     */
    @Override
    public List<YourOrderItemDTO> findAll(Long yourOrderId) {
        List<YourOrderItem> items = yourOrderItemRepository.findAllByOrderId(yourOrderId);
        return items.stream().map(this::buildYourOrderItemDTO).toList();
    }

    /**
     * create order item from shopping cart
     * @param shoppingCartId
     * @return
     */
    @Override
    @Transactional
    public List<YourOrderItemDTO> createOrderItem(Long shoppingCartId) {
        List<ShoppingCartItemDTO> items = shoppingCartItemServiceImpl.findAll(shoppingCartId);
        boolean isCreatable = true;
        for (ShoppingCartItemDTO itemDTO: items) {
            if (itemDTO.getProductQuantity() > itemDTO.getProductDTO().getStock()) {
                isCreatable = false;
                throw new ResourceNotFoundException("You just can buy max of "
                        + itemDTO.getProductDTO().getStock()
                        + " "
                        + itemDTO.getProductDTO().getProductName());
            }

        }

        if (isCreatable) {
            for (ShoppingCartItemDTO itemDTO: items) {
                Integer newStock = itemDTO.getProductDTO().getStock() - itemDTO.getProductQuantity();
                productServiceImpl.updateStock(newStock, itemDTO.getProductDTO().getProductName());
            }
            shoppingCartItemServiceImpl.deleteAllItem(shoppingCartId);
        }
        return items.stream().map(this::buildYourOrderItemDTO).toList();
    }

    /**
     * Helper function to build YourOrderItemDTO
     * @param yourOrderItem The item in order
     * @return YourOrderItemDTO consit of: ProductDTO and quantity
     */
    private YourOrderItemDTO buildYourOrderItemDTO(YourOrderItem yourOrderItem) {
        return YourOrderItemDTO.builder()
                .productDTO(productServiceImpl.findById(yourOrderItem.getProductId()))
                .productQuantity(yourOrderItem.getQuantity())
                .build();
    }

    /**
     * Helper function to build Shopping Cart Item
     * @param shoppingCartItemDTO
     * @return
     */
    private YourOrderItemDTO buildYourOrderItemDTO(ShoppingCartItemDTO shoppingCartItemDTO) {
        return YourOrderItemDTO.builder()
                .productDTO(shoppingCartItemDTO.getProductDTO())
                .productQuantity(shoppingCartItemDTO.getProductQuantity())
                .build();
    }

    /*public void deleteAllItem(Long shoppingCartId) {
        shoppingCartItemServiceImpl.deleteAllItem(shoppingCartId);
    }*/
}
