package com.kofteciyusuf.app.businness.services;

import com.kofteciyusuf.app.core.DataResult;
import com.kofteciyusuf.app.core.Result;
import com.kofteciyusuf.app.entities.Order;

import java.util.List;

public interface OrderService {

    Result createOrder(Order order);

    DataResult<Order> completeOrder(String orderId);

    Result deleteOrder(String orderId);

    DataResult<List<Order>> getAllOrders();

    DataResult<Order> getOrder(String orderId);

    DataResult<Order> changeToOrderDesk(String orderId,String deskId);
}
