package com.kofteciyusuf.app.businness.managers;

import com.kofteciyusuf.app.businness.enums.OrderEnums;
import com.kofteciyusuf.app.businness.services.OrderService;
import com.kofteciyusuf.app.entities.Desk;
import com.kofteciyusuf.app.entities.Order;
import com.kofteciyusuf.app.repositories.DeskRepository;
import com.kofteciyusuf.app.repositories.OrderRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class OrderManager implements OrderService {

    private OrderRepository orderRepository;

    private DeskRepository deskRepository;

    @Autowired
    public OrderManager(OrderRepository orderRepository, DeskRepository deskRepository) {
        this.orderRepository = orderRepository;
        this.deskRepository = deskRepository;
    }

    @Override
    public Order createOrder(Order order) {
        order.setCreateDate(new Date());
        order.setUpdateDate(new Date());
        order.setDeleted(false);
        order.setStatus(OrderEnums.WAITING.toString());
        order.setComplated(false);

        this.orderRepository.save(order);

        Desk desk = this.deskRepository.findById(order.getDeskId()).orElseGet(Desk::new);

        desk.setUpdateDate(new Date());
        desk.setActiveOrderId(order.getId());

        this.deskRepository.save(desk);

        return order;
    }

    @Override
    public Order complateToOrder(String orderId) {
        Order order = this.orderRepository.findById(orderId).orElseGet(Order::new);
        order.setComplated(true);
        order.setUpdateDate(new Date());
        order.setStatus(OrderEnums.READY.toString());

        this.orderRepository.save(order);

        return order;
    }

    @Override
    public Order deleteOrder(String orderId) {
        Order order = this.orderRepository.findById(orderId).orElseGet(Order::new);
        order.setDeleted(true);
        order.setStatus(OrderEnums.CANCEL.toString());
        order.setUpdateDate(new Date());
        order.setComplated(false);

        this.orderRepository.save(order);

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order getOrder(String orderId) {
        Order order = this.orderRepository.findById(orderId).orElseGet(Order::new);
        return order;
    }

    @Override
    public Order changeToOrderDesk(String orderId,String deskId) {
        Order order = this.orderRepository.findById(orderId).orElseGet(Order::new);
        Desk desk = this.deskRepository.findById(order.getDeskId()).orElseGet(Desk::new);

        //masa değiştirirken kalkılan masanın müsaitlik durumu true olarak güncellenecek
        desk.setActiveOrderId(null);
        desk.setUpdateDate(new Date());

        this.deskRepository.save(desk);

        order.setDeskId(deskId);
        order.setUpdateDate(new Date());

        Desk newDesk = this.deskRepository.findById(deskId).orElseGet(Desk::new);

        newDesk.setActiveOrderId(order.getId());
        newDesk.setUpdateDate(new Date());

        this.orderRepository.save(order);
        this.deskRepository.save(newDesk);

        return order;
    }
}
