package com.kofteciyusuf.app.businness.services;

import com.kofteciyusuf.app.entities.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    Order completeOrder(String orderId);

    Order deleteOrder(String orderId);

    List<Order> getAllOrders();

    Order getOrder(String orderId);

    Order changeToOrderDesk(String orderId,String deskId);
}
