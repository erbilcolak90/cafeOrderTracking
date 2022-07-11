package com.kofteciyusuf.app.controllers;

import com.kofteciyusuf.app.businness.services.OrderService;
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
    public Order createOrder(@Valid @RequestBody Order order){
        return this.orderService.createOrder(order);
    }

    @GetMapping("/getOrder")
    public Order getOrder(@RequestParam String orderId){
        return this.orderService.getOrder(orderId);
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders(){
        return this.orderService.getAllOrders();
    }

    @PutMapping("/complateToOrder")
    public Order complateToOrder(@Valid @RequestParam String orderId){
        return this.orderService.complateToOrder(orderId);
    }
    @PutMapping("/changeToOrderDesk")
    public Order changeToOrderDesk(@Valid @RequestParam String orderId,@RequestParam String deskId){
        return this.orderService.changeToOrderDesk(orderId,deskId);
    }
    @DeleteMapping("/deleteOrder")
    public Order deleteOrder(@Valid @RequestParam String orderId){
        return this.orderService.deleteOrder(orderId);
    }


}
