package com.newwave.bu3internecommerce.service;

import com.newwave.bu3internecommerce.dto.response.OrdersDTO;
import com.newwave.bu3internecommerce.exception.AppException;
import com.newwave.bu3internecommerce.exception.ErrorCode;
import com.newwave.bu3internecommerce.model.order.OrderItem;
import com.newwave.bu3internecommerce.model.order.OrderStatus;
import com.newwave.bu3internecommerce.model.order.Orders;
import com.newwave.bu3internecommerce.model.shoppingcart.Cart;
import com.newwave.bu3internecommerce.model.shoppingcart.CartItem;
import com.newwave.bu3internecommerce.repository.CartRepository;
import com.newwave.bu3internecommerce.repository.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    private final CartRepository cartRepository;

    public OrdersService(OrdersRepository ordersRepository,
                         CartRepository cartRepository) {
        this.ordersRepository = ordersRepository;
        this.cartRepository = cartRepository;
    }

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
        return new OrdersDTO(orders);
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
        List<OrderItem> orderItems = convertCartItemsToOrderItems(cartItems);
        for (OrderItem orderItem: orderItems) {
            orderItem.setOrders(orders);
        }
        orders.setOrderItems(orderItems);
        cart.getCartItems().clear();

        // Save everything be changed
        cartRepository.save(cart);
        ordersRepository.save(orders);
        return new OrdersDTO(orders);
    }

    /**
     * Convert cart items to order items
     *
     * @param cartItems List of CartItem from Shopping Cart
     * @return List<OrderItem> containing Order Items
     */
    @Transactional
    public List<OrderItem> convertCartItemsToOrderItems(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setLaptop(cartItem.getLaptop());
                    orderItem.setQuantity(cartItem.getQuantity());
                    return orderItem;
                })
                .collect(Collectors.toList());
    }

}










