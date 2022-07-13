package com.kofteciyusuf.app.businness.services;

import com.kofteciyusuf.app.core.DataResult;
import com.kofteciyusuf.app.core.Result;
import com.kofteciyusuf.app.entities.OrderProduct;

import java.util.List;

public interface OrderProductService {

    DataResult<List<OrderProduct>> getAllOrderProducts();

    Result addOrderProduct(OrderProduct orderProduct);

    Result deleteOrderProduct(String orderProductId);
}
