package com.newwave.bu3internecommerce.service;

import com.newwave.bu3internecommerce.dto.OrdersDTO;
import com.newwave.bu3internecommerce.exception.AppException;
import com.newwave.bu3internecommerce.exception.ErrorCode;
import com.newwave.bu3internecommerce.entity.Laptop;
import com.newwave.bu3internecommerce.entity.order.OrdersItem;
import com.newwave.bu3internecommerce.entity.order.OrderStatus;
import com.newwave.bu3internecommerce.entity.order.Orders;
import com.newwave.bu3internecommerce.entity.cart.Cart;
import com.newwave.bu3internecommerce.entity.cart.CartItem;
import com.newwave.bu3internecommerce.repository.CartRepository;
import com.newwave.bu3internecommerce.repository.LaptopRepository;
import com.newwave.bu3internecommerce.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;

    private final CartRepository cartRepository;

    private final LaptopRepository laptopRepository;

    /**
     * Get Orders by ordersId and update orderStatus
     *
     * @param newOrderStatus The new order status
     * @param ordersId       The ordersId of Orders
     * @return OrdersDTO contain information of Orders
     */
    public OrdersDTO updateOrderStatus(OrderStatus newOrderStatus, Long ordersId) {
        Orders orders = ordersRepository.findById(ordersId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDERS_NOT_EXISTED));
        orders.setOrderStatus(newOrderStatus);
        ordersRepository.save(orders);
        return  OrdersDTO.fromEntity(orders);
    }

    /**
     * Place order, items in order be got from Shopping Cart
     *
     * @param cartId The cartId of Shopping Cart
     * @return OrdersDTO containing information about Orders
     */
    @Transactional
    public OrdersDTO placeOrder(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_EXISTED));

        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems.isEmpty()) {
            throw new AppException(ErrorCode.CART_EMPTY);
        }

        Orders orders = new Orders();
        orders.setOrderDate(LocalDate.now());
        orders.setOrderStatus(OrderStatus.PENDING);
        orders.setCart(cart);

        // Convert cart items to order items
        List<OrdersItem> orderItems = convertCartItemsToOrderItems(cartItems);
        for (OrdersItem orderItem: orderItems) {
            orderItem.setOrders(orders);
            Laptop laptop = orderItem.getLaptop();

            // Change laptop quantity in inventory
            int stock = laptop.getStock() - orderItem.getQuantity();
            laptop.setStock(stock);
            laptopRepository.save(laptop);
        }
        orders.setOrderItems(orderItems);
        cart.getCartItems().clear();

        // Save everything be changed
        cartRepository.save(cart);
        ordersRepository.save(orders);
        return  OrdersDTO.fromEntity(orders);
    }

    /**
     * Convert cart items to order items
     *
     * @param cartItems List of CartItem from Shopping Cart
     * @return List<OrderItem> containing Order Items
     */
    @Transactional
    public List<OrdersItem> convertCartItemsToOrderItems(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(cartItem -> {
                    OrdersItem orderItem = new OrdersItem();
                    orderItem.setLaptop(cartItem.getLaptop());
                    orderItem.setQuantity(cartItem.getQuantity());
                    return orderItem;
                })
                .collect(Collectors.toList());
    }

}










