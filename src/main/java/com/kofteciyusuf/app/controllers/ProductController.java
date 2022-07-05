package com.kofteciyusuf.app.controllers;

import com.kofteciyusuf.app.businness.services.ProductService;
import com.kofteciyusuf.app.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return this.productService.addProduct(product);
    }

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return this.productService.getProducts();
    }

    @PostMapping("/changeProductPrice")
    public Product changeProductPrice(@RequestParam String id,@RequestParam int price){
        return this.productService.changeProductPrice(id,price);
    }

    @DeleteMapping("/deleteProduct")
    public Product deleteProduct(@RequestParam String id){
        return this.productService.deleteProduct(id);
    }

}
