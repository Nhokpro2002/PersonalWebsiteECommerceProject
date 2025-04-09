
package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.constant.YourOrderStatus;
import com.example.laptopstorebackend.dto.UserDTO;
import com.example.laptopstorebackend.dto.YourOrderDTO;
import com.example.laptopstorebackend.dto.YourOrderItemDTO;
import com.example.laptopstorebackend.entity.YourOrder;
import com.example.laptopstorebackend.exception.ResourceNotFoundException;
import com.example.laptopstorebackend.repository.YourOrderRepository;
import com.example.laptopstorebackend.service.interfaces.IYourOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YourOrderServiceImpl implements IYourOrderService {

    private final YourOrderItemServiceImpl yourOrderItemServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final YourOrderRepository yourOrderRepository;


    /**
     *
     * @param shoppingCartId Your Shopping Cart Id
     * @return
     */
    @Override
    public YourOrderDTO createYourOrder(Long shoppingCartId) {
        UserDTO userDTO = userServiceImpl.findById(shoppingCartId);
        List<YourOrderItemDTO> items = yourOrderItemServiceImpl.createOrderItem(shoppingCartId);
        YourOrder yourOrder =  YourOrder.builder()
                .saleAgent("NMT Computer")
                .customerAddress(userDTO.getAddress())
                .customerName(userDTO.getUserName())
                .totalPrice(calculusTotalPrice(shoppingCartId))
                .yourOrderStatus(YourOrderStatus.PENDING)
                .build();

        return convertFromEntity(yourOrder);

    }

    /**
     *
     * @param yourOrderId
     * @param newStatus
     * @return
     */
    @Override
    public YourOrderDTO changeYourOrderStatus(Long yourOrderId, YourOrderStatus newStatus) {
        YourOrder yourOrder = yourOrderRepository.findById(yourOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        yourOrder.setYourOrderStatus(newStatus);
        yourOrderRepository.save(yourOrder);
        return convertFromEntity(yourOrder);
    }

    /**
     *
     * @param yourOrderId
     * @return
     */
    @Override
    public YourOrderDTO getYourOrder(Long yourOrderId) {
        YourOrder yourOrder = yourOrderRepository.findById(yourOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return convertFromEntity(yourOrder);
    }

    /**
     *
     * @param shoppingCartId
     * @return
     */
    private BigDecimal calculusTotalPrice(Long shoppingCartId) {
        List<YourOrderItemDTO> items = yourOrderItemServiceImpl.createOrderItem(shoppingCartId);
        BigDecimal totalPrice = items.stream()
                .map(item -> item.getProductDTO()
                        .getSellingPrice()
                        .multiply(BigDecimal.valueOf(item.getProductQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPrice;
    }

    /**
     *
     * @param yourOrder
     * @return
     */
    private YourOrderDTO convertFromEntity(YourOrder yourOrder, Long shoppingCartId) {
        return YourOrderDTO.builder()
                .yourOrderStatus(yourOrder.getYourOrderStatus())
                .totalPrice(yourOrder.getTotalPrice())
                .items(yourOrderItemServiceImpl.createOrderItem(shoppingCartId))
                .customerName(yourOrder.getCustomerName())
                .customerAddress(yourOrder.getCustomerAddress())
                .agentName(yourOrder.getSaleAgent())
                .build();
    }
}

