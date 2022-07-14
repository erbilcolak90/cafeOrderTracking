package com.kofteciyusuf.app.controllers;

import com.kofteciyusuf.app.businness.services.OrderProductService;
import com.kofteciyusuf.app.core.Result;
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
    public Result addOrderProduct(@Valid @RequestBody OrderProduct orderProduct){
        return this.orderProductService.addOrderProduct(orderProduct);
    }
    @GetMapping("/getAllOrderProducts")
    public Result<List<OrderProduct>> getAllOrderProducts(){
        return this.orderProductService.getAllOrderProducts();
    }
    @DeleteMapping("/deleteOrderProduct")
    public Result deleteOrderProduct(@Valid @RequestParam String orderProductId){
        return this.orderProductService.deleteOrderProduct(orderProductId);
    }


}
