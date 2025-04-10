package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.dto.ShoppingCartItemDTO;
import com.example.laptopstorebackend.dto.YourOrderItemDTO;
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
    public List<YourOrderItemDTO> createOrderItem(Long shoppingCartId, Long yourOrderId) {
        List<ShoppingCartItemDTO> items = shoppingCartItemServiceImpl.findAll(shoppingCartId);
        for (ShoppingCartItemDTO itemDTO: items) {
            if (itemDTO.getProductQuantity() > itemDTO.getProductDTO().getStock()) {
                throw new ResourceNotFoundException("You just can buy max of "
                        + itemDTO.getProductDTO().getStock()
                        + " "
                        + itemDTO.getProductDTO().getProductName());
            }
        }

        for (ShoppingCartItemDTO itemDTO: items) {
            YourOrderItem yourOrderItem = YourOrderItem.builder()
                    .orderId(yourOrderId)
                    .productId(itemDTO.getProductDTO().getProductId())
                    .quantity(itemDTO.getProductQuantity())
                    .build();
            yourOrderItemRepository.save(yourOrderItem);

            Integer newStock = itemDTO.getProductDTO().getStock() - itemDTO.getProductQuantity();
            productServiceImpl.updateStock(newStock, itemDTO.getProductDTO().getProductName());
        }
        shoppingCartItemServiceImpl.deleteAllItem(shoppingCartId);

        return items.stream().map(this::buildYourOrderItemDTO).toList();
    }

    /**
     * Helper function to build YourOrderItemDTO
     * @param shoppingCartItemDTO The item in order
     * @return YourOrderItemDTO consit of: ProductDTO and quantity
     */
    private YourOrderItemDTO buildYourOrderItemDTO(ShoppingCartItemDTO shoppingCartItemDTO) {
        return YourOrderItemDTO.builder()
                .productDTO(shoppingCartItemDTO.getProductDTO())
                .productQuantity(shoppingCartItemDTO.getProductQuantity())
                .build();
    }

    /**
     *
     * @param yourOrderItem
     * @return
     */
    private YourOrderItemDTO buildYourOrderItemDTO(YourOrderItem yourOrderItem) {
        return YourOrderItemDTO.builder()
                .productDTO(productServiceImpl.findById(yourOrderItem.getProductId()))
                .productQuantity(yourOrderItem.getQuantity())
                .build();
    }

}
