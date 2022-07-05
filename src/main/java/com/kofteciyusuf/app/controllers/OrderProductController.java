package com.kofteciyusuf.app.controllers;

import com.kofteciyusuf.app.businness.services.OrderProductService;
import com.kofteciyusuf.app.entities.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderProducts")
public class OrderProductController {

    private OrderProductService orderProductService;

    @Autowired
    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @GetMapping("/getOrderProducts")
    public List<OrderProduct> getOrderProducts(){
        return this.orderProductService.getOrderProducts();
    }

    @PostMapping("/addOrderProduct")
    public OrderProduct addOrderProduct(@RequestBody OrderProduct orderProduct){
        return this.orderProductService.addOrderProduct(orderProduct);
    }

    @DeleteMapping("/deleteOrderProduct")
    public OrderProduct deleteOrderProduct(@RequestParam String orderProductId){
        return this.deleteOrderProduct(orderProductId);
    }


}
