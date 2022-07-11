package com.kofteciyusuf.app.businness.services.managers;

import com.kofteciyusuf.app.enums.OrderEnums;
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

        try {
            order.setCreateDate(new Date());
            order.setUpdateDate(new Date());
            order.setDeleted(false);
            order.setStatus(OrderEnums.WAITING.toString());
            order.setComplated(false);

            Desk desk = this.deskRepository.findById(order.getDeskId()).orElseThrow();
            desk.setUpdateDate(new Date());
            desk.setActiveOrderId(order.getId());
            this.deskRepository.save(desk);
            this.orderRepository.save(order);

            return order;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public Order getOrder(String orderId) {
        try {
            Order order = this.orderRepository.findById(orderId).orElseThrow();
            return order;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Order> getAllOrders() {
        try {
            return this.orderRepository.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public Order complateToOrder(String orderId) {
        try {
            Order order = this.orderRepository.findById(orderId).orElseThrow();
            order.setComplated(true);
            order.setUpdateDate(new Date());
            order.setStatus(OrderEnums.READY.toString());
            //save order database
            this.orderRepository.save(order);
            return order;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public Order changeToOrderDesk(String orderId, String deskId) {
        try {
            Order order = this.orderRepository.findById(orderId).orElseThrow();
            Desk desk = this.deskRepository.findById(order.getDeskId()).orElseThrow();
            //masa değiştirirken kalkılan masanın müsaitlik durumu true olarak güncellenecek
            desk.setActiveOrderId(null);
            desk.setUpdateDate(new Date());
            //save desk database
            this.deskRepository.save(desk);

            order.setDeskId(deskId);
            order.setUpdateDate(new Date());

            Desk newDesk = this.deskRepository.findById(deskId).orElseThrow();
            newDesk.setActiveOrderId(order.getId());
            newDesk.setUpdateDate(new Date());
            //save order and new desk database
            this.orderRepository.save(order);
            this.deskRepository.save(newDesk);

            return order;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    public Order deleteOrder(String orderId) {
        try {
            Order order = this.orderRepository.findById(orderId).orElseThrow();
            order.setDeleted(true);
            order.setStatus(OrderEnums.CANCEL.toString());
            order.setUpdateDate(new Date());
            order.setComplated(false);
            //save order database
            this.orderRepository.save(order);
            return order;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
