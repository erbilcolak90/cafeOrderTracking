package com.cafeordertracking.app.businness.services.managers;

import com.cafeordertracking.app.businness.services.OrderProductService;
import com.cafeordertracking.app.core.Result;
import com.cafeordertracking.app.entities.Order;
import com.cafeordertracking.app.entities.OrderProduct;
import com.cafeordertracking.app.entities.Product;
import com.cafeordertracking.app.repositories.OrderProductRepository;
import com.cafeordertracking.app.repositories.OrderRepository;
import com.cafeordertracking.app.repositories.ProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class OrderProductManager implements OrderProductService {

    private OrderProductRepository orderProductRepository;

    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    @Autowired
    public OrderProductManager(OrderProductRepository orderProductRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderProductRepository = orderProductRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Result addOrderProduct(OrderProduct orderProduct) {
        try {
            Product product = this.productRepository.findById(orderProduct.getProductId()).orElseThrow();
            Order order = this.orderRepository.findById(orderProduct.getOrderId()).orElseThrow();
            orderProduct.setCreateDate(new Date());
            orderProduct.setUpdateDate(new Date());
            orderProduct.setDeleted(false);
            orderProduct.setCurrentProductPrice(product.getPrice());

            //save orderproduct database
            this.orderProductRepository.save(orderProduct);

            List<OrderProduct> orderProductList = order.getOrderProductList();
            orderProductList.add(orderProduct);
            order.setOrderProductList(orderProductList);
            order.setUpdateDate(new Date());
            //save order database
            this.orderRepository.save(order);

            return new Result(true,"OrderProduct added",order);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Result<List<OrderProduct>> getAllOrderProducts() {
        try {
            return new Result<List<OrderProduct>>(true,"OrderProduct listed",this.orderProductRepository.findAll());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public Result deleteOrderProduct(String orderProductId) {
        try {
            OrderProduct orderProduct = this.orderProductRepository.findById(orderProductId).orElseThrow();
            orderProduct.setDeleted(true);
            orderProduct.setUpdateDate(new Date());
            //save orderProduct database
            this.orderProductRepository.save(orderProduct);
            return new Result(true,"OrderProduct deleted",null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
