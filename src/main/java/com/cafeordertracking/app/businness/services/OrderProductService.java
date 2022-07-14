package com.cafeordertracking.app.businness.services;

import com.cafeordertracking.app.core.Result;
import com.cafeordertracking.app.entities.OrderProduct;

import java.util.List;

public interface OrderProductService {

    Result<List<OrderProduct>> getAllOrderProducts();

    Result addOrderProduct(OrderProduct orderProduct);

    Result deleteOrderProduct(String orderProductId);
}
