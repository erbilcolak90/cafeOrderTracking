package com.kofteciyusuf.app.businness.services;

import com.kofteciyusuf.app.core.Result;
import com.kofteciyusuf.app.entities.Order;

import java.util.List;

public interface OrderService {

    Result createOrder(Order order);

    Result<Order> completeOrder(String orderId);

    Result deleteOrder(String orderId);

    Result<List<Order>> getAllOrders();

    Result<Order> getOrder(String orderId);

    Result<Order> changeToOrderDesk(String orderId, String deskId);
}
