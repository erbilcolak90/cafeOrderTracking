package com.kofteciyusuf.app.controllers;

import com.kofteciyusuf.app.businness.services.OrderService;
import com.kofteciyusuf.app.core.Result;
import com.kofteciyusuf.app.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    public Result createOrder(@Valid @RequestBody Order order) {
        return this.orderService.createOrder(order);
    }

    @PutMapping("/completeOrder")
    public Result<Order> completeOrder(@Valid @RequestParam String orderId) {
        return this.orderService.completeOrder(orderId);
    }

    @PutMapping("/changeToOrderDesk")
    public Result<Order> changeToOrderDesk(@Valid @RequestParam String orderId, @RequestParam String deskId) {
        return this.orderService.changeToOrderDesk(orderId, deskId);
    }

    @GetMapping("/getOrder")
    public Result<Order> getOrder(@RequestParam String orderId) {
        return this.orderService.getOrder(orderId);
    }

    @GetMapping("/getAllOrders")
    public Result<List<Order>> getAllOrders() {
        return this.orderService.getAllOrders();
    }

    @DeleteMapping("/deleteOrder")
    public Result deleteOrder(@Valid @RequestParam String orderId) {
        return this.orderService.deleteOrder(orderId);
    }


}
