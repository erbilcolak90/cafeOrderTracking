package com.kofteciyusuf.app.businness.managers;

import com.kofteciyusuf.app.businness.services.OrderProductService;
import com.kofteciyusuf.app.entities.Order;
import com.kofteciyusuf.app.entities.OrderProduct;
import com.kofteciyusuf.app.entities.Product;
import com.kofteciyusuf.app.repositories.OrderProductRepository;
import com.kofteciyusuf.app.repositories.OrderRepository;
import com.kofteciyusuf.app.repositories.ProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<OrderProduct> getOrderProducts() {
        return this.orderProductRepository.findAll();
    }

    @Override
    public OrderProduct addOrderProduct(OrderProduct orderProduct) {

        Product product = this.productRepository.findById(orderProduct.getProductId()).orElseGet(Product::new);

        orderProduct.setCreateDate(new Date());
        orderProduct.setUpdateDate(new Date());
        orderProduct.setDeleted(false);
        orderProduct.setCurrentProductPrice(product.getPrice());
        this.orderProductRepository.save(orderProduct);

        Order order = this.orderRepository.findById(orderProduct.getOrderId()).orElseGet(Order::new);

        if(order.getOrderProductList()==null){
            List<OrderProduct> tempOrderProductList = new ArrayList<OrderProduct>();
            order.setOrderProductList(tempOrderProductList);
        }


        List<OrderProduct> orderProductList = order.getOrderProductList();

        orderProductList.add(orderProduct);

        order.setOrderProductList(orderProductList);
        order.setUpdateDate(new Date());


        this.orderRepository.save(order);

        return orderProduct;
    }

    @Override
    public OrderProduct deleteOrderProduct(String orderProductId) {
        OrderProduct orderProduct = this.orderProductRepository.findById(orderProductId).orElseGet(OrderProduct::new);

        orderProduct.setDeleted(true);
        orderProduct.setUpdateDate(new Date());

        this.orderProductRepository.save(orderProduct);

        return orderProduct;
    }
}
