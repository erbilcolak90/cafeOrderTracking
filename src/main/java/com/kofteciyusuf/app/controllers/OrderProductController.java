package com.kofteciyusuf.app.controllers;

import com.kofteciyusuf.app.businness.services.OrderProductService;
import com.kofteciyusuf.app.entities.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orderProducts")
public class OrderProductController {

    private OrderProductService orderProductService;

    @Autowired
    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @PostMapping("/addOrderProduct")
    public OrderProduct addOrderProduct(@Valid @RequestBody OrderProduct orderProduct){
        return this.orderProductService.addOrderProduct(orderProduct);
    }
    @GetMapping("/getAllOrderProducts")
    public List<OrderProduct> getAllOrderProducts(){
        return this.orderProductService.getAllOrderProducts();
    }
    @DeleteMapping("/deleteOrderProduct")
    public OrderProduct deleteOrderProduct(@Valid @RequestParam String orderProductId){
        return this.deleteOrderProduct(orderProductId);
    }


}
