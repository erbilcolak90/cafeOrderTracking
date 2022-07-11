package com.kofteciyusuf.app.businness.services;

import com.kofteciyusuf.app.entities.OrderProduct;

import java.util.List;

public interface OrderProductService {

    List<OrderProduct> getAllOrderProducts();

    OrderProduct addOrderProduct(OrderProduct orderProduct);

    OrderProduct deleteOrderProduct(String orderProductId);
}
