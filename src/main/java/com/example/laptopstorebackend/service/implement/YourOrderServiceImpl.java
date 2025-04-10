
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
import java.time.LocalDate;
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
        /*UserDTO userDTO = userServiceImpl.findById(shoppingCartId);
        YourOrder yourOrder =  new YourOrder();
        yourOrder.setSaleAgent("NMT Computer");
        yourOrder.setCustomerId(shoppingCartId);
        yourOrder.setCustomerAddress(userDTO.getAddress());
        yourOrder.setCustomerName(userDTO.getUserName());
        yourOrder.setYourOrderStatus(YourOrderStatus.PENDING);
        yourOrder = yourOrderRepository.save(yourOrder);
        List<YourOrderItemDTO> items = yourOrderItemServiceImpl.createOrderItem(shoppingCartId, yourOrder.getYourOrderId());
        yourOrder.setTotalPrice(calculusTotalPrice(items));
        return convertFromEntity(yourOrder, items);*/
        UserDTO userDTO = userServiceImpl.findById(shoppingCartId);
        YourOrder yourOrder = (YourOrder.builder()
                .createAt(LocalDate.now())
                .yourOrderStatus(YourOrderStatus.PENDING)
                .customerAddress(userDTO.getAddress()))
                .customerId(userDTO.getUserId())
                .customerName(userDTO.getUserName())
                .saleAgent("NMT Computer")
                .build();
        yourOrder = yourOrderRepository.save(yourOrder);
        List<YourOrderItemDTO> items = yourOrderItemServiceImpl.createOrderItem(shoppingCartId, yourOrder.getYourOrderId());
        yourOrder.setTotalPrice(calculusTotalPrice(items));
        yourOrderRepository.save(yourOrder);
        return convertFromEntity(yourOrder, items);
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
        List<YourOrderItemDTO> items = yourOrderItemServiceImpl.findAll(yourOrderId);
        yourOrderRepository.save(yourOrder);
        return convertFromEntity(yourOrder, items);
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
        List<YourOrderItemDTO> items = yourOrderItemServiceImpl.findAll(yourOrderId);
        return convertFromEntity(yourOrder, items);
    }

    /**
     *
     * @param items
     * @return
     */
    private BigDecimal calculusTotalPrice(List<YourOrderItemDTO> items) {
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
    private YourOrderDTO convertFromEntity(YourOrder yourOrder, List<YourOrderItemDTO> items) {
        return YourOrderDTO.builder()
                .yourOrderId(yourOrder.getYourOrderId())
                .yourOrderStatus(yourOrder.getYourOrderStatus())
                .totalPrice(yourOrder.getTotalPrice())
                .items(items)
                .customerName(yourOrder.getCustomerName())
                .customerAddress(yourOrder.getCustomerAddress())
                .agentName(yourOrder.getSaleAgent())
                .build();
    }
}

