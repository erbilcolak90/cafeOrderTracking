package com.kofteciyusuf.app.controllers;

import com.kofteciyusuf.app.businness.services.ProductService;
import com.kofteciyusuf.app.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

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

    @GetMapping("/getProductPage")
    public Page<Product> getProductList(@RequestParam int number,@RequestParam int size){
        return  this.productService.getProductList(number, size);
    }


}
