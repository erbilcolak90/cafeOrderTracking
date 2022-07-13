package com.kofteciyusuf.app.businness.services.managers;

import com.kofteciyusuf.app.core.DataResult;
import com.kofteciyusuf.app.core.Result;
import com.kofteciyusuf.app.core.SuccessDataResult;
import com.kofteciyusuf.app.core.SuccessResult;
import com.kofteciyusuf.app.entities.OrderProduct;
import com.kofteciyusuf.app.enums.OrderEnums;
import com.kofteciyusuf.app.businness.services.OrderService;
import com.kofteciyusuf.app.entities.Desk;
import com.kofteciyusuf.app.entities.Order;
import com.kofteciyusuf.app.repositories.DeskRepository;
import com.kofteciyusuf.app.repositories.OrderProductRepository;
import com.kofteciyusuf.app.repositories.OrderRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Service
public class OrderManager implements OrderService {

    private OrderRepository orderRepository;

    private DeskRepository deskRepository;

    private OrderProductRepository orderProductRepository;

    @Autowired
    public OrderManager(OrderRepository orderRepository, DeskRepository deskRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.deskRepository = deskRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public Result createOrder(Order order) {

        try {
            order.setCreateDate(new Date());
            order.setUpdateDate(new Date());
            order.setDeleted(false);
            order.setStatus(OrderEnums.WAITING.toString());
            order.setComplated(false);
            order.setOrderProductList(new ArrayList<OrderProduct>());
            Desk desk = this.deskRepository.findById(order.getDeskId()).orElseThrow();
            desk.setUpdateDate(new Date());
            desk.setActiveOrderId(order.getId());
            this.deskRepository.save(desk);
            this.orderRepository.save(order);

            return new SuccessResult("Order created");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Order not created",ex);
        }
    }

    @Override
    public DataResult<Order> getOrder(String orderId) {
        try {
            Order order = this.orderRepository.findById(orderId).orElseThrow();
            return new SuccessDataResult<Order>("Order listed",order);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found",ex);
        }
    }

    @Override
    public DataResult<List<Order>> getAllOrders() {
        try {
            return new SuccessDataResult<List<Order>>("Orders listed",this.orderRepository.findAll());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Orders not listed",ex);
        }
    }

    @Override
    public DataResult<Order> completeOrder(String orderId) {
        try {
            Order order = this.orderRepository.findById(orderId).orElseThrow();
            order.setComplated(true);
            order.setUpdateDate(new Date());
            order.setStatus(OrderEnums.COMPLETE.toString());
            order.setTotal(order.calculateTotal());
            //save order database
            this.orderRepository.save(order);
            return new SuccessDataResult<Order>("Order completed",order);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found",ex);
        }
    }

    @Override
    public DataResult<Order> changeToOrderDesk(String orderId, String deskId) {
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

            return new SuccessDataResult<Order>("Desk changed");

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Please check orderId or deskId",ex);
        }
    }

    @Override
    public Result deleteOrder(String orderId) {
        try {
            Order order = this.orderRepository.findById(orderId).orElseThrow();
            Desk desk = this.deskRepository.findById(order.getDeskId()).orElseThrow();
            order.setDeleted(true);
            order.setStatus(OrderEnums.CANCEL.toString());
            order.setUpdateDate(new Date());
            order.setComplated(false);
            order.setDeskId("");
            desk.setActiveOrderId("");
            //save order database
            this.orderRepository.save(order);
            this.deskRepository.save(desk);
            return new SuccessResult("Order deleted");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,"Order not deleted",ex);
        }
    }
}
