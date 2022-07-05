package com.kofteciyusuf.app.businness.managers;

import com.kofteciyusuf.app.businness.services.BillService;
import com.kofteciyusuf.app.entities.Bill;
import com.kofteciyusuf.app.entities.Order;
import com.kofteciyusuf.app.entities.OrderProduct;
import com.kofteciyusuf.app.repositories.BillRepository;
import com.kofteciyusuf.app.repositories.OrderRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class BillManager implements BillService {

    private BillRepository billRepository;

    private OrderRepository orderRepository;

    public BillManager(BillRepository billRepository, OrderRepository orderRepository) {
        this.billRepository = billRepository;
        this.orderRepository = orderRepository;
    }


    /*
    @Override
    public Bill checkPlease(String orderId) {

        Order order = this.orderRepository.findById(orderId).orElseGet(Order::new);

        List<Order> orderList = new ArrayList<>();

        orderList.add(order);


               return ;
    }
     */
}
